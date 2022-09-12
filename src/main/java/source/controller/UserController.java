package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.request.UserCreateRequestDto;
import source.dto.response.BaseResponse;
import source.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(RouterConstant.CREATE_USER)
    public BaseResponse<Object> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) throws Exception {
        return BaseResponse.ofSucceeded(userCreateRequestDto.getRequestId(), userService.createUser(userCreateRequestDto));
    }
}
