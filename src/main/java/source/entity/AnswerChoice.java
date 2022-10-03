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
@Table(name = "answerchoice")
public class AnswerChoice extends BaseEntity {

    @Column(name = "AnswerId")
    private String answerId;

    @Builder
    public AnswerChoice(String id, Date createTime, Date updateTime, String answerId) {
        super(id, createTime, updateTime);
        this.answerId = answerId;
    }
}
