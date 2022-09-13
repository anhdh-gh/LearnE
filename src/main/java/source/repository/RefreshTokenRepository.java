package source.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.stereotype.Repository;
import source.entity.RefreshToken;

import java.util.Optional;

@Repository
public class RefreshTokenRepository extends DefaultFirebaseRealtimeDatabaseRepository<RefreshToken, String> {

}
