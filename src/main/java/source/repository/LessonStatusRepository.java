package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import source.entity.LessonStatus;

import java.util.Optional;

@Repository
public interface LessonStatusRepository extends JpaRepository<LessonStatus, String> {

    @Query(value = 
        "select count(distinct UserId) from lessonstatus " +
        "inner join lesson on lesson.Id = lessonstatus.LessonId " +
        "inner join chapter on chapter.Id = lesson.ChapterId " +
        "inner join course on course.Id = chapter.CourseId " +
        "where course.Id = ?1", nativeQuery = true)
    Long countDistinctUserId(String courseId);

    Optional<LessonStatus> findLessonStatusByUserIdAndLessonId(String userId, Long lessonId);
}
