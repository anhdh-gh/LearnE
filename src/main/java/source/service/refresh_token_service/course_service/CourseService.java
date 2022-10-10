package source.service.refresh_token_service.course_service;

import source.dto.request.CreateCourseRequestDto;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;

public interface CourseService {
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception;

    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;
}
