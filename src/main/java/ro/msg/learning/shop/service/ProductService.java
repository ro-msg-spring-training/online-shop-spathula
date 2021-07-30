package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.exception.RepositoryException;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public Product create(Product product) {
        log.trace("ProductService -> create : {}", product);

        return productRepository.save(product);
    }

    public Product update(int id, Product newProduct) {
        log.trace("ProductService -> update : {}", newProduct);

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setWeight(newProduct.getWeight());
                    product.setImageUrl(newProduct.getImageUrl());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    public void delete(Integer id) {
        log.trace("ProductService -> delete : {}", id);

        productRepository.deleteById(id);
    }

    public Product readById(Integer id) {
        Optional<Product> optional = productRepository.findById(id);

        if(optional.isPresent())
            return optional.get();
        else throw new RepositoryException("Could not find product with id " + id);
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }
}
