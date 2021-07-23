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
public class MapRequestDto {
    private List<AddressDto> locations;
    private Options options;

    @Override
    public String toString() {
        return "MapRequestDto{" +
                "locations=" + locations +
                ", options=" + options +
                '}';
    }

    private class Options {
        private final boolean manyToOne = true;
    }

}
