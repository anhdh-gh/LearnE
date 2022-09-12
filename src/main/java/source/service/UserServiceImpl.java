package source.service;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.constant.Role;
import source.dto.request.UserComparePasswordRequestDto;
import source.dto.request.UserCreateRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.FieldViolation;
import source.dto.response.UserComparePasswordResponseDto;
import source.entity.Account;
import source.entity.User;
import source.exception.BusinessError;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.AccountRepository;
import source.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Environment environment;

    @Override
    public BaseResponse createUser(UserCreateRequestDto request) throws Exception {
        // Check username already exists
        User userByUserName = userRepository.findByUserName(request.getUserName());
        if(userByUserName != null) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, "Invalid parameters of object: " + request.getClass(),
                Collections.singletonList(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "UserName"), ErrorCodeConstant.USERNAME_ALREADY_EXISTS_400010, environment.getProperty(ErrorCodeConstant.USERNAME_ALREADY_EXISTS_400010))));
        }

        // Check email already exists
        Account accountByEmail = accountRepository.findByEmail(request.getEmail());
        if(accountByEmail != null) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, "Invalid parameters of object: " + request.getClass(),
                Collections.singletonList(new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Email"), ErrorCodeConstant.EMAIL_ALREADY_EXISTS_400009, environment.getProperty(ErrorCodeConstant.EMAIL_ALREADY_EXISTS_400009))));
        }

        // Save user
        User userSave = userRepository.save(User.builder()
            .userName(request.getUserName())
            .role(Role.USER)
            .account(Account.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build()
            )
            .build()
        );

        return BaseResponse.ofSucceeded(request.getRequestId(), userSave);
    }

    @Override
    public BaseResponse comparePassword(UserComparePasswordRequestDto request) {
        boolean isCorrect = false;
        Optional<User> userOptional = userRepository.findById(request.getIdUser());
        if(userOptional.isPresent()){
            String encodedPassword = userOptional.get().getAccount().getPassword();
            if(bCryptPasswordEncoder.matches(request.getPassword(),encodedPassword)){
                isCorrect = true;
            }
        }else {
            int errorCode = Integer.parseInt(ErrorCodeConstant.USERNAME_IS_NOT_EXISTS_400011);
            return BaseResponse.ofFailed(request.getRequestId(),
                    new BusinessError(
                    errorCode,
                    environment.getProperty(String.valueOf(errorCode)),
                    HttpStatus.BAD_REQUEST
            ));
        }
        return BaseResponse.ofSucceeded(request.getRequestId(), new UserComparePasswordResponseDto(isCorrect));
    }
}
