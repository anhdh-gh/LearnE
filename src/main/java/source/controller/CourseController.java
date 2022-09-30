package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.CreateCourseRequestDto;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;
import source.service.refresh_token_service.course_service.CourseService;

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
    @PostMapping(RouterConstant.QUESTION_GET_BY_IDS)
    public BaseResponse getQuestionsByIds(@RequestBody QuestionGetByIdsRequestDto requestDto) throws Exception {
        return courseService.getQuestionsByIds(requestDto);
    }
}
