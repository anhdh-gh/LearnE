package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.Address;
import source.entity.FullName;
import source.entity.enumeration.Gender;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto extends BasicRequest{

    private String id;

    private Gender gender;

    private Date dateOfBirth;

    private String phoneNumber;

    private String avatar;

    private Address address;

    private FullName fullName;
}
