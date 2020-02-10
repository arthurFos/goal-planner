package fkal.goalplanner.goalplanner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import fkal.goalplanner.goalplanner.service.GoalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/goal")
@RestController
@RequiredArgsConstructor
@Api(tags = "GoalController", value = "goal")
public class GoalController {

	private final GoalService goalService;
	
	@GetMapping
	@ApiOperation(value = "Returns all goals (filter)")
	public ResponseEntity<List<GoalDto>> getGoals() {
		
		return ResponseEntity.ok(goalService.getAllGoals());
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Returns goal by its id")
	public ResponseEntity<GoalDto> getOneGoal(
			@ApiParam(value = "The id of the goal to return") @PathVariable(name = "id") String goalId) throws NotFoundException {
	
		return ResponseEntity.ok(goalService.getGoalById(goalId));
	}
	
	@GetMapping(value = "/{customer_id}/customers")
	@ApiOperation(value = "Returns all goal of a customer")
	public ResponseEntity<List<GoalDto>> getGoalByCustomer(
			@ApiParam(value = "The Customerid to return its goals", required = true) @PathVariable(value = "customer_id") String customerId) {

		return ResponseEntity.ok(goalService.getGoalByCustomerId(customerId));
	}
	
	@GetMapping(value =  "/{category_id}/category")
	@ApiOperation(value = "Return all goals of a category")
	public ResponseEntity<List<GoalDto>> getGoalByCategory(
			@ApiParam(value = "The id of the category to returning the goals", required = true) @PathVariable (name = "category_id") String categoryId) {

		return ResponseEntity.ok(goalService.getGoalByCategoryId(categoryId));
	}
	
	@PostMapping
	@ApiOperation(value = "Create a new goal")
	public ResponseEntity<GoalDto> createGoal(
			@ApiParam(value = "The goal to be created", required = true) @RequestBody GoalDto goalDto) throws Exception {

		return ResponseEntity.ok(goalService.createGoal(goalDto));
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Updated an existing goal")
	public ResponseEntity<GoalDto> updateGoal(
			@ApiParam(value = "The id of the goal to update") @PathVariable (name = "id") String goalId,
			@ApiParam(value = "The goal to update", required = true) @RequestBody GoalDto goalDto) throws NotFoundException {

		return ResponseEntity.ok(goalService.updateGoal(goalId, goalDto));
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Delete a goal")
	public ResponseEntity<Void> deleteGoal(
			@ApiParam(value = "The id of the goal to delete", required = true) @PathVariable (name = "id") String goalId) {

		goalService.deleteGoal(goalId);
		
		return ResponseEntity.ok().build();
	}
}
