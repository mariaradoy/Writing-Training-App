package org.vaadin.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.vaadin.example.entity.Exercise;
import org.vaadin.example.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ExerciseController {
    @Autowired
    private ExerciseRepository exerciseRepository;


    public List<Exercise> all() {return exerciseRepository.findAll();}

    public Exercise newExercise(Exercise newExercise) { return exerciseRepository.save(newExercise); }

    public Optional<Exercise> findByIdExercise(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise replaceExercise(Exercise newExercise,Long id) {
        return exerciseRepository.save(exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.setText(newExercise.getText());
                    exercise.setType(newExercise.getType());
                    return exerciseRepository.save(exercise);
                })
                .orElseGet(() -> {
                    newExercise.setId(id);
                    return exerciseRepository.save(newExercise);
                }));
    }

    public void deleteExercise(Long id) { exerciseRepository.deleteById(id);}

}
