package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.GetCourseDetailRequestDto;
import source.dto.request.question.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;
import source.entity.User;
import source.service.refresh_token_service.course_service.CourseService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(RouterConstant.COURSE_CREATE)
    public BaseResponse createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto) throws Exception {
        return courseService.createCourse(createCourseRequestDto);
    }

    @PostMapping(RouterConstant.QUESTION_GET_BY_IDS)
    public BaseResponse getQuestionsByIds(@RequestBody QuestionGetByIdsRequestDto requestDto) throws Exception {
        return courseService.getQuestionsByIds(requestDto);
    }

    @PostMapping(RouterConstant.GET_COURSE_DETAIL)
    public BaseResponse getDetailCourse(@RequestBody GetCourseDetailRequestDto requestDto, HttpServletRequest request) throws Exception {
        String userId = ((User) request.getAttribute(RequestKeyConstant.USER_AUTH)).getId();
        if(userId != null){
            requestDto.setUserId(userId);
        }
        return courseService.getCourseDetail(requestDto);
    }
}
