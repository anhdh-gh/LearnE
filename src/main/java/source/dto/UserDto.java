package source.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends BaseDto {

    private String id;

    private String role;

    private String gender;

    private String userName;

    private Date dateOfBirth;

    private String phoneNumber;

    private String avatar;

    private Object account;

    private Object address;

    private Object fullName;
}
