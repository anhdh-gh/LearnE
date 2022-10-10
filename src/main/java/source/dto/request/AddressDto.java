package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String nation;

    private String city;

    private String province;

    private String district;

    private String street;

    private String numberHouse;
}
