package source.service;

import org.springframework.http.ResponseEntity;
import source.dto.request.UserDeleteAvatarRequestDto;
import source.dto.request.UserDownloadAvatarRequestDto;
import source.dto.request.UserUploadAvatarRequestDto;
import source.dto.response.BaseResponse;

public interface UserMultimediaService {

    BaseResponse uploadAvatar(UserUploadAvatarRequestDto request) throws Exception;

    BaseResponse deleteAvatar(UserDeleteAvatarRequestDto request) throws Exception;

    ResponseEntity<Object> downloadAvatar(UserDownloadAvatarRequestDto request) throws Exception;
}