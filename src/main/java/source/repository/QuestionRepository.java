package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
}