package fkal.goalplanner.goalplanner.model.bo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(schema = "goal_planner", name = "category")
@Entity
@Data
public class CategoryBo {
	
	@Id
	@Column(name = "category_id")
	private String categoryId;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "categoryBo")
	private List<GoalBo> goalBos;
}
