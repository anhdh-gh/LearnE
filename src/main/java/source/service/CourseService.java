package source.service;


import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto);
}
