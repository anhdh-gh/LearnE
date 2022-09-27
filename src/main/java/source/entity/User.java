package source.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import source.entity.enumeration.Gender;
import source.entity.enumeration.Role;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "UserName")
    private String userName;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "Avatar")
    private String avatar;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
    private Account account;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @OneToOne(targetEntity = Address.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "AddressID")
    private Address address;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @OneToOne(targetEntity = FullName.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "FullNameID")
    private FullName fullName;

    @Builder
    public User(String id, Date createTime, Date updateTime, Role role, String userName, Date dateOfBirth, String phoneNumber, String avatar, Account account, Address address, FullName fullName) {
        super(id, createTime, updateTime);
        this.role = role;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.account = account;
        this.address = address;
        this.fullName = fullName;
    }
}

// https://hocspringboot.net/2020/10/23/onetomany-va-manytoone-trong-spring-boot/