package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import source.entity.Lesson;
import source.entity.LessonExercise;
import source.entity.LessonExerciseStatus;
import source.entity.LessonStatus;

@Repository
public interface LessonExerciseStatusRepository extends JpaRepository<LessonExerciseStatus, String> {
    public LessonExerciseStatus getByUserId(@Param("userId") String userId);

    public LessonExerciseStatus getByUserIdAndAndLessonExercise(@Param("userId") String userId, @Param("lessonExercise") LessonExercise lessonExercise);
}
