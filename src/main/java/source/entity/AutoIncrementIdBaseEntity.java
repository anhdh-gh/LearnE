package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass // Class cha không phải là entity. https://techmaster.vn/posts/36499/hibernate-inheritance-mapping
@Data  // All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AutoIncrementIdBaseEntity extends BaseEntity {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}