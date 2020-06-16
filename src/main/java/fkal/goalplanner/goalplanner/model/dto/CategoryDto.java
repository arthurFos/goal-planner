package fkal.goalplanner.goalplanner.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@ApiModel(value = "CategoryDto")
@Data
public class CategoryDto {

    @ApiParam(value = "Categorys id")
    private String id;

    @ApiParam(value = "Cateorys name")
    private String name;

    @ApiParam(value = "Categorys description")
    private String description;

    @ApiParam(value = "Goals of a category")
    private List<GoalDto> goalDtos;
}
