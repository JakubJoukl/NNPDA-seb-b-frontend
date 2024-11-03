package org.vaadin.example.dtos.measuringDevice;

public class AddMeasuringDeviceSensorDto {
    private String sensorName;

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

