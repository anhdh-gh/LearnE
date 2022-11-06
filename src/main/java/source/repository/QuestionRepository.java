package source.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.stereotype.Repository;
import source.entity.Question;

@Repository
public class QuestionRepository extends DefaultFirebaseRealtimeDatabaseRepository<Question, String> {

}
