package source.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account", schema = "public")
public class Account extends BaseEntity {

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Builder
    public Account(String id, Date createTime, Date updateTime, String email, String password) {
        super(id, createTime, updateTime);
        this.email = email;
        this.password = password;
    }
}