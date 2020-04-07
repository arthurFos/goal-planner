package fkal.goalplanner.goalplanner.model.dao;

import fkal.goalplanner.goalplanner.model.bo.Userbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Userbo, String> {

    List<Userbo> findByFirstname(String firstname);

    List<Userbo> findByLastname(String lastname);
}
