package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.LessonExercise;

@Repository
public interface LessonExerciseRepository extends JpaRepository<LessonExercise, String> {
}
