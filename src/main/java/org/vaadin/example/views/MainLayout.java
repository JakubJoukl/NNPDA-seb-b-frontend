package org.vaadin.example.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.security.SecurityService;

@CssImport("./themes/my-theme/main-layout.css")
public class MainLayout extends Composite<VerticalLayout> implements HasComponents, RouterLayout, AfterNavigationObserver //, BeforeEnterObserver
    {

    private SecurityService securityService;

    VerticalLayout menuBar = new VerticalLayout();

    private final Div childWrapper = new Div();

    @Autowired
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        getContent().setSizeFull();
        getContent().addClassName("main-layout");

        H1 header = new H1("Measuring devices management app");
        header.addClassName("main-layout-title");
        add(header);

        HorizontalLayout mainContent = new HorizontalLayout();
        mainContent.addClassName("content");

        menuBar = new VerticalLayout();
        menuBar.setWidth("20%");
        //menuBar.add(new RouterLink("Logout", LogoutRoute.class)); // Bonus: Logout with a Route

        // Bonus: Logout with an Anchor
        /*-

        -*/

        mainContent.add(menuBar);

        mainContent.add(childWrapper);
        mainContent.setFlexGrow(1, childWrapper);

        add(mainContent);

        H1 footer = new H1("Footer");
        add(footer);

        getContent().setFlexGrow(1, mainContent);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, header);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, footer);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH, mainContent);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        childWrapper.getElement().appendChild(content.getElement());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        menuBar.removeAll();
        if(securityService.isUserLoggedIn()) {
            menuBar.add(new RouterLink("Measuring devices", MeasuringDeviceView.class));
            //menuBar.add(new RouterLink("Sensors", SensorView.class));
            menuBar.add(new RouterLink("User", UserView.class));

            Button logout = new Button("Logout");
            logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            logout.getElement().addEventListener("click", e -> {
                VaadinSession.getCurrent().getSession().invalidate();
                UI.getCurrent().navigate(LoginView.class);
            });
            menuBar.add(logout);
        } else {
            menuBar.add(new RouterLink("Login", LoginView.class));
            menuBar.add(new RouterLink("Register", RegisterView.class));
            menuBar.add(new RouterLink("Reset password", ResetPasswordView.class));
        }
    }

    /*@Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!securityService.isUserLoggedIn()) {
            VaadinSession.getCurrent().setAttribute("intendedPath", event.getLocation().getPath());
            event.forwardTo(LoginView.class);
        }
    }*/

}

