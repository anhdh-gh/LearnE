package source.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FieldViolation implements Serializable {
    private static final long serialVersionUID = 1606619939023232333L;
    private String field;
    private String code;
    private String description;
}

