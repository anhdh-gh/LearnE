package source.third_party.course.service;

import source.dto.request.CreateCourseRequestDto;
import source.dto.request.GetCourseDetailRequestDto;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;

public interface CourseServiceThirdParty {
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception;

    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;

    public BaseResponse getDetailCourse(GetCourseDetailRequestDto requestDto) throws Exception;
}
