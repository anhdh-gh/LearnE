package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.request.UserSignupRequestDto;
import source.dto.response.BaseResponse;
import source.service.user_service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signup(@RequestBody UserSignupRequestDto userRequestSignupDto) throws Exception {
        return userService.signUp(userRequestSignupDto);
    }
}
