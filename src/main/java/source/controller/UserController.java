package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.annotation.LogsActivityAnnotation;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.user.*;
import source.dto.response.BaseResponse;
import source.entity.User;
import source.service.refresh_token_service.RefreshTokenService;
import source.service.user_service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) throws Exception {
        return userService.signUp(userSignUpRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@RequestBody UserSignInRequestDto userSignInRequestDto) throws Exception {
        return userService.signIn(userSignInRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_REFRESH_TOKEN)
    public BaseResponse refreshToken(@RequestBody TokenRefreshRequestDto request) {
        return refreshTokenService.refreshToken(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_ALL)
    public BaseResponse getAllUser(@RequestBody UserGetAllRequestDto request) throws Exception {
        return userService.getAllUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_UPDATE)
    public BaseResponse updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest request) throws Exception {
        userUpdateRequestDto.setId(((User) request.getAttribute(RequestKeyConstant.USER_AUTH)).getId());
        return userService.updateUser(userUpdateRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_DELETE)
    public BaseResponse deleteUser(@RequestBody UserDeleteRequestDto userDeleteRequestDto, HttpServletRequest request) throws Exception {
        userDeleteRequestDto.setId(((User) request.getAttribute(RequestKeyConstant.USER_AUTH)).getId());
        return userService.deleteUser(userDeleteRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_INFO)
    public BaseResponse getUserInformation(@RequestBody UserGetInfoRequestDto userGetInfoRequestDto, HttpServletRequest request) throws Exception {
        userGetInfoRequestDto.setId(((User) request.getAttribute(RequestKeyConstant.USER_AUTH)).getId());
        return userService.getUserInfo(userGetInfoRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_BY_ID)
    public BaseResponse getUserById(@RequestBody UserGetInfoRequestDto userGetInfoRequestDto, HttpServletRequest request) throws Exception {
        return userService.getUserInfo(userGetInfoRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.ADMIN_DELETE_USER)
    public BaseResponse deleteUserById(@RequestBody UserDeleteRequestDto userDeleteRequestDto) throws Exception {
      return userService.deleteUser(userDeleteRequestDto);
    }
}