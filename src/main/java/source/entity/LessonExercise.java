package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.Provider;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonexercise", schema = "public")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonExercise extends AutoIncrementIdBaseEntity {

    @Column(name = "ReferenceId")
    private String referenceId;

    @Column(name = "Provider")
    @Enumerated(EnumType.STRING)
    private Provider provider;
}