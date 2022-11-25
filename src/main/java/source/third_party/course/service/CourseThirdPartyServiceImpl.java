package source.third_party.course.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import source.dto.response.BaseResponse;
import source.third_party.course.constant.RouterConstant;
import source.third_party.course.dto.request.CallBackQuestionDeleteRequestDto;
import source.third_party.multimedia.BaseService;
import source.util.JsonUtil;

@Service
public class CourseThirdPartyServiceImpl extends BaseService implements CourseThirdPartyService {

    @Value("${service.course.baseurl}")
    private String baseUrl;

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
