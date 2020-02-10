package fkal.goalplanner.goalplanner.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fkal.goalplanner.goalplanner.model.bo.CategoryBo;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryBo, String> {

}
