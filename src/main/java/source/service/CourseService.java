package source.service;

import source.dto.request.CreateCourseRequestDto;
import source.dto.request.GetCourseByCourseIdAndUserIdRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse getCourseByCourseIdAndUserIdRequestDto(GetCourseByCourseIdAndUserIdRequestDto request) throws Exception;
}