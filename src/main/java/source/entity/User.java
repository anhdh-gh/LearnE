package source.entity;

import lombok.*;
import source.entity.enumeration.Gender;
import source.entity.enumeration.Role;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
public class User extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Avatar")
    private String avatar;

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
    private Account account;

    @OneToOne(targetEntity = Address.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "AddressID")
    private Address address;

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