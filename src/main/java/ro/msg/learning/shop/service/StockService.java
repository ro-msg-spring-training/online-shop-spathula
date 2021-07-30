package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private StockRepository stockRepository;

    public List<Stock> readByLocationId(Integer locationId) {
        return stockRepository.findByLocationId(locationId);
    }
}
