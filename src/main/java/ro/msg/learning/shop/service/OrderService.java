package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.CustomerOrder;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.exception.OrderException;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private ProductRepository productRepository;

    private void validateOrderDetails(List<OrderDetail> orderDetails) {
        List<Integer> productIds = new ArrayList<>();

        productRepository.findAll().forEach(product -> productIds.add(product.getId()));

        orderDetails.forEach(orderDetail -> {
            if (!productIds.contains(orderDetail.getProduct().getId()))
                throw new OrderException("One or more product does not exist!");
            else orderDetail.setProduct(productRepository.findById(orderDetail.getProduct().getId()).get());
        });
    }

    private void updateStock(Stock stock) {
        stockRepository.findById(stock.getId())
                .ifPresent(s -> s.setQuantity(s.getQuantity() - stock.getQuantity()));
    }

    public CustomerOrder placeOrder(PlacedOrderDto placedOrderDto) {
        List<OrderDetail> orderDetails = placedOrderDto.getOrderDetails();
        validateOrderDetails(orderDetails);

        SingleLocationStrategy singleLocationStrategy = new SingleLocationStrategy();
        List<Stock> orderStock = singleLocationStrategy.selectLocation(orderDetails, locationRepository.findAll(), stockRepository);

        if (orderStock == null) throw new OrderException("Did not find any location to process the order!");
        orderStock.forEach(this::updateStock);

        CustomerOrder customerOrder = CustomerOrder.builder()
                .shippedFrom(orderStock.get(0).getLocation())
                .createdAt(LocalDateTime.now())
                .country(placedOrderDto.getCountry())
                .city(placedOrderDto.getCity())
                .county(placedOrderDto.getCounty())
                .streetAddress(placedOrderDto.getStreetAddress())
                .build();

        return orderRepository.save(customerOrder);
    }
}
