package com.xpto.toggle.gateway;

import com.xpto.toggle.ApplicationConstant;
import com.xpto.toggle.Exceptions.BedRequestExcpetion;
import com.xpto.toggle.Exceptions.Error;
import com.xpto.toggle.Helper.Helper;
import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToggleGatewayImpl implements ToggleGateway {

    private static final Logger logger = LoggerFactory.getLogger(ToggleGatewayImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Helper helper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BedRequestExcpetion.class})
    public int createToogle(ServiceToggleDTO request) {
        logger.debug("start createToogle called");
        try {
            int serviceId = addService(request);
            if (serviceId < 1) {
                throw new BedRequestExcpetion(new Error());
            }
            int toggleId = addToggle(request.getToggle());
            if (toggleId < 1) {
                throw new BedRequestExcpetion(new Error());
            }
            int count = addOnetoManyRealtionship(serviceId, toggleId, 1);
            if (count > 0) {
                return 1;
            }
        } catch (DataAccessException e) {
            logger.error("error " + e.getMessage());
            throw new BedRequestExcpetion(new Error(1, "TOGGLE-CREATE-FAILED", "toggle create failed " + e.getMessage()));
        }
        logger.debug("end createToogle called");
        throw new BedRequestExcpetion(new Error(1, "TOGGLE-CREATE-FAILED", "toggle create failed"));
    }

    @Override
    public int updateServiceToogle(ServiceToggleDTO request) {
        logger.debug("start updateServiceToogle called");
        Object param[] = {request.getToggle().getStatus() ? 1 : 0, request.getServiceName(), request.getToggle().getName()};
        return jdbcTemplate.update(ApplicationConstant.updateServiceToggle, param);
    }

    @Override
    public List<ToggleDTO> getTogglesBySericeName(String serviceName, String version) {
        logger.debug("start getTogglesBySericeName called");
        String param[] = {serviceName, version};
        return jdbcTemplate.query(ApplicationConstant.getTogglesByServiceNameSql, param, helper.getRowMapper());
    }

    private int addOnetoManyRealtionship(int serviceId, int toggleId, int stauts) {
        logger.debug("start addOnetoManyRealtionship called");
        return jdbcTemplate.update(helper.addRelationPreparedStatementCreator(serviceId, toggleId, stauts));
    }

    private int addService(ServiceToggleDTO request) {
        logger.debug("start addService called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(helper.addServicePreparedStatementCreator(request), keyHolder);
        return helper.getID(keyHolder);
    }


    private int addToggle(ToggleDTO toggle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(helper.addTogglePreparedStatementCreator(toggle), keyHolder);
        return helper.getID(keyHolder);
    }
}
