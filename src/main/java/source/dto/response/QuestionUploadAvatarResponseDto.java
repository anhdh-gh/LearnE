package source.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionUploadAvatarResponseDto {

    private String id;

    private String urlImage;

    private String extensionImage;

    private String urlAudio;

    private String extensionAudio;
}
