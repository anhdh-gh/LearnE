package source.third_party.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.*;
import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.DeleteCourseByIdRequestDto;
import source.dto.request.course.UpdateCourseRequestDto;
import source.dto.request.questionBank.*;
import source.dto.request.studyset.TestResultDto;
import source.dto.request.studyset.*;
import source.dto.response.BaseResponse;
import source.third_party.auth.constant.RouterAuthServiceConstant;
import source.util.JsonUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class AuthServiceThirdPartyImpl implements AuthServiceThirdParty {

    @Value("${service.auth.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterAuthServiceConstant.SIGN_IN),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SIGN_UP),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_ALL_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.UPDATE_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.DELETE_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse refreshToken(UserRefreshToken request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.REFRESH_TOKEN),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getUserInformation(UserGetUserInformationRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_USER_INFORMATION),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getUserById(UserGetUserByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_USER_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteUserById(UserDeleteByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.DELETE_USER_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_GET_DETAIL_FOR_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_GET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_CREATE),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteCourse(DeleteCourseByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_DELETE_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateCourse(UpdateCourseRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_UPDATE),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_GET_ALL),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.COURSE_UPDATE_LESSON_STATUS),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse createStudyset(StudysetDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.CREATE_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateStudyset(StudysetDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.UPDATE_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.DELETE_STUDYSET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_STUDYSET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse saveTestResultStudyset(TestResultDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SAVE_STUDYSET_TEST_RESULT),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_ALL_STUDYSET_BY_OWNER_USER_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_ALL_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse searchAllStudysetByOwnerUserId(SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SEARCH_ALL_STUDYSET_BY_OWNER_USER_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse searchAllStudyset(SearchAllStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SEARCH_ALL_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_RANK_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.CHECK_OWNER_STUDYSET_VALID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse createQuestion(QuestionDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_CREATE),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateQuestion(QuestionDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_UPDATE),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteQuestionById(DeleteQuestionByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_DELETE_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getQuestionById(GetQuestionByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_GET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllQuestion(GetAllQuestionDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_GET_ALL),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getRankQuestion(GetRankQuestionDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_GET_RANK),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse saveTestResultQuestion(source.dto.request.questionBank.TestResultDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.QUESTION_SAVE_TEST_RESULT),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse searchQuestion(SearchQuestionRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SEARCH_QUESTION),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    private HttpEntity<BasicRequest> getHeader(BasicRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-ID", request.getRequestId());
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", request.getAuthorization());
        return new HttpEntity<>(request, headers);
    }
}
