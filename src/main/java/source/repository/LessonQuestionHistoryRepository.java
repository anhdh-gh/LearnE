package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.LessonQuestionHistory;

@Repository
public interface LessonQuestionHistoryRepository extends JpaRepository<LessonQuestionHistory, String> {
}