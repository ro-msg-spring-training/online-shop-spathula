package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends Repository<Stock, Integer> {
    Optional<Stock> findByProductAndLocation(Product product, Location location);
}
