package org.vaadin.example.ui.creatingViews;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.MainLayout;
import org.vaadin.example.entity.Status;
import org.vaadin.example.service.StoryPlotService;
import org.vaadin.example.entity.StoryPlot;
import org.vaadin.example.entity.User;

@Route(value = CreatePlotsView.ROUTE, layout = MainLayout.class)
@PermitAll
public class CreatePlotsView extends VerticalLayout {

    public static final String ROUTE = "addplot";

    @Autowired
    private StoryPlotService storyPlotService;


    CreatePlotsView() {

        setSizeFull();

        TextField storyName = new TextField();
        storyName.setLabel("Название работы");

        Accordion idea = new Accordion();

        TextArea ideaText = new TextArea();
        ideaText.setWidth("80%");
        ideaText.setMinHeight("200px");
        ideaText.setMaxHeight("300px");
        ideaText.setPlaceholder("Опишите идею вашей работы одним предложением");

        idea.getElement().getStyle().set("width", "100%");
        idea.add("Идея", ideaText);

        Accordion shortDescription = new Accordion();

        TextArea shortDescriptionText = new TextArea();
        shortDescriptionText.setWidth("80%");
        shortDescriptionText.setMinHeight("200px");
        shortDescriptionText.setMaxHeight("300px");
        shortDescriptionText.setPlaceholder("Расширьте идею вашей работы до 5-6 предложений, определитесь с завязкой, " +
                "развитием конфликта, кульминацией и развязкой.");

        shortDescription.getElement().getStyle().set("width", "100%");
        shortDescription.add("Краткое описание идеи", shortDescriptionText);

        Accordion synopsis = new Accordion();

        TextArea synopsisText = new TextArea();
        synopsisText.setWidth("80%");
        synopsisText.setMinHeight("200px");
        synopsisText.setMaxHeight("300px");
        synopsisText.setPlaceholder("Опираясь на предыдущий абзац распишите каждое предложение подробнее," +
                "чтобы иметь полноценное представление о сюжете.");

        synopsis.getElement().getStyle().set("width", "100%");
        synopsis.add("Синопсис", synopsisText);


        Accordion longSynopsis = new Accordion();

        TextArea longSynopsisText = new TextArea();
        longSynopsisText.setWidth("80%");
        longSynopsisText.setMinHeight("200px");
        longSynopsisText.setMaxHeight("300px");
        longSynopsisText.setPlaceholder("Распишите каждый абзац из синопсиса до целой страницы. " +
                "Так вы будете иметь 5-6 страничную сжатую модель вашего произведения.");

        longSynopsis.getElement().getStyle().set("width", "100%");
        longSynopsis.add("Расширенный синопсис", longSynopsisText);

        Button savePlotButton = new Button("Сохранить");

        savePlotButton.addClickListener(event -> {
            //Получение информации о текущем пользователя из сессии Vaadin
            User currentUser = (User) VaadinSession.getCurrent()
                    .getAttribute("currentUser");

            //Установление полей
            StoryPlot storyPlot = new StoryPlot();
            storyPlot.setUser(currentUser);
            storyPlot.setStoryName(storyName.getValue());
            storyPlot.setIdeaText(ideaText.getValue());
            storyPlot.setShortDescriptionText(shortDescriptionText.getValue());
            storyPlot.setSynopsisText(synopsisText.getValue());
            storyPlot.setLongSynopsisText(longSynopsisText.getValue());
            storyPlot.setStatus(Status.IN_PLAN);

            storyPlotService.newStory(storyPlot);

            Notification.show("Работа успешно создана", 3000, Notification.Position.TOP_CENTER);

            getUI().ifPresent(ui->ui.navigate("mystoryplots"));
        });

        add(storyName, idea, shortDescription, synopsis, longSynopsis, savePlotButton);
    }
}
