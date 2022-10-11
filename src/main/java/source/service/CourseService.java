package source.service;

import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.request.create_course.CreateCourseRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;
}