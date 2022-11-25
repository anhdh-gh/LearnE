package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Question;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    List<Question> findByIdIn(Set<String> ids);

    Page<Question> findAllByTextContainingIgnoreCase(String text, PageRequest pageRequest);
}