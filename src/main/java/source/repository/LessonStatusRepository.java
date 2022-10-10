package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.LessonStatus;

@Repository
public interface LessonStatusRepository extends JpaRepository<LessonStatus, String> {
}
