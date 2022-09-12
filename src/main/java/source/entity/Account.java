package source.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    public Account(String id, Date createTime, Date updateTime, String email, String password) {
        super(id, createTime, updateTime);
        this.email = email;
        this.password = password;
    }
}