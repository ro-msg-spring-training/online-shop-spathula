package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.CustomerOrder;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.exception.OrderException;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.Strategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final Strategy strategy;

    private final LocationRepository locationRepository;

    private final StockRepository stockRepository;

    private final CustomerOrderRepository orderRepository;

    private final OrderDetailRepository detailRepository;

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private void validateOrderDetails(List<OrderDetail> orderDetails) {
        List<Integer> productIds = new ArrayList<>();

        productRepository.findAll().forEach(product -> productIds.add(product.getId()));

        orderDetails.forEach(orderDetail -> {
            if(orderDetail.getQuantity() <= 0)
                throw new OrderException("Order is not valid!");

            if (!productIds.contains(orderDetail.getProduct().getId()))
                throw new OrderException("One or more product does not exist!");

            else orderDetail.setProduct(productRepository.findById(orderDetail.getProduct().getId()).orElse(null));
        });
    }

    private void updateStock(Stock stock) {
        stockRepository.findByProductAndLocation(stock.getProduct(), stock.getLocation())
                .ifPresent(s -> {
                    s.setQuantity(s.getQuantity() - stock.getQuantity());
                    stockRepository.save(s);
                });
    }

    private void addOrderDetails(OrderDetail orderDetail, CustomerOrder order) {
        orderDetail.setCustomerOrder(order);
        detailRepository.save(orderDetail);
    }

    public CustomerOrder createOrder(PlacedOrderDto placedOrderDto) {
        validateOrderDetails(placedOrderDto.getOrderDetails());

        List<Stock> orderStock = strategy.getOrderStock(placedOrderDto, locationRepository.findAll(), stockRepository);

        if (orderStock.isEmpty()) throw new OrderException("Could not process the order!");
        orderStock.forEach(this::updateStock);

        CustomerOrder customerOrder = CustomerOrder.builder()
                .customer(customerRepository.findById(33).orElseThrow())
                .shippedFrom(orderStock.get(0).getLocation())
                .createdAt(LocalDateTime.now())
                .country(placedOrderDto.getCountry())
                .city(placedOrderDto.getCity())
                .county(placedOrderDto.getCounty())
                .streetAddress(placedOrderDto.getStreetAddress())
                .build();

        CustomerOrder order = orderRepository.save(customerOrder);
        placedOrderDto.getOrderDetails().forEach(orderDetail -> addOrderDetails(orderDetail, order));

        return customerOrder;
    }
}
