package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

public class MostAbundantStrategy implements Strategy {
    private Stock checkStockAvailability(Product product, StockRepository stockRepository) {
        return stockRepository.findByProductOrderByQuantityDesc(product).stream().findFirst().orElse(null);
    }

    @Override
    public List<Stock> selectLocation(List<OrderDetail> orderDetails, List<Location> locations, StockRepository stockRepository) {
        List<Stock> orderStock = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            Stock stock = checkStockAvailability(orderDetail.getProduct(), stockRepository);
            if (stock == null || stock.getQuantity() < orderDetail.getQuantity()) return new ArrayList<>();
            else orderStock.add(Stock.builder().location(stock.getLocation()).product(stock.getProduct()).quantity(orderDetail.getQuantity()).build());
        }

        return orderStock;
    }
}