package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.CustomerOrder;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.CustomerOrderDto;

@Component
public class CustomerOrderConverter extends BaseConverter<CustomerOrder, CustomerOrderDto> {
    @Override
    public CustomerOrder convertDtoToModel(CustomerOrderDto dto) {
        CustomerOrder customerOrder = CustomerOrder.builder()
                .createdAt(dto.getCreatedAt())
                .country(dto.getCountry())
                .city(dto.getCity())
                .county(dto.getCounty())
                .streetAddress(dto.getStreetAddress())
                .build();

        Location location = Location.builder().build();
        location.setId(dto.getLocationId());

        Customer customer = Customer.builder().build();
        customer.setId(dto.getCustomerId());

        customerOrder.setShippedFrom(location);
        customerOrder.setCustomer(customer);
        customerOrder.setId(dto.getId());
        return customerOrder;
    }

    @Override
    public CustomerOrderDto convertModelToDto(CustomerOrder customerOrder) {
        CustomerOrderDto customerOrderDto = CustomerOrderDto.builder()
                .locationId(customerOrder.getShippedFrom().getId())
                .locationName(customerOrder.getShippedFrom().getName())
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
