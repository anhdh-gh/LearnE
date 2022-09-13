package source.service.user_service;

import com.google.common.base.CaseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.constant.ErrorFirebaseConstant;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.FieldViolation;
import source.exception.BusinessErrors;
import source.exception.firebase.auth.FirebaseAuthException;
import source.third_party.firebase_user_authentication.bean.FirebaseSignInSignUpResponseBean;
import source.third_party.firebase_user_authentication.exception.HttpBadRequestException;
import source.third_party.firebase_user_authentication.service.UserAuthenticationServiceImpl;
import source.third_party.user_service.dto.request.UserSignUpThirdPartyRequestDto;
import source.third_party.user_service.service.UserServiceThirdParty;
import source.util.JsonUtil;

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

    @Override
    public BaseResponse signUp(UserSignUpRequestDto userRequestSignupDto) throws Exception {
        try {
            // Sign up in firebase
            FirebaseSignInSignUpResponseBean firebaseSignInSignUpResponseBean =
                userAuthenticationServiceImpl.signUpWithEmailAndPassword(userRequestSignupDto.getEmail(), userRequestSignupDto.getPassword());

            // Sign up to service user
            UserSignUpThirdPartyRequestDto userSignupThirdPartyRequestDto = modelMapper.map(userRequestSignupDto, UserSignupThirdPartyRequestDto.class);
            userSignupThirdPartyRequestDto.setId(firebaseSignInSignUpResponseBean.getLocalId());
            BaseResponse response = userServiceThirdParty.createUser(userSignupThirdPartyRequestDto);
            if(!Objects.equals(response.getMeta().getCode(), BaseResponse.OK_CODE)) {
                userAuthenticationServiceImpl.deleteUserAccount(firebaseSignInSignUpResponseBean.getIdToken());
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
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Password"), ErrorCodeConstant.PASSWORD_IS_NOT_STRONG_400006, ErrorCodeConstant.PASSWORD_IS_NOT_STRONG_400006));
                    break;
                default:
                    return BaseResponse.ofFailed(BusinessErrors.INVALID_PARAMETERS);
            }

            return BaseResponse.ofFailed(userRequestSignupDto.getRequestId(), BusinessErrors.INVALID_PARAMETERS, "Invalid parameters of object: " + userRequestSignupDto.getClass(), errors);
        }
    }
}
