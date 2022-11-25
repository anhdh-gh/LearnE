package source.third_party.course.service;

import source.dto.response.BaseResponse;
import source.third_party.course.dto.request.CallBackQuestionDeleteRequestDto;

public interface CourseThirdPartyService {

    BaseResponse callBackQuestionDelete(CallBackQuestionDeleteRequestDto request) throws Exception;
}
