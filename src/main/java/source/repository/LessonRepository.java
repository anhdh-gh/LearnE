package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import source.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query(value =
        "select count(*) from lesson " +
        "inner join chapter on chapter.Id = lesson.ChapterId " +
        "inner join course on course.Id = chapter.CourseId " +
        "where course.Id = ?1", nativeQuery = true)
    Long countByCourseId(String courseId);
}
