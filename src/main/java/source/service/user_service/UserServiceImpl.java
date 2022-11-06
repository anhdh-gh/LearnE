package source.service.user_service;

import com.google.common.base.CaseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.constant.ErrorFirebaseConstant;
import source.constant.JwtTokenTypeConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.dto.response.FieldViolation;
import source.dto.response.TokenResponseDto;
import source.entity.RefreshToken;
import source.entity.User;
import source.entity.enumeration.Role;
import source.exception.BusinessErrors;
import source.exception.firebase.auth.FirebaseAuthException;
import source.repository.UserRepository;
import source.service.refresh_token_service.RefreshTokenService;
import source.third_party.firebase_user_authentication.bean.FirebaseSignInSignUpResponseBean;
import source.third_party.firebase_user_authentication.exception.HttpBadRequestException;
import source.third_party.firebase_user_authentication.service.UserAuthenticationServiceImpl;
import source.third_party.user.dto.request.UserSignUpThirdPartyRequestDto;
import source.third_party.user.service.UserServiceThirdParty;
import source.util.JsonUtil;
import source.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationServiceImpl;

    @Autowired
    private Environment environment;

    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception {
        try {
            // Sign up in firebase
            FirebaseSignInSignUpResponseBean firebaseSignInSignUpResponseBean =
                userAuthenticationServiceImpl.signUpWithEmailAndPassword(userSignUpRequestDto.getEmail(), userSignUpRequestDto.getPassword());

            // Sign up to service user
            UserSignUpThirdPartyRequestDto userSignUpThirdPartyRequestDto = modelMapper.map(userSignUpRequestDto, UserSignUpThirdPartyRequestDto.class);
            userSignUpThirdPartyRequestDto.setId(firebaseSignInSignUpResponseBean.getLocalId());
            BaseResponse response = userServiceThirdParty.createUser(userSignUpThirdPartyRequestDto);
            if(!Objects.equals(response.getMeta().getCode(), BaseResponse.OK_CODE)) {
                userAuthenticationServiceImpl.deleteUserAccount(firebaseSignInSignUpResponseBean.getIdToken());
            } else {
                User user = JsonUtil.getGenericObject(response.getData(), User.class);
                user = userRepository.set(user);
            }

            return response;
        } catch (HttpBadRequestException e) {
            FirebaseAuthException firebaseAuthError = JsonUtil.convertJsonStrToObject(e.getMessage(), FirebaseAuthException.class);
            String type = firebaseAuthError.getError().getMessage().split(" : ")[0];
            String message = "System error";
            List<FieldViolation> errors = new ArrayList<>();
            switch (type) {
                case ErrorFirebaseConstant.EMAIL_EXISTS:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Email"), ErrorCodeConstant.EMAIL_ALREADY_EXISTS_400009, environment.getProperty(ErrorCodeConstant.EMAIL_ALREADY_EXISTS_400009)));
                    break;
                case ErrorFirebaseConstant.WEAK_PASSWORD:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Password"), ErrorCodeConstant.PASSWORD_IS_NOT_STRONG_400006, environment.getProperty(ErrorCodeConstant.PASSWORD_IS_NOT_STRONG_400006)));
                    break;
                default:
                    return BaseResponse.ofFailed(BusinessErrors.INVALID_PARAMETERS);
            }

            return BaseResponse.ofFailed(userSignUpRequestDto.getRequestId(), BusinessErrors.INVALID_PARAMETERS, "Invalid parameters of object: " + userSignUpRequestDto.getClass(), errors);
        } catch (Exception e) {
            return BaseResponse.ofFailed(userSignUpRequestDto.getRequestId(), BusinessErrors.INTERNAL_SERVER_ERROR, BusinessErrors.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse signIn(UserSignInRequestDto userSignInRequestDto) throws Exception {
        try {
            // Sign in firebase
            FirebaseSignInSignUpResponseBean firebaseSignInSignUpResponseBean =
                userAuthenticationServiceImpl.signInWithEmailAndPassword(userSignInRequestDto.getEmail(), userSignInRequestDto.getPassword());

            // Get user id
            String idUser = firebaseSignInSignUpResponseBean.getLocalId();

            // Get user
            User user = userRepository.get(idUser);

            // Create token
            String accessToken = jwtUtil.generateJwtToken(user);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

            return BaseResponse.ofSucceeded(userSignInRequestDto.getRequestId(),
                TokenResponseDto.builder().refreshToken(refreshToken.getToken()).accessToken(accessToken).tokenType(JwtTokenTypeConstant.BEARER).user(user).build());
        } catch (HttpBadRequestException e) {
            FirebaseAuthException firebaseAuthError = JsonUtil.convertJsonStrToObject(e.getMessage(), FirebaseAuthException.class);
            String type = firebaseAuthError.getError().getMessage().split(" : ")[0];
            List<FieldViolation> errors = new ArrayList<>();
            switch (type) {
                case ErrorFirebaseConstant.MISSING_PASSWORD:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Password"), ErrorCodeConstant.PASSWORD_IS_NOT_EMPTY_400012, environment.getProperty(ErrorCodeConstant.PASSWORD_IS_NOT_EMPTY_400012)));
                    break;
                case ErrorFirebaseConstant.MISSING_EMAIL:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Email"), ErrorCodeConstant.EMAIL_IS_NOT_VALID_400002, environment.getProperty(ErrorCodeConstant.EMAIL_IS_NOT_VALID_400002)));
                    break;
                case ErrorFirebaseConstant.INVALID_PASSWORD:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Password"), ErrorCodeConstant.PASSWORD_IS_NOT_VALID_400013, environment.getProperty(ErrorCodeConstant.PASSWORD_IS_NOT_VALID_400013)));
                    break;
                case ErrorFirebaseConstant.EMAIL_NOT_FOUND:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Email"), ErrorCodeConstant.EMAIL_NOT_FOUND_400014, environment.getProperty(ErrorCodeConstant.EMAIL_NOT_FOUND_400014)));
                    break;
                case ErrorFirebaseConstant.TOO_MANY_ATTEMPTS_TRY_LATER:
                    return BaseResponse.ofFailed(BusinessErrors.BAD_REQUEST, "Access to this account has been temporarily disabled due to multiple failed login attempts. Please try again later");
                default:
                    return BaseResponse.ofFailed(BusinessErrors.INVALID_PARAMETERS, "System error");
            }
            return BaseResponse.ofFailed(userSignInRequestDto.getRequestId(), BusinessErrors.INVALID_PARAMETERS, "Invalid parameters of object: " + userSignInRequestDto.getClass(), errors);
        }
    }

    @Override
    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception {
        return userServiceThirdParty.getAllUser(request);
    }

    @Override
    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception {
        // Kiểm tra xem có phải là admin không, nếu không thì set một số trường là null
        if(!request.getUserAuthRole().equals(Role.ADMIN.getValue())) {
            request.setId(request.getUserAuthId());
            request.setRole(null);
        }

        BaseResponse response = userServiceThirdParty.updateUser(request);
        if(!Objects.equals(response.getMeta().getCode(), BaseResponse.OK_CODE)) {
            return response;
        }

        // Update in firebase
        User user = JsonUtil.getGenericObject(response.getData(), User.class);
        user = userRepository.set(user);

        // Trả về kết quả
        return response;
    }

    @Override
    public BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception {
        return userServiceThirdParty.deleteUser(userDeleteRequestDto);
    }

    @Override
    public BaseResponse getUserInfo(UserGetInfoRequestDto userGetInfoRequestDto) throws Exception {
        return userServiceThirdParty.getUserInfo(userGetInfoRequestDto);
    }
}
