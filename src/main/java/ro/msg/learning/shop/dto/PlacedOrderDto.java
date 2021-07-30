package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.OrderDetail;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class PlacedOrderDto extends BaseDto {
    private String country;

    private String city;

    private String county;

    private String streetAddress;

    private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "PlacedOrderDto{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
