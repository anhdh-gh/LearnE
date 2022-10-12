package source.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass // Class cha không phải là entity. https://techmaster.vn/posts/36499/hibernate-inheritance-mapping
@Data  // All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UidBaseEntity extends BaseEntity {

    @Id
    @Column(name = "Id")
    protected String id;

    @Override
    protected void init() {
        super.init();
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}