package fkal.goalplanner.goalplanner.controller;

import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import fkal.goalplanner.goalplanner.service.GoalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/goals")
@RestController
@RequiredArgsConstructor
@Api(tags = "GoalController", value = "goals")
@CrossOrigin
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    @ApiOperation("Returns all goals (filter)")
    public ResponseEntity<List<GoalDto>> getGoals() {

        return ResponseEntity.ok(goalService.fetchAllGoals());
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a goal by its id")
    public ResponseEntity<GoalDto> getOneGoal(
            @ApiParam("goalId") @PathVariable("id") String goalId) throws NotFoundException {

        return ResponseEntity.ok(goalService.fetchOneGoal(goalId));
    }

    @GetMapping("/users/{id}")
    @ApiOperation("Returns all goals of a user")
    public ResponseEntity<List<GoalDto>> getGoalsByUser(
            @ApiParam(value = "userId", required = true) @PathVariable("id") String userId) {

        return ResponseEntity.ok(goalService.fetchAllGoalsForUser(userId));
    }

    @GetMapping("/categories/{id}")
    @ApiOperation("Return all goals of a category")
    public ResponseEntity<List<GoalDto>> getGoalsByCategory(
            @ApiParam(value = "categoryId", required = true) @PathVariable("id") String categoryId) {

        return ResponseEntity.ok(goalService.fetchGoalsForCategory(categoryId));
    }

    @PostMapping
    @ApiOperation("Create a new goal")
    public ResponseEntity<GoalDto> createGoal(
            @ApiParam(value = "GoalDto", required = true) @RequestBody GoalDto goalDto) {

        return ResponseEntity.ok(goalService.createGoal(goalDto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Updated an existing goal")
    public ResponseEntity<GoalDto> updateGoal(
            @ApiParam("goalId") @PathVariable("id") String goalId,
            @ApiParam(value = "GoalDto", required = true) @RequestBody GoalDto goalDto) throws NotFoundException {

        return ResponseEntity.ok(goalService.updateGoal(goalId, goalDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a goal")
    public ResponseEntity<Void> deleteGoal(
            @ApiParam(value = "goalId", required = true) @PathVariable("id") String goalId) {

        goalService.deleteGoal(goalId);

        return ResponseEntity.ok().build();
    }
}
