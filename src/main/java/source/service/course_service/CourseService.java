package source.service.course_service;

import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;
}
