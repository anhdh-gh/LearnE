package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Course;
import source.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String> {
}
