package source.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.response.BaseResponse;

@RestController
public class UserController {

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse<Object> signUp() {
        return BaseResponse.ofSucceeded(new Object());
    }
}
