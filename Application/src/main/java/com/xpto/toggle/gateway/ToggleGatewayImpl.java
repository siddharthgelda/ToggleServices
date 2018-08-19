package com.xpto.toggle.gateway;

import com.xpto.toggle.ApplicationConstant;
import com.xpto.toggle.Exceptions.BedRequestExcpetion;
import com.xpto.toggle.Exceptions.Error;
import com.xpto.toggle.dto.CreateToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class ToggleGatewayImpl implements ToggleGateway {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BedRequestExcpetion.class})
    public boolean createToogle(CreateToggleDTO request) {
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
            return true;
        }
        throw new BedRequestExcpetion(new Error());
    }

    @Override
    public List<ToggleDTO> getTogglesBySericeName(String serviceName,String version) {
        String param[]={serviceName,version};
        return  jdbcTemplate.query(ApplicationConstant.getTogglesByServiceNameSql,param,new ToggleRowMapper());
       }

    private int addOnetoManyRealtionship(int serviceId, int toggleId, int stauts) {
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(ApplicationConstant.createOnetoManyRealtionshipSql);

                ps.setInt(1, serviceId);
                ps.setInt(2, toggleId);
                ps.setInt(3, stauts);

                return ps;
            }
        });
    }

    private int addService(CreateToggleDTO request) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(ApplicationConstant.ServiceSql,
                        new String[]{"id"});

                ps.setString(1, request.getServiceName());
                ps.setString(2, request.getVersion());

                return ps;
            }
        }, keyHolder);
        int id = 0;
        if (keyHolder.getKey() != null) {
            id = keyHolder.getKey().intValue();
        }
        return id;
    }


    private int addToggle(ToggleDTO toggle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(ApplicationConstant.toggleSql,
                        new String[]{"id"});

                ps.setString(1, toggle.getName());
                ps.setInt(2, toggle.getStatus() ? 1 : 0);

                return ps;
            }
        }, keyHolder);
        int id = 0;
        if (keyHolder.getKey() != null) {
            id = keyHolder.getKey().intValue();
        }
        return id;

    }
}
