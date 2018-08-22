package com.xpto.toggle.gateway;

import com.xpto.toggle.dto.ToggleDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToggleIdRowMapper implements RowMapper<Integer> {
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = new Integer(rs.getInt("id"));
         return id;
    }

}
