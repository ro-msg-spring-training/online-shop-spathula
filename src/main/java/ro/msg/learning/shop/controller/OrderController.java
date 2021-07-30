package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.converter.CustomerOrderConverter;
import ro.msg.learning.shop.domain.CustomerOrder;
import ro.msg.learning.shop.dto.CustomerOrderDto;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.service.MailService;
import ro.msg.learning.shop.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    private final MailService mailService;

    private final CustomerOrderConverter customerOrderConverter;

    @PostMapping("/orders")
    public CustomerOrderDto createOrder(@RequestBody PlacedOrderDto placedOrderDto) {
        CustomerOrder order = orderService.createOrder(placedOrderDto);
        //mailService.orderSuccessEmail(order);
        return customerOrderConverter.convertModelToDto(order);
    }
}
