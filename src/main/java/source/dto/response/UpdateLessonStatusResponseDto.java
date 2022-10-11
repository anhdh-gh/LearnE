package source.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;
import source.entity.enumeration.StatusType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateLessonStatusResponseDto {

    private String userId;

    private String lessonId;

    private StatusType status;
}