package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingleLocationStrategy implements Strategy {


    private List<Stock> checkStockAvailability(List<OrderDetail> orderDetails, Location location, StockRepository stockRepository) {
        List<Stock> orderStock = new ArrayList<>();

        orderDetails.forEach(orderDetail -> stockRepository.findByProductAndLocation(orderDetail.getProduct(), location).ifPresent(
                stock -> {
                    if (stock.getQuantity() >= orderDetail.getQuantity())
                        orderStock.add(Stock.builder().location(stock.getLocation()).product(stock.getProduct()).quantity(orderDetail.getQuantity()).build());
                }
        ));

        return orderStock;
    }

    @Override
    public List<Stock> selectLocation(List<OrderDetail> orderDetails, List<Location> locations, StockRepository stockRepository) {
        for (Location location : locations) {
            List<Stock> orderStock = checkStockAvailability(orderDetails, location, stockRepository);
            if (orderStock.size() == orderDetails.size()) return orderStock;
        }

        return null;
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.SingleLocationStrategy;
    }
}
