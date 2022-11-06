package source.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.stereotype.Repository;
import source.entity.RefreshToken;

@Repository
public class RefreshTokenRepository extends DefaultFirebaseRealtimeDatabaseRepository<RefreshToken, String> {

}
