package source.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import source.constant.ErrorCodeConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.dto.response.FieldViolation;
import source.dto.response.UserComparePasswordResponseDto;
import source.entity.Account;
import source.entity.User;
import source.entity.enumeration.Role;
import source.exception.BusinessError;
import source.exception.BusinessErrors;
import source.repository.AccountRepository;
import source.repository.UserRepository;

import java.util.Collections;
import java.util.Objects;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
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
        User userSave = User.builder()
            .id(request.getId())
            .userName(request.getUserName())
            .role(Role.USER)
            .account(Account.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build()
            )
            .build();

        userSave = userRepository.save(userSave);
        userSave = objectMapper.readValue(objectMapper.writeValueAsString(userSave), User.class);

        return BaseResponse.ofSucceeded(request.getRequestId(), maskPassword(userSave));
    }

    @Override
    public BaseResponse comparePassword(UserComparePasswordRequestDto request) {
        boolean isCorrect = false;
        Optional<User> userOptional = userRepository.findById(request.getIdUser());
        if(userOptional.isPresent()) {
            String encodedPassword = userOptional.get().getAccount().getPassword();
            if(bCryptPasswordEncoder.matches(request.getPassword(),encodedPassword)) {
                isCorrect = true;
            }
        } else {
            int errorCode = Integer.parseInt(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011);
            return BaseResponse.ofFailed(request.getRequestId(),
                new BusinessError(
                errorCode,
                environment.getProperty(String.valueOf(errorCode)),
                HttpStatus.BAD_REQUEST
            ));
        }
        return BaseResponse.ofSucceeded(request.getRequestId(), new UserComparePasswordResponseDto(isCorrect));
    }

    private User checkUserIsExist(String id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public BaseResponse getUserById(UserGetByIdRequestDto userGetByIdRequestDto) throws Exception {
        User userResponse = checkUserIsExist(userGetByIdRequestDto.getId());
        if(userResponse == null){
            int errorCode = Integer.parseInt(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011);
            return BaseResponse.ofFailed(userGetByIdRequestDto.getRequestId(),
                new BusinessError(
                    errorCode,
                    environment.getProperty(String.valueOf(errorCode)),
                    HttpStatus.BAD_REQUEST
            ));
        }

        return BaseResponse.ofSucceeded(userGetByIdRequestDto.getRequestId(), userResponse);
    }

    @Override
    public BaseResponse getAllUser(UserGetAllRequestDto dataRequest) throws Exception {
        PageRequest pageRequest = PageRequest.of(dataRequest.getPage(), dataRequest.getSize());

        Page<User> allUsers = userRepository.findAll(pageRequest);

        return BaseResponse.ofSucceeded(dataRequest.getRequestId(), allUsers);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception {
        User user = checkUserIsExist(request.getId());
        if(user == null){
            int errorCode = Integer.parseInt(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011);
            return BaseResponse.ofFailed(request.getRequestId(),
                new BusinessError(
                    errorCode,
                    environment.getProperty(String.valueOf(errorCode)),
                    HttpStatus.BAD_REQUEST));
        } else {
            user.setGender(Objects.nonNull(request.getGender()) ? request.getGender() : user.getGender());
            user.setAddress(Objects.nonNull(request.getAddress()) ? request.getAddress() : user.getAddress());
            user.setAvatar(Objects.nonNull(request.getAvatar()) ? request.getAvatar() : user.getAvatar());
            user.setDateOfBirth(Objects.nonNull(request.getDateOfBirth()) ? request.getDateOfBirth() : user.getDateOfBirth());
            user.setPhoneNumber(Objects.nonNull(request.getPhoneNumber()) ? request.getPhoneNumber() : user.getPhoneNumber());
            user.setFullName(Objects.nonNull(request.getFullName()) ? request.getFullName() : user.getFullName());
        }
        return BaseResponse.ofSucceeded(request.getRequestId(), maskPassword(objectMapper.readValue(objectMapper.writeValueAsString(userRepository.save(user)), User.class)));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception {
        User user = checkUserIsExist(request.getId());
        if(user == null){
            int errorCode = Integer.parseInt(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011);
            return BaseResponse.ofFailed(request.getRequestId(),
                new BusinessError(
                    errorCode,
                    environment.getProperty(String.valueOf(errorCode)),
                    HttpStatus.BAD_REQUEST));
        } else{
            userRepository.delete(user);
            return BaseResponse.ofSucceeded(request.getRequestId(), user);
        }
    }

    private User maskPassword(User user){
        if(Objects.nonNull(user))
            user.getAccount().setPassword(null);
        return user;
    }
}