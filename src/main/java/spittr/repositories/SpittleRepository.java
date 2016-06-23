package spittr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spittr.entities.Spittle;


@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Long> {

}