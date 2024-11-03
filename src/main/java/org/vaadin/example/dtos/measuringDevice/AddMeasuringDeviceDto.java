package org.vaadin.example.dtos.measuringDevice;

import java.util.ArrayList;
import java.util.List;

public class AddMeasuringDeviceDto {
    private String deviceName;

    private List<AddMeasuringDeviceSensorDto> sensors = new ArrayList<>();

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<AddMeasuringDeviceSensorDto> getSensors() {
        return sensors;
    }

    public void setSensors(List<AddMeasuringDeviceSensorDto> sensors) {
        this.sensors = sensors;
    }
}
