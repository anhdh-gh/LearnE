package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Examination;

import java.util.Optional;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, String> {

    Optional<Examination> findExaminationByUserIdAndStudysetId(String userId, String studysetId);
}
