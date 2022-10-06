package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.UserGetUserInformationRequestDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.service.auth.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(RouterConstant.SIGN_UP)
    public BaseResponse signUp(@Valid @RequestBody UserSignUpRequestDto request) throws Exception {
        return authService.signUp(request);
    }

    @PostMapping(RouterConstant.SIGN_IN)
    public BaseResponse signIn(@Valid @RequestBody UserSignInRequestDto request) throws Exception {
        return authService.signIn(request);
    }

    @PostMapping(RouterConstant.GET_ALL_USER)
    public BaseResponse getAllUsers(@Valid @RequestBody UserGetAllRequestDto request) throws Exception {
        return authService.getAllUser(request);
    }

    @PostMapping(RouterConstant.UPDATE_USER)
    public BaseResponse updateUser(@Valid @RequestBody UserUpdateRequestDto request) throws Exception {
        return authService.updateUser(request);
    }

    @PostMapping(RouterConstant.DELETE_USER)
    public BaseResponse deleteUser(@Valid @RequestBody UserDeleteRequestDto request) throws Exception {
        return authService.deleteUser(request);
    }

    @PostMapping(RouterConstant.REFRESH_TOKEN)
    public BaseResponse refreshToken(@Valid @RequestBody UserRefreshToken request) throws Exception {
        return authService.refreshToken(request);
    }

    @PostMapping(RouterConstant.USER_GET_INFO)
    public BaseResponse getUserInformation(@Valid @RequestBody UserGetUserInformationRequestDto request) throws Exception {
        return authService.getUserInformation(request);
    }

    @PostMapping(RouterConstant.USER_GET_BY_ID)
    public BaseResponse getUserById(@Valid @RequestBody UserGetUserByIdRequestDto request) throws Exception {
        return authService.getUserById(request);
    }

    @PostMapping(RouterConstant.ADMIN_DELETE_USER)
    public BaseResponse deleteUserById(@Valid @RequestBody UserDeleteByIdRequestDto request) throws Exception {
        return authService.deleteUserById(request);
    }

    @PostMapping(RouterConstant.QUESTION_CREATE)
    public BaseResponse createQuestion(@RequestBody CreateQuestionRequestDto createQuestionRequestDto) throws Exception {
        return authService.createQuestion(createQuestionRequestDto);
    }

    @PostMapping(RouterConstant.QUESTION_GET_BY_QUESTION_ID)
    public BaseResponse getQuestionByQuestionId(@RequestBody GetQuestionByQuestionIdRequestDto request) throws Exception {
        return authService.getQuestionByQuestionId(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_ALL)
    public BaseResponse getQuestion(@RequestBody QuestionGetAllRequestDto request) throws Exception {
        return authService.getAllQuestion(request);
    }

    @PostMapping(RouterConstant.COURSE_CREATE)
    public BaseResponse createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto) throws Exception {
        return authService.createCourse(createCourseRequestDto);
    }

    @PostMapping(RouterConstant.QUESTION_GET_BY_IDS)
    public BaseResponse getQuestionsByIds(@RequestBody QuestionGetByIdsRequestDto requestDto) throws Exception {
        return authService.getQuestionsByIds(requestDto);
    }

    @PostMapping(RouterConstant.GET_COURSE_DETAIL)
    public BaseResponse getDetailCourse(@RequestBody GetCourseDetailRequestDto requestDto) throws Exception {
        return authService.getCourseDetail(requestDto);
    }
}
