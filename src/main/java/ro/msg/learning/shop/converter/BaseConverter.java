package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.domain.BaseEntity;
import ro.msg.learning.shop.dto.BaseDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity, Dto extends BaseDto> implements Converter<Model, Dto> {
    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}
