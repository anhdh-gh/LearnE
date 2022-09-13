package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.constant.RouterConstant;
import source.dto.request.UserComparePasswordRequestDto;
import source.dto.request.UserCreateRequestDto;
import source.dto.request.UserGetRoleByUserIdRequestDto;
import source.dto.response.BaseResponse;
import source.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(RouterConstant.CREATE_USER)
    public BaseResponse createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) throws Exception {
        return userService.createUser(userCreateRequestDto);
    }

    @PostMapping(RouterConstant.COMPARE_PASSWORD)
    public BaseResponse comparePassword(@RequestBody UserComparePasswordRequestDto userComparePasswordRequestDto ) throws Exception {
        return userService.comparePassword(userComparePasswordRequestDto);
    }

    @PostMapping(RouterConstant.GET_ROLE_BY_USER_ID)
    public BaseResponse getRoleByUserId(@RequestBody UserGetRoleByUserIdRequestDto userGetRoleByUserIdRequestDto) throws Exception {
        return userService.getRoleByUserId(userGetRoleByUserIdRequestDto);
    }
}
