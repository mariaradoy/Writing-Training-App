package org.vaadin.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.example.entity.ExerciseAnswer;

@Repository
public interface ExerciseAnswerRepository extends JpaRepository<ExerciseAnswer, Long> {
}
