package fkal.goalplanner.goalplanner.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@ApiModel(value = "CustomerDto")
@Data
public class CustomerDto {
	
	@ApiParam(value = "customer id")
	private String id;

	@ApiParam(value = "customer lastname")
	private String lastname;
	
	@ApiParam(value = "customer firstname")
	private String firstname;

	@ApiParam(value = "goals of the customer")
	private List<GoalDto> goalDtos;
}
