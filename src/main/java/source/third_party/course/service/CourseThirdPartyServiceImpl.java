package source.third_party.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.response.BaseResponse;
import source.third_party.course.constant.RouterConstant;
import source.third_party.course.dto.request.CallBackQuestionDeleteRequestDto;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class CourseThirdPartyServiceImpl implements CourseThirdPartyService {

    @Value("${service.course.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse callBackQuestionDelete(CallBackQuestionDeleteRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterConstant.COURSE_CALLBACK_QUESTION_DELETE),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
