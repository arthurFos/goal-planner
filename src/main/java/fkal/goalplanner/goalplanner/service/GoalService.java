package fkal.goalplanner.goalplanner.service;

import fkal.goalplanner.goalplanner.model.bo.CategoryBo;
import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import fkal.goalplanner.goalplanner.model.bo.Userbo;
import fkal.goalplanner.goalplanner.model.dao.GoalRepository;
import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import fkal.goalplanner.goalplanner.model.dto.UserDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<GoalDto> fetchAllGoals() {
        return goalRepository.findAll().stream()
                .map(goalBo -> mapGoalBoToGoalDto(goalBo, new GoalDto())).collect(Collectors.toList());
    }

    public GoalDto fetchOneGoal(String goalId) throws NotFoundException {
        GoalBo goalBo = goalRepository.findById(goalId)
                .orElseThrow(() -> new NotFoundException("Could not find the goal with id -> " + goalId));

        return mapGoalBoToGoalDto(goalBo, new GoalDto());
    }

    public GoalDto createGoal(GoalDto goalDto) {
        if (goalRepository.existsById(goalDto.getId()))
            throw new BadRequestException("The goal with id -> " + goalDto.getId() + " already exists");
        GoalBo goalBo = goalRepository.save(mapGoalDtoToGoalBo(goalDto, new GoalBo()));

        return mapGoalBoToGoalDto(goalBo, new GoalDto());
    }

    public GoalDto updateGoal(String goalId, GoalDto goalDto) throws NotFoundException {

        GoalBo goalBo = goalRepository.findById(goalId)
                .orElseThrow(() -> new NotFoundException("The goal with goal id -> " + goalId + " doesnt exist"));

        GoalBo goalBo1 = mapGoalDtoToGoalBo(goalDto, goalBo);

        return mapGoalBoToGoalDto(goalRepository.save(goalBo1), goalDto);
    }

    public void deleteGoal(String goalId) {
        goalRepository.deleteById(goalId);
    }

    public List<GoalDto> fetchAllGoalsForUser(String userId) {
        return goalRepository.findGoalsByUserId(userId).stream()
                .map(goalBo -> mapGoalBoToGoalDto(goalBo, new GoalDto())).collect(Collectors.toList());
    }

    public List<GoalDto> fetchGoalsForCategory(String categoryId) {
        return goalRepository.getGoalsByCategoryId(categoryId).stream()
                .map(goalBo -> mapGoalBoToGoalDto(goalBo, new GoalDto())).collect(Collectors.toList());
    }

    private GoalDto mapGoalBoToGoalDto(GoalBo goalBo, GoalDto goalDto) {
        goalDto.setId(goalBo.getGoalId());
        goalDto.setName(goalBo.getName());
        goalDto.setDescription(goalBo.getDescription());
        goalDto.setBeginAt(goalBo.getBeginAt());
        goalDto.setEndAt(goalBo.getEndAt());
        goalDto.setCategoryDto(mapper.map(goalBo.getCategoryBo(), CategoryDto.class));
        goalDto.setUserDto(mapper.map(goalBo.getUserBo(), UserDto.class));

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
        if (goalDto.getUserDto() != null)
            goalBo.setUserBo(mapper.map(goalDto.getUserDto(), Userbo.class));

        return goalBo;
    }

}
