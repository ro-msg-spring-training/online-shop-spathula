package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.*;

@Service
@RequiredArgsConstructor
@Profile("test")
public class TestService {
    private final LocationRepository locationRepository;

    private final StockRepository stockRepository;

    private final CustomerOrderRepository orderRepository;

    private final OrderDetailRepository detailRepository;

    private final ProductRepository productRepository;

    public void populate() {
        Product product1 = Product.builder().name("product1").build();
        product1.setId(1);
        product1 = productRepository.save(product1);

        Product product2 = Product.builder().name("product2").build();
        product2.setId(2);
        product2 = productRepository.save(product2);

        Location location1 = locationRepository.save(Location.builder().name("location1").build());
        Location location2 = locationRepository.save(Location.builder().name("location2").build());

        stockRepository.save(Stock.builder().location(location1).product(product1).quantity(7).build());
        stockRepository.save(Stock.builder().location(location1).product(product2).quantity(2).build());
        stockRepository.save(Stock.builder().location(location2).product(product2).quantity(4).build());
    }

    public void clear() {
        detailRepository.deleteAll();
        orderRepository.deleteAll();
        stockRepository.deleteAll();
        locationRepository.deleteAll();
        productRepository.deleteAll();
    }
}
