package org.vaadin.example.dtos.measuringDevice;

import java.time.Instant;

public class MeasuredValueDto {
    private Integer measuredValueId;
    private Instant measuredOn;
    private Integer measuredValue;
    private Integer sensorId;

    public MeasuredValueDto(Integer measuredValueId, Instant measuredOn, Integer measuredValue, Integer sensorId) {
        this.measuredValueId = measuredValueId;
        this.measuredOn = measuredOn;
        this.measuredValue = measuredValue;
        this.sensorId = sensorId;
    }

    public MeasuredValueDto(){

    }

    public Integer getMeasuredValueId() {
        return measuredValueId;
    }

    public void setMeasuredValueId(Integer measuredValueId) {
        this.measuredValueId = measuredValueId;
    }

    public Instant getMeasuredOn() {
        return measuredOn;
    }

    public void setMeasuredOn(Instant measuredOn) {
        this.measuredOn = measuredOn;
    }

    public Integer getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(Integer measuredValue) {
        this.measuredValue = measuredValue;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }
}
