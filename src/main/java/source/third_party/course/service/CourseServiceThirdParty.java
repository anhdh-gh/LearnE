package source.third_party.course.service;

import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.response.BaseResponse;

public interface CourseServiceThirdParty {

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;
}
