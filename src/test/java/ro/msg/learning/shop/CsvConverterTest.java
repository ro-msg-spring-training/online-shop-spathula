package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.converter.CsvConverter;
import ro.msg.learning.shop.dto.StockDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CsvConverterTest {
    private static CsvConverter csvConverter;
    private static String csvString;
    private static List<StockDto> stockDtoList;

    @BeforeAll
    static void setUp() {
        csvConverter = new CsvConverter();
        csvString = "0,location1,product1,7\n" +
                "0,location1,product2,2\n";
        stockDtoList = Arrays.asList(StockDto.builder().location("location1").product("product1").quantity(7).build(),
                StockDto.builder().location("location1").product("product2").quantity(2).build());
    }

    @Test
    void serializationTest() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        csvConverter.toCsv(StockDto.class, stockDtoList, outputStream);
        assertThat(outputStream).hasToString(csvString);
    }

    @Test
    void deSerializationTest() throws IOException {
        assertThat(csvConverter.fromCsv(StockDto.class, new ByteArrayInputStream(csvString.getBytes(StandardCharsets.UTF_8))))
                .isEqualTo(stockDtoList);
    }
}
