package source.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.StatusType;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonstatus", schema = "public")
@SuperBuilder
public class LessonStatus extends UidBaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", columnDefinition = "varchar(255) default 'UNFINISHED'")
    private StatusType status;

    @ManyToOne(targetEntity = Lesson.class)
    @JoinColumn (name = "LessonId", nullable = false)
    private Lesson lesson;
}
