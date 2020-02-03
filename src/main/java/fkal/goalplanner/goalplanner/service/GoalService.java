package fkal.goalplanner.goalplanner.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fkal.goalplanner.goalplanner.model.dao.GoalRepository;
import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {

	private final GoalRepository goalRepository;
	private final Logger logger = Logger.getLogger(GoalService.class);
	
	public List<GoalDto> getAllGoals() {
		return null;
	}

	public GoalDto getGoalById(String goalId) {
		return null;
	}

	public GoalDto createGoal(GoalDto goalDto) {
		return null;
	}

	public GoalDto updateGoal(String goalId, GoalDto goalDto) {
		return null;
	}

	
	public void deleteGoal(String goalId) {
	}

	
	public GoalDto getGoalByCustomerId(String customerId) {
		return null;
	}

	public CategoryDto getGoalByCategoryId(String categoryId) {
		return null;
	}

}
