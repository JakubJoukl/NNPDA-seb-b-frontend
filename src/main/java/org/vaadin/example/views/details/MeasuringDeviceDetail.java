package org.vaadin.example.views.details;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.vaadin.example.components.MeasuringDeviceForm;
import org.vaadin.example.dtos.measuringDevice.AddMeasuringDeviceSensorDto;
import org.vaadin.example.dtos.measuringDevice.MeasuringDeviceDto;
import org.vaadin.example.services.MeasuringDeviceService;
import org.vaadin.example.views.MainLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "user/measuringDevice/", layout = MainLayout.class)
@PageTitle("Measuring device")
@AnonymousAllowed
public class MeasuringDeviceDetail extends FlexLayout implements HasUrlParameter<String>, BeforeEnterObserver {

    private MeasuringDeviceService measuringDeviceService;

    private String deviceName = null;

    public MeasuringDeviceDetail(MeasuringDeviceService measuringDeviceService) {
        this.measuringDeviceService = measuringDeviceService;
        setFlexDirection(FlexDirection.ROW);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        MeasuringDeviceDto measuringDeviceDto = measuringDeviceService.getMeasuringDeviceByName(deviceName);
        FlexLayout layout = new FlexLayout();
        layout.setFlexDirection(FlexDirection.COLUMN);

        FormLayout deviceEditLayout = new FormLayout();
        deviceEditLayout.add(new MeasuringDeviceForm(measuringDeviceDto, measuringDeviceService));
        add(layout);
    }
}
