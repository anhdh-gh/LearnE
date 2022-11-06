package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.constant.FirebaseStorageConstant;
import source.dto.request.QuestionUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.QuestionUploadAvatarResponseDto;
import source.entity.Question;
import source.repository.QuestionRepository;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;

import java.util.UUID;

@Service
public class QuestionMultimediaServiceImpl extends BaseService implements QuestionMultimediaService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public BaseResponse uploadQuestion(QuestionUploadAvatarRequestDto request) throws Exception {
        Question question = Question
            .builder()
            .id(UUID.randomUUID().toString())
            .build();

        if(request.getImage() != null) {
            FirebaseUploadFileResponseDto firebaseUploadImageResponseDto =
                firebaseStorageService.uploadFile(FirebaseUploadFileRequestDto
                    .builder()
                    .multipartFile(request.getImage())
                    .folder(FirebaseStorageConstant.BASE_PATH_QUESTION)
                    .fileName(question.getId())
                    .build());
            question.setImage(firebaseUploadImageResponseDto.getUrl());
            question.setExtensionImage(firebaseUploadImageResponseDto.getExtension());
        }

        if(request.getAudio() != null) {
            FirebaseUploadFileResponseDto firebaseUploadAudioResponseDto =
                firebaseStorageService.uploadFile(FirebaseUploadFileRequestDto
                    .builder()
                    .multipartFile(request.getAudio())
                    .folder(FirebaseStorageConstant.BASE_PATH_QUESTION)
                    .fileName(question.getId())
                    .build());

            question.setAudio(firebaseUploadAudioResponseDto.getUrl());
            question.setExtensionAudio(firebaseUploadAudioResponseDto.getExtension());
        }

        // Save info file to firebase
        questionRepository.set(question);

        // Return
        QuestionUploadAvatarResponseDto responseDto = modelMapper.map(question, QuestionUploadAvatarResponseDto.class);
        responseDto.setUrlImage(question.getImage());
        responseDto.setUrlAudio(question.getAudio());
        responseDto.setExtensionImage(question.getExtensionImage());
        responseDto.setExtensionAudio(question.getExtensionAudio());
        return BaseResponse.ofSucceeded(request.getRequestId(), responseDto);
    }
}
