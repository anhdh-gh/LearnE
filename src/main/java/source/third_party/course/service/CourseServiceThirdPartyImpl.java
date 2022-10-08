package source.third_party.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.GetCourseDetailRequestDto;
import source.dto.request.question.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.course.constant.RouterCourseServiceConstant;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class CourseServiceThirdPartyImpl implements CourseServiceThirdParty{

    @Value("${service.course.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterCourseServiceConstant.COURSE_CREATE),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterCourseServiceConstant.QUESTION_GET_BY_IDS),
            HttpMethod.POST,
            getHeader(requestDto),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getDetailCourse(GetCourseDetailRequestDto requestDto) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterCourseServiceConstant.GET_COURSE_DETAIL),
                HttpMethod.POST,
                getHeader(requestDto),
                new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
