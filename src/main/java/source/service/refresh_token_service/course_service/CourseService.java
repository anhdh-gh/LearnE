package source.service.refresh_token_service.course_service;

import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.GetCourseDetailRequestDto;
import source.dto.request.question.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception;

    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;

    public BaseResponse getCourseDetail(GetCourseDetailRequestDto requestDto) throws Exception;
}
