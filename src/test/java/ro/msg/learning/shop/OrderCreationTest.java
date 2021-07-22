package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.PlacedOrderDto;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderCreationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc.perform(get("/populate"));
    }

    @AfterEach
    void tearDown() throws Exception {
        mockMvc.perform(get("/clear"));
    }

    @Test
    void createOrderSuccess() throws Exception {
        Product product1 = Product.builder().build();
        product1.setId(1);
        Product product2 = Product.builder().build();
        product2.setId(2);

        OrderDetail orderDetail1 = OrderDetail.builder().quantity(5).product(product1).build();
        OrderDetail orderDetail2 = OrderDetail.builder().quantity(2).product(product2).build();

        PlacedOrderDto placedOrderDto = PlacedOrderDto.builder().orderDetails(Arrays.asList(orderDetail1, orderDetail2)).build();

        mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(placedOrderDto)))
                .andExpect(status().isOk());
    }

    @Test
    void createOrderMissingStock() throws Exception {
        Product product1 = Product.builder().build();
        product1.setId(1);
        Product product2 = Product.builder().build();
        product2.setId(2);

        OrderDetail orderDetail1 = OrderDetail.builder().quantity(10).product(product1).build();
        OrderDetail orderDetail2 = OrderDetail.builder().quantity(10).product(product2).build();

        PlacedOrderDto placedOrderDto = PlacedOrderDto.builder().orderDetails(Arrays.asList(orderDetail1, orderDetail2)).build();

        mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(placedOrderDto)))
                .andExpect(status().isBadRequest());
    }

}
