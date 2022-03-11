package com.wtrungvu.customer;

import com.wtrungvu.clients.fraud.FraudCheckResponse;
import com.wtrungvu.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email is valid
        // todo: check if email is not already registered
        // todo: store customer in database
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = 
                fraudClient.isFraudster(customer.getId());

        // todo: check if fraudster
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is a fraudster");
        }

        // todo: send notification
    }
}
