package org.vaadin.example.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

@Service
public class ExerciseService {
    public String getRandomExercise() {
        String randomExercise = null;
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/writing_exercise", "postgres", "postgres")) {
            String sql = "SELECT * FROM exercises ORDER BY random() LIMIT 1";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    randomExercise = resultSet.getString("text");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return randomExercise;
    }
}
