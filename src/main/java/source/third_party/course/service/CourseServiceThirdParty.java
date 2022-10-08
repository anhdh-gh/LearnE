package source.third_party.course.service;

import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.GetCourseDetailRequestDto;
import source.dto.request.question.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;

public interface CourseServiceThirdParty {
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception;

    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;

    public BaseResponse getDetailCourse(GetCourseDetailRequestDto requestDto) throws Exception;
}
