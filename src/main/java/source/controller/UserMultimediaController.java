package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.anotation.LogsActivityAnnotation;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.UserDeleteAvatarRequestDto;
import source.dto.request.UserDownloadAvatarRequestDto;
import source.dto.request.UserUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.service.UserMultimediaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserMultimediaController {

    @Autowired
    private UserMultimediaService userMultimediaService;

    @PostMapping(RouterConstant.USER_AVATAR_UPLOAD)
    public BaseResponse uploadAvatar(@Valid UserUploadAvatarRequestDto userUploadAvatarRequestDto, HttpServletRequest request) throws Exception {
        userUploadAvatarRequestDto.setRequestId((String) request.getAttribute(RequestKeyConstant.REQUEST_ID));
        userUploadAvatarRequestDto.setUserId((String) request.getAttribute(RequestKeyConstant.USER_AUTH_ID));
        return userMultimediaService.uploadAvatar(userUploadAvatarRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_AVATAR_DELETE)
    public BaseResponse deleteAvatar(@RequestBody UserDeleteAvatarRequestDto userDeleteAvatarRequestDto) throws Exception {
        userDeleteAvatarRequestDto.setUserId(userDeleteAvatarRequestDto.getUserAuthId());
        return userMultimediaService.deleteAvatar(userDeleteAvatarRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_AVATAR_DOWNLOAD)
    public ResponseEntity<Object> downloadAvatar(@RequestBody UserDownloadAvatarRequestDto userDownloadAvatarRequestDto) throws Exception {
        userDownloadAvatarRequestDto.setUserId(userDownloadAvatarRequestDto.getUserAuthId());
        return userMultimediaService.downloadAvatar(userDownloadAvatarRequestDto);
    }
}