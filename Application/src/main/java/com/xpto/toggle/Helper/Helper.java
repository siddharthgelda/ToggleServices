package com.xpto.toggle.Helper;

import com.xpto.toggle.ApplicationConstant;
import com.xpto.toggle.dto.ServiceToggleDTO;
import com.xpto.toggle.dto.ToggleDTO;
import com.xpto.toggle.gateway.ToggleRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Helper {
    public int getID(KeyHolder keyHolder) {
        int id = 0;
        if (keyHolder.getKey() != null) {
            id = keyHolder.getKey().intValue();
        }
        return id;
    }

    public PreparedStatementCreator addServicePreparedStatementCreator(ServiceToggleDTO request) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(ApplicationConstant.ServiceSql,
                        new String[]{"id"});

                ps.setString(1, request.getServiceName());
                ps.setString(2, request.getVersion());

                return ps;
            }
        };
    }

    public PreparedStatementCreator addTogglePreparedStatementCreator(ToggleDTO toggle) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(ApplicationConstant.toggleSql,
                        new String[]{"id"});

                ps.setString(1, toggle.getName());
                ps.setInt(2, toggle.getStatus() ? 1 : 0);

                return ps;
            }
        };
    }

    public PreparedStatementCreator addRelationPreparedStatementCreator(int serviceId, int toggleId, int stauts) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(ApplicationConstant.createOneToManyRelationshipSql);

                ps.setInt(1, serviceId);
                ps.setInt(2, toggleId);
                ps.setInt(3, stauts);

                return ps;
            }
        };
    }

    public ToggleRowMapper getRowMapper()
    {
        return new ToggleRowMapper();
    }

}
