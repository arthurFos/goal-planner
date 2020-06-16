package fkal.goalplanner.goalplanner.model.dao;

import fkal.goalplanner.goalplanner.model.bo.CategoryBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryBo, String> {
}
