package ro.msg.learning.shop.dto;

import lombok.*;

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

    private int categoryId;

    private String categoryName;

    private String imageUrl;

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", category=" + categoryName +
                '}';
    }
}
