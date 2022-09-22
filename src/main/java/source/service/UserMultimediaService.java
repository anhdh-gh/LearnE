package source.service;

import org.springframework.http.ResponseEntity;
import source.dto.request.UserDeleteAvatarRequestDto;
import source.dto.request.UserDownloadAvatarRequestDto;
import source.dto.request.UserUploadAvatarRequestDto;
import source.dto.response.BaseResponse;

public interface UserMultimediaService {

    public BaseResponse uploadAvatar(UserUploadAvatarRequestDto request) throws Exception;

    public BaseResponse deleteAvatar(UserDeleteAvatarRequestDto request) throws Exception;

    public ResponseEntity<Object> downloadAvatar(UserDownloadAvatarRequestDto request) throws Exception;
}