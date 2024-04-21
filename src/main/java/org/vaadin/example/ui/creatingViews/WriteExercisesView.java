package org.vaadin.example.ui.creatingViews;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.vaadin.example.entity.Exercise;
import org.vaadin.example.entity.ExerciseAnswer;
import org.vaadin.example.entity.User;
import org.vaadin.example.service.ExerciseAnswerService;
import org.vaadin.example.service.ExerciseService;
import org.vaadin.example.MainLayout;

import java.io.Serial;

@Route(value = WriteExercisesView.ROUTE, layout = MainLayout.class)
@PermitAll
public class WriteExercisesView extends VerticalLayout {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final String ROUTE = "newexercise";
    public static final String TITLE = "Упражнения";


    public WriteExercisesView(ExerciseService exerciseService, ExerciseAnswerService answerService) {
        setSizeFull();

        final Text exerciseText = new Text(exerciseService.getRandomExercise());


        TextArea answerArea = new TextArea();
        answerArea.setWidth("80%");
        answerArea.setMinHeight("200px");
        answerArea.setMaxHeight("300px");

        answerArea.setValueChangeMode(ValueChangeMode.EAGER);

        answerArea.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText("Количество символов: " + e.getValue().length());
        });

        HorizontalLayout buttons = new HorizontalLayout();

        Button newExerciseButton = new Button("Новое упражнение");
        newExerciseButton.addClickListener(buttonClickEvent -> {
            exerciseText.setText(exerciseService.getRandomExercise());
            answerArea.clear();
        });

        Button saveButton = new Button("Сохранить");

        saveButton.addClickListener(event -> {

            User currentUser = (User) VaadinSession.getCurrent()
                    .getAttribute("currentUser");

            ExerciseAnswer answer = new ExerciseAnswer();
            answer.setUser(currentUser);
            answer.setExerciseText(exerciseText.getText());
            answer.setExerciseAnswer(answerArea.getValue());

            answerService.newAnswer(answer);

            Notification.show("Упрражнение успешно сохранено", 3000, Notification.Position.TOP_CENTER);

            UI.getCurrent().getPage().reload();

        });

        buttons.add(newExerciseButton, saveButton);


        add(exerciseText, answerArea, buttons);

    }

}
