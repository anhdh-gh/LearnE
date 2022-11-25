package source.service;

import source.dto.request.*;
import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception;

    BaseResponse getCourseDetailForUser(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception;

    BaseResponse deleteCourseById(DeleteCourseByIdRequestDto request) throws Exception;

    BaseResponse updateCourse(UpdateCourseRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;

    BaseResponse callBackQuestionDelete(CallBackQuestionDeleteRequestDto request) throws Exception;
}