package com.xpto.toggle.gateway;

import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;

import java.util.List;

public interface ToggleGateway {

    public int createToogle(ServiceToggleDTO request);

    public int updateServiceToogle(ServiceToggleDTO request);

    public List<ToggleDTO> getTogglesBySericeName(String serviceName, String version);
}