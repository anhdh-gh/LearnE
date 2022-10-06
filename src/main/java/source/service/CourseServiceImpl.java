package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.CourseDto;
import source.dto.LessonDto;
import source.dto.LessonQuestionDto;
import source.dto.QuestionDto;
import source.dto.request.CreateCourseRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.response.BaseResponse;
import source.entity.*;
import source.entity.enumeration.StatusType;
import source.exception.BusinessException;
import source.repository.*;
import source.third_party.service.QuestionBankThirdPartyService;
import source.util.JsonUtil;

import java.math.BigInteger;
import java.util.*;

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
        if (responseFormQuestionBankService.getMeta().getCode() == errorCode) {
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }

        Course courseSave = modelMapper.map(request, Course.class);
        courseSave = courseRepository.save(courseSave);

        return BaseResponse.ofSucceeded(request.getRequestId(), courseSave);
    }

    @Override
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        String userId = request.getUserId();
        if (!courseOptional.isPresent()) {
            int errorCode = Integer.parseInt(ErrorCodeConstant.COURSE_NOT_FOUND_400033);
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }

        Course course = courseOptional.get();
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        Long numberOfPeople = lessonStatusRepository.getDistinctCountByUserId();
        courseDto.setNumberOfPeople(numberOfPeople);

        // init ( create list lessonDtos, lessonQuestionDtos, questionIds )
        List<LessonDto> lessonDtos = new ArrayList<>();
        List<LessonQuestionDto> lessonQuestionDtos = new ArrayList<>();
        List<String> questionIds = new ArrayList<>();
        courseDto.getChapters().forEach(
            chapterDto -> chapterDto.getLessons().forEach(
                lessonDto -> {
                    lessonDtos.add(lessonDto);
                    lessonDto.getLessonExercises().forEach(
                        lessonExerciseDto -> {
                            lessonExerciseDto.getLessonQuestions().forEach(
                                lessonQuestionDto -> {
                                    lessonQuestionDtos.add(lessonQuestionDto);
                                    String questionId = lessonQuestionDto.getQuestionId();
                                    questionIds.add(questionId);
                                }
                            );
                        }
                    );
                }
            )
        );

        // call api get question by ids
        BaseResponse responseFormQuestionBankService = questionBankThirdPartyService.getQuestionsByIds(questionIds);
        int errorCode = Integer.parseInt(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031);
        if (responseFormQuestionBankService.getMeta().getCode() != 200) {
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }

        //extract response to question dto
        List questionsRaw = JsonUtil.getGenericObject(responseFormQuestionBankService.getData(),List.class);
        List<QuestionDto> questionDtos = new ArrayList<>();
        questionsRaw.forEach(
            o -> {
                questionDtos.add(JsonUtil.getGenericObject(o, QuestionDto.class));
            }
        );

        // set questionDto to lessonQuestionDto
        Map<String, QuestionDto> mapQuestion = new HashMap<>();
        questionDtos.forEach(questionDto ->
            mapQuestion.put(questionDto.getId(), questionDto)
        );
        lessonQuestionDtos.forEach(lessonQuestionDto -> {
            lessonQuestionDto.setQuestion(mapQuestion.get(lessonQuestionDto.getQuestionId()));
        });



        // check user is exist
        if (userId == null) {
            return BaseResponse.ofSucceeded(request.getRequestId(), courseDto);
        }
        // check user has in lesson
        if (lessonStatusRepository.getByUserId(userId) == null) {
            return BaseResponse.ofSucceeded(request.getRequestId(), courseDto);
        }
        // set status of user in lesson

        lessonDtos.forEach(
            lessonDto -> {
                Lesson lesson = modelMapper.map(lessonDto, Lesson.class);
                System.out.println("do some thing");
                LessonStatus lessonStatus =  lessonStatusRepository.getByUserIdAndAndLesson(userId, lesson);
                if(!Objects.isNull(lessonStatus)){
                    lessonDto.setStatus(lessonStatus.getStatus());
                 }
                }
        );
        // check user has do exercise
        if(lessonExerciseStatusRepository.getByUserId(userId) == null){
            return BaseResponse.ofSucceeded(request.getRequestId(), courseDto);
        }

        // set status of user in exercise
        lessonDtos.forEach(lessonDto -> {
            lessonDto.getLessonExercises().forEach(
                lessonExerciseDto -> {
                    LessonExercise lessonExercise = modelMapper.map(lessonExerciseDto, LessonExercise.class);
                    LessonExerciseStatus lessonExerciseStatus = lessonExerciseStatusRepository.getByUserIdAndAndLessonExercise(userId, lessonExercise);
                    if(lessonExerciseStatus != null){
                        lessonExerciseDto.setStatus(lessonExerciseStatus.getStatus());
                    }

                }
            );
        });

        // set questionDto to lessonQuestionDto and mark score and check answer belong user or not
        lessonQuestionDtos.forEach(
            lessonQuestionDto -> {
                // todo: đoạn này cần check thêm xem user đã làm question chưa
               Float score = lessonQuestionHistoryRepository.findScoreByUserIdAndLessonQuestionIdNative(userId, lessonQuestionDto.getId());
                QuestionDto questionDto = lessonQuestionDto.getQuestion();
                if(Objects.isNull(score)) {
                    return;
                }
                questionDto.setScore(score);
                questionDto.getAnswers().forEach(
                    answerDto -> {
                        BigInteger isChoice = lessonQuestionHistoryRepository.checkAnswerOfUser(lessonQuestionDto.getId(),answerDto.getId());
                        answerDto.setChoice(isChoice.compareTo(BigInteger.ONE) == 0);
                    }
                );
            }
        );


        return BaseResponse.ofSucceeded(request.getRequestId(), courseDto);
    }

}