package org.vaadin.example.ui.editViews;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.MainLayout;
import org.vaadin.example.entity.StoryPlot;
import org.vaadin.example.service.StoryPlotService;

import java.util.Optional;

@Route(value = EditPlotsView.ROUTE, layout = MainLayout.class)
@PermitAll
public class EditPlotsView extends VerticalLayout {
    StoryPlot storyPlot = null;
    public static final String ROUTE = "editplots";


    EditPlotsView(@Autowired StoryPlotService storyPlotService) {

        Long storyPlotId = (Long) VaadinSession.getCurrent().getAttribute("storyPlotId");

        if(storyPlotId != null){
            Optional<StoryPlot> optionalStoryPlot = storyPlotService.getStoryPlotById(storyPlotId);
            if (optionalStoryPlot.isPresent()) {
                storyPlot = optionalStoryPlot.get();

            } else {
                Notification.show("Такой работы не существует", 3000, Notification.Position.TOP_CENTER);
            }
        }


        TextArea ideaTextArea = new TextArea("Текст идеи");
        ideaTextArea.setWidth("80%");
        ideaTextArea.setMinHeight("200px");
        ideaTextArea.setMaxHeight("300px");

        TextArea shortDescriptionTextArea = new TextArea("Краткое описание");
        shortDescriptionTextArea.setWidth("80%");
        shortDescriptionTextArea.setMinHeight("200px");
        shortDescriptionTextArea.setMaxHeight("300px");


        TextArea synopsisTextArea = new TextArea("Синопсис");
        synopsisTextArea.setWidth("80%");
        synopsisTextArea.setMinHeight("200px");
        synopsisTextArea.setMaxHeight("300px");

        TextArea longSynopsisTextArea = new TextArea("Расширенный синопсис");
        longSynopsisTextArea.setWidth("80%");
        longSynopsisTextArea.setMinHeight("200px");
        longSynopsisTextArea.setMaxHeight("300px");

        H3 storyName = new H3(storyPlot.getStoryName());

        ideaTextArea.setValue(storyPlot.getIdeaText());
        shortDescriptionTextArea.setValue(storyPlot.getShortDescriptionText());
        synopsisTextArea.setValue(storyPlot.getSynopsisText());
        longSynopsisTextArea.setValue(storyPlot.getLongSynopsisText());

        Button saveButton = new Button("Сохранить");
        //<theme-editor-local-classname>
        saveButton.addClassName("save-button");

        saveButton.addClickListener(event -> {

            storyPlot.setIdeaText(ideaTextArea.getValue());
            storyPlot.setShortDescriptionText(shortDescriptionTextArea.getValue());
            storyPlot.setSynopsisText(synopsisTextArea.getValue());
            storyPlot.setLongSynopsisText(longSynopsisTextArea.getValue());

            Notification.show("Изменение сохранено", 3000, Notification.Position.TOP_CENTER);

            storyPlotService.updateStoryPlote(storyPlot, storyPlot.getId());

        });

        add(storyName, ideaTextArea, shortDescriptionTextArea, synopsisTextArea, longSynopsisTextArea, saveButton);

    }
}
