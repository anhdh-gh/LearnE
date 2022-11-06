package source.third_party.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.UpdateLessonStatusRequestDto;
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
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterCourseServiceConstant.COURSE_GET_DETAIL_FOR_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterCourseServiceConstant.COURSE_UPDATE_LESSON_STATUS),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
