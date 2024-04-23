package org.vaadin.example.ui.editViews;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;

public class EditExerciseAnswerView extends VerticalLayout {
        public EditExerciseAnswerView() {


            TextArea textArea = new TextArea();
            textArea.setWidth("80%");
            textArea.setMinHeight("200px");
            textArea.setMaxHeight("300px");

            textArea.setValueChangeMode(ValueChangeMode.EAGER);

            textArea.addValueChangeListener(e -> {
                e.getSource()
                        .setHelperText("Количество символов: " + e.getValue().length());
            });

            add(textArea);
        }



}
