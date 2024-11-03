package org.vaadin.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.vaadin.example.dtos.measuringDevice.AddMeasuringDeviceDto;
import org.vaadin.example.dtos.measuringDevice.AddMeasuringDeviceSensorDto;
import org.vaadin.example.dtos.measuringDevice.MeasuringDeviceDto;
import org.vaadin.example.dtos.measuringDevice.UpdateMeasuringDeviceDto;

import java.util.List;

//TODO API calls
@Service
public class MeasuringDeviceService {

    @Autowired
    private RestService restService;

    public List<MeasuringDeviceDto> getMeasuringDevices() {
        return restService.getForObject("user/device/getDevices", null, new ParameterizedTypeReference<List<MeasuringDeviceDto>>() {}, true);
    }

    public MeasuringDeviceDto getMeasuringDeviceByName(String deviceName) {
        return restService.getForObject("user/device/getDevice/" + deviceName, null, new ParameterizedTypeReference<MeasuringDeviceDto>() {}, true);
    }

    public void saveNewMeasuringDevice(AddMeasuringDeviceDto measuringDeviceDto) {
        restService.putForObject("user/device/addDevice", measuringDeviceDto, new ParameterizedTypeReference<Object>() {}, true);
    }

    public void updateMeasuringDevice(UpdateMeasuringDeviceDto measuringDeviceDto) {
        restService.postForObject("user/device/updateDevice", measuringDeviceDto, new ParameterizedTypeReference<Object>() {}, true);
    }
}
