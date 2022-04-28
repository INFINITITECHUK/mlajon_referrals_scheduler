package com.redltd.tech.model.rowmapper;

import com.redltd.tech.model.ReferRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferRequestRowMapper implements RowMapper<ReferRequest> {
    @Override
    public ReferRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReferRequest(
                rs.getLong("id"),
                rs.getLong("Msisdn"),
                rs.getString("Msisdn"),
                rs.getLong("Friend_Msisdn"),
                rs.getString("Created_By"),
                rs.getTimestamp("Created_Date"),
                rs.getInt("OTP"),
                rs.getInt("OTPUSED"),
                rs.getLong("POINT_GIVEN_SOURCE"),
                rs.getInt("IS_FIRST_TRANSACTION_DONE"),
                rs.getLong("POINT_GIVEN_DESTINATION"),
                rs.getLong("NO_OF_TRANSACTION_FRIEND")
        );
    }
}
