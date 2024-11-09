package org.vaadin.example.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.example.dtos.measuringDevice.*;
import org.vaadin.example.services.MeasuringDeviceService;
import org.vaadin.example.views.MeasuringDeviceView;

import java.util.ArrayList;
import java.util.List;

public class MeasuringDeviceForm extends VerticalLayout {

    private FormLayout formLayout = new FormLayout();
    private Button addSensorButton = new Button("Add sensor");
    private Button submitButton = new Button("Save measuring device");
    private Button deleteDeviceButton = new Button("Delete measuring device");
    private TextField deviceNameField = new TextField("Device name");
    private List<TextField> sensorFields = new ArrayList<>();

    private boolean isNew = false;

    private MeasuringDeviceService measuringDeviceService;
    private String oldDeviceName;

    public MeasuringDeviceForm(MeasuringDeviceDto measuringDeviceDto, MeasuringDeviceService measuringDeviceService) {
        this.measuringDeviceService = measuringDeviceService;
        deleteDeviceButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        setSizeFull();
        formLayout.setWidthFull();

        if(measuringDeviceDto == null) {
            measuringDeviceDto = new MeasuringDeviceDto();
            isNew = true;
        } else {
            oldDeviceName = measuringDeviceDto.getDeviceName();
        }

        H2 title = new H2(isNew? "Add measuring device" : "Edit measuring device " + oldDeviceName);

        formLayout.add(deviceNameField, 2);

        if(!isNew) {
            deviceNameField.setValue(measuringDeviceDto.getDeviceName());
            measuringDeviceDto.getSensors().forEach(addMeasuringDeviceSensorDto -> addSensorField(addMeasuringDeviceSensorDto.getSensorName()));
        }

        FlexLayout deviceStateButtons = getButtonsLayout();

        add(title, formLayout, addSensorButton, deviceStateButtons);
    }

    private FlexLayout getButtonsLayout() {
        FlexLayout deviceStateButtons = new FlexLayout();
        deviceStateButtons.setFlexDirection(FlexLayout.FlexDirection.ROW);
        deviceStateButtons.setWidthFull();
        deviceStateButtons.add(submitButton);

        if(!isNew) {
            deleteDeviceButton.addClickListener(e -> deleteMeasuringDevice());
            deviceStateButtons.add(deleteDeviceButton);
        }

        addSensorButton.addClickListener(e -> addSensorField());
        submitButton.addClickListener(e -> saveMeasuringDevice());

        return deviceStateButtons;
    }

    private void deleteMeasuringDevice() {
        DeleteMeasuringDeviceDto deleteMeasuringDeviceDto = new DeleteMeasuringDeviceDto();
        deleteMeasuringDeviceDto.setDeviceName(this.oldDeviceName);
        measuringDeviceService.deleteMeasuringDevice(deleteMeasuringDeviceDto);
        UI.getCurrent().navigate(MeasuringDeviceView.class);
    }

    private void addSensorField(){
        addSensorField(null);
    }

    private void addSensorField(String sensorName) {
        TextField sensorField = new TextField("Sensor name");
        if(sensorName != null) sensorField.setValue(sensorName);
        sensorFields.add(sensorField);

        Button removeButton = new Button("Remove sensor", event -> {
            formLayout.remove(sensorField);
            formLayout.remove(event.getSource());
            Notification.show("Sensor removed");
        });

        removeButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        if(sensorName == null) Notification.show("Sensor added");
        formLayout.add(sensorField, removeButton);
    }

    private void saveMeasuringDevice() {
        if(isNew) {
            AddMeasuringDeviceDto addMeasuringDeviceDto = new AddMeasuringDeviceDto();
            addMeasuringDeviceDto.setDeviceName(deviceNameField.getValue());
            sensorFields.forEach(sensorTextField -> addMeasuringDeviceDto.getSensors().add(new AddMeasuringDeviceSensorDto(sensorTextField.getValue())));
            measuringDeviceService.saveNewMeasuringDevice(addMeasuringDeviceDto);
        } else {
            UpdateMeasuringDeviceDto updateMeasuringDeviceDto = new UpdateMeasuringDeviceDto();
            updateMeasuringDeviceDto.setNewDeviceName(deviceNameField.getValue());
            updateMeasuringDeviceDto.setDeviceName(oldDeviceName);
            sensorFields.forEach(sensorTextField -> updateMeasuringDeviceDto.getSensors().add(new AddMeasuringDeviceSensorDto(sensorTextField.getValue())));
            measuringDeviceService.updateMeasuringDevice(updateMeasuringDeviceDto);
        }
        UI.getCurrent().navigate(MeasuringDeviceView.class);
    }
}
