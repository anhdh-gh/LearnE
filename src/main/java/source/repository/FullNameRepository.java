package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Account;
import source.entity.FullName;

@Repository
public interface FullNameRepository extends JpaRepository<FullName, String> {
}