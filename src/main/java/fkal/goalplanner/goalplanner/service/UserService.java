package fkal.goalplanner.goalplanner.service;

import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import fkal.goalplanner.goalplanner.model.bo.Userbo;
import fkal.goalplanner.goalplanner.model.dao.UserRepository;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import fkal.goalplanner.goalplanner.model.dto.UserDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ModelMapper mapper = new ModelMapper();
	
	public List<UserDto> fetchAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> mapUserBoToUserDto(user, new UserDto())).collect(Collectors.toList());
	}

	public UserDto fetchOneUser(String userId) throws NotFoundException {
		Userbo userbo = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("Could not found the user with the id -> " + userId));
		
		return mapUserBoToUserDto(userbo, new UserDto());
	}

	public UserDto createUser(UserDto userDto) {
		
		if (userRepository.existsById(userDto.getId()))
			throw new BadRequestException("This user already exists");
		
		Userbo userbo = mapUserDtoToUserBo(userDto, new Userbo());
		userbo.setUserId(UUID.randomUUID().toString());
		
		return mapUserBoToUserDto(userRepository.save(userbo), new UserDto());
	}

	public UserDto updateUser(String userId, String lastName, String firstName) throws NotFoundException {
		
		Userbo updatedUserbo = userRepository.save(checkAndUpdateUserFields(userId, firstName, lastName));
		
		return mapUserBoToUserDto(updatedUserbo, new UserDto());
	}

	private Userbo checkAndUpdateUserFields(String userId, String firstName, String lastName) throws NotFoundException {
		Userbo userbo = userRepository.findById(userId)
				.orElseThrow (() -> new NotFoundException("The user you want to update doesnt exists"));
		
		if (firstName != null) userbo.setFirstname(firstName);
		if (lastName != null) userbo.setLastname(lastName);
		
		return userbo;
	}
	
	private UserDto mapUserBoToUserDto(Userbo userbo, UserDto userDto) {
		
		userDto.setId(userbo.getUserId());
		userDto.setFirstname(userbo.getFirstname());
		userDto.setLastname(userbo.getLastname());
		if (userbo.getGoalBos() != null)
			userDto.setGoalDtos(userbo.getGoalBos().stream()
				.map(goal -> mapper.map(goal, GoalDto.class)).collect(Collectors.toList()));
		
		return userDto;
	}
	
	private Userbo mapUserDtoToUserBo(UserDto userDto, Userbo userbo) {
		userbo.setUserId(userDto.getId());
		userbo.setFirstname(userDto.getFirstname());
		userbo.setLastname(userDto.getLastname());
		if (userDto.getGoalDtos() != null)
			userbo.setGoalBos(userDto.getGoalDtos().stream()
				.map(goal -> mapper.map(goal, GoalBo.class)).collect(Collectors.toList()));
		
		return userbo;
	}
}
