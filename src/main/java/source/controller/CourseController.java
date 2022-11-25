package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.*;
import source.dto.request.CreateCourseRequestDto;
import source.dto.response.BaseResponse;
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
        return courseService.getCourseDetailForUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_BY_ID)
    public BaseResponse getCourseById(@RequestBody GetCourseByIdRequestDto request) throws Exception {
        return courseService.getCourseById(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_DELETE_BY_ID)
    public BaseResponse deleteCourseById(@RequestBody DeleteCourseByIdRequestDto request) throws Exception {
        return courseService.deleteCourseById(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_UPDATE)
    public BaseResponse updateCourse(@RequestBody UpdateCourseRequestDto request) throws Exception {
        return courseService.updateCourse(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_CALLBACK_QUESTIONS_DELETE)
    public BaseResponse callBackQuestionDelete(@RequestBody CallBackQuestionDeleteRequestDto request) throws Exception {
        return courseService.callBackQuestionDelete(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_UPDATE_LESSON_STATUS)
    public BaseResponse updateLessonStatus(@RequestBody UpdateLessonStatusRequestDto request) throws Exception {
        return courseService.updateLessonStatus(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_ALL)
    public BaseResponse getAllCourse(@RequestBody GetAllCourseRequestDto request) throws Exception {
        return courseService.getAllCourse(request);
    }
}
