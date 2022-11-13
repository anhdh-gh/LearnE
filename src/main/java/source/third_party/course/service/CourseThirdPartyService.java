package source.third_party.course.service;

import source.dto.response.BaseResponse;
import source.third_party.course.dto.request.CallBackQuestionsDeleteRequestDto;

public interface CourseThirdPartyService {

    BaseResponse callBackQuestionsDelete(CallBackQuestionsDeleteRequestDto request) throws Exception;
}
