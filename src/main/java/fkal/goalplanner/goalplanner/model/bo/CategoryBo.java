package fkal.goalplanner.goalplanner.model.bo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(schema = "goal_planner", name = "category")
@Entity
@Data
public class CategoryBo {
	
	@Id
	@Column(name = "id")
	private String categoryId;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "categoryBo")
	private List<GoalBo> goalBos;
}
