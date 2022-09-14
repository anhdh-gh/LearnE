package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.anotation.NullOrNotBlank;
import source.constant.ErrorCodeConstant;
import source.constant.Gender;


import javax.validation.Valid;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto extends BasicRequest{

    @Size(max = 100, message = ErrorCodeConstant.USER_ID_IS_NOT_MORE_THAN_100_CHARACTERS_400026)
    @NotNull(message = ErrorCodeConstant.USER_ID_IS_NOT_NULL_400025)
    @NotBlank(message = ErrorCodeConstant.USER_ID_IS_NOT_EMPTY_400024)
    @NotEmpty(message = ErrorCodeConstant.USER_ID_IS_NOT_EMPTY_400024)
    private String id;

    private Gender gender;

    @NullOrNotBlank()
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$|null", message = ErrorCodeConstant.DATE_IS_NOT_VALID_400018)
    private String dateOfBirth;


    @NullOrNotBlank(message = ErrorCodeConstant.PHONE_NUMBER_IS_NOT_EMPTY_400017)
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b|null",
            message = ErrorCodeConstant.PHONE_NUMBER_IS_NOT_VALID_400016)
    private String phoneNumber;

    @Size(max = 500, message = ErrorCodeConstant.IMAGE_URL_IS_NOT_MORE_THAN_500_CHARACTERS_400020)
    @NullOrNotBlank(message = ErrorCodeConstant.IMAGE_URL_IS_NOT_VALID_400019)
    @Pattern(regexp = "(http)?s?:?(\\/\\/[^\"']*\\.(?:png|jpg|jpeg|gif|png|svg))|null", message = "http")
    private String avatar;

    @Valid
    private AddressDto address;

    @Valid
    private FullNameDto fullNameDto;
}
