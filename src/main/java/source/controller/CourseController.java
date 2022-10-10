package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.create_course.CreateCourseRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.get_course_detail_for_user.GetCourseDetailForUserResponseDto;
import source.service.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_CREATE)
    public BaseResponse createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto) throws Exception {
        return courseService.createCourse(createCourseRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_DETAIL_FOR_USER)
    public BaseResponse getCourseDetailForUser(@RequestBody GetCourseDetailForUserRequestDto request) throws Exception {
        return courseService.getCourseDetailForUserRequestDto(request);
    }
}
