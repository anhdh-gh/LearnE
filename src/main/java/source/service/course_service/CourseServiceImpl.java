package source.service.course_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.GetAllCourseRequestDto;
import source.dto.request.GetCourseByIdRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.request.course.DeleteCourseByIdRequestDto;
import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.UpdateCourseRequestDto;
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

    @Override
    public BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception {
        return courseServiceThirdParty.getCourseById(request);
    }

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        return courseServiceThirdParty.createCourse(request);
    }

    @Override
    public BaseResponse deleteCourse(DeleteCourseByIdRequestDto request) throws Exception {
        return courseServiceThirdParty.deleteCourse(request);
    }

    @Override
    public BaseResponse updateCourse(UpdateCourseRequestDto request) throws Exception {
        return courseServiceThirdParty.updateCourse(request);
    }
}
