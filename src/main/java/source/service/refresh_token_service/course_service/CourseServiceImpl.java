package source.service.refresh_token_service.course_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.CreateCourseRequestDto;
import source.dto.request.GetCourseDetailRequestDto;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.course.service.CourseServiceThirdParty;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseServiceThirdParty courseServiceThirdParty;

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception {
        return courseServiceThirdParty.createCourse(createCourseRequestDto);
    }

    @Override
    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception {
        return courseServiceThirdParty.getQuestionsByIds(requestDto);
    }

    @Override
    public BaseResponse getCourseDetail(GetCourseDetailRequestDto requestDto) throws Exception {
        return courseServiceThirdParty.getDetailCourse(requestDto);
    }
}
