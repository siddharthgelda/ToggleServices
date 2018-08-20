package com.xpto.toggle.service;

import com.xpto.toggle.Exceptions.BedRequestExcpetion;
import com.xpto.toggle.Exceptions.Error;
import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.gateway.ToggleGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ToggleServiceImpl implements ToggleService {
    @Autowired
    private ToggleGateway toggleGateway;

    @Override
    public int createToogle(ServiceToggleDTO request) {
        int result = toggleGateway.createToogle(request);
        return result;

    }

    @Override
    public ResponseEntity updateServiceToggle(ServiceToggleDTO request) {
        int result = toggleGateway.updateServiceToogle(request);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new BedRequestExcpetion(new Error(1, "TOGGLE-UPDATE-FAILED", "toggle update failed"));
        }
    }

    @Override
    public ResponseEntity<List<ToggleDTO>> getTogglesByServiceName(String serviceName, String version) {
        List<ToggleDTO> list = toggleGateway.getTogglesBySericeName(serviceName, version);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
