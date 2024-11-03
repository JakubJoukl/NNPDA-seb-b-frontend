package org.vaadin.example.dtos.measuringDevice;

import java.util.List;

public class UpdateMeasuringDeviceDto {
    String deviceName;

    String newDeviceName;

    private List<AddMeasuringDeviceSensorDto> sensors;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getNewDeviceName() {
        return newDeviceName;
    }

    public void setNewDeviceName(String newDeviceName) {
        this.newDeviceName = newDeviceName;
    }

    public List<AddMeasuringDeviceSensorDto> getSensors() {
        return sensors;
    }

    public void setSensors(List<AddMeasuringDeviceSensorDto> sensors) {
        this.sensors = sensors;
    }
}
