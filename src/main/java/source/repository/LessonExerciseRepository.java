package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import source.entity.LessonExercise;

import java.util.Collection;
import java.util.List;

@Repository
public interface LessonExerciseRepository extends JpaRepository<LessonExercise, String> {

    @Query(value =
        "select * from lessonexercise " +
        "inner join lessonquestion on lessonquestion.LessonExerciseId = lessonexercise.Id " +
        "where lessonquestion.QuestionId in :questionIds"
    , nativeQuery = true)
    List<LessonExercise> findAllByQuestionIds(List<String> questionIds);

    @Query(value =
        "delete from lessonexercise " +
        "where lessonexercise.Id in :ids"
    , nativeQuery = true)
    void deleteByIdIn(Collection<Long> ids);

    List<LessonExercise> findAllByReferenceId(String referenceId);
}
