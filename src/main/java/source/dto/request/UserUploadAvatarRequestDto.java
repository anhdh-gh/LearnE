package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
import source.anotation.ValidFileSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserUploadAvatarRequestDto extends BasicRequest {

    private String userId;

//    @ValidFileSize()
    private MultipartFile avatar;
}
