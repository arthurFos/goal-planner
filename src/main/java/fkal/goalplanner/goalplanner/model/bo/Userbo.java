package fkal.goalplanner.goalplanner.model.bo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(schema = "goal_planner", name = "user")
@Entity
@Data
public class Userbo {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String lastname;

    private String firstname;

    @OneToMany(mappedBy = "userBo")
    private List<GoalBo> goalBos;
}
