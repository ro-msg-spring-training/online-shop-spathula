package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class StockDto extends BaseDto {
    String product;

    String location;

    private int quantity;

    @Override
    public String toString() {
        return "StockDto{" +
                "product=" + product +
                ", location=" + location +
                ", quantity=" + quantity +
                '}';
    }
}
