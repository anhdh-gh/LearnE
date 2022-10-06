package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import source.entity.Lesson;
import source.entity.LessonStatus;

import java.util.List;

@Repository
public interface LessonStatusRepository extends JpaRepository<LessonStatus, String> {

    public List<LessonStatus> getByUserId(@Param("userId") String userId);

    public LessonStatus getByUserIdAndAndLesson(@Param("userId") String userId, @Param("lesson") Lesson lesson);

    @Query("SELECT COUNT (DISTINCT userId) FROM LessonStatus")
    public long getDistinctCountByUserId();
}
