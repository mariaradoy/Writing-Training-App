package org.vaadin.example.ui.works;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.vaadin.example.MainLayout;
import org.vaadin.example.entity.ExerciseAnswer;


import java.util.List;

@Route(value = ExercisesView.ROUTE, layout = MainLayout.class)
@PermitAll
public class ExercisesView extends VerticalLayout {

    public static final String ROUTE = "myexercises";

    ExercisesView() {

        Grid<ExerciseAnswer> answerGrid = new Grid<>();

        answerGrid.addColumn(ExerciseAnswer::getExerciseAnswer)
                .setHeader("Текст задания");

        answerGrid.addColumn(ExerciseAnswer::getExerciseAnswer)
                .setHeader("Ответ");

        add(answerGrid);
    }
}
