package org.vaadin.example.views.details;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.example.components.MeasuringDeviceForm;
import org.vaadin.example.dtos.measuringDevice.MeasuringDeviceDto;
import org.vaadin.example.services.MeasuringDeviceService;
import org.vaadin.example.views.MainLayout;

@Route(value = "user/measuringDevice/", layout = MainLayout.class)
@PageTitle("Measuring device")
@AnonymousAllowed
public class MeasuringDeviceDetail extends FlexLayout implements HasUrlParameter<String>, BeforeEnterObserver {

    private MeasuringDeviceService measuringDeviceService;

    private String deviceName = null;

    public MeasuringDeviceDetail(MeasuringDeviceService measuringDeviceService) {
        this.measuringDeviceService = measuringDeviceService;
        setSizeFull();
        setFlexDirection(FlexDirection.ROW);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        MeasuringDeviceDto measuringDeviceDto = null;
        if(deviceName != null) {
            measuringDeviceDto = measuringDeviceService.getMeasuringDeviceByName(deviceName);
        }
        add(new MeasuringDeviceForm(measuringDeviceDto, measuringDeviceService));
    }
}
