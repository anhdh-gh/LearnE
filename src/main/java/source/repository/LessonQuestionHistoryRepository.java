package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import source.entity.LessonQuestionHistory;

import java.math.BigInteger;

@Repository
public interface LessonQuestionHistoryRepository extends JpaRepository<LessonQuestionHistory, String> {

    public LessonQuestionHistory findLessonQuestionHistoriesByUserId(@Param("userId") String userId);

    @Query(
            value = "SELECT score FROM lessonquestionhistory lqh WHERE lqh.UserId = ?1 and lqh.LessonQuestionId = ?2",
            nativeQuery = true)
    public Float findScoreByUserIdAndLessonQuestionIdNative(String userId, String lessonQuestionId);

    @Query(
            value = "select exists(select * from answerchoice ac inner join lessonquestionhistory lqh" +
                    " on ac.LessonQuestionHistoryId = lqh.Id and lqh.Id = ?1 and ac.Id = ?2)",
            nativeQuery = true
    )
    public BigInteger checkAnswerOfUser(String lessonQuestionId, String AnswerId);
}