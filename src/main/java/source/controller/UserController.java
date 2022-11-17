package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.service.refresh_token_service.RefreshTokenService;
import source.service.user_service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@RequestBody UserSignUpRequestDto request) throws Exception {
        return userService.signUp(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@RequestBody UserSignInRequestDto request) throws Exception {
        return userService.signIn(request);
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
    public BaseResponse updateUser(@RequestBody UserUpdateRequestDto request) throws Exception {
        return userService.updateUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_DELETE)
    public BaseResponse deleteUser(@RequestBody UserDeleteRequestDto request) throws Exception {
        request.setId(request.getUserAuthId());
        return userService.deleteUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_INFO)
    public BaseResponse getUserInformation(@RequestBody UserGetInfoRequestDto request) throws Exception {
        request.setId(request.getUserAuthId());
        return userService.getUserInfo(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_BY_ID)
    public BaseResponse getUserById(@RequestBody UserGetInfoRequestDto request) throws Exception {
        return userService.getUserInfo(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.ADMIN_DELETE_USER)
    public BaseResponse deleteUserById(@RequestBody UserDeleteRequestDto request) throws Exception {
        return userService.deleteUser(request);
    }
}