package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.converter.CustomerOrderConverter;
import ro.msg.learning.shop.dto.CustomerOrderDto;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.service.OrderService;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    CustomerOrderConverter customerOrderConverter;

    @PostMapping("/orders")
    public CustomerOrderDto createOrder(@RequestBody PlacedOrderDto placedOrderDto) {
        return customerOrderConverter.convertModelToDto(orderService.createOrder(placedOrderDto));
    }
}
