package source.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * The Class RequestBasicObj.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicRequest implements Serializable {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1606619939033520333L;

  /**
   * The request id.
   */
  @JsonProperty("request_id")
  private String requestId;

  @JsonProperty("uri")
  private String uri;
}
