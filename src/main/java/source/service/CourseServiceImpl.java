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
import source.dto.response.BaseDto;
import source.dto.response.BaseResponse;
import source.dto.response.get_course_detail_for_user.GetCourseDetailForUserResponseDto;
import source.dto.response.UpdateLessonStatusResponseDto;
import source.dto.response.get_course_detail_for_user.*;
import source.entity.*;
import source.entity.enumeration.Provider;
import source.entity.enumeration.StatusType;
import source.exception.BusinessErrors;
import source.repository.*;
import source.third_party.question_bank.dto.request.QuestionGetByIdsRequestDto;
import source.third_party.question_bank.service.QuestionBankThirdPartyService;
import source.third_party.studyset.dto.request.StudysetGetByIdsRequestDto;
import source.third_party.studyset.service.StudysetThirdPartyService;
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

    @Autowired
    private LessonExerciseRepository lessonExerciseRepository;

    @Autowired
    private QuestionBankThirdPartyService questionBankThirdPartyService;

    @Autowired
    private StudysetThirdPartyService studysetThirdPartyService;

    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        // Lấy ra list các id của studyset và question
        List<String> referenceIdsStudyset = new ArrayList<>();
        List<String> referenceIdsQuestion = new ArrayList<>();
        if(request.getChapters() != null) {
            request.getChapters().forEach(chapterDto -> {
                if(chapterDto.getLessons() != null) {
                    chapterDto.getLessons().forEach(lessonDto -> {
                        if(lessonDto.getLessonExercises() != null) {
                            lessonDto.getLessonExercises().forEach(lessonExercise -> {
                                if(lessonExercise.getProvider().getValue().equals(Provider.QUESTION_BANK.getValue())) {
                                    referenceIdsQuestion.add(lessonExercise.getReferenceId());
                                } else if(lessonExercise.getProvider().getValue().equals(Provider.STUDYSET.getValue())) {
                                    referenceIdsStudyset.add(lessonExercise.getReferenceId());
                                }
                            });
                        }
                    });
                }
            });
        }

        // Kiểm tra question có tồn tại hay không
        if(!referenceIdsQuestion.isEmpty()) {
            BaseResponse baseResponse = questionBankThirdPartyService.getQuestionByQuestionIds(
                QuestionGetByIdsRequestDto
                    .builder()
                    .requestId(request.getRequestId())
                    .questionIds(new HashSet<>(referenceIdsQuestion))
                    .build()
            );
            if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS);
            }
        }

        // Kiểm tra studyset có tồn tại hay không
        if(!referenceIdsStudyset.isEmpty()) {
            BaseResponse baseResponse = studysetThirdPartyService.getStudysetByStudysetIds(
                StudysetGetByIdsRequestDto
                    .builder()
                    .requestId(request.getRequestId())
                    .studysetIds(new HashSet<>(referenceIdsStudyset))
                    .build()
            );
            if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
                return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS);
            }
        }

        // Thực hiện save
        Course courseSave = modelMapper.map(request, Course.class);
        courseSave = courseRepository.save(courseSave);

        // Trả về kết quả
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
    public BaseResponse getCourseDetailForUser(GetCourseDetailForUserRequestDto request) throws Exception {
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

        // Get question and studyset
        Set<String> questionIds = new HashSet<>();
        Set<String> studysetIds = new HashSet<>();
        for(LessonExercise lessonExercise: lessonExercises) {
            if(lessonExercise.getProvider().getValue().equals(Provider.QUESTION_BANK.getValue())) {
                questionIds.add(lessonExercise.getReferenceId());
            } else if(lessonExercise.getProvider().getValue().equals(Provider.STUDYSET.getValue())) {
                studysetIds.add(lessonExercise.getReferenceId());
            }
        }

        // Lấy ra list question by ids
        BaseResponse baseResponseQuestion = questionBankThirdPartyService.getQuestionByQuestionIds(
            QuestionGetByIdsRequestDto
            .builder()
            .requestId(request.getRequestId())
            .userId(request.getUserId())
            .questionIds(questionIds)
            .build()
        );
        if(!baseResponseQuestion.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INTERNAL_SERVER_ERROR, env.getProperty(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031));
        }

        List questionBanksRaw = JsonUtil.getGenericObject(baseResponseQuestion.getData(), List.class);
        Map<String, Object> mapQuestion = new HashMap<>();
        questionBanksRaw.forEach(questionBankRaw -> {
            BaseDto questionDto = JsonUtil.getGenericObject(questionBankRaw, BaseDto.class);
            mapQuestion.put(questionDto.getId(), questionBankRaw);
        });

        // Lấy ra list studyset by ids
        BaseResponse baseResponseStudyset = studysetThirdPartyService.getStudysetByStudysetIds(
            StudysetGetByIdsRequestDto
                .builder()
                .requestId(request.getRequestId())
                .userId(request.getUserId())
                .studysetIds(studysetIds)
                .build()
        );
        if(!baseResponseStudyset.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INTERNAL_SERVER_ERROR, env.getProperty(ErrorCodeConstant.STUDYSET_NOT_FOUND_400032));
        }

        List studysetsRaw = JsonUtil.getGenericObject(baseResponseStudyset.getData(), List.class);
        Map<String, Object> mapStudyset = new HashMap<>();
        studysetsRaw.forEach(studysetRaw -> {
            BaseDto studysetDto = JsonUtil.getGenericObject(studysetRaw, BaseDto.class);
            mapStudyset.put(studysetDto.getId(), studysetRaw);
        });

        // Gán vào lessonExerciseDtos
        for(LessonExerciseDto lessonExerciseDto: lessonExerciseDtos) {
            if(lessonExerciseDto.getProvider().getValue().equals(Provider.QUESTION_BANK.getValue())) {
                lessonExerciseDto.setQuestion(mapQuestion.get(lessonExerciseDto.getReferenceId()));
            } else if(lessonExerciseDto.getProvider().getValue().equals(Provider.STUDYSET.getValue())) {
                lessonExerciseDto.setQuestion(mapStudyset.get(lessonExerciseDto.getReferenceId()));
            }
        }

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

        // Xóa course cũ
        courseRepository.delete(courseOptional.get());

        // Prepare để tạo mới course
        CreateCourseRequestDto createCourseRequestDto = CreateCourseRequestDto.builder().id(courseOptional.get().getId()).build();
        modelMapper.map(courseOptional.get(), createCourseRequestDto);
        modelMapper.map(request, createCourseRequestDto);

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
    public BaseResponse callBackQuestionDelete(CallBackQuestionDeleteRequestDto request) throws Exception {
        // Lấy ra các bản ghi
        List<LessonExercise> lessonExercises = lessonExerciseRepository.findAllByReferenceIdAndProvider(
            request.getReferenceId(), request.getProvider());

        // Thực hiện xóa
        if(lessonExercises != null && !lessonExercises.isEmpty()) {
            lessonExerciseRepository.deleteByIdIn(lessonExercises.stream().map(AutoIncrementIdBaseEntity::getId).collect(Collectors.toList()));
        }

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getRequestId(), "Callback successfully");
    }

    @Override
    public BaseResponse searchCourse(SearchCourseRequestDto request) throws Exception {
        // Lấy ra list theo paging and sorting
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("updateTime").descending().and(Sort.by("createTime").descending())
        );
        Page<Course> coursesPage = courseRepository.findAllByNameContainingIgnoreCase(request.getName(), pageRequest);

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