package com.redltd.tech.db;

import com.redltd.tech.model.ReferRequest;
import com.redltd.tech.model.ReferralConfig;
import com.redltd.tech.model.Request;
import com.redltd.tech.model.Wallet;
import com.redltd.tech.model.rowmapper.ReferRequestRowMapper;
import com.redltd.tech.model.rowmapper.ReferralConfigRowMapper;
import com.redltd.tech.model.rowmapper.RequestRowMapper;
import com.redltd.tech.model.rowmapper.WalletRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class DSRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public List<Map<String, Object>> getList(String query) {
//
//        List<Map<String, Object>> getList = new ArrayList<>();
//
//        try {
//
//            getList = jdbcTemplate.queryForList(query);
//
//        } catch (Exception e) {
//            log.error("Exception: "+e);
//        }
//
//        return getList;
//    }

    public Long updateTable(String query) {

        int count = 0;

        try {

            count = jdbcTemplate.update(query);

        } catch (Exception e) {
            log.error("Exception: "+e);
        }
        return Long.valueOf(count);
    }

    public String getTranDatetime(String sql) {

        String tranDatetime = null;

        try {

            tranDatetime = (String) jdbcTemplate.queryForObject( sql, String.class);

        } catch (Exception e) {
            log.error("Exception: "+ e);
        }
        return tranDatetime;
    }

    public Optional<ReferralConfig> loadReferralConfig(){

        log.info("LOADING CONFIGURATION...");
        String sql = "SELECT * FROM SW_TBL_REFERRAL_CONFIG";

        try{

            return jdbcTemplate.query(sql, new ReferralConfigRowMapper())
                    .stream()
                    .findFirst();

        }catch (Exception e){

            log.error("Exception: "+ e);
            return Optional.empty();
        }

    }

    public Optional<Wallet> loadWallet(long friendMsisdn){

        log.info("LOADING CONFIGURATION...");
        String sql = "SELECT * FROM SW_TBL_WALLET WHERE Wallet_MSISDN = "+friendMsisdn;

        try{

            return jdbcTemplate.query(sql, new WalletRowMapper())
                    .stream()
                    .findFirst();

        }catch (Exception e){

            log.error("Exception: "+ e);
            return Optional.empty();
        }

    }

    public List<ReferRequest> loadReferRequest(String sql){

        log.info("LOADING REFER REQUEST...");
//        String sql = "SELECT * FROM SW_TBL_REFERRAL_CONFIG";

        try{

            return jdbcTemplate.query(sql, new ReferRequestRowMapper());

        }catch (Exception e){

            log.error("Exception: "+ e);
            return null;
        }

    }

    public List<Request> loadRequest(String sql){

        log.info("LOADING REFER REQUEST...");

        try{

            return jdbcTemplate.query(sql, new RequestRowMapper());

        }catch (Exception e){

            log.error("Exception: "+ e);
            return null;
        }

    }

    public int countMsisdn(String sql) {

        log.info("COUNTING MSISDN...");
        int count = 0;

        try {

            count = jdbcTemplate.queryForObject(sql, Integer.class);

        }catch (Exception e){

            log.error("Exception: "+ e);
        }

        return count;
    }


}
