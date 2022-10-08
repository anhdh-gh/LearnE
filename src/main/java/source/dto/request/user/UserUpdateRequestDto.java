package source.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.Gender;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto extends BasicRequest {

    private String id;

    private Gender gender;

    private String dateOfBirth;

    private String phoneNumber;

    private String avatar;


    private AddressDto address;

    private FullNameDto fullNameDto;
}
