package com.redltd.tech.model.rowmapper;

import com.redltd.tech.model.Request;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {
    @Override
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Request(
                rs.getLong("Transaction_ID"),
                rs.getString("Keyword"),
                rs.getString("Msg_Body"),
                rs.getInt("Status"),
                rs.getLong("Wallet_MSISDN"),
                rs.getTimestamp("Created_Date"),
                rs.getString("Request_Origin"),
                rs.getString("Currency"),
                rs.getBigDecimal("Ex_Rate")
        );
    }
}
