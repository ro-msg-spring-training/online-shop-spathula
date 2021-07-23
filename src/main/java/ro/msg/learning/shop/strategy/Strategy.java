package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

@Component
public interface Strategy {
    List<Stock> getOrderStock(PlacedOrderDto placedOrderDto, List<Location> locations, StockRepository stockRepository);
}
