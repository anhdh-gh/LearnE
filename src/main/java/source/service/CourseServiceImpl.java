package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import source.dto.request.create_course.CreateCourseRequestDto;
import source.dto.request.GetCourseDetailForUserRequestDto;
import source.dto.request.create_course.LessonQuestionDto;
import source.dto.response.BaseResponse;
import source.entity.Course;
import source.exception.BusinessError;
import source.exception.BusinessErrors;
import source.repository.*;
import source.third_party.question_bank.dto.request.CreateListQuestionsRequestDto;
import source.third_party.question_bank.dto.request.QuestionGetByIdsRequestDto;
import source.third_party.question_bank.service.QuestionBankThirdPartyService;
import source.util.JsonUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

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

    // ------------------------------------------
    @Autowired
    private QuestionBankThirdPartyService questionBankThirdPartyService;

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        // Lấy ra list các LessonQuestion
        List<LessonQuestionDto> lessonQuestionDtosCreate = new ArrayList<>();
        List<LessonQuestionDto> lessonQuestionDtosExist = new ArrayList<>();
        if(request.getChapters() != null) {
            request.getChapters().forEach(chapterDto -> {
                if(chapterDto.getLessons() != null) {
                    chapterDto.getLessons().forEach(lessonDto -> {
                        if(lessonDto.getLessonExercises() != null) {
                            lessonDto.getLessonExercises().forEach(lessonExercise -> {
                                if(lessonExercise.getLessonQuestions() != null) {
                                    lessonExercise.getLessonQuestions().forEach(lessonQuestionDto -> {
                                        if(lessonQuestionDto.getQuestionId() == null) {
                                            lessonQuestionDto.setQuestionId(UUID.randomUUID().toString());
                                            lessonQuestionDtosCreate.add(lessonQuestionDto);
                                        } else {
                                            lessonQuestionDtosExist.add(lessonQuestionDto);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        }

        if(!lessonQuestionDtosExist.isEmpty()) {
            BaseResponse baseResponse = questionBankThirdPartyService.getQuestionByQuestionIds(
                QuestionGetByIdsRequestDto
                    .builder()
                    .requestId(request.getRequestId())
                    .questionIds(lessonQuestionDtosExist.stream().map(LessonQuestionDto::getQuestionId).collect(Collectors.toSet()))
                    .build()
            );
            if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS);
            }
        }

        if(!lessonQuestionDtosCreate.isEmpty()) {
            BaseResponse baseResponse = questionBankThirdPartyService.createQuestionsList(
                CreateListQuestionsRequestDto
                    .builder()
                    .requestId(request.getRequestId())
                    .questions(lessonQuestionDtosCreate)
                    .build()
            );
            if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS);
            }
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