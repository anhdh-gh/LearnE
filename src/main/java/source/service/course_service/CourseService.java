package source.service.course_service;

import source.dto.request.GetAllCourseRequestDto;
import source.dto.request.GetCourseByIdRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.request.course.DeleteCourseByIdRequestDto;
import source.dto.request.course.create_course.CreateCourseRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;

    BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception;

    BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception;

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse deleteCourse(DeleteCourseByIdRequestDto request) throws Exception;
}