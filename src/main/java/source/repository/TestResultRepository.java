package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.TestResult;

import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, String> {

    Optional<TestResult> findTestResultByUserIdAndQuestionId(String userId, String questionId);

    Page<TestResult> findAllByQuestionId(String questionId, Pageable pageable);
}
