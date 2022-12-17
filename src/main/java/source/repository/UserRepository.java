package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserName(String userName);

    List<User> findByIdIn(Set<String> id);

    Page<User> findAllByUserNameContainingIgnoreCase(String userName, PageRequest pageRequest);
}