package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.CreateCourseRequestDto;
import source.dto.request.GetCourseByCourseIdAndUserIdRequestDto;
import source.dto.response.BaseResponse;
import source.entity.Course;
import source.repository.*;

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

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        Course courseSave = modelMapper.map(request, Course.class);

        courseSave = courseRepository.save(courseSave);

        // Cần gọi API để kiểm tra các questionId trong LessonQuestion có tồn tại trong Database QuestionBank không?

        return BaseResponse.ofSucceeded(request.getRequestId(), courseSave);
    }

    @Override
    public BaseResponse getCourseByCourseIdAndUserIdRequestDto(GetCourseByCourseIdAndUserIdRequestDto request) throws Exception {
        return null;
    }
}