package org.vaadin.example.dtos.measuringDevice;

import java.util.List;

public class AddMeasuringDeviceSensorDto {
    private String sensorName;

    private List<MeasuredValueDto> measuredValues;

    public AddMeasuringDeviceSensorDto(String sensorName) {
        this.sensorName = sensorName;
    }

    public AddMeasuringDeviceSensorDto(){

    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}

