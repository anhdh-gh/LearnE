package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullNameDto {

    @Size(max = 100, message = "")
    private String firstName;

    @Size(max = 100, message = "")
    private String midName;


    @Size(max = 100, message = "")
    private String lastName;
}
