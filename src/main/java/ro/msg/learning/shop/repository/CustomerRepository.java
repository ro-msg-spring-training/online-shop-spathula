package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends Repository<Customer, Integer> {
    Optional<Customer> findByUsername(String username);
}
