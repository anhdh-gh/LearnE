package source.service;

import source.dto.request.*;
import source.dto.request.create_course.CreateCourseRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception;

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception;

    BaseResponse deleteCourseById(DeleteCourseByIdRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;
}