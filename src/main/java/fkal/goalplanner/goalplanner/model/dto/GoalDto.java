package fkal.goalplanner.goalplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "GoalDto")
@Data
public class GoalDto {

    @ApiParam(value = "Goals id")
    private String id;

    @ApiParam(value = "Goals name")
    private String name;

    @ApiParam(value = "Descriptions name")
    private String description;

    @ApiParam(value = "Begin of the goal")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginAt;

    @ApiParam(value = "Ende of the goal")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAt;

    @ApiParam(value = "The category of this goal")
    private CategoryDto categoryDto;

    @ApiParam(value = "The user of this goal")
    private UserDto userDto;
}
