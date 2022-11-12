package source.third_party.multimedia.service;

import source.dto.response.BaseResponse;
import source.third_party.multimedia.dto.request.QuestionCheckExistRequestDto;
import source.third_party.multimedia.dto.request.QuestionDeleteByGroupIdRequestDto;

public interface MultimediaThirdPartyService {

    BaseResponse checkQuestionExist(QuestionCheckExistRequestDto request) throws Exception;

    BaseResponse deleteQuestionByGroupId(QuestionDeleteByGroupIdRequestDto request) throws Exception;
}
