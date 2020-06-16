package fkal.goalplanner.goalplanner.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@ApiModel(value = "UserDto")
@Data
public class UserDto {

    @ApiParam(value = "user id")
    private String id;

    @ApiParam(value = "user lastname")
    private String lastname;

    @ApiParam(value = "user firstname")
    private String firstname;

    @ApiParam(value = "goals of the user")
    private List<GoalDto> goalDtos;
}
