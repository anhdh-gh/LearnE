package source.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.constant.FirebaseStorageConstant;
import source.dto.request.FilesUploadRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.UserUploadAvatarResponseDto;
import source.entity.FileInformation;
import source.repository.FileInformationRepository;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;

import java.util.UUID;

@Service
public class FileInformationServiceImpl extends BaseService implements FileInformationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionMultimediaServiceImpl.class);

    @Autowired
    private FileInformationRepository fileInformationRepository;

    @Override
    public BaseResponse uploadFile(FilesUploadRequestDto request) throws Exception {
        String id = UUID.randomUUID().toString();

        FirebaseUploadFileResponseDto firebaseUploadFileResponseDto =
            firebaseStorageService.uploadFile(
                FirebaseUploadFileRequestDto
                    .builder()
                    .multipartFile(request.getFile())
                    .folder(FirebaseStorageConstant.BASE_PATH_FILE_INFORMATION)
                    .fileName(id)
                    .build());

        // Save info file to firebase
        FileInformation fileInformation = fileInformationRepository.set(FileInformation
            .builder()
            .id(id)
            .url(firebaseUploadFileResponseDto.getUrl())
            .extension(firebaseUploadFileResponseDto.getExtension())
            .build()
        );

        return BaseResponse.ofSucceeded(request.getRequestId(), fileInformation);
    }
}
