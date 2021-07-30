package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.converter.StockConverter;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    private final StockConverter stockConverter;

    @GetMapping(value = "/stocks/{id}", produces = "text/csv")
    public ResponseEntity<List<StockDto>> readByLocationId(@PathVariable("id") Integer locationId) {
        List<StockDto> stockDtos = stockConverter.convertModelsToDtos(stockService.readByLocationId(locationId));
        return new ResponseEntity<>(stockDtos, HttpStatus.OK);
    }
}
