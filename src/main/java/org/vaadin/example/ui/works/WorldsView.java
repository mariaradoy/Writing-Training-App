package org.vaadin.example.ui.works;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.MainLayout;
import org.vaadin.example.entity.Status;
import org.vaadin.example.entity.World;
import org.vaadin.example.repository.WorldRepository;
import org.vaadin.example.service.WorldService;
import org.vaadin.example.ui.editViews.EditWorldsView;

import java.util.List;

@Route(value = WorldsView.ROUTE, layout = MainLayout.class)
@PermitAll
public class WorldsView extends VerticalLayout {

    public static final String ROUTE = "myworlds";
    @Autowired
    private final WorldService worldService;

    WorldsView(WorldService worldService) {

        this.worldService = worldService;

        setSizeFull();

        List<World> worlds = worldService.all();

        VerticalLayout worldsList = createWorldsLayout(worlds);

        add(worldsList);
    }

    private VerticalLayout createWorldsLayout(List<World> worlds) {
        VerticalLayout layout = new VerticalLayout();
        for (World world : worlds) {

            HorizontalLayout buttons = new HorizontalLayout();
            buttons.setAlignItems(Alignment.CENTER);
            buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            Button editButton = new Button("Редактировать");
            editButton.addClickListener(event -> {

                Long worldId = world.getId();

                VaadinSession.getCurrent().setAttribute("worldId", worldId);
                UI.getCurrent().navigate(EditWorldsView.class);
            });

            Button deleteButton = new Button("Удалить");
            deleteButton.addClickListener(buttonClickEvent -> {
                Long storyPlotId = world.getId();
                boolean isDeleted = worldService.deleteWorld(storyPlotId);

                if (isDeleted){
                    UI.getCurrent().getPage().reload();
                    Notification.show("Мир успешно удален", 3000, Notification.Position.TOP_CENTER);
                } else {
                    Notification.show("Ошибка при удалении мира", 3000, Notification.Position.TOP_CENTER);
                }
            });

            buttons.add(editButton, deleteButton);

            layout.add(new Span(world.getWorldName()), buttons);
        }
        return layout;
    }
}
