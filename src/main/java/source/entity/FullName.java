package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fullname")
public class FullName extends BaseEntity {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "FirstName")
    private String firstName;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "MidName")
    private String midName;

    @JsonInclude(JsonInclude.Include. NON_NULL)
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