package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.LessonQuestion;

@Repository
public interface LessonQuestionRepository extends JpaRepository<LessonQuestion, String> {
}