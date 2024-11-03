package org.vaadin.example.services;

import com.fasterxml.classmate.GenericType;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.dtos.measuringDevice.MeasuringDeviceDto;

import java.util.ArrayList;
import java.util.List;

//TODO API calls
@Service
public class MeasuringDeviceService {

    @Autowired
    private RestService restService;

    public List<MeasuringDeviceDto> getMeasuringDevices() {
        return (List<MeasuringDeviceDto>) restService.getForObject("measuringDevices/", List.class, true);
    }
}
