package source.third_party.firebase_storage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FirebaseUploadFileRequestDto extends FirebaseStorageRequestDto {

    private MultipartFile multipartFile;
}
