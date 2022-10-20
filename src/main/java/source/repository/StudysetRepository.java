package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Studyset;

@Repository
public interface StudysetRepository extends JpaRepository<Studyset, String> {
}
