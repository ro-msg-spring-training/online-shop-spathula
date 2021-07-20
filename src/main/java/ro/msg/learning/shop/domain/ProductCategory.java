package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "productcategory")
public class ProductCategory extends BaseEntity {
    private String name;

    private String description;

    @Override
    public String toString() {
        return "Product Name: " + name + " | Product Description: " + description;
    }

}
