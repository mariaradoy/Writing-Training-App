package org.vaadin.example.ui.editViews;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
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
import org.vaadin.example.entity.User;
import org.vaadin.example.entity.World;
import org.vaadin.example.service.WorldService;

import java.util.Optional;

@Route(value = EditWorldsView.ROUTE, layout = MainLayout.class)
@PermitAll
public class EditWorldsView extends VerticalLayout {
    public static final String ROUTE = "editworlds";
    World world = null;

    EditWorldsView(@Autowired WorldService worldService) {

        Long worldId = (Long) VaadinSession.getCurrent().getAttribute("worldId");

        if(worldId != null) {
            Optional<World> optionalWorld = worldService.getWorldById(worldId);
            if(optionalWorld.isPresent()) {
                world = optionalWorld.get();
            } else {
                Notification.show("Такого мира не существует", 3000, Notification.Position.TOP_CENTER);
            }
        }


        Accordion history = new Accordion();

        TextArea historyTextArea = new TextArea();
        historyTextArea.setWidth("80%");
        historyTextArea.setMinHeight("200px");
        historyTextArea.setMaxHeight("300px");

        history.getElement().getStyle().set("width", "100%");
        history.add("" +
                "История", historyTextArea);

        Accordion economy = new Accordion();

        TextArea economyTextArea = new TextArea();
        economyTextArea.setWidth("80%");
        economyTextArea.setMinHeight("200px");
        economyTextArea.setMaxHeight("300px");

        economy.getElement().getStyle().set("width", "100%");
        economy.add("Экономика", economyTextArea);

        Accordion politics = new Accordion();

        TextArea politicsTextArea = new TextArea();
        politicsTextArea.setWidth("80%");
        politicsTextArea.setMinHeight("200px");
        politicsTextArea.setMaxHeight("300px");

        politics.getElement().getStyle().set("width", "100%");
        politics.add("Политика", politicsTextArea);

        Accordion religion = new Accordion();

        TextArea religionTextArea = new TextArea();
        religionTextArea.setWidth("80%");
        religionTextArea.setMinHeight("200px");
        religionTextArea.setMaxHeight("300px");

        religion.getElement().getStyle().set("width", "100%");
        religion.add("Религия", religionTextArea);

        Accordion geography = new Accordion();

        TextArea geographyTextArea = new TextArea();
        geographyTextArea.setWidth("80%");
        geographyTextArea.setMinHeight("200px");
        geographyTextArea.setMaxHeight("300px");

        geography.getElement().getStyle().set("width", "100%");
        geography.add("География", geographyTextArea);

        Accordion culture = new Accordion();

        TextArea cultureTextArea = new TextArea();
        cultureTextArea.setWidth("80%");
        cultureTextArea.setMinHeight("200px");
        cultureTextArea.setMaxHeight("300px");


        culture.getElement().getStyle().set("width", "100%");
        culture.add("Культура", cultureTextArea);

        Accordion inhabitants = new Accordion();

        TextArea inhabitantsTextArea = new TextArea();
        inhabitantsTextArea.setWidth("80%");
        inhabitantsTextArea.setMinHeight("200px");
        inhabitantsTextArea.setMaxHeight("300px");

        inhabitants.getElement().getStyle().set("width", "100%");
        inhabitants.add("Обитатели", inhabitantsTextArea);

        Accordion magic = new Accordion();

        TextArea magicTextArea = new TextArea();
        magicTextArea.setWidth("80%");
        magicTextArea.setMinHeight("200px");
        magicTextArea.setMaxHeight("300px");

        magic.getElement().getStyle().set("width", "100%");
        magic.add("Магия", magicTextArea);

        H3 worldName = new H3(world.getWorldName());

        historyTextArea.setValue(world.getHistoryText());
        economyTextArea.setValue(world.getEconomyText());
        politicsTextArea.setValue(world.getEconomyText());
        religionTextArea.setValue(world.getReligionText());
        geographyTextArea.setValue(world.getGeographyText());
        cultureTextArea.setValue(world.getCultureText());
        inhabitantsTextArea.setValue(world.getInhabitantsText());
        magicTextArea.setValue(world.getMagicText());


        Button saveButton = new Button("Сохранить");
        //<theme-editor-local-classname>
        saveButton.addClassName("save-button");

        saveButton.addClickListener(event -> {
            User currentUser = (User) VaadinSession.getCurrent()
                    .getAttribute("currentUser");

            World world = new World();
            world.setUser(currentUser);

            world.setHistoryText(historyTextArea.getValue());
            world.setEconomyText(economyTextArea.getValue());
            world.setPoliticsText(politicsTextArea.getValue());
            world.setReligionText(religionTextArea.getValue());
            world.setGeographyText(geographyTextArea.getValue());
            world.setCultureText(cultureTextArea.getValue());
            world.setInhabitantsText(inhabitantsTextArea.getValue());
            world.setMagicText(magicTextArea.getValue());

            worldService.updateWorld(world, worldId);

            Notification.show("Информация сохранена", 3000, Notification.Position.TOP_CENTER);

            getUI().ifPresent(ui->ui.navigate("myworlds"));
        });

        add(worldName, history, economy, politics, religion, geography,
                culture, inhabitants, magic, saveButton);
    }
}
