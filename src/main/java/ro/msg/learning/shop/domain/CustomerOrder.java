package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "customerorder")
public class CustomerOrder extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "shippedfrom", referencedColumnName = "id")
    Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    Customer customer;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    private String country;

    private String city;

    private String county;

    @Column(name = "streetaddress")
    private String streetAddress;

    @Override
    public String toString() {
        return "Shipped From: " + shippedFrom.getName() + " | Customer Name: " + customer.getFirstName() + " " + customer.getLastName();
    }
}
