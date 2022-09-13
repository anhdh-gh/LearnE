package source.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.Gender;
import source.constant.Role;
import source.entity.Account;
import source.entity.Address;
import source.entity.FullName;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetAllResponseDto {

    private Role role;

    private Gender gender;

    private String userName;

    private Date dateOfBirth;

    private String phoneNumber;

    private String avatar;

    private String email;

    private Address address;

    private FullName fullName;
}
