package source.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {

    @Column(name = "Text")
    private String text;

    @Builder
    public Answer(String id, Date createTime, Date updateTime, String text) {
        super(id, createTime, updateTime);
        this.text = text;
    }
}