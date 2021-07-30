package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "orderdetail")
public class OrderDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customerorder", referencedColumnName = "id")
    CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    Product product;

    private int quantity;

    @Override
    public String toString() {
        return "Product Name: " + product.getName() + " | Quantity: " + quantity;
    }
}
