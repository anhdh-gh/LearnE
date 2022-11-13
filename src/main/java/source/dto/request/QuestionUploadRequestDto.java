package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
import source.anotation.ValidFile;
import source.anotation.ValidFileSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionUploadRequestDto extends BasicRequest {

//    @ValidFileSize(maxSize = 2000)
//    @ValidFile()
    private MultipartFile image;

//    @ValidFileSize(maxSize = 10000)
//    @ValidFile(type = ValidFile.AUDIO)
    private MultipartFile audio;

    private String groupId;

    private String id;
}
