package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import source.entity.LessonQuestionHistory;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface LessonQuestionHistoryRepository extends JpaRepository<LessonQuestionHistory, String> {

    @Query(
        value = "SELECT score FROM lessonquestionhistory lqh WHERE lqh.UserId = ?1 and lqh.LessonQuestionId = ?2",
        nativeQuery = true
    )
    public Float findLessonQuestionHistoriesByUserIdAndLessonQuestionId(String userId, String lessonQuestionId);

    @Query(
        value = "select if(count(*) > 0, 'true', 'false') from answerchoice ac inner join lessonquestionhistory lqh" +
                " on ac.LessonQuestionHistoryId = lqh.Id and lqh.LessonQuestionId = ?1 and ac.Id = ?2",
        nativeQuery = true
    )
    boolean checkAnswerOfUser(String lessonQuestionId, String AnswerId);
}