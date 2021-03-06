package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final ProductConverter productConverter;

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        List<Product> products = productService.readAll();
        return productConverter.convertModelsToDtos(products);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.create(productConverter.convertDtoToModel(productDto));
        return productConverter.convertModelToDto(product);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        Product product = productService.readById(id);
        return productConverter.convertModelToDto(product);
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Product product = productService.update(id, productConverter.convertDtoToModel(productDto));
        return productConverter.convertModelToDto(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
