package org.vaadin.example.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.context.annotation.Scope;

//TODO zde asi dat moznost zobrazit senzory zarizeni dle volby
@Route(value = "user/sensors", layout = MainLayout.class)
@PageTitle("Sensors")
@AnonymousAllowed
public class SensorView extends VerticalLayout {

}
