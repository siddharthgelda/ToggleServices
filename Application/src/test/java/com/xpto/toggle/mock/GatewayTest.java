package com.xpto.toggle.mock;

import com.xpto.toggle.ApplicationConstant;
import com.xpto.toggle.Helper.Helper;
import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.gateway.ToggleGatewayImpl;
import com.xpto.toggle.gateway.ToggleRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayTest {

    @MockBean
    JdbcTemplate jdbcTemplate;
    @MockBean
    PreparedStatementCreator preparedStatementCreator;
    @Autowired
    private ToggleGatewayImpl toggleGateway;
    @MockBean
    private Helper helper;
    @MockBean
    private ToggleRowMapper mapper;
    @Test
    public void createToogleTest() {
        ServiceToggleDTO request = new ServiceToggleDTO();
//        PreparedStatementCreator p=  new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//return null;}};
        request.setServiceName("IDM");
        request.setVersion("v2.0");
        ToggleDTO toggleDTO = new ToggleDTO();
        toggleDTO.setName("coupon");
        toggleDTO.setStatus(Boolean.TRUE);
        request.setToggle(toggleDTO);
        when(helper.addServicePreparedStatementCreator(any(ServiceToggleDTO.class))).thenReturn(preparedStatementCreator);
        when(helper.addTogglePreparedStatementCreator(any(ToggleDTO.class))).thenReturn(preparedStatementCreator);
        when(helper.addRelationPreparedStatementCreator(anyInt(), anyInt(), anyInt())).thenReturn(preparedStatementCreator);
        when(jdbcTemplate.update(any(PreparedStatementCreator.class), any(KeyHolder.class))).thenReturn(2);
        when(helper.getID(any(KeyHolder.class))).thenReturn(2);
        when(jdbcTemplate.update(any(PreparedStatementCreator.class))).thenReturn(2);
        int result = toggleGateway.createToogle(request);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getToggleByServiceName() {
        List<ToggleDTO> listresult=new ArrayList<>();
        when(helper.getRowMapper()).thenReturn(mapper);
        when(jdbcTemplate.query(anyString(),any(Object[].class),mapper)).thenReturn(listresult);
        List<ToggleDTO> list = toggleGateway.getTogglesBySericeName("ABC", "v1.0");
        Assert.assertNotNull(list);
    }

    @Test
    public void updateToogleTest() {
        ServiceToggleDTO request = new ServiceToggleDTO();

        request.setServiceName("ABC");
        ToggleDTO toggleDTO = new ToggleDTO();
        toggleDTO.setName("isButtonBlue");
        toggleDTO.setStatus(Boolean.TRUE);
        request.setToggle(toggleDTO);
        //sjdbcTemplate.update(ApplicationConstant.updateServiceToggle, param);
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(2);
        int result = toggleGateway.updateServiceToogle(request);
        Assert.assertEquals(2, result);
    }
}
