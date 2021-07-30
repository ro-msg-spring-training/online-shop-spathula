package ro.msg.learning.shop.converter;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvConverter extends AbstractGenericHttpMessageConverter<Object> {
    private final CsvMapper csvMapper = new CsvMapper();

    public CsvConverter() {
        super(new MediaType("text","csv"));
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        toCsv((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0], (List) o, httpOutputMessage.getBody());
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return fromCsv(aClass, httpInputMessage.getBody());
    }

    @Override
    public Object read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(aClass, httpInputMessage);
    }

    public <T> List<T> fromCsv(Class<T> aClass, InputStream inputStream) throws IOException {
        CsvSchema csvSchema = csvMapper.schemaFor(aClass);

        MappingIterator<T> it = csvMapper.readerFor(aClass)
                .with(csvSchema)
                .readValues(inputStream);

        return it.readAll();
    }

    public <T> void toCsv(Class<T> aClass, List<T> pojoList, OutputStream outputStream) throws IOException {
        CsvSchema csvSchema = csvMapper.schemaFor(aClass);

        for(T pojo : pojoList) {
            String csv = csvMapper.writer(csvSchema).writeValueAsString(pojo);
            outputStream.write(csv.getBytes(StandardCharsets.UTF_8));
        }

        outputStream.flush();
        outputStream.close();
    }
}
