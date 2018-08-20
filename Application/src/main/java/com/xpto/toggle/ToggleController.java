package com.xpto.toggle;

import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController("/toggle")
public class ToggleController {

    @Autowired
    private ToggleService toggleService;

    @PostMapping
    
    public Resource createToogle(@RequestBody  ServiceToggleDTO request) {
       int result= toggleService.createToogle(request);
       ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getTogglesByServiceName(request.getServiceName(),request.getVersion()));
       Resource resource=new Resource(result);
       resource.add(linkTo.withRel("get-services"));

        return resource;

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
