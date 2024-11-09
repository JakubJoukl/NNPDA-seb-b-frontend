package org.vaadin.example.dtos.measuringDevice;

import java.util.ArrayList;
import java.util.List;

public class AddMeasuringDeviceSensorDto {
    private String sensorName;

    private List<MeasuredValueDto> measuredValues = new ArrayList<>();

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

    public List<MeasuredValueDto> getMeasuredValues() {
        return measuredValues;
    }

    public void setMeasuredValues(List<MeasuredValueDto> measuredValues) {
        this.measuredValues = measuredValues;
    }
}

