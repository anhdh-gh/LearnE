package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import source.entity.Question;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    List<Question> findByIdIn(Set<String> ids);

    List<Question> findAllByGroupId(String groupId);

    void deleteAllByGroupId(String groupId);

    @Query(value = "select * from question q where q.QuestionType = ?1 order by random() limit ?2", nativeQuery = true)
    List<Question> findByQuestionTypeAndLimit(String questionType, long limit);
}