package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.UserGetUserInformationRequestDto;
import source.dto.request.*;
import source.dto.request.studyset.AddOrUpdateExaminationRequestDto;
import source.dto.request.studyset.CreateStudysetRequestDto;
import source.dto.request.studyset.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;
import source.service.auth.AuthService;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@Valid @RequestBody UserSignUpRequestDto request) throws Exception {
        return authService.signUp(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@Valid @RequestBody UserSignInRequestDto request) throws Exception {
        return authService.signIn(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.GET_ALL_USER)
    public BaseResponse getAllUsers(@Valid @RequestBody UserGetAllRequestDto request) throws Exception {
        return authService.getAllUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.UPDATE_USER)
    public BaseResponse updateUser(@Valid @RequestBody UserUpdateRequestDto request) throws Exception {
        return authService.updateUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.DELETE_USER)
    public BaseResponse deleteUser(@Valid @RequestBody UserDeleteRequestDto request) throws Exception {
        return authService.deleteUser(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.REFRESH_TOKEN)
    public BaseResponse refreshToken(@Valid @RequestBody UserRefreshToken request) throws Exception {
        return authService.refreshToken(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_INFO)
    public BaseResponse getUserInformation(@Valid @RequestBody UserGetUserInformationRequestDto request) throws Exception {
        return authService.getUserInformation(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.USER_GET_BY_ID)
    public BaseResponse getUserById(@Valid @RequestBody UserGetUserByIdRequestDto request) throws Exception {
        return authService.getUserById(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.ADMIN_DELETE_USER)
    public BaseResponse deleteUserById(@Valid @RequestBody UserDeleteByIdRequestDto request) throws Exception {
        return authService.deleteUserById(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_CREATE)
    public BaseResponse createQuestion(@Valid @RequestBody CreateQuestionRequestDto createQuestionRequestDto) throws Exception {
        return authService.createQuestion(createQuestionRequestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_BY_QUESTION_ID)
    public BaseResponse getQuestionByQuestionId(@Valid @RequestBody GetQuestionByQuestionIdRequestDto request) throws Exception {
        return authService.getQuestionByQuestionId(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_ALL)
    public BaseResponse getQuestion(@Valid @RequestBody QuestionGetAllRequestDto request) throws Exception {
        return authService.getAllQuestion(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_BY_IDS)
    public BaseResponse getQuestionsByIds(@Valid @RequestBody QuestionGetByIdsRequestDto requestDto) throws Exception {
        return authService.getQuestionsByIds(requestDto);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_GET_DETAIL_FOR_USER)
    public BaseResponse getCourseDetailForUser(@Valid @RequestBody GetCourseDetailForUserRequestDto request) throws Exception {
        return authService.getCourseDetailForUserRequestDto(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.COURSE_UPDATE_LESSON_STATUS)
    public BaseResponse getCourseDetailForUser(@Valid @RequestBody UpdateLessonStatusRequestDto request) throws Exception {
        return authService.updateLessonStatus(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.CREATE_STUDYSET)
    public BaseResponse createStudyset(@Valid @RequestBody CreateStudysetRequestDto request) throws Exception {
        return authService.createStudyset(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.GET_STUDYSET_BY_ID)
    public BaseResponse getStudysetById(@Valid @RequestBody GetStudysetByIdRequestDto request) throws Exception {
        return authService.getStudysetById(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.ADD_OR_UPDATE_EXAMINATION)
    public BaseResponse addOrUpdateExaminationRequestDto(@Valid @RequestBody AddOrUpdateExaminationRequestDto request) throws Exception {
        return authService.addOrUpdateExaminationRequestDto(request);
    }
}
