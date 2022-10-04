package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.request.CreateCourseRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.response.BaseResponse;
import source.entity.Chapter;
import source.entity.Course;
import source.entity.Lesson;
import source.exception.BusinessException;
import source.repository.*;
import source.third_party.service.QuestionBankThirdPartyService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private Environment environment;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionBankThirdPartyService questionBankThirdPartyService;

    @Autowired
    private ModelMapper modelMapper;

    // ------------------------------------------
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonStatusRepository lessonStatusRepository;

    // ------------------------------------------
    @Autowired
    private LessonExerciseRepository lessonExerciseRepository;

    @Autowired
    private LessonExerciseStatusRepository lessonExerciseStatusRepository;

    // ------------------------------------------
    @Autowired
    private LessonQuestionRepository questionRepository;

    @Autowired
    private LessonQuestionHistoryRepository lessonQuestionHistoryRepository;

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        List<String> questionIds = new ArrayList<>();
        request.getChapters().forEach(
            chapter -> chapter.getLessons().forEach(
                lesson -> lesson.getLessonExercises().forEach(
                    lessonExercise -> lessonExercise.getLessonQuestions().forEach(
                        lessonQuestion -> questionIds.add(lessonQuestion.getQuestionId())
                    )
                )
            )
        );

        BaseResponse responseFormQuestionBankService = questionBankThirdPartyService.getQuestionsByIds(questionIds);
        int errorCode = Integer.parseInt(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031);
        if(responseFormQuestionBankService.getMeta().getCode() == errorCode){
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }

        Course courseSave = modelMapper.map(request, Course.class);
        courseSave = courseRepository.save(courseSave);

        return BaseResponse.ofSucceeded(request.getRequestId(), courseSave);
    }

    @Override
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        return null;
    }
}