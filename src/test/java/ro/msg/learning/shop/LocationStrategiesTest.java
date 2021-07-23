package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.strategy.Strategy;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationStrategiesTest {
    @Mock
    private StockRepository stockRepository;

    private static Product product1, product2;

    private static Location location1, location2;

    @BeforeAll
    static void setUp() {
        product1 = Product.builder().name("product1").build();
        product2 = Product.builder().name("product2").build();

        location1 = Location.builder().name("location1").build();
        location2 = Location.builder().name("location2").build();
    }

    @Test
    void singleLocationStrategyTest() {
        List<Stock> stocks = Arrays.asList(Stock.builder().location(location1).product(product1).quantity(7).build(),
                Stock.builder().location(location1).product(product2).quantity(2).build(),
                Stock.builder().location(location2).product(product2).quantity(4).build());

        OrderDetail orderDetail1 = OrderDetail.builder().quantity(5).product(product1).build();
        OrderDetail orderDetail2 = OrderDetail.builder().quantity(2).product(product2).build();
        List<OrderDetail> orderDetails = Arrays.asList(orderDetail1, orderDetail2);
        PlacedOrderDto placedOrderDto = PlacedOrderDto.builder().orderDetails(orderDetails).build();

        List<Stock> expectedResult = Arrays.asList(Stock.builder().location(location1).product(product1).quantity(5).build(),
                Stock.builder().location(location1).product(product2).quantity(2).build());

        when(stockRepository.findByProductAndLocation(any(), any()))
                .thenAnswer(arguments -> stocks.stream()
                        .filter(stock -> stock.getProduct().getName().equals(arguments.getArgument(0, Product.class).getName())
                            && stock.getLocation().getName().equals(arguments.getArgument(1, Location.class).getName())).findFirst());

        Strategy strategy = new SingleLocationStrategy();
        assertThat(strategy.getOrderStock(placedOrderDto, Arrays.asList(location1, location2), stockRepository))
                .isEqualTo(expectedResult);
    }

    @Test
    void mostAbundantStrategyTest() {
        List<Stock> stocks = Arrays.asList(Stock.builder().location(location1).product(product1).quantity(7).build(),
                Stock.builder().location(location1).product(product2).quantity(2).build(),
                Stock.builder().location(location2).product(product2).quantity(4).build());

        OrderDetail orderDetail1 = OrderDetail.builder().quantity(5).product(product1).build();
        OrderDetail orderDetail2 = OrderDetail.builder().quantity(2).product(product2).build();
        List<OrderDetail> orderDetails = Arrays.asList(orderDetail1, orderDetail2);
        PlacedOrderDto placedOrderDto = PlacedOrderDto.builder().orderDetails(orderDetails).build();

        List<Stock> expectedResult = Arrays.asList(Stock.builder().location(location1).product(product1).quantity(5).build(),
                Stock.builder().location(location2).product(product2).quantity(2).build());

        when(stockRepository.findByProductOrderByQuantityDesc(any()))
                .thenAnswer(arguments -> stocks.stream()
                    .filter(stock -> stock.getProduct().getName().equals(arguments.getArgument(0, Product.class).getName()))
                        .sorted(Comparator.comparing(Stock::getQuantity).reversed()).collect(Collectors.toList()));

        Strategy strategy = new MostAbundantStrategy();
        assertThat(strategy.getOrderStock(placedOrderDto, Arrays.asList(location1, location2), stockRepository))
                .isEqualTo(expectedResult);
    }
}
