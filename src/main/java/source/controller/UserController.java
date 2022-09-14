package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.constant.RouterConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.entity.User;
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

    @PostMapping(RouterConstant.GET_USER_BY_ID)
    public BaseResponse getUserById(@RequestBody UserGetByIdRequestDto userGetByIdRequestDto) throws Exception {
        return userService.getUserById(userGetByIdRequestDto);
    }

    @PostMapping( RouterConstant.GET_ALL_USER)
    public BaseResponse getAllUsers(@RequestBody UserGetAllRequestDto userGetAllRequestDto) throws Exception{
        return userService.getAllUser(userGetAllRequestDto);
    }

    @PostMapping(RouterConstant.UPDATE_USER)
    public BaseResponse updateUser(@RequestBody UserUpdateRequestDto userGetAllRequestDto) throws Exception{
        return userService.updateUser(userGetAllRequestDto);
    }

    @PostMapping(RouterConstant.DELETE_USER)
    public BaseResponse deleteUser(@RequestBody UserDeleteRequestDto userDeleteRequestDto) throws Exception{
        return userService.deleteUser(userDeleteRequestDto);
    }


}
