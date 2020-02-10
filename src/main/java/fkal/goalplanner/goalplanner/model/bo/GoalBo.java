package fkal.goalplanner.goalplanner.model.bo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(schema = "goal_planner", name = "goal")
@Entity
@Data
public class GoalBo {

	@Id
	@Column(name = "id")
	private String goalId;
	
	private String name;
	
	private String description;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "begin_at")
	private LocalDateTime beginAt;
	
	@Column(name = "end_at")
	private LocalDateTime endAt;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryBo categoryBo;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private CustomerBo customerBo;
	
}
