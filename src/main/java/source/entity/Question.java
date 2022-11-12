package source.entity;

import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.PathDatabaseFirebaseConstant;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FirebaseDocument(PathDatabaseFirebaseConstant.INFO_QUESTION)
public class Question {

    @FirebaseId
    private String groupId;

    private List<QuestionDetail> questionDetails;
}