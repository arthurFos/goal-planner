package fkal.goalplanner.goalplanner.model.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(schema = "goal_planner", name = "customer")
@Entity
@Data
public class CustomerBo {
	
	@Id
	private String customer_id;

	private String lastname;

	private String firstname;

	@OneToMany(mappedBy = "customerBo")
	private List<GoalBo> goalBos;
}