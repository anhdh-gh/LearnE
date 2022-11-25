package source.third_party.course.service;

import source.dto.request.GetAllCourseRequestDto;
import source.dto.request.GetCourseByIdRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.request.course.*;
import source.dto.response.BaseResponse;

public interface CourseServiceThirdParty {

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;

    BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception;

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse updateCourse(UpdateCourseRequestDto request) throws Exception;

    BaseResponse deleteCourse(DeleteCourseByIdRequestDto request) throws Exception;

    BaseResponse searchCourse(SearchCourseRequestDto request) throws Exception;
}
