package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import source.constant.FirebaseStorageConstant;
import source.dto.request.UserDeleteAvatarRequestDto;
import source.dto.request.UserDownloadAvatarRequestDto;
import source.dto.request.UserUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.UserUploadAvatarResponseDto;
import source.entity.User;
import source.exception.BusinessErrors;
import source.repository.UserRepository;
import source.third_party.firebase_storage.dto.request.FirebaseStorageRequestDto;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;
import source.third_party.firebase_storage.service.FirebaseStorageService;

@Service
public class UserMultimediaServiceImpl extends BaseService implements UserMultimediaService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse uploadAvatar(UserUploadAvatarRequestDto request) throws Exception {
        FirebaseUploadFileResponseDto firebaseUploadFileResponseDto = firebaseStorageService.uploadFile(FirebaseUploadFileRequestDto.builder().multipartFile(request.getAvatar()).folder(FirebaseStorageConstant.BASE_PATH_USER_AVATAR).fileName(request.getUserId()).build());

        // Save info file to firebase
        userRepository.set(User
            .builder()
            .id(request.getUserId())
            .avatar(firebaseUploadFileResponseDto.getUrl())
            .extension(firebaseUploadFileResponseDto.getExtension())
            .build()
        );

        return BaseResponse.ofSucceeded(request.getRequestId(),
            UserUploadAvatarResponseDto.builder()
            .urlAvatar(firebaseUploadFileResponseDto.getUrl())
            .extension(firebaseUploadFileResponseDto.getExtension())
            .build());
    }

    @Override
    public BaseResponse deleteAvatar(UserDeleteAvatarRequestDto request) throws Exception {
        User user = userRepository.get(request.getUserId());

        boolean result = firebaseStorageService.deleteFile(FirebaseStorageRequestDto.builder().folder(FirebaseStorageConstant.BASE_PATH_USER_AVATAR).fileName(request.getUserId() + "." + getExtensionByUrl(user.getAvatar())).build());
        if(result) {
            userRepository.remove(user.getId());
            return BaseResponse.ofSucceeded(request.getRequestId(), "Delete image successfully");
        }

        return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.BAD_REQUEST,"Delete image fail");
    }

    @Override
    public ResponseEntity<Object> downloadAvatar(UserDownloadAvatarRequestDto request) throws Exception {
        User user = userRepository.get(request.getUserId());
        return firebaseStorageService.downloadFile(FirebaseStorageRequestDto.builder().requestId(request.getRequestId()).folder(FirebaseStorageConstant.BASE_PATH_USER_AVATAR).fileName(request.getUserId() + "." + getExtensionByUrl(user.getAvatar())).build());
    }
}