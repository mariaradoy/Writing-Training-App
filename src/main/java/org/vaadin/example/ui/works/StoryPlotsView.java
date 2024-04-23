package org.vaadin.example.ui.works;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.MainLayout;
import org.vaadin.example.entity.Status;
import org.vaadin.example.entity.StoryPlot;
import org.vaadin.example.repository.StoryPlotRepository;
import org.vaadin.example.service.StoryPlotService;
import org.vaadin.example.ui.editViews.EditPlotsView;

import java.util.List;

@Route(value = StoryPlotsView.ROUTE, layout = MainLayout.class)
@PermitAll
public class StoryPlotsView extends VerticalLayout {
    @Autowired
    private final StoryPlotRepository storyPlotRepository;
    @Autowired
    private final StoryPlotService storyPlotService;
    public static final String ROUTE = "mystoryplots";

    StoryPlotsView(StoryPlotRepository storyPlotRepository, StoryPlotService storyPlotService) {
        this.storyPlotRepository = storyPlotRepository;
        this.storyPlotService = storyPlotService;

        setSizeFull();

        List<StoryPlot> inPlanWorks = storyPlotRepository.findByStatus(Status.IN_PLAN);
        List<StoryPlot> inProgressWorks = storyPlotRepository.findByStatus(Status.IN_PROGRESS);
        List<StoryPlot> finishedWorks = storyPlotRepository.findByStatus(Status.FINISHED);
        List<StoryPlot> pausedWorks = storyPlotRepository.findByStatus(Status.PAUSED);

        Tab inPlanTab = new Tab("В планах");
        Tab inProgressTab = new Tab("В процессе");
        Tab finishedTab = new Tab("Завершено");
        Tab pausedTab = new Tab("Замороженно");

        // Создание содержимого для вкладок
        VerticalLayout tab1Content = createWorksLayout(inPlanWorks);
        VerticalLayout tab2Content = createWorksLayout(inProgressWorks);
        VerticalLayout tab3Content = createWorksLayout(finishedWorks);
        VerticalLayout tab4Content = createWorksLayout(pausedWorks);

        // Создание компонента Tabs и добавление вкладок
        Tabs tabs = new Tabs(inPlanTab, inProgressTab, finishedTab, pausedTab);

        // Установка содержимого для каждой вкладки
        tabs.addSelectedChangeListener(event -> {
            removeAll();
            if (event.getSelectedTab() == inPlanTab) {
                add(tabs, tab1Content);
            } else if (event.getSelectedTab() == inProgressTab) {
                add(tabs, tab2Content);
            }
            else if (event.getSelectedTab() == finishedTab) {
                add(tabs, tab3Content);
            } else if (event.getSelectedTab() == pausedTab) {
                add(tabs, tab4Content);
            }
        });

        // Добавление компонента Tabs на страницу
        add(tabs, tab1Content);
    }

    private VerticalLayout createWorksLayout(List<StoryPlot> storyPlots) {
        VerticalLayout layout = new VerticalLayout();
        for (StoryPlot storyPlot : storyPlots) {

            HorizontalLayout buttons = new HorizontalLayout();
            buttons.setAlignItems(Alignment.CENTER);
            buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            Button editButton = new Button("Редактировать");
            editButton.addClickListener(event -> {

                Long storyPlotId = storyPlot.getId();

                VaadinSession.getCurrent().setAttribute("storyPlotId", storyPlotId);
                UI.getCurrent().navigate(EditPlotsView.class);
            });

            Button deleteButton = new Button("Удалить");
            deleteButton.addClickListener(buttonClickEvent -> {
                Long storyPlotId = storyPlot.getId();
                boolean isDeleted = storyPlotService.deleteStoryPlot(storyPlotId);

                if (isDeleted){
                    UI.getCurrent().getPage().reload();
                    Notification.show("Работа успешно удалена", 3000, Notification.Position.TOP_CENTER);
                } else {
                    Notification.show("Ошибка при удалении работы", 3000, Notification.Position.TOP_CENTER);
                }
            });

            ComboBox<Status> statusComboBox = new ComboBox<>();
            statusComboBox.setItems(Status.values());
            statusComboBox.addValueChangeListener(event -> {
                Long storyPlotId = storyPlot.getId();

                Status selectedStatus = event.getValue();
                if(selectedStatus != null) {
                    storyPlot.setStatus(selectedStatus);
                    storyPlotService.updateStoryPlote(storyPlot, storyPlotId);
                    UI.getCurrent().getPage().reload();
                }
            });

            buttons.add(editButton, deleteButton, statusComboBox);

            layout.add(new Span(storyPlot.getStoryName()), buttons);
        }
        return layout;
    }
}
