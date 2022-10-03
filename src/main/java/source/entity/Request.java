package source.entity;

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
@Table(name = "request")
public class Request extends BaseEntity {

    @Column(name = "Text")
    private String text;

    @Builder
    public Request(String id, Date createTime, Date updateTime, String text) {
        super(id, createTime, updateTime);
        this.text = text;
    }
}