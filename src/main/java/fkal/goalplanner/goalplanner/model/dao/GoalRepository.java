package fkal.goalplanner.goalplanner.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fkal.goalplanner.goalplanner.model.bo.GoalBo;

@Repository
public interface GoalRepository extends JpaRepository<GoalBo, String>{

	@Query(value = "select * from goal_planner.goal where customer_id =: id", nativeQuery = true)
	List<GoalBo> getGoalsByCustomerId(@Param(value = "id") String customerId);
	
	@Query(value = "select * from goal_planner.goal where category_id =: id", nativeQuery = true)
	List<GoalBo> getGoalsByCategoryId(@Param(value = "id") String categoryId);
}
