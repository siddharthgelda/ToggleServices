package com.xpto.toggle.gateway;

import com.xpto.toggle.dto.CreateToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;

import java.util.List;

public interface ToggleGateway {

    public boolean createToogle(CreateToggleDTO request);

    public List<ToggleDTO> getTogglesBySericeName(String serviceName,String version);
}