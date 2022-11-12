package source.entity;

import com.github.fabiomaffioletti.firebase.document.FirebaseId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDetail {

    private String id;

    private String image;

    private String audio;

    private String extensionImage;

    private String extensionAudio;
}
