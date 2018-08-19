package com.xpto.toggle.service;

import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToggleService {
    public ResponseEntity createToogle(ServiceToggleDTO request);

    public ResponseEntity updateServiceToggle(ServiceToggleDTO request);

    public ResponseEntity<List<ToggleDTO>> getTogglesByServiceName(String serviceName, String version);
}
