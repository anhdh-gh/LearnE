package source.service.user_service;

import com.github.alperkurtul.firebaseuserauthentication.bean.FirebaseSignInSignUpResponseBean;
import com.github.alperkurtul.firebaseuserauthentication.exception.HttpBadRequestException;
import com.google.common.base.CaseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import source.constant.ErrorFirebaseConstant;
import source.dto.request.UserSignupDto;
import source.dto.response.BaseResponse;
import source.dto.response.FieldViolation;
import source.exception.BusinessError;
import source.exception.BusinessErrors;
import source.exception.firebase.auth.FirebaseAuthException;
import source.util.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAuthenticationServiceImplCustom userAuthenticationServiceImplCustom;

    @Autowired
    private Environment environment;

    @Override
    public BaseResponse signUp(UserSignupDto userSignupDto) throws Exception {
        try {
            FirebaseSignInSignUpResponseBean firebaseSignInSignUpResponseBean =
                userAuthenticationServiceImplCustom.signUpWithEmailAndPassword(userSignupDto.getEmail(), userSignupDto.getPassword());
            return BaseResponse.ofSucceeded(firebaseSignInSignUpResponseBean);
        } catch (HttpBadRequestException e) {
            FirebaseAuthException firebaseAuthError = JsonUtil.convertJsonStrToObject(e.getMessage(), FirebaseAuthException.class);
            String type = firebaseAuthError.getError().getMessage().split(" : ")[0];
            String message = "System error";
            List<FieldViolation> errors = new ArrayList<>();
            switch (type) {
                case ErrorFirebaseConstant.EMAIL_EXISTS:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Email"), "400009", environment.getProperty("400009")));
                    break;
                case ErrorFirebaseConstant.WEAK_PASSWORD:
                    errors.add(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Password"), "400006", environment.getProperty("400006")));
                    break;
                default:
                    return BaseResponse.ofFailed(BusinessErrors.INVALID_PARAMETERS);
            }

            return BaseResponse.ofFailed(BusinessErrors.INVALID_PARAMETERS, "Invalid parameters of object: " + userSignupDto.getClass(), errors);
        }
    }
}
