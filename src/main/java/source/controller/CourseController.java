package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RouterConstant;
import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;
import source.service.course_service.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(RouterConstant.COURSE_CREATE)
    public BaseResponse createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto) throws Exception {
        return courseService.createCourse(createCourseRequestDto);
    }
}
