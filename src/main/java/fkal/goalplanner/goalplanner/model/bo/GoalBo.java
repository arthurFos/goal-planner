package fkal.goalplanner.goalplanner.model.bo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(schema = "goal_planner", name = "goal")
@Entity
@Data
public class GoalBo {

    @Id
    @Column(name = "goal_id")
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
    @JoinColumn(name = "userId")
    private Userbo userBo;

}
