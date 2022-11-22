package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "answer", schema = "public")
public class Answer extends AutoIncrementIdBaseEntity {

    @Column(name = "Text")
    private String text;

    @Column(name = "Audio")
    private String audio;
}