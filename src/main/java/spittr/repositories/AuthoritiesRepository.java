package spittr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spittr.entities.Authorities;


@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

}