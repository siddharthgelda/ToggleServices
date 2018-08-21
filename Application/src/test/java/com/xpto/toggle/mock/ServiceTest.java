package com.xpto.toggle.mock;


import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.gateway.ToggleGateway;
import com.xpto.toggle.service.ToggleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private ToggleService toggleService;

    @MockBean
    private ToggleGateway toggleGateway;

    @Test
    public void createToggleTest() {
        ServiceToggleDTO request=   createRequest();
        when(toggleGateway.createToogle(any(ServiceToggleDTO.class))).thenReturn(2);
        int result = toggleService.createToogle(createRequest());
        Assert.assertEquals(2, result);

    }


    @Test
    public void updateServiceToggleTest() {
        ServiceToggleDTO request=   createRequest();
        when(toggleGateway.updateServiceToogle(any(ServiceToggleDTO.class))).thenReturn(1);
        ResponseEntity entity = toggleService.updateServiceToggle(request);
        Assert.assertNotNull(entity);
        Assert.assertEquals(200, entity.getStatusCodeValue());

    }

    @Test
    public void getTogglesByServiceNameTest() {
        List<ToggleDTO> listresult=new ArrayList<>();
        when(toggleGateway.getTogglesBySericeName(anyString(),anyString())).thenReturn(listresult);
        ResponseEntity<List<ToggleDTO>> entity = toggleService.getTogglesByServiceName("IDM", "v2.0");
        Assert.assertNotNull(entity);
        Assert.assertEquals(200, entity.getStatusCodeValue());
    }

    private ServiceToggleDTO createRequest() {
        ServiceToggleDTO request = new ServiceToggleDTO();

        request.setServiceName("IDM");
        request.setVersion("v2.0");
        ToggleDTO toggleDTO = new ToggleDTO();
        toggleDTO.setName("coupon");
        toggleDTO.setStatus(Boolean.TRUE);
        request.setToggle(toggleDTO);
        return request;
    }
}
