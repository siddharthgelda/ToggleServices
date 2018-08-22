package com.xpto.toggle.service;

import com.xpto.toggle.Exceptions.BedRequestExcpetion;
import com.xpto.toggle.Exceptions.Error;
import com.xpto.toggle.helper.ToggleValidation;
import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.gateway.ToggleGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ToggleServiceImpl implements ToggleService {

    private static final Logger logger = LoggerFactory.getLogger(ToggleServiceImpl.class);

    @Autowired
    private ToggleGateway toggleGateway;
    @Value("${application.cacheControl}")
    private int cacheControl;

    @Override
    public int createToogle(ServiceToggleDTO request) {
        logger.debug("start validation called");
        ToggleValidation.serviceToggleDTOValidate(request);
        logger.debug("end validation called");
        logger.debug("start createToogle called");
        int result = toggleGateway.createToogle(request);
        logger.debug("end create Toggle called. result " + result);
        return result;

    }

    @Override
    public ResponseEntity updateServiceToggle(ServiceToggleDTO request) {
        logger.debug("start validation called");
        ToggleValidation.serviceToggleDTOValidate(request);
        logger.debug("end validation called");
        logger.debug("start updateServiceToogle called");
        int result = toggleGateway.updateServiceToogle(request);
        logger.debug("end updateServiceToogle called. result " + result);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new BedRequestExcpetion(new Error(1, "TOGGLE-UPDATE-FAILED", "toggle update failed"));
        }
    }

    @Override
    public ResponseEntity<List<ToggleDTO>> getTogglesByServiceName(String serviceName, String version) {
        logger.debug("start validation called");
        ToggleValidation.getServiceToggleDTOValidate(serviceName, version);
        logger.debug("end validation called");
        logger.debug("start getTogglesBySericeName called");
        List<ToggleDTO> list = toggleGateway.getTogglesBySericeName(serviceName, version);
        logger.debug("end getTogglesBySericeName called");
        // return new ResponseEntity(list, HttpStatus.OK);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(list);
    }
}
