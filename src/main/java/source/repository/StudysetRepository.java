package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Studyset;

import java.util.List;
import java.util.Set;

@Repository
public interface StudysetRepository extends JpaRepository<Studyset, String> {

    // https://www.baeldung.com/spring-data-jpa-pagination-sorting
    Page<Studyset> findAllByOwnerUserId(String ownerUserId, Pageable pageable);

    Page<Studyset> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Studyset> findAllByOwnerUserIdAndTitleContainingIgnoreCase(String ownerUserId, String title, Pageable pageable);

    List<Studyset> findByIdIn(Set<String> ids);
}
