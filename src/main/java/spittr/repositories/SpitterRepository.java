package spittr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spittr.entities.Spitter;

@Repository
public interface SpitterRepository extends JpaRepository<Spitter, Long> {

	  Spitter findByUsername(String username);
}