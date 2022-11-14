package source.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "answer", schema = "public")
public class Answer extends BaseEntity {

    @Column(name = "Text")
    private String text;

    @Column(name = "IsCorrect")
    private boolean isCorrect;

    @Builder
    public Answer(String id, Date createTime, Date updateTime, String text, boolean isCorrect) {
        super(id, createTime, updateTime);
        this.text = text;
        this.isCorrect = isCorrect;
    }
}