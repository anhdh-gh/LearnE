package source.entity;

import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.PathDatabaseFirebaseConstant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FirebaseDocument(PathDatabaseFirebaseConstant.FILE_INFORMATION)
public class FileInformation {

    @FirebaseId
    private String id;

    private String url;

    private String extension;
}
