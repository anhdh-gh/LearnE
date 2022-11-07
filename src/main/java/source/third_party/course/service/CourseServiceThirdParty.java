package source.third_party.course.service;

import source.dto.request.GetAllCourseRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.response.BaseResponse;

public interface CourseServiceThirdParty {

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;

    BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception;
}
