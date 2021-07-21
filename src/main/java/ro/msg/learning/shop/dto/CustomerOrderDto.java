package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Location;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class CustomerOrderDto extends BaseDto {
    private int locationId;

    private String locationName;

    private int customerId;

    private String customerName;

    private LocalDateTime createdAt;

    private String country;

    private String city;

    private String county;

    private String streetAddress;

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "shippedFrom=" + locationName +
                ", customer=" + locationId +
                ", createdAt=" + createdAt +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }
}
