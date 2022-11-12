package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import source.constant.ErrorCodeConstant;
import source.constant.FirebaseStorageConstant;
import source.dto.request.QuestionCheckExistRequestDto;
import source.dto.request.QuestionDeleteByGroupIdRequestDto;
import source.dto.request.QuestionUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.QuestionUploadAvatarResponseDto;
import source.entity.Question;
import source.entity.QuestionDetail;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.QuestionRepository;
import source.third_party.firebase_storage.dto.request.FirebaseStorageRequestDto;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionMultimediaServiceImpl extends BaseService implements QuestionMultimediaService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public BaseResponse uploadQuestion(QuestionUploadAvatarRequestDto request) throws Exception {
        QuestionDetail questionDetail = QuestionDetail
            .builder()
            .id(request.getId() == null ? UUID.randomUUID().toString() : request.getId())
            .build();

        // Tìm xem có tồn tại request id đó không
        Question question;
        try {
            question = questionRepository.get(request.getGroupId());
            question.getQuestionDetails().add(questionDetail);
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                question = Question
                    .builder()
                    .questionDetails(Collections.singletonList(questionDetail))
                    .groupId(request.getGroupId() == null ? UUID.randomUUID().toString() : request.getGroupId())
                    .build();
            } else {
                throw e;
            }
        }

        if(request.getImage() != null) {
            FirebaseUploadFileResponseDto firebaseUploadImageResponseDto =
                firebaseStorageService.uploadFile(FirebaseUploadFileRequestDto
                    .builder()
                    .multipartFile(request.getImage())
                    .folder(FirebaseStorageConstant.BASE_PATH_QUESTION + question.getGroupId() + "/")
                    .fileName(questionDetail.getId())
                    .build());
            questionDetail.setImage(firebaseUploadImageResponseDto.getUrl());
            questionDetail.setExtensionImage(firebaseUploadImageResponseDto.getExtension());
        }

        if(request.getAudio() != null) {
            FirebaseUploadFileResponseDto firebaseUploadAudioResponseDto =
                firebaseStorageService.uploadFile(FirebaseUploadFileRequestDto
                    .builder()
                    .multipartFile(request.getAudio())
                    .folder(FirebaseStorageConstant.BASE_PATH_QUESTION + question.getGroupId() + "/")
                    .fileName(questionDetail.getId())
                    .build());

            questionDetail.setAudio(firebaseUploadAudioResponseDto.getUrl());
            questionDetail.setExtensionAudio(firebaseUploadAudioResponseDto.getExtension());
        }

        // Save info file to firebase
        questionRepository.set(question);

        // Return
        QuestionUploadAvatarResponseDto responseDto = modelMapper.map(questionDetail, QuestionUploadAvatarResponseDto.class);
        responseDto.setId(questionDetail.getId());
        responseDto.setUrlImage(questionDetail.getImage());
        responseDto.setUrlAudio(questionDetail.getAudio());
        responseDto.setExtensionImage(questionDetail.getExtensionImage());
        responseDto.setExtensionAudio(questionDetail.getExtensionAudio());
        responseDto.setGroupId(question.getGroupId());
        return BaseResponse.ofSucceeded(request.getRequestId(), responseDto);
    }

    private Question getQuestionGroupId(String groupId) throws Exception {
        Question question = questionRepository.get(groupId);
        if(question == null) {
            throw new BusinessException(BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.QUESTION_GROUP_ID_NOT_EXIST_400031));
        }

        return question;
    }

    private void deleteQuestionByGroupId(String groupId) throws Exception {
        Question question = getQuestionGroupId(groupId);
        for(QuestionDetail questionDetail: question.getQuestionDetails()) {
            if(questionDetail.getImage() != null) {
                firebaseStorageService.deleteFile(FirebaseStorageRequestDto
                    .builder()
                    .folder(FirebaseStorageConstant.BASE_PATH_QUESTION + question.getGroupId() + "/")
                    .fileName(questionDetail.getId() + "." + questionDetail.getExtensionImage())
                    .build());
            }

            if(questionDetail.getAudio() != null) {
                firebaseStorageService.deleteFile(FirebaseStorageRequestDto
                    .builder()
                    .folder(FirebaseStorageConstant.BASE_PATH_QUESTION + question.getGroupId() + "/")
                    .fileName(questionDetail.getId() + "." + questionDetail.getExtensionAudio())
                    .build());
            }
        }
        questionRepository.remove(groupId);
    }

    @Override
    public BaseResponse checkQuestionExist(QuestionCheckExistRequestDto request) throws Exception {
        Question question = getQuestionGroupId(request.getGroupId());

        Map<String, QuestionDetail> questionMap = question.getQuestionDetails()
            .stream().collect(Collectors.toMap(QuestionDetail::getId, questionDetail -> questionDetail));

        Map<String, QuestionDetail> questionMapRequest = request.getQuestions()
            .stream().collect(Collectors.toMap(QuestionDetail::getId, questionDetail -> questionDetail));

        for (Map.Entry<String, QuestionDetail> entry : questionMap.entrySet()) {
            if(!questionMapRequest.containsKey(entry.getKey())) {
                deleteQuestionByGroupId(question.getGroupId());
                return BaseResponse.ofFailed(
                    request.getRequestId(),
                    BusinessErrors.INVALID_PARAMETERS,
                    environment.getProperty(BusinessErrors.INVALID_PARAMETERS.getMessage())
                );
            }

            QuestionDetail questionDetail = entry.getValue();
            QuestionDetail questionDetailRequest = questionMapRequest.get(entry.getKey());

            if((questionDetail.getImage() != null && (questionDetailRequest.getImage() == null || !questionDetail.getImage().equals(questionDetailRequest.getImage())))
                || (questionDetail.getAudio() != null && (questionDetailRequest.getAudio() == null || !questionDetail.getAudio().equals(questionDetailRequest.getAudio())))) {
                deleteQuestionByGroupId(question.getGroupId());

                return BaseResponse.ofFailed(
                    request.getRequestId(),
                    BusinessErrors.INVALID_PARAMETERS,
                    environment.getProperty(BusinessErrors.INVALID_PARAMETERS.getMessage())
                );
            }
        }

        return BaseResponse.ofSucceeded(request.getRequestId(), question);
    }

    @Override
    public BaseResponse deleteQuestionByGroupId(QuestionDeleteByGroupIdRequestDto request) throws Exception {
        deleteQuestionByGroupId(request.getGroupId());
        return BaseResponse.ofSucceeded(request.getRequestId(), "Remove question by groupId successfully");
    }
}
