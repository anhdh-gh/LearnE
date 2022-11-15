package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import source.constant.ErrorCodeConstant;
import source.dto.request.*;
import source.dto.request.CreateCourseRequestDto;
import source.dto.LessonQuestionDto;
import source.dto.response.BaseResponse;
import source.dto.response.UpdateLessonStatusResponseDto;
import source.dto.response.get_course_detail_for_user.*;
import source.entity.*;
import source.entity.enumeration.StatusType;
import source.exception.BusinessErrors;
import source.repository.*;
import source.third_party.question_bank.dto.request.CreateListQuestionsRequestDto;
import source.third_party.question_bank.dto.request.QuestionGetByIdsRequestDto;
import source.third_party.question_bank.service.QuestionBankThirdPartyService;
import source.third_party.user.dto.request.UserGetByIdRequestDto;
import source.third_party.user.service.UserServiceThirdParty;
import source.util.JsonUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Environment env;

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

    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

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
    public BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception {
        // Lấy ra list theo paging and sorting
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("updateTime").descending().and(Sort.by("createTime").descending())
        );
        Page<Course> coursesPage = courseRepository.findAll(pageRequest);

        // Convert sang DTO
        Page<GetCourseDetailForUserResponseDto> response = coursesPage.map(course -> {
            GetCourseDetailForUserResponseDto responseDto = modelMapper.map(course, GetCourseDetailForUserResponseDto.class);
            responseDto.setNumberOfPeople(lessonStatusRepository.countDistinctUserId(course.getId()));
            responseDto.setStatus(StatusType.UNFINISHED);
            if(request.getUserId() != null) {
                // Kiểm tra user xem có tồn tại hay không
                try {
                    BaseResponse responseGetUserById = getUserById(request, request.getUserId());
                    if(responseGetUserById.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                        Long countStatus = lessonStatusRepository.countByCourseIdAndUserId(course.getId(), request.getUserId());
                        responseDto.setStatus(
                            countStatus == null || countStatus == 0
                                ? StatusType.UNFINISHED
                                : countStatus == lessonRepository.countByCourseId(course.getId())
                                    ? StatusType.FINISHED
                                    : StatusType.PROCESSING
                        );
                    }
                } catch (Exception ex) {
                    BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INTERNAL_SERVER_ERROR, ex.getMessage());
                }
            }
            return responseDto;
        });

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getRequestId(), response);
    }

    @Override
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        if(!courseOptional.isPresent()) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, env.getProperty(ErrorCodeConstant.COURSE_NOT_FOUND_400033));
        }

        GetCourseDetailForUserResponseDto response = modelMapper.map(courseOptional.get(), GetCourseDetailForUserResponseDto.class);

        // Set infor for course
        response.setNumberOfPeople(lessonStatusRepository.countDistinctUserId(courseOptional.get().getId()));

        // Kiểm tra user xem có tồn tại hay không
        if(request.getUserId() != null) {
            BaseResponse responseGetUserById = getUserById(request, request.getUserId());
            if(!responseGetUserById.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                return responseGetUserById;
            }
        }

        // Set info for chapterDtos
        response.setChapterDtos(mapList(response.getChapters(), ChapterDto.class));
        List<ChapterDto> chapterDtos = response.getChapterDtos();

        // Set info for lessonDtos
        List<Lesson> lessons = new ArrayList<>();
        List<LessonDto> lessonDtos = new ArrayList<>();
        for(int i = 0 ; i < chapterDtos.size() ; i++) {
            chapterDtos.get(i).setLessonDtos(mapList(response.getChapters().get(i).getLessons(), LessonDto.class));
            lessonDtos.addAll(chapterDtos.get(i).getLessonDtos());
            lessons.addAll(response.getChapters().get(i).getLessons());
        }

        if(request.getUserId() != null) {
            lessonDtos.forEach(lessonDto -> {
                Optional<LessonStatus> lessonStatus = lessonStatusRepository.findLessonStatusByUserIdAndLessonId(request.getUserId(), lessonDto.getId());
                if(lessonStatus.isPresent()) {
                    lessonDto.setStatus(lessonStatus.get().getStatus());
                } else {
                    lessonDto.setStatus(StatusType.UNFINISHED);
                }
            });
        }


        // Set info for lessonExercises
        List<LessonExercise> lessonExercises = new ArrayList<>();
        List<LessonExerciseDto> lessonExerciseDtos = new ArrayList<>();
        for(int i = 0 ; i < lessonDtos.size() ; i++) {
            lessonDtos.get(i).setLessonExerciseDtos(mapList(lessons.get(i).getLessonExercises(), LessonExerciseDto.class));
            lessonExerciseDtos.addAll(lessonDtos.get(i).getLessonExerciseDtos());
            lessonExercises.addAll(lessons.get(i).getLessonExercises());
        }
        if(request.getUserId() != null) {
            lessonExerciseDtos.forEach(lessonExerciseDto -> {
                Optional<LessonExerciseStatus> lessonExerciseStatus = lessonExerciseStatusRepository.findLessonExerciseStatusByUserIdAndLessonExerciseId(request.getUserId(), lessonExerciseDto.getId());
                if(lessonExerciseStatus.isPresent()) {
                    lessonExerciseDto.setStatus(lessonExerciseStatus.get().getStatus());
                } else {
                    lessonExerciseDto.setStatus(StatusType.UNFINISHED);
                }
            });
        }

        // Set info for lessonQuestions
        List<source.dto.response.get_course_detail_for_user.LessonQuestionDto> lessonQuestionDtos = new ArrayList<>();
        for(int i = 0 ; i < lessonExerciseDtos.size() ; i++) {
            lessonExerciseDtos.get(i).setLessonQuestionDtos(mapList(lessonExercises.get(i).getLessonQuestions(), source.dto.response.get_course_detail_for_user.LessonQuestionDto.class));
            lessonQuestionDtos.addAll(lessonExerciseDtos.get(i).getLessonQuestionDtos());
        }
        Set<String> questionIds = lessonQuestionDtos.stream().map(LessonQuestion::getQuestionId).collect(Collectors.toSet());
        BaseResponse baseResponse = questionBankThirdPartyService.getQuestionByQuestionIds(
            QuestionGetByIdsRequestDto
            .builder()
            .requestId(request.getRequestId())
            .questionIds(questionIds)
            .build()
        );
        if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INTERNAL_SERVER_ERROR, env.getProperty(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031));
        }
        List questionBanksRaw = JsonUtil.getGenericObject(baseResponse.getData(), List.class);
        Map<String, QuestionDto> map = new HashMap<>();
        questionBanksRaw.forEach(questionBankRaw -> {
            QuestionDto questionDto = JsonUtil.getGenericObject(questionBankRaw, QuestionDto.class);
            map.put(questionDto.getId(), questionDto);
        });
        lessonQuestionDtos.forEach(lessonQuestionDto -> {
            lessonQuestionDto.setQuestion(map.get(lessonQuestionDto.getQuestionId()));

            if(request.getUserId() != null) {
                Float score = lessonQuestionHistoryRepository.findLessonQuestionHistoriesByUserIdAndLessonQuestionId(request.getUserId(), lessonQuestionDto.getId());
                lessonQuestionDto.getQuestion().setScore(score != null ? score : 0);

                lessonQuestionDto.getQuestion().getAnswers()
                    .forEach(answerDto -> answerDto
                        .setIsChoice(lessonQuestionHistoryRepository
                            .checkAnswerOfUser(lessonQuestionDto.getId(), answerDto.getId())));
            }
        });

        return BaseResponse.ofSucceeded(request.getRequestId(), response);
    }

    @Override
    public BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        if(!courseOptional.isPresent()) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, env.getProperty(ErrorCodeConstant.COURSE_NOT_FOUND_400033));
        }

        GetCourseDetailForUserResponseDto response = modelMapper.map(courseOptional.get(), GetCourseDetailForUserResponseDto.class);

        // Set info for chapterDtos
        response.setChapterDtos(mapList(response.getChapters(), ChapterDto.class));
        List<ChapterDto> chapterDtos = response.getChapterDtos();

        // Set info for lessonDtos
        List<Lesson> lessons = new ArrayList<>();
        List<LessonDto> lessonDtos = new ArrayList<>();
        for(int i = 0 ; i < chapterDtos.size() ; i++) {
            chapterDtos.get(i).setLessonDtos(mapList(response.getChapters().get(i).getLessons(), LessonDto.class));
            lessonDtos.addAll(chapterDtos.get(i).getLessonDtos());
            lessons.addAll(response.getChapters().get(i).getLessons());
        }

        // Set info for lessonExercises
        List<LessonExercise> lessonExercises = new ArrayList<>();
        List<LessonExerciseDto> lessonExerciseDtos = new ArrayList<>();
        for(int i = 0 ; i < lessonDtos.size() ; i++) {
            lessonDtos.get(i).setLessonExerciseDtos(mapList(lessons.get(i).getLessonExercises(), LessonExerciseDto.class));
            lessonExerciseDtos.addAll(lessonDtos.get(i).getLessonExerciseDtos());
            lessonExercises.addAll(lessons.get(i).getLessonExercises());
        }

        // Set info for lessonQuestions
        List<source.dto.response.get_course_detail_for_user.LessonQuestionDto> lessonQuestionDtos = new ArrayList<>();
        for(int i = 0 ; i < lessonExerciseDtos.size() ; i++) {
            lessonExerciseDtos.get(i).setLessonQuestionDtos(mapList(lessonExercises.get(i).getLessonQuestions(), source.dto.response.get_course_detail_for_user.LessonQuestionDto.class));
            lessonQuestionDtos.addAll(lessonExerciseDtos.get(i).getLessonQuestionDtos());
        }
        Set<String> questionIds = lessonQuestionDtos.stream().map(LessonQuestion::getQuestionId).collect(Collectors.toSet());
        BaseResponse baseResponse = questionBankThirdPartyService.getQuestionByQuestionIds(
            QuestionGetByIdsRequestDto
                .builder()
                .requestId(request.getRequestId())
                .questionIds(questionIds)
                .build()
        );
        if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INTERNAL_SERVER_ERROR, env.getProperty(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031));
        }
        List questionBanksRaw = JsonUtil.getGenericObject(baseResponse.getData(), List.class);
        Map<String, QuestionDto> map = new HashMap<>();
        questionBanksRaw.forEach(questionBankRaw -> {
            QuestionDto questionDto = JsonUtil.getGenericObject(questionBankRaw, QuestionDto.class);
            map.put(questionDto.getId(), questionDto);
        });
        lessonQuestionDtos.forEach(lessonQuestionDto -> {
            lessonQuestionDto.setQuestion(map.get(lessonQuestionDto.getQuestionId()));
        });

        return BaseResponse.ofSucceeded(request.getRequestId(), response);
    }

    @Override
    public BaseResponse deleteCourseById(DeleteCourseByIdRequestDto request) throws Exception {
        // Kiểm tra course có tồn tại hay không
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        if(!courseOptional.isPresent()) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, env.getProperty(ErrorCodeConstant.COURSE_NOT_FOUND_400033));
        }

        // Thực hiện delete
        courseRepository.delete(courseOptional.get());

        // Trả về kết quả
        return BaseResponse.ofSucceeded(
            request.getRequestId(),
            courseOptional.get()
        );
    }

    @Override
    public BaseResponse updateCourse(UpdateCourseRequestDto request) throws Exception {
        // Kiểm tra course có tồn tại hay không
        Optional<Course> courseOptional = courseRepository.findById(request.getId());
        if(!courseOptional.isPresent()) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, env.getProperty(ErrorCodeConstant.COURSE_NOT_FOUND_400033));
        }

        // Prepare để tạo mới course
        CreateCourseRequestDto createCourseRequestDto = CreateCourseRequestDto.builder().build();
        modelMapper.map(courseOptional.get(), createCourseRequestDto);
        modelMapper.map(request, createCourseRequestDto);

        // Xóa course cũ
        courseRepository.delete(courseOptional.get());

        return this.createCourse(createCourseRequestDto);
    }

    @Override
    public BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception {
        // Kiểm tra xem lessonId có tồn tại hay không
        Optional<Lesson> optionalLesson = lessonRepository.findById(request.getLessonId());
        if(!optionalLesson.isPresent()) {
            return BaseResponse
                .ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, env.getProperty(ErrorCodeConstant.LESSON_IS_NOT_FOUND_400034));
        }

        // Kiểm tra user xem có tồn tại hay không
        if(request.getUserId() != null) {
            BaseResponse responseGetUserById = getUserById(request, request.getUserId());
            if(!responseGetUserById.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                return responseGetUserById;
            }
        }

        // Thực hiện update status lesson
        Optional<LessonStatus> optionalLessonStatus = lessonStatusRepository.findLessonStatusByUserIdAndLessonId(request.getUserId(), request.getLessonId());
        if(optionalLessonStatus.isPresent()) {
            LessonStatus lessonStatusUpdate = optionalLessonStatus.get();
            lessonStatusUpdate.setStatus(request.getStatus());
            lessonStatusRepository.save(lessonStatusUpdate);
            return BaseResponse.ofSucceeded(
                request.getRequestId(), modelMapper.map(request, UpdateLessonStatusResponseDto.class));
        } else {
            LessonStatus lessonStatusCreate = LessonStatus
                .builder()
                .lesson(optionalLesson.get())
                .status(request.getStatus())
                .userId(request.getUserId())
                .build();
            lessonStatusRepository.save(lessonStatusCreate);
            return BaseResponse.ofSucceeded(
                request.getRequestId(), modelMapper.map(request, UpdateLessonStatusResponseDto.class));
        }
    }

    @Override
    public BaseResponse callBackQuestionsDelete(CallBackQuestionsDeleteRequestDto request) throws Exception {
        // Lấy ra các bản ghi
        List<LessonExercise> lessonExercises = lessonExerciseRepository.findAllByQuestionIds(
            new ArrayList<>(request.getQuestionIds())
        );

        // Thực hiện xóa
        if(lessonExercises != null && !lessonExercises.isEmpty()) {
            lessonExerciseRepository.deleteByIdIn(lessonExercises.stream().map(AutoIncrementIdBaseEntity::getId).collect(Collectors.toList()));
        }

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getRequestId(), "Callback successfully");
    }

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }

    private BaseResponse getUserById(BasicRequest request, String userId) throws Exception {
        // Kiểm tra user xem có tồn tại hay không
        BaseResponse baseResponse = userServiceThirdParty.getUserById(UserGetByIdRequestDto
            .builder()
            .requestId(request.getRequestId())
            .id(userId)
            .build());

        // Trả về kết quả
        if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, env.getProperty(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011));
        }

        return baseResponse;
    }
}