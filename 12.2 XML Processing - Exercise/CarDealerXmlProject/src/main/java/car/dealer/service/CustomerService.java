package car.dealer.service;

import car.dealer.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void save(Customer customer);
    void save(List<Customer> customers);
    Optional<Customer> getById(Long id);
    List<Customer> getAllCustomersSorted();
}
