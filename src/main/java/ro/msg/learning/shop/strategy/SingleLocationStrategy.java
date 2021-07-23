package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

public class SingleLocationStrategy implements Strategy {
    private List<Stock> checkStockAvailability(List<OrderDetail> orderDetails, Location location, StockRepository stockRepository) {
        List<Stock> orderStock = new ArrayList<>();

        orderDetails.forEach(orderDetail -> stockRepository.findByProductAndLocationAndQuantityGreaterThan(orderDetail.getProduct(), location, orderDetail.getQuantity()).ifPresent(
                stock -> orderStock.add(Stock.builder().location(stock.getLocation()).product(stock.getProduct()).quantity(orderDetail.getQuantity()).build())
        ));

        return orderStock;
    }

    @Override
    public List<Stock> getOrderStock(PlacedOrderDto placedOrderDto, List<Location> locations, StockRepository stockRepository) {
        List<OrderDetail> orderDetails = placedOrderDto.getOrderDetails();

        for (Location location : locations) {
            List<Stock> orderStock = checkStockAvailability(orderDetails, location, stockRepository);
            if (orderStock.size() == orderDetails.size()) return orderStock;
        }

        return new ArrayList<>();
    }
}
