package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Account;
import source.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}