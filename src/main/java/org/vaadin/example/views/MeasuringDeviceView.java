package org.vaadin.example.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.example.dtos.measuringDevice.AddMeasuringDeviceSensorDto;
import org.vaadin.example.dtos.measuringDevice.MeasuringDeviceDto;
import org.vaadin.example.services.MeasuringDeviceService;

import java.util.stream.Collectors;

@Route(value = "user/measuringDevices", layout = MainLayout.class)
@PageTitle("Measuring devices")
@AnonymousAllowed
public class MeasuringDeviceView extends VerticalLayout {

    @Autowired
    private MeasuringDeviceService measuringDeviceService;

    public MeasuringDeviceView(){
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        flexLayout.setSizeFull();

        Grid<MeasuringDeviceDto> measuringDeviceDtoGrid = new Grid<>();
        measuringDeviceDtoGrid.addColumn(MeasuringDeviceDto::getDeviceName).setHeader("Device name");
        measuringDeviceDtoGrid.addColumn(
                measuringDeviceDto -> measuringDeviceDto.getSensors().stream()
                .map(AddMeasuringDeviceSensorDto::getSensorName).collect(Collectors.joining(", "))
        ).setHeader("Sensors");
        measuringDeviceDtoGrid.setItems(measuringDeviceService.getMeasuringDevices());

        flexLayout.add(measuringDeviceDtoGrid);

        add(flexLayout);
    }
}
