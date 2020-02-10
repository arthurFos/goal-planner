package fkal.goalplanner.goalplanner.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fkal.goalplanner.goalplanner.model.bo.CustomerBo;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerBo, String> {

	List<CustomerBo> findByFirstname(String firstname);
	List<CustomerBo> findByLastname(String lastname);
}
