package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.domain.BaseEntity;
import ro.msg.learning.shop.dto.BaseDto;

public interface Converter<Model extends BaseEntity, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
