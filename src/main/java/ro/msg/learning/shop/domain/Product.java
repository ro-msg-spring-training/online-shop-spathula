package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Product extends BaseEntity {
    private String name;

    private String description;

    private BigDecimal price;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "supplier", referencedColumnName = "id")
    Supplier supplier;

    @Column(name = "imageurl")
    private String imageUrl;

    @Override
    public String toString() {
        return "Product Name: " + name;
    }
}
