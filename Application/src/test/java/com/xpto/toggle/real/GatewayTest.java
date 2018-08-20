package com.xpto.toggle.real;

import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.gateway.ToggleGateway;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayTest {

    @Autowired
    private ToggleGateway toggleGateway;

    @Test
    public void createToogleTest() {
        ServiceToggleDTO request = new ServiceToggleDTO();

        request.setServiceName("IDM");
        request.setVersion("v2.0");
        ToggleDTO toggleDTO = new ToggleDTO();
        toggleDTO.setName("coupon");
        toggleDTO.setStatus(Boolean.TRUE);
        request.setToggle(toggleDTO);
        int result = toggleGateway.createToogle(request);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getToggleByServiceName() {
        List<ToggleDTO> list = toggleGateway.getTogglesBySericeName("ABC", "v1.0");
        Assert.assertNotNull(list);
    }

    @Test
    public void UpdateToogleTest() {
        ServiceToggleDTO request = new ServiceToggleDTO();

        request.setServiceName("ABC");
        ToggleDTO toggleDTO = new ToggleDTO();
        toggleDTO.setName("isButtonBlue");
        toggleDTO.setStatus(Boolean.TRUE);
        request.setToggle(toggleDTO);
        int result = toggleGateway.updateServiceToogle(request);
        Assert.assertEquals(1, result);
    }
}