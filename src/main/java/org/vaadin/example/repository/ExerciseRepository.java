package org.vaadin.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.example.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
