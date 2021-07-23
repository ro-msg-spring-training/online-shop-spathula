package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDto implements Serializable {
    protected int id;
}
