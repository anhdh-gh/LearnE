package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.service.auth.AuthService;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@Valid @RequestBody UserSignUpRequestDto request) throws Exception{
        return authService.signUp(request);
    }

    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@Valid @RequestBody UserSignInRequestDto request) throws Exception{
        return authService.signIn(request);
    }

    @PostMapping(RouterConstant.GET_ALL_USER)
    public BaseResponse getAllUsers(@Valid @RequestBody UserGetAllRequestDto request) throws Exception{
        return authService.getAllUser(request);
    }

    @PostMapping(RouterConstant.UPDATE_USER)
    public BaseResponse updateUser(@Valid @RequestBody UserUpdateRequestDto request) throws Exception{
        return authService.updateUser(request);
    }

    @PostMapping(RouterConstant.DELETE_USER)
    public BaseResponse deleteUser(@Valid @RequestBody UserDeleteRequestDto request) throws Exception{
        return authService.deleteUser(request);
    }

    @PostMapping(RouterConstant.REFRESH_TOKEN)
    public BaseResponse refreshToken(@Valid @RequestBody UserRefreshToken request) throws Exception{
        return authService.refreshToken(request);
    }
}
