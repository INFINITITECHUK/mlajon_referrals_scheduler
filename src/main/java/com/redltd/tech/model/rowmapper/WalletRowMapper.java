package com.redltd.tech.model.rowmapper;

import com.redltd.tech.model.Wallet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRowMapper implements RowMapper<Wallet> {
    @Override
    public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Wallet(
                rs.getLong("Wallet_MSISDN"),
                rs.getInt("Wallet_Code"),
                rs.getBigDecimal("Amount"),
                rs.getDate("Created_Date"),
                rs.getString("Created_By"),
                rs.getInt("Status"),
                rs.getLong("Last_Transaction_ID"),
                rs.getBigDecimal("Last_Transaction_Amount"),
                rs.getBigDecimal("Balance_Before"),
                rs.getDate("Modified_Date"),
                rs.getString("Modified_By"),
                rs.getLong("Current_Year_Reward_Point"),
                rs.getLong("Last_Year_Reward_Point"),
                rs.getLong("Parent")

        );
    }
}
