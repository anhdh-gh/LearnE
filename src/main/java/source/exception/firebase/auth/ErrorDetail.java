package source.exception.firebase.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetail {

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("message")
    private String message;
}
