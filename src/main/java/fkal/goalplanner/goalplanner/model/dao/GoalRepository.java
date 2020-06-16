package fkal.goalplanner.goalplanner.model.dao;

import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalBo, String> {

    @Query(value = "select * from goal_planner.goal where user_id =: id", nativeQuery = true)
    List<GoalBo> findGoalsByUserId(@Param(value = "id") String userId);

    @Query(value = "select * from goal_planner.goal where category_id =: id", nativeQuery = true)
    List<GoalBo> getGoalsByCategoryId(@Param(value = "id") String categoryId);
}
