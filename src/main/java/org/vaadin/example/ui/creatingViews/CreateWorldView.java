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
import org.vaadin.example.entity.User;
import org.vaadin.example.entity.World;
import org.vaadin.example.service.WorldService;


@Route(value = CreateWorldView.ROUTE, layout = MainLayout.class)
@PermitAll
public class CreateWorldView extends VerticalLayout {

    public static final String ROUTE = "addworld";

    @Autowired
    private WorldService worldService;

    CreateWorldView() {
        setSizeFull();

        TextField worldName = new TextField();
        worldName.setLabel("Название мира");

        Accordion history = new Accordion();

        TextArea historyText = new TextArea();
        historyText.setWidth("80%");
        historyText.setMinHeight("200px");
        historyText.setMaxHeight("300px");
        historyText.setPlaceholder("Эпохи. Ключевые исторические события. Технологии");

        history.getElement().getStyle().set("width", "100%");
        history.add("" +
                "История", historyText);

        Accordion economy = new Accordion();

        TextArea economyText = new TextArea();
        economyText.setWidth("80%");
        economyText.setMinHeight("200px");
        economyText.setMaxHeight("300px");
        economyText.setPlaceholder("Валюты. Ресурсы. Тороговые компании");

        economy.getElement().getStyle().set("width", "100%");
        economy.add("Экономика", economyText);

        Accordion politics = new Accordion();

        TextArea politicsText = new TextArea();
        politicsText.setWidth("80%");
        politicsText.setMinHeight("200px");
        politicsText.setMaxHeight("300px");
        politicsText.setPlaceholder("Системы правления. Социальные структуры");

        politics.getElement().getStyle().set("width", "100%");
        politics.add("Политика", politicsText);

        Accordion religion = new Accordion();

        TextArea religionText = new TextArea();
        religionText.setWidth("80%");
        religionText.setMinHeight("200px");
        religionText.setMaxHeight("300px");
        religionText.setPlaceholder("Божества. Мифы и легенды. Ритуалы");

        religion.getElement().getStyle().set("width", "100%");
        religion.add("Религия", religionText);

        Accordion geography = new Accordion();

        TextArea geographyText = new TextArea();
        geographyText.setWidth("80%");
        geographyText.setMinHeight("200px");
        geographyText.setMaxHeight("300px");
        geographyText.setPlaceholder("Карта мира");

        geography.getElement().getStyle().set("width", "100%");
        geography.add("География", geographyText);

        Accordion culture = new Accordion();

        TextArea cultureText = new TextArea();
        cultureText.setWidth("80%");
        cultureText.setMinHeight("200px");
        cultureText.setMaxHeight("300px");
        cultureText.setPlaceholder("Мода. Исскуство. Образование");

        culture.getElement().getStyle().set("width", "100%");
        culture.add("Культура", cultureText);

        Accordion inhabitants = new Accordion();

        TextArea inhabitantsText = new TextArea();
        inhabitantsText.setWidth("80%");
        inhabitantsText.setMinHeight("200px");
        inhabitantsText.setMaxHeight("300px");
        inhabitantsText.setPlaceholder("Расы. Животные. Растения");

        inhabitants.getElement().getStyle().set("width", "100%");
        inhabitants.add("Обитатели", inhabitantsText);

        Accordion magic = new Accordion();

        TextArea magicText = new TextArea();
        magicText.setWidth("80%");
        magicText.setMinHeight("200px");
        magicText.setMaxHeight("300px");
        magicText.setPlaceholder("Системы магии. Возможности и ограничения");

        magic.getElement().getStyle().set("width", "100%");
        magic.add("Магия", magicText);

        Button saveWorldButton = new Button("Сохранить");

        saveWorldButton.addClickListener(event -> {
            User currentUser = (User) VaadinSession.getCurrent()
                    .getAttribute("currentUser");

            World world = new World();
            world.setUser(currentUser);
            world.setWorldName(worldName.getValue());
            world.setHistoryText(historyText.getValue());
            world.setEconomyText(economyText.getValue());
            world.setPoliticsText(politicsText.getValue());
            world.setReligionText(religionText.getValue());
            world.setGeographyText(geographyText.getValue());
            world.setCultureText(cultureText.getValue());
            world.setInhabitantsText(inhabitantsText.getValue());
            world.setMagicText(magicText.getValue());

            worldService.newWorld(world);

            Notification.show("Мир успешно создан", 3000, Notification.Position.TOP_CENTER);

            getUI().ifPresent(ui->ui.navigate("myworlds"));
        });

        add(worldName, history, economy, politics, religion, geography,
                culture, inhabitants, magic, saveWorldButton);
    }
}