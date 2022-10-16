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
@Table(name = "fullname", schema = "public")
public class FullName extends BaseEntity {

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "MidName")
    private String midName;

    @Column(name = "LastName")
    private String lastName;

    @Builder
    public FullName(String id, Date createTime, Date updateTime, String firstName, String midName, String lastName) {
        super(id, createTime, updateTime);
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
    }
}