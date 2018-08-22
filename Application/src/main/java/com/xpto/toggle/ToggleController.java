package com.xpto.toggle;

import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.service.ToggleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/services")
public class ToggleController {

    private static final Logger logger = LoggerFactory.getLogger(ToggleController.class);
    @Autowired
    private ToggleService toggleService;

    @PostMapping
    @RequestMapping(value = "/toggle")
    public Resource createToogle(@RequestBody ServiceToggleDTO request) {
        logger.info("request start for create toggle " + request.toString());
        int result = toggleService.createToogle(request);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getTogglesByServiceName(request.getServiceName(), request.getVersion()));
        Resource resource = new Resource(result);
        resource.add(linkTo.withRel("get-services"));
        logger.info("request end for create toggle " + resource.toString());
        return resource;

    }
    @PutMapping("/toggle")
    public ResponseEntity updateServiceToggle(@RequestBody ServiceToggleDTO request) {
        logger.info("request start for update toggle " + request.toString());
        ResponseEntity entity = toggleService.updateServiceToggle(request);
        logger.info("request end for update toggle " + entity.toString());
        return entity;
    }


    @GetMapping("/{serviceName}/{version}")
    public ResponseEntity<List<ToggleDTO>> getTogglesByServiceName(@PathVariable("serviceName") String serviceName, @PathVariable("version") String version) {
        logger.info("request start for getTogglesByServiceName service name " + serviceName + " verstion name " + version);
        ResponseEntity<List<ToggleDTO>> entity = toggleService.getTogglesByServiceName(serviceName, version);
        logger.info("request end for getTogglesByServiceName " + entity.toString());
        return entity;
    }
}
