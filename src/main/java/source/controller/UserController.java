package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.CREATE_USER)
    public BaseResponse createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) throws Exception {
        return userService.createUser(userCreateRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COMPARE_PASSWORD)
    public BaseResponse comparePassword(@RequestBody UserComparePasswordRequestDto userComparePasswordRequestDto) throws Exception {
        return userService.comparePassword(userComparePasswordRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.GET_USER_BY_ID)
    public BaseResponse getUserById(@RequestBody UserGetByIdRequestDto userGetByIdRequestDto) throws Exception {
        return userService.getUserById(userGetByIdRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.GET_ALL_USER)
    public BaseResponse getAllUsers(@RequestBody UserGetAllRequestDto userGetAllRequestDto) throws Exception {
        return userService.getAllUser(userGetAllRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.UPDATE_USER)
    public BaseResponse updateUser(@RequestBody UserUpdateRequestDto userGetAllRequestDto) throws Exception {
        return userService.updateUser(userGetAllRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.DELETE_USER)
    public BaseResponse deleteUser(@RequestBody UserDeleteRequestDto userDeleteRequestDto) throws Exception {
        return userService.deleteUser(userDeleteRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.GET_USER_LIST_BY_IDS)
    public BaseResponse getUserByUserIds(@RequestBody UserGetListByIdsRequestDto request) throws Exception {
        return userService.getUserByUserIds(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.GET_ALL_USER)
    public BaseResponse searchUser(@RequestBody SearchUserRequestDto request) throws Exception {
        return userService.searchUser(request);
    }
}
