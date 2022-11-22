package source.dto.request.questionBank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.BaseDto;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
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
