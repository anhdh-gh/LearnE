package source.service;

import source.dto.request.QuestionUploadAvatarRequestDto;
import source.dto.response.BaseResponse;

public interface QuestionMultimediaService {

    BaseResponse uploadQuestion(QuestionUploadAvatarRequestDto request) throws Exception;
}