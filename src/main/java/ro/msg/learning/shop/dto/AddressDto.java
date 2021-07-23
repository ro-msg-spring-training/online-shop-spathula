package ro.msg.learning.shop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDto {
    private String street;
    private String city;
    private String county;
    private String country;

    @Override
    public String toString() {
        return "AddressDto{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
