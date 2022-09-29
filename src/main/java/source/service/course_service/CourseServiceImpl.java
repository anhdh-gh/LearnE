package source.service.course_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.course.service.CourseServiceThirdParty;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseServiceThirdParty courseServiceThirdParty;

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception {
        return courseServiceThirdParty.createCourse(createCourseRequestDto);
    }
}
