package fkal.goalplanner.goalplanner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fkal.goalplanner.goalplanner.model.dao.CustomerRepository;
import fkal.goalplanner.goalplanner.model.dto.CustomerDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	public List<CustomerDto> getCustomers() {
		return null;
	}

	public CustomerDto getCustomerById(String customerId) {
		return null;
	}

}
