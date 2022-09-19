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
@FirebaseDocument(PathDatabaseFirebaseConstant.USER)
public class User {

    @FirebaseId
    private String id;

    private Role role;

    private String gender;

    private String userName;

    private String dateOfBirth;

    private String phoneNumber;

    private String avatar;

    private Object account;

    private Object address;

    private Object fullName;
}