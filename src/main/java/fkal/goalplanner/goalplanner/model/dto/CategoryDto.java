package fkal.goalplanner.goalplanner.model.dto;

import java.util.List;

import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

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
	private List<GoalBo> goalBos;
}
