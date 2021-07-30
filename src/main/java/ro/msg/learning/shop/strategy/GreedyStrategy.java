package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.dto.MapRequestDto;
import ro.msg.learning.shop.dto.MapResponseDto;
import ro.msg.learning.shop.dto.PlacedOrderDto;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyStrategy implements Strategy {
    private static final String API_KEY = "AaTMOEvaaGBVRb4C1wv6Js3HW2WO2r1U";
    private static final String MAP_URL = "http://www.mapquestapi.com/directions/v2/routematrix?key=";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StockRepository stockRepository;

    private AddressDto buildAddress(String street, String city, String county, String country) {
        return AddressDto.builder()
                .street(street)
                .city(city)
                .county(county)
                .country(country)
                .build();
    }

    private List<Integer> getDistances(List<AddressDto> addressList) {
        RequestEntity<MapRequestDto> requestEntity = RequestEntity.post(MAP_URL + API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(MapRequestDto.builder().locations(addressList).build());
//        MapResponseDto mapResponseDto = restTemplate.exchange(requestEntity, MapResponseDto.class);
//        System.out.println(mapResponseDto);
        return null;
    }

    @Override
    public List<Stock> getOrderStock(PlacedOrderDto placedOrderDto, List<Location> locations, StockRepository stockRepository) {
        List<AddressDto> addressList = new ArrayList<>();

        addressList.add(buildAddress(placedOrderDto.getStreetAddress(),
                placedOrderDto.getCity(), placedOrderDto.getCounty(), placedOrderDto.getCountry()));

        locations.forEach(location -> addressList.add(buildAddress(location.getStreetAddress(),
                location.getCity(), location.getCounty(), location.getCountry())));

        getDistances(addressList);
        return null;
    }
}
