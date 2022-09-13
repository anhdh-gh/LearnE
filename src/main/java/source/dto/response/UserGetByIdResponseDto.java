package source.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGetByIdResponseDto {

    private String id;
    private String role;
    private String email;
    private String password;
}