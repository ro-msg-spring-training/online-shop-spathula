package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Stock extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "id")
    Location location;

    private int quantity;

    @Override
    public String toString() {
        return "Product Name: " + product.getName() + " | Quantity: " + quantity;
    }
}
