package source.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.Address;
import source.entity.FullName;
import source.entity.enumeration.Gender;
import source.entity.enumeration.Role;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetAllResponseDto {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Role role;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Gender gender;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String userName;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Date dateOfBirth;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String phoneNumber;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String avatar;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Address address;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private FullName fullName;
}
