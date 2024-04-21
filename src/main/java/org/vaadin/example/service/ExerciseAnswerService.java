package org.vaadin.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.entity.ExerciseAnswer;
import org.vaadin.example.repository.ExerciseAnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseAnswerService {

    @Autowired
    private ExerciseAnswerRepository answerRepository;

    public ExerciseAnswerService(ExerciseAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    public List<ExerciseAnswer> all() {
        return answerRepository.findAll();
    }

    public Optional<ExerciseAnswer> getAnswerById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public void newAnswer(ExerciseAnswer answer) {
        answerRepository.save(answer);
    }

    public ExerciseAnswer updatedAnswer(ExerciseAnswer updatedAnswer, Long answerId) {
        return answerRepository.save(answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setExerciseAnswer(updatedAnswer.getExerciseAnswer());

                    return answerRepository.save(updatedAnswer);
                })
                .orElseGet(() -> {
                    updatedAnswer.setId(answerId);
                    return answerRepository.save(updatedAnswer);
                }));
    }

    public boolean deleteExerciseAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
        return true;
    }
}
