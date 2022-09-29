package source.third_party.course.service;

import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;

public interface CourseServiceThirdParty {
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception;
}
