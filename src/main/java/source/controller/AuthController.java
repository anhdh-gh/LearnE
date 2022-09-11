package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.request.AuthSignInRequestDto;
import source.dto.request.AuthSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.service.user_service.AuthService;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@Valid @RequestBody AuthSignUpRequestDto request) throws Exception{
        return authService.signUp(request);
    }

    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@Valid @RequestBody AuthSignInRequestDto request) throws Exception{
        return authService.signIn(request);
    }
}
