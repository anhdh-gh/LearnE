package source.entity;

import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.PathDatabaseFirebaseConstant;
import source.entity.enumeration.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FirebaseDocument(PathDatabaseFirebaseConstant.INFO_USER_AVATAR)
public class User {

    @FirebaseId
    private String id;

    private Role role;

    private String avatar;

    private String extension;
}