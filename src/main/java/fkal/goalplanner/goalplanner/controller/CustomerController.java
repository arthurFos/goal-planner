package fkal.goalplanner.goalplanner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fkal.goalplanner.goalplanner.model.dto.CustomerDto;
import fkal.goalplanner.goalplanner.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
			@ApiParam(value = "The id of the customer to return", required = true) @PathVariable(name = "id") String customerId) { 
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
}
