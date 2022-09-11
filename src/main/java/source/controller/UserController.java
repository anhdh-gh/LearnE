package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.service.user_service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@Valid @RequestBody UserSignUpRequestDto request) throws Exception{
        return userService.signUp(request);
    }

    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@Valid @RequestBody UserSignInRequestDto request) throws Exception{
        return userService.signIn(request);
    }
}
