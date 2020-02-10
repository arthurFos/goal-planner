package fkal.goalplanner.goalplanner.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import fkal.goalplanner.goalplanner.model.bo.CustomerBo;
import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import fkal.goalplanner.goalplanner.model.dao.CustomerRepository;
import fkal.goalplanner.goalplanner.model.dto.CustomerDto;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final ModelMapper mapper = new ModelMapper();
	
	public List<CustomerDto> getCustomers() {
		return customerRepository.findAll().stream()
				.map(customer -> mapCustomerBoToCustomerDto(customer, new CustomerDto())).collect(Collectors.toList());
	}

	public CustomerDto getCustomerById(String customerId) throws NotFoundException {
		CustomerBo customerBo = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Coulnd find any customer the customer id -> " + customerId));
		
		return mapCustomerBoToCustomerDto(customerBo, new CustomerDto());
	}

	public CustomerDto createCustomer(CustomerDto customerDto) throws Exception {
		
		if (customerRepository.existsById(customerDto.getId()))
			throw new Exception("This customer already exists");
		
		CustomerBo customerBo = mapCustomerDtoToCustomerBo(customerDto, new CustomerBo());
		customerBo.setCustomerId(UUID.randomUUID().toString());
		
		return mapCustomerBoToCustomerDto(customerRepository.save(customerBo), new CustomerDto());
	}

	public CustomerDto updateCustomer(String customerId, String lastname, String firstname) throws NotFoundException {
		
		CustomerBo updatedCustomerBo = customerRepository.save(checkAndUpdateCustomerFields(customerId, lastname, firstname));
		
		return mapCustomerBoToCustomerDto(updatedCustomerBo, new CustomerDto());
	}

	private CustomerBo checkAndUpdateCustomerFields(String customerId, String firstname, String lastname) throws NotFoundException {
		CustomerBo customerBoToUpdateBo = customerRepository.findById(customerId)
				.orElseThrow (() -> new NotFoundException("The customer you want to update doesnt exists"));
		
		if (firstname != null) customerBoToUpdateBo.setFirstname(firstname);;
		if (lastname != null) customerBoToUpdateBo.setLastname(lastname);;
		
		return customerBoToUpdateBo;
	}
	
	private CustomerDto mapCustomerBoToCustomerDto(CustomerBo customerBo, CustomerDto customerDto) {
		
		customerDto.setId(customerBo.getCustomerId());
		customerDto.setFirstname(customerBo.getFirstname());
		customerDto.setLastname(customerBo.getLastname());
		if (customerBo.getGoalBos() != null)
			customerDto.setGoalDtos(customerBo.getGoalBos().stream()
				.map(goal -> mapper.map(goal, GoalDto.class)).collect(Collectors.toList()));
		
		return customerDto;
	}
	
	private CustomerBo mapCustomerDtoToCustomerBo(CustomerDto customerDto, CustomerBo customerBo) {
		customerBo.setCustomerId(customerDto.getId());
		customerBo.setFirstname(customerDto.getFirstname());
		customerBo.setLastname(customerDto.getLastname());
		if (customerDto.getGoalDtos() != null)
			customerBo.setGoalBos(customerDto.getGoalDtos().stream()
				.map(goal -> mapper.map(goal, GoalBo.class)).collect(Collectors.toList()));
		
		return customerBo;
	}
	
}
