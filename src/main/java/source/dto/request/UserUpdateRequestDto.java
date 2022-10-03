package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.anotation.NullOrNotBlank;
import source.constant.ErrorCodeConstant;
import source.constant.Gender;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto extends BasicRequest {

    private Gender gender;

    @NullOrNotBlank()
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$|null", message = ErrorCodeConstant.DATE_IS_NOT_VALID_400018)
    private String dateOfBirth;


    @NullOrNotBlank(message = ErrorCodeConstant.PHONE_NUMBER_IS_NOT_EMPTY_400017)
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b|null",
            message = ErrorCodeConstant.PHONE_NUMBER_IS_NOT_VALID_400016)
    private String phoneNumber;

    @Size(max = 500, message = ErrorCodeConstant.IMAGE_URL_IS_NOT_MORE_THAN_500_CHARACTERS_400020)
    @NullOrNotBlank(message = ErrorCodeConstant.IMAGE_URL_IS_NOT_EMPTY_400032)
    @Pattern(regexp = "^https://firebasestorage.googleapis.com/v0/b/learne-bfb9c.appspot.com/o/" +
            "Backend-Service-Multimedia%2FUser%2FAvatar%2F(?<userId>[^\"']+).(?<extension>png|jpg|" +
            "jpeg|gif|png|svg)?alt=media$|null", message = ErrorCodeConstant.IMAGE_URL_IS_NOT_VALID_400019)
    private String avatar;

    @Valid
    private AddressDto address;

    @Valid
    private FullNameDto fullNameDto;
}
