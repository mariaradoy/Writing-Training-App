package org.vaadin.example.security;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.example.MainLayout;

@Route(value = LoginView.ROUTE, layout = MainLayout.class)
@AnonymousAllowed //позволяет всем иметь доступ к форме регистрации
public class LoginView extends VerticalLayout implements BeforeEnterListener {

    public static final String ROUTE = "login";
    private LoginForm login = new LoginForm();


    public LoginView () {

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(
                login
        );

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
