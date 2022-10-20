package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "wordcard", schema = "public")
@EqualsAndHashCode(callSuper=false)
public class WordCard extends BaseEntity {

    @Column(name = "Key")
    private String key;

    @Column(name = "Value")
    private String value;

    @Column(name = "Image")
    private String image;
}
