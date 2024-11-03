package org.vaadin.example.dtos.measuringDevice;

import java.util.ArrayList;
import java.util.List;

public class MeasuringDeviceDto {

    private Integer measuringDeviceId;

    private String deviceName;

    private List<AddMeasuringDeviceSensorDto> sensors = new ArrayList<>();

    private Integer userId;

    public Integer getMeasuringDeviceId() {
        return measuringDeviceId;
    }

    public void setMeasuringDeviceId(Integer measuringDeviceId) {
        this.measuringDeviceId = measuringDeviceId;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
