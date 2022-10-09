package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.LessonExerciseStatus;

@Repository
public interface LessonExerciseStatusRepository extends JpaRepository<LessonExerciseStatus, String> {
}