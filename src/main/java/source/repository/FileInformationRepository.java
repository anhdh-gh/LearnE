package source.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.stereotype.Repository;
import source.entity.FileInformation;

@Repository
public class FileInformationRepository extends DefaultFirebaseRealtimeDatabaseRepository<FileInformation, String> {

}