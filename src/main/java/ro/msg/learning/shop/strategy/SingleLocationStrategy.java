package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

public class SingleLocationStrategy implements Strategy {
    private Stock checkStockAvailability(Product product, Location location, Integer quantity, StockRepository stockRepository) {
        return stockRepository.findByProductAndLocationAndQuantityGreaterThanEqual(product, location, quantity).orElse(null);
    }

    @Override
    public List<Stock> getOrderStock(PlacedOrderDto placedOrderDto, List<Location> locations, StockRepository stockRepository) {
        for (Location location : locations) {
            List<Stock> orderStock = new ArrayList<>();

            for (OrderDetail orderDetail : placedOrderDto.getOrderDetails()) {
                Stock stock = checkStockAvailability(orderDetail.getProduct(), location, orderDetail.getQuantity(), stockRepository);
                if(stock == null) continue;
                else orderStock.add(Stock.builder().location(stock.getLocation()).product(stock.getProduct()).quantity(orderDetail.getQuantity()).build());
            }

            if (orderStock.size() == placedOrderDto.getOrderDetails().size()) return orderStock;
        }

        return new ArrayList<>();
    }
}
