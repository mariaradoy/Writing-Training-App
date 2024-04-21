package org.vaadin.example.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.vaadin.example.MainLayout;

@Route(value = UserProfileView.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class UserProfileView extends VerticalLayout {

    public static final String ROUTE = "userprofile";
    public static final String TITLE = "Личный профиль";

    UserProfileView() {
        HorizontalLayout functionButtons = new HorizontalLayout();

        Button exercisesButton = new Button("Мои упражнения");
        Button plotsButton = new Button("Мои сюжеты");
        Button worldsButton = new Button("Мои вселенные");

        functionButtons.add(exercisesButton, plotsButton, worldsButton);

        exercisesButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("myexercises"));
        });

        plotsButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("mystoryplots"));
        });

        worldsButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("myworlds"));
        });

        Button createButton = new Button("Создать");
        createButton.addClassName("save-button");

        ContextMenu createMenu = new ContextMenu();
        createMenu.setOpenOnClick(true);

        createMenu.setTarget(createButton);
        MenuItem exerciseItem = createMenu.addItem("Новое упражнение");
        MenuItem plotItem = createMenu.addItem("Новый сюжет");
        MenuItem worldItem = createMenu.addItem("Новая вселенная");

        exerciseItem.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("newexercise"));
        });

        plotItem.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("addplot"));
        });
        worldItem.addClickListener(event -> {
           getUI().ifPresent(ui->ui.navigate("addworld"));
        });


        add(functionButtons, createButton);
    }



}
