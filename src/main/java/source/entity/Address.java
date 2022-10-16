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
@Table(name = "address", schema = "public")
public class Address extends BaseEntity {

    @Column(name = "Nation")
    private String nation;

    @Column(name = "City")
    private String city;

    @Column(name = "Province")
    private String province;

    @Column(name = "District")
    private String district;

    @Column(name = "Street")
    private String street;

    @Column(name = "NumberHouse")
    private String numberHouse;

    @Builder
    public Address(String id, Date createTime, Date updateTime, String nation, String city, String province, String district, String street, String numberHouse) {
        super(id, createTime, updateTime);
        this.nation = nation;
        this.city = city;
        this.province = province;
        this.district = district;
        this.street = street;
        this.numberHouse = numberHouse;
    }
}