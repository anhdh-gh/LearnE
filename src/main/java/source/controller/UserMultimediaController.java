package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.UserDeleteAvatarRequestDto;
import source.dto.request.UserDownloadAvatarRequestDto;
import source.dto.request.UserUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.entity.User;
import source.service.UserMultimediaService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserMultimediaController {

    @Autowired
    private UserMultimediaService userMultimediaService;

    @PostMapping(RouterConstant.USER_AVATAR_UPLOAD)
    public BaseResponse uploadAvatar(UserUploadAvatarRequestDto userUploadAvatarRequestDto, HttpServletRequest request) throws Exception {
        userUploadAvatarRequestDto.setRequestId((String) request.getAttribute(RequestKeyConstant.REQUEST_ID));
        userUploadAvatarRequestDto.setUserId(((User) request.getAttribute(RequestKeyConstant.USER)).getId());
        return userMultimediaService.uploadAvatar(userUploadAvatarRequestDto);
    }

    @PostMapping(RouterConstant.USER_AVATAR_DELETE)
    public BaseResponse deleteAvatar(@RequestBody UserDeleteAvatarRequestDto userDeleteAvatarRequestDto, HttpServletRequest request) throws Exception {
        userDeleteAvatarRequestDto.setUserId(((User) request.getAttribute(RequestKeyConstant.USER)).getId());
        return userMultimediaService.deleteAvatar(userDeleteAvatarRequestDto);
    }

    @PostMapping(RouterConstant.USER_AVATAR_DOWNLOAD)
    public ResponseEntity<Object> downloadAvatar(@RequestBody UserDownloadAvatarRequestDto userDownloadAvatarRequestDto, HttpServletRequest request) throws Exception {
        userDownloadAvatarRequestDto.setUserId(((User) request.getAttribute(RequestKeyConstant.USER)).getId());
        return userMultimediaService.downloadAvatar(userDownloadAvatarRequestDto);
    }
}