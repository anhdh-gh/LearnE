package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.GetAllCourseRequestDto;
import source.dto.request.GetCourseByIdRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
import source.dto.response.BaseResponse;
import source.service.course_service.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_DETAIL_FOR_USER)
    public BaseResponse getCourseDetailForUser(@RequestBody GetCourseDetailForUserRequestDto request) throws Exception {
        if(request.getUserId() == null) {
            request.setUserId(request.getUserAuthId());
        }
        return courseService.getCourseDetailForUserRequestDto(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_BY_ID)
    public BaseResponse getCourseById(@RequestBody GetCourseByIdRequestDto request) throws Exception {
        return courseService.getCourseById(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_UPDATE_LESSON_STATUS)
    public BaseResponse getCourseDetailForUser(@RequestBody UpdateLessonStatusRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return courseService.updateLessonStatus(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_ALL)
    public BaseResponse getAllCourse(@RequestBody GetAllCourseRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return courseService.getAllCourse(request);
    }
}
