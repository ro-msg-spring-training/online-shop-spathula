package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;

@Component
public class StockConverter extends BaseConverter<Stock, StockDto>{
    @Override
    public Stock convertDtoToModel(StockDto stockDto) {
        Stock stock = Stock.builder()
                .quantity(stockDto.getQuantity())
                .build();

        stock.setId(stockDto.getId());
        return stock;
    }

    @Override
    public StockDto convertModelToDto(Stock stock) {
        StockDto stockDto = StockDto.builder()
                .location(stock.getLocation().getName())
                .product(stock.getProduct().getName())
                .quantity(stock.getQuantity())
                .build();

        stockDto.setId(stock.getId());
        return stockDto;
    }
}
