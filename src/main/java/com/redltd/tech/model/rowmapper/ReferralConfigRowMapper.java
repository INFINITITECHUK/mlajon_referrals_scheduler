package com.redltd.tech.model.rowmapper;

import com.redltd.tech.model.ReferralConfig;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferralConfigRowMapper implements RowMapper<ReferralConfig> {
    @Override
    public ReferralConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReferralConfig(
                rs.getLong("id"),
                rs.getLong("UNIQUE_REFER_COUNT"),
                rs.getLong("TOTAL_ALLOW_REFEREL"),
                rs.getInt("POINT_SOURCE"),
                rs.getDouble("EXCHANGERATE"),
                rs.getInt("FIRST_TRANSACTION_PERIOD"),
                rs.getString("SERVICE_ALLOWED"),
                rs.getLong("POINT_DESTINATION"),
                rs.getInt("MINIMUM_NO_OF_REFER"),
                rs.getInt("MINIMUM_NO_OF_UNIQUE_TRANSACTION")
        );
    }
}
