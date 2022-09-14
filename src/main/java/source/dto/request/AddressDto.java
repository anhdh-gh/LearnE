package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.anotation.NullOrNotBlank;
import source.constant.ErrorCodeConstant;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NullOrNotBlank(message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_EMPTY_400021)
    @Size(max = 100, message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_MORE_THAN_100_CHARACTERS_400015)
    private String nation;

    @NullOrNotBlank(message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_EMPTY_400021)
    @Size(max = 100, message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_MORE_THAN_100_CHARACTERS_400015)
    private String city;

    @NullOrNotBlank(message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_EMPTY_400021)
    @Size(max = 100, message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_MORE_THAN_100_CHARACTERS_400015)
    private String province;

    @NullOrNotBlank(message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_EMPTY_400021)
    @Size(max = 100, message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_MORE_THAN_100_CHARACTERS_400015)
    private String district;

    @NullOrNotBlank(message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_EMPTY_400021)
    @Size(max = 100, message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_MORE_THAN_100_CHARACTERS_400015)
    private String street;

    @NullOrNotBlank(message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_EMPTY_400021)
    @Size(max = 100, message = ErrorCodeConstant.ADDRESS_INFORMATION_IS_NOT_MORE_THAN_100_CHARACTERS_400015)
    private String numberHouse;
}
