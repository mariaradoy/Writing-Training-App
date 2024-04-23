package org.vaadin.example;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.example.security.LoginView;
import org.vaadin.example.security.SecurityService;
import org.vaadin.example.ui.UserProfileView;

public class MainLayout extends AppLayout {
    private final transient AuthenticationContext authContext;

    public MainLayout(@Autowired AuthenticationContext authContext) {
        this.authContext = authContext;

        addToNavbar(new DrawerToggle());
        addToNavbar(new H3("Интерактивное приложение для развития писательских навыков"));

        HorizontalLayout
                header =
                authContext.getAuthenticatedUser(UserDetails.class)
                        .map(user -> {
                            Button logout = new Button("Logout", click ->
                                    this.authContext.logout());
                            return new HorizontalLayout( logout);
                        }).orElseGet(() -> new HorizontalLayout());

        header.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        final VerticalLayout menuBar = new VerticalLayout();

        menuBar.setId("menuBar");
        menuBar.add(new RouterLink(UserProfileView.TITLE, UserProfileView.class));


        addToDrawer(menuBar, header);
    }

}
