package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Supplier extends BaseEntity {
    private String name;

    @Override
    public String toString() {
        return "Supplier Name: " + name;
    }
}
