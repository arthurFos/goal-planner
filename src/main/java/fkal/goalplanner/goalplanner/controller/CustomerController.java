package fkal.goalplanner.goalplanner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fkal.goalplanner.goalplanner.model.dto.CustomerDto;
import fkal.goalplanner.goalplanner.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@GetMapping(value = "/")
	@ApiOperation(value = "Returns all customers (filter)")
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		
		return ResponseEntity.ok(customerService.getCustomers());
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Returns customer by its id")
	public ResponseEntity<CustomerDto> getOneCustomer(
			@ApiParam(value = "The id of the customer to return", required = true)
			@PathVariable(name = "id") String customerId) throws NotFoundException { 
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	
	@PostMapping
	@ApiOperation(value = "Create a new customer")
	public ResponseEntity<CustomerDto> createCustomer(
			@ApiParam(value = "CustomerDto", required = true) @RequestBody CustomerDto customerDto) throws Exception {
		
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}
	
	@PatchMapping(value = "/{id}")
	@ApiOperation(value = "Update an existing customer")
	public ResponseEntity<CustomerDto> updateCustomer(
			@ApiParam(value = "Customer id of the customer to update", required = true) @PathVariable(value = "id") String customerId,
			@ApiParam(value = "Customer lastname") @RequestParam(required = false) String lastname,
			@ApiParam(value = "Customer firstname") @RequestParam(required = false) String firstname) throws NotFoundException {
	
		return ResponseEntity.ok(customerService.updateCustomer(customerId, lastname, firstname));
	}
}
