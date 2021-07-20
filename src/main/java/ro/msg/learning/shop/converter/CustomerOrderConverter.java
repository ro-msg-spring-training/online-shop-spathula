package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.CustomerOrder;
import ro.msg.learning.shop.dto.CustomerOrderDto;

import java.time.LocalDateTime;

@Component
public class CustomerOrderConverter extends BaseConverter<CustomerOrder, CustomerOrderDto> {
    @Override
    public CustomerOrder convertDtoToModel(CustomerOrderDto dto) {
        CustomerOrder customerOrder = CustomerOrder.builder()
                .shippedFrom(dto.getShippedFrom())
                .customer(dto.getCustomer())
                .createdAt(dto.getCreatedAt())
                .country(dto.getCountry())
                .city(dto.getCity())
                .county(dto.getCounty())
                .streetAddress(dto.getStreetAddress())
                .build();

        customerOrder.setId(dto.getId());
        return customerOrder;
    }

    @Override
    public CustomerOrderDto convertModelToDto(CustomerOrder customerOrder) {
        CustomerOrderDto customerOrderDto = CustomerOrderDto.builder()
                .shippedFrom(customerOrder.getShippedFrom())
                .customer(customerOrder.getCustomer())
                .createdAt(customerOrder.getCreatedAt())
                .country(customerOrder.getCountry())
                .city(customerOrder.getCity())
                .county(customerOrder.getCounty())
                .streetAddress(customerOrder.getStreetAddress())
                .build();

        customerOrderDto.setId(customerOrder.getId());
        return customerOrderDto;
    }
}
