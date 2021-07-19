package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class CustomerOrder extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "shippedFrom", referencedColumnName = "id")
    Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    Customer customer;

    private LocalDateTime createdAt;

    private String country;

    private String city;

    private String county;

    private String streetAddress;

    @Override
    public String toString() {
        return "Shipped From: " + shippedFrom.getName() + " | Customer Name: " + customer.getFirstName() + " " + customer.getLastName();
    }
}
