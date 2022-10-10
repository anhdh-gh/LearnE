package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.LessonExerciseStatus;
import source.entity.LessonStatus;

import java.util.Optional;

@Repository
public interface LessonExerciseStatusRepository extends JpaRepository<LessonExerciseStatus, String> {

    Optional<LessonExerciseStatus> findLessonExerciseStatusByUserIdAndLessonExerciseId(String userId, String lessonExerciseId);
}
