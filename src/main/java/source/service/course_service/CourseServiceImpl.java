package source.service.course_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.GetAllCourseRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.course.service.CourseServiceThirdParty;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseServiceThirdParty courseServiceThirdParty;

    @Override
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        return courseServiceThirdParty.getCourseDetailForUserRequestDto(request);
    }

    @Override
    public BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception {
        return courseServiceThirdParty.updateLessonStatus(request);
    }

    @Override
    public BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception {
        return courseServiceThirdParty.getAllCourse(request);
    }
}
