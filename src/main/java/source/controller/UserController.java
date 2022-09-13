package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.constant.RouterConstant;
import source.dto.request.TokenRefreshRequestDto;
import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.service.refresh_token_service.RefreshTokenService;
import source.service.user_service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) throws Exception {
        return userService.signUp(userSignUpRequestDto);
    }

    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@RequestBody UserSignInRequestDto userSignInRequestDto) throws Exception {
        return userService.signIn(userSignInRequestDto);
    }

    @PostMapping(RouterConstant.USER_REFRESH_TOKEN)
    public BaseResponse refreshToken(@RequestBody TokenRefreshRequestDto request) {
        return refreshTokenService.refreshToken(request);
    }
}