package source.third_party.course.service;

import source.dto.response.BaseResponse;
import source.third_party.course.dto.request.CallBackQuestionsDeleteRequestDto;
import source.third_party.multimedia.dto.request.QuestionCheckExistRequestDto;
import source.third_party.multimedia.dto.request.QuestionDeleteByGroupIdRequestDto;

public interface CourseThirdPartyService {

    BaseResponse callBackQuestionsDelete(CallBackQuestionsDeleteRequestDto request) throws Exception;
}
