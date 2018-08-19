package com.xpto.toggle.gateway;

import com.xpto.toggle.dto.ToggleDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToggleRowMapper implements RowMapper<ToggleDTO> {

   public ToggleDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
       ToggleDTO toggleDTO=new ToggleDTO();

       toggleDTO.setName(rs.getString("name"));
       toggleDTO.setId(rs.getInt("id"));
       toggleDTO.setStatus(rs.getInt("isActivate")==1 ? Boolean.TRUE : Boolean.FALSE);
       return  toggleDTO;
    }

}
