package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.ProductCategory;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class ProductDto extends BaseDto {
    private String name;

    private String description;

    private BigDecimal price;

    private double weight;

    ProductCategory category;

    private String imageUrl;

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", category=" + category +
                '}';
    }
}
