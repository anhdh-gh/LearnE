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
@Table(name = "answer", schema = "public")
public class Answer extends BaseEntity {

    @Column(name = "Text")
    private String text;

    @Column(name = "Audio")
    private String audio;

    @Column(name = "IsCorrect")
    private boolean isCorrect;

    @Builder
    public Answer(String id, Date createTime, Date updateTime, String text, String audio, boolean isCorrect) {
        super(id, createTime, updateTime);
        this.text = text;
        this.audio = audio;
        this.isCorrect = isCorrect;
    }
}