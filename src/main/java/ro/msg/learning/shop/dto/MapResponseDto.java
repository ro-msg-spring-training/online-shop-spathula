package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MapResponseDto {
    private List<Integer> distance;

    @Override
    public String toString() {
        return "MapResponseDto{" +
                "distance=" + distance +
                '}';
    }
}
