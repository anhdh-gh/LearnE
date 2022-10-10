package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;
import source.service.CourseService;

import javax.validation.Valid;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(RouterConstant.COURSE_CREATE)
    public BaseResponse createCourse(@Valid @RequestBody CreateCourseRequestDto request) throws Exception {
        return courseService.createCourse(request);
    }
}
