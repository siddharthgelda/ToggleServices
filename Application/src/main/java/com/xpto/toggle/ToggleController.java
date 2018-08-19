package com.xpto.toggle;

import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/toggle")
public class ToggleController {

    @Autowired
    private ToggleService toggleService;

    @PostMapping
    
    public ResponseEntity createToogle(@RequestBody  ServiceToggleDTO request) {
        return toggleService.createToogle(request);
    }

    @PutMapping
    public ResponseEntity updateServiceToggle(@RequestBody ServiceToggleDTO request) {
        return toggleService.updateServiceToggle(request);
    }


    @GetMapping("/services/{serviceName}/{version}")
    public ResponseEntity<List<ToggleDTO>> getTogglesByServiceName(@PathVariable("serviceName") String serviceName,@PathVariable("version") String version) {
        return toggleService.getTogglesByServiceName(serviceName, version);
    }
}
