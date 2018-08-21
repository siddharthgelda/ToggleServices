package com.xpto.toggle.mock;


import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.service.ToggleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private ToggleService toggleService;

    @Test
    public void createToogleTest() {
        int result = toggleService.createToogle(createRequest());
        Assert.assertEquals(1, result);

    }


    @Test
    public void updateServiceToggleTest() {
        ResponseEntity entity = toggleService.updateServiceToggle(createRequest());
        Assert.assertNotNull(entity);
        Assert.assertEquals(200, entity.getStatusCodeValue());

    }

    @Test
    public void getTogglesByServiceNameTest() {
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
