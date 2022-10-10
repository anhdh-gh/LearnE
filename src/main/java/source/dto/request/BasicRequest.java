package source.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.User;

import java.io.Serializable;

/**
 * The Class RequestBasicObj.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class BasicRequest implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1606619939033520333L;

  /** The request id. */
  @JsonProperty("request_id")
  private String requestId;

  @JsonProperty("uri")
  private String uri;

  @JsonProperty("user_auth_id")
  private String userAuthId;

  @JsonProperty("user_auth_role")
  private String userAuthRole;
}
