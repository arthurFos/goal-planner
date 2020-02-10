package fkal.goalplanner.goalplanner.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fkal.goalplanner.goalplanner.model.bo.CategoryBo;
import fkal.goalplanner.goalplanner.model.bo.CustomerBo;
import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import fkal.goalplanner.goalplanner.model.dao.GoalRepository;
import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.model.dto.CustomerDto;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {

	private final GoalRepository goalRepository;
	private final ModelMapper mapper = new ModelMapper();
	private final Logger logger = Logger.getLogger(GoalService.class);
	
	public List<GoalDto> getAllGoals() {
		return goalRepository.findAll().stream()
				.map(goalBo -> mapGoalBoToGoalDto(goalBo, new GoalDto())).collect(Collectors.toList());
	}

	public GoalDto getGoalById(String goalId) throws NotFoundException {
		GoalBo goalBo = goalRepository.findById(goalId)
				.orElseThrow(() -> new NotFoundException("Could not find any goal with goal id -> " + goalId));
		
		return mapGoalBoToGoalDto(goalBo, new GoalDto());
	}

	public GoalDto createGoal(GoalDto goalDto) throws Exception {
		if (goalRepository.existsById(goalDto.getId()))
			throw new Exception("The goal with id -> " +goalDto.getId() + " already exists");
		GoalBo goalBo = goalRepository.save(mapGoalDtoToGoalBo(goalDto, new GoalBo()));
		
		return mapGoalBoToGoalDto(goalBo, new GoalDto());
	}

	public GoalDto updateGoal(String goalId, GoalDto goalDto) throws NotFoundException {
		
		if (!goalRepository.existsById(goalId))
			throw new NotFoundException("The goal with goal id -> " + goalId + " doesnt exist");
		
		GoalBo goalBo = mapGoalDtoToGoalBo(goalDto, goalRepository.findById(goalId).get());
		
		return mapGoalBoToGoalDto(goalRepository.save(goalBo), goalDto);
	}

	
	public void deleteGoal(String goalId) {
		goalRepository.deleteById(goalId);
	}

	
	public List<GoalDto> getGoalByCustomerId(String customerId) {
		return goalRepository.getGoalsByCustomerId(customerId).stream()
				.map(goalBo -> mapGoalBoToGoalDto(goalBo, new GoalDto())).collect(Collectors.toList());
	}

	public List<GoalDto> getGoalByCategoryId(String categoryId) {
		return goalRepository.getGoalsByCategoryId(categoryId).stream()
				.map(goalBo -> mapGoalBoToGoalDto(goalBo, new GoalDto())).collect(Collectors.toList());	}
	
	private GoalDto mapGoalBoToGoalDto(GoalBo goalBo, GoalDto goalDto) {
		goalDto.setId(goalBo.getGoalId());
		goalDto.setName(goalBo.getName());
		goalDto.setDescription(goalBo.getDescription());
		goalDto.setBeginAt(goalBo.getBeginAt());
		goalDto.setEndAt(goalBo.getEndAt());
		goalDto.setCategoryDto(mapper.map(goalBo.getCategoryBo(), CategoryDto.class));
		goalDto.setCustomerDto(mapper.map(goalBo.getCustomerBo(), CustomerDto.class));
		
		return goalDto;
	}
	
	private GoalBo mapGoalDtoToGoalBo(GoalDto goalDto, GoalBo goalBo) {
		goalBo.setGoalId(goalDto.getId());
		goalBo.setBeginAt(goalDto.getBeginAt());
		goalBo.setEndAt(goalDto.getEndAt());
		goalBo.setName(goalDto.getName());
		goalBo.setDescription(goalDto.getDescription());
		if (goalDto.getCategoryDto() != null)
			goalBo.setCategoryBo(mapper.map(goalDto.getCategoryDto(), CategoryBo.class));
		if (goalDto.getCustomerDto() != null)
			goalBo.setCustomerBo(mapper.map(goalDto.getCustomerDto(), CustomerBo.class));
		
		return goalBo;
	}

}
