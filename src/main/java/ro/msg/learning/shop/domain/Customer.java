package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Customer extends BaseEntity {
    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String emailAddress;

    @Override
    public String toString() {
        return "Customer Name: " + firstName + " " + lastName + " | Customer Username: " + username;
    }
}
