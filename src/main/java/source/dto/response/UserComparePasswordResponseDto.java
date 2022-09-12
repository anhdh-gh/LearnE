package source.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComparePasswordResponseDto {
    @JsonProperty("isCorrect")
    private boolean isCorrect;
}
