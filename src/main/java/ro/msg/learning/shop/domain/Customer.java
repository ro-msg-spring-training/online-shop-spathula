package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Customer extends BaseEntity {
    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String username;

    private String password;

    @Column(name = "emailaddress")
    private String emailAddress;

    @Override
    public String toString() {
        return "Customer Name: " + firstName + " " + lastName + " | Customer Username: " + username;
    }
}
