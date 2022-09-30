package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.CreateCourseRequestDto;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;
import source.entity.*;
import source.repository.CourseRepository;
import source.third_party.service.QuestionThirdPartyService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionThirdPartyService questionThirdPartyService;

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) {

        Course courseSave = Course.builder()
            .name(request.getName())
            .author(request.getAuthor())
            .image(request.getImage())
            .numberOfPeople(request.getNumberOfPeople())
            .description(request.getDescription())
            .extraDataList(request.getExtraDataList().stream().map(
                extraDataDto -> ExtraData.builder()
                    .extraDataValue(extraDataDto.getExtraDataValue())
                    .extraDataKey(extraDataDto.getExtraDataKey())
                    .build()).collect(Collectors.toList()))
            .chapters(request.getChapters().stream().map(
                chapterDto -> Chapter.builder()
                    .name(chapterDto.getName())
                    .lessons(chapterDto.getLessons().stream().map(
                        lessonDto -> Lesson.builder()
                            .name(lessonDto.getName())
                            .description(lessonDto.getDescription())
                            .video(lessonDto.getVideo())
                            .lessonExercises(lessonDto.getLessonExercises().stream()
                                .map(lessonExerciseDto -> LessonExercise.builder()
                                    .questionId(lessonExerciseDto.getQuestionId())
                                        .build()).collect(Collectors.toList()))
                            .build()).collect(Collectors.toList()))
                    .build()).collect(Collectors.toList()))
            .build();

        return BaseResponse.ofSucceeded(request.getRequestId(),courseRepository.save(courseSave));
    }

    @Override
    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception {
        return questionThirdPartyService.getQuestionsByIds(requestDto);
    }
}
