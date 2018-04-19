package car.dealer.service;

import car.dealer.model.entity.Customer;
import car.dealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void save(List<Customer> customers) {
        this.customerRepository.saveAll(customers);
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomersSorted() {
        return this.customerRepository.getCustomerOrderByBirthDateAndYoungDriver();
    }
}
