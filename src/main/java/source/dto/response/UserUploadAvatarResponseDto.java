package source.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUploadAvatarResponseDto {

    private String urlAvatar;

    private String extension;
}
