package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

public interface Strategy {
    List<Stock> selectLocation(List<OrderDetail> orderDetails, List<Location> locations, StockRepository stockRepository);

    StrategyName getStrategyName();
}
