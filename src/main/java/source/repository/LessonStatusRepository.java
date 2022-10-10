package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import source.entity.LessonStatus;

import java.util.Optional;

@Repository
public interface LessonStatusRepository extends JpaRepository<LessonStatus, String> {

    @Query(value = "select count(distinct UserId) FROM lessonstatus", nativeQuery = true)
    Long countDistinctUserId();

    Optional<LessonStatus> findLessonStatusByUserIdAndLessonId(String userId, String lessonId);
}
