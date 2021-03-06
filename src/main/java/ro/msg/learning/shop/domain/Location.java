package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Location extends BaseEntity {
    private String name;

    private String country;

    private String city;

    private String county;

    @Column(name = "streetaddress")
    private String streetAddress;

    @Override
    public String toString() {
        return "Location Name: " + name;
    }
}
