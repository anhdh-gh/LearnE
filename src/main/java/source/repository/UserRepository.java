package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserName(String userName);
}