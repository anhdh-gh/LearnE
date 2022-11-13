package source.service;

import source.dto.request.QuestionCheckExistRequestDto;
import source.dto.request.QuestionDeleteByGroupIdRequestDto;
import source.dto.request.QuestionUploadRequestDto;
import source.dto.response.BaseResponse;

public interface QuestionMultimediaService {

    BaseResponse uploadQuestion(QuestionUploadRequestDto request) throws Exception;

    BaseResponse checkQuestionExist(QuestionCheckExistRequestDto request) throws Exception;

    BaseResponse deleteQuestionByGroupId(QuestionDeleteByGroupIdRequestDto request) throws Exception;
}