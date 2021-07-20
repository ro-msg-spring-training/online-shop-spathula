package ro.msg.learning.shop.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductsDto {
    private List<ProductDto> products;
}
