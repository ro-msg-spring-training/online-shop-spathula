package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;

@Component
public class ProductConverter extends BaseConverter<Product, ProductDto> {
    @Override
    public Product convertDtoToModel(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .category(productDto.getCategory())
                .imageUrl(productDto.getImageUrl())
                .build();

        product.setId(productDto.getId());

        return product;
    }

    @Override
    public ProductDto convertModelToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();

        productDto.setId(product.getId());

        return productDto;
    }
}
