package source.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.stereotype.Repository;
import source.entity.User;

@Repository
public class UserRepository extends DefaultFirebaseRealtimeDatabaseRepository<User, String> {

}
