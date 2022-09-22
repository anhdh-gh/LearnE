package source.third_party.firebase_storage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;
import source.dto.response.BaseResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FirebaseStorageRequestDto extends BasicRequest {

    private String folder;
    private String fileName;

    public String getPath() {
        return this.folder + this.fileName;
    }
}
