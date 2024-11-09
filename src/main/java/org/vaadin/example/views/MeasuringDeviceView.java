package org.vaadin.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.example.views.details.MeasuringDeviceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dtos.measuringDevice.AddMeasuringDeviceSensorDto;
import org.vaadin.example.dtos.measuringDevice.MeasuringDeviceDto;
import org.vaadin.example.services.MeasuringDeviceService;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "user/measuringDevices", layout = MainLayout.class)
@PageTitle("Measuring devices")
@AnonymousAllowed
public class MeasuringDeviceView extends VerticalLayout {

    private MeasuringDeviceService measuringDeviceService;
    private Grid<MeasuringDeviceDto> measuringDeviceDtoGrid = new Grid<>();
    private Button addMeasuringDeviceButton = new Button("Add measuring device");

    @Autowired
    public MeasuringDeviceView(MeasuringDeviceService measuringDeviceService){
        this.measuringDeviceService = measuringDeviceService;
        this.setSizeFull();

        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        flexLayout.setSizeFull();

        measuringDeviceDtoGrid.setSizeFull();

        measuringDeviceDtoGrid.addColumn(MeasuringDeviceDto::getDeviceName).setHeader("Device name");
        measuringDeviceDtoGrid.addColumn(
                measuringDeviceDto -> measuringDeviceDto.getSensors().stream()
                .map(AddMeasuringDeviceSensorDto::getSensorName).collect(Collectors.joining(", "))
        ).setHeader("Sensors");

        List<MeasuringDeviceDto> measuringDeviceDtos = measuringDeviceService.getMeasuringDevices();
        measuringDeviceDtoGrid.setItems(measuringDeviceDtos);
        
        measuringDeviceDtoGrid.asSingleSelect().addValueChangeListener(event -> {
            MeasuringDeviceDto selectedItem = event.getValue();
            if (selectedItem != null) {
                UI.getCurrent().navigate(MeasuringDeviceDetail.class, selectedItem.getDeviceName());
            }
        });

        addMeasuringDeviceButton.addClickListener(event -> UI.getCurrent().navigate(MeasuringDeviceDetail.class));

        flexLayout.add(measuringDeviceDtoGrid, addMeasuringDeviceButton);

        add(flexLayout);
    }
}
