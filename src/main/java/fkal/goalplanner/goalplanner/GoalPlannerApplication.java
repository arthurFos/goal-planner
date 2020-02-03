package fkal.goalplanner.goalplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GoalPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoalPlannerApplication.class, args);
	}

}