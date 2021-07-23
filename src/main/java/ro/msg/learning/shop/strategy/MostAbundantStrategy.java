package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

public class MostAbundantStrategy implements Strategy {
    private Stock checkStockAvailability(Product product, Integer quantity, StockRepository stockRepository) {
        return stockRepository.findByProductAndQuantityGreaterThanEqualOrderByQuantityDesc(product, quantity).stream().findFirst().orElse(null);
    }

    @Override
    public List<Stock> getOrderStock(PlacedOrderDto placedOrderDto, List<Location> locations, StockRepository stockRepository) {
        List<Stock> orderStock = new ArrayList<>();

        for (OrderDetail orderDetail : placedOrderDto.getOrderDetails()) {
            Stock stock = checkStockAvailability(orderDetail.getProduct(), orderDetail.getQuantity(), stockRepository);
            if (stock == null) return new ArrayList<>();
            else orderStock.add(Stock.builder().location(stock.getLocation()).product(stock.getProduct()).quantity(orderDetail.getQuantity()).build());
        }

        return orderStock;
    }
}
