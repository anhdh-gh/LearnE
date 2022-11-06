package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
import source.anotation.ValidFile;
import source.anotation.ValidFileSize;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserUploadAvatarRequestDto extends BasicRequest {

    private String userId;

    @NotNull
    @ValidFileSize()
    @ValidFile()
    private MultipartFile avatar;
}
