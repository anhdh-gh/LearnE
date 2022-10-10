package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
public class AnswerChoice extends BaseEntity {

    @Column(name = "AnswerId")
    private String answerId;
}
