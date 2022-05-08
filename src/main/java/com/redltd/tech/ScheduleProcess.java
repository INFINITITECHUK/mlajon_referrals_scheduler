package com.redltd.tech;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redltd.tech.db.DSRepo;
import com.redltd.tech.model.ReferRequest;
import com.redltd.tech.model.ReferralConfig;
import com.redltd.tech.model.Request;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ScheduleProcess {


    @Autowired
    private DSRepo databaseRepo;

    @Autowired
    private WaitSchedule waitSchedule;


    public void getStart() {

        String query = "select * from SW_TBL_REFER_REQUEST where OTPUSED = 1 and IS_FIRST_TRANSACTION_DONE = 0";

        List<ReferRequest> referRequests = databaseRepo.loadReferRequest(query);

        if (!referRequests.isEmpty()) {

            Optional<ReferralConfig> referralConfig = databaseRepo.loadReferralConfig();

            if (referralConfig.isPresent()){

                log.info("Row Count ->> " + referRequests.size());

                for (ReferRequest referRequest: referRequests){

                    log.info("Refer Request ->> "+getJsonString(referRequest));

                    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    DateTime dt = DateTime.parse(referRequest.getCreatedDate().toString(), dateTimeFormatter);

                    LocalDate startOfMonth = dt.dayOfMonth().withMinimumValue().toLocalDate();
                    LocalDate endOfMonth = dt.dayOfMonth().withMaximumValue().toLocalDate();

                    String totalQuery = "SELECT COUNT(*) FROM SW_TBL_REFER_REQUEST where OTPUSED = 1 and Msisdn = "+ referRequest.getMsisdn();
                    String monthQuery = "SELECT COUNT(*) FROM SW_TBL_REFER_REQUEST where OTPUSED = 1 and Msisdn = "+ referRequest.getMsisdn()+
                            " and TRY_CONVERT(DATE, Created_Date) >= '"+ startOfMonth +"' and TRY_CONVERT(DATE, Created_Date) <= '"+ endOfMonth +"';";

                    int totalCount = databaseRepo.countMsisdn(totalQuery);

                    if (referralConfig.get().getTotalAllowReferel() > totalCount){

                        int monthCount = databaseRepo.countMsisdn(monthQuery);

                        if (referralConfig.get().getUniqueReferCount() > monthCount){

                            if (referRequest.getPointGivenSource() == 0){

                                long sourcePoint = referralConfig.get().getPointSource();
                                String query2 = "update SW_TBL_REFER_REQUEST set POINT_GIVEN_SOURCE = "+ sourcePoint +" where id = "+ referRequest.getId();
                                databaseRepo.updateTable(query2);

                                log.info(" ->> Source point is successfully updated!!");

                            }else {

                                log.info(" ->> Source point is already given!!");
                            }

                        }else {

                            log.info(" ->> Unique refer is over!!");
                        }

                    }else {

                        log.info(" ->> Total allow referral is over!!");
                    }

                    if (referRequest.getIsFirstTransactionDone() == 0){

                        String[] services = referralConfig.get().getServiceAllowed().split(",");

                        for (String service: services){

                            String query3 = "select * from SW_TBL_REQUEST where KEYWORD = '"+ service +"' and Wallet_MSISDN = "+ referRequest.getFriendMsisdn();

                            List<Request> requests = databaseRepo.loadRequest(query3);

                            if (requests == null || requests.size() ==0){

                                log.info(" ->> First Transaction not found!!");
                            }else {

                                if (requests.size()==1){

                                    DateTime tranDate = DateTime.parse(requests.get(0).getCreatedDate().toString(), dateTimeFormatter);
                                    DateTime closeDate = dt.plusDays(referralConfig.get().getFirstTransactionPeriod());

                                    if (tranDate.equals(dt) || (tranDate.isAfter(dt) && tranDate.isBefore(closeDate)) || tranDate.equals(closeDate)){

                                        long destPoint = referralConfig.get().getPointSource();
                                        String query4 = "update SW_TBL_REFER_REQUEST set IS_FIRST_TRANSACTION_DONE = 1 , POINT_GIVEN_DESTINATION = "+ destPoint +" where id = "+ referRequest.getId();
                                        databaseRepo.updateTable(query4);

                                        log.info(" ->> Destination point is successfully updated!!");

                                    }else {

                                        log.info(" ->> First transaction time is over!!");
                                        String query5 = "update SW_TBL_REFER_REQUEST set IS_FIRST_TRANSACTION_DONE = 1 where id = "+ referRequest.getId();
                                        databaseRepo.updateTable(query5);
                                    }

                                }else {

                                    log.info(" ->> First transaction is over!!");
                                    String query5 = "update SW_TBL_REFER_REQUEST set IS_FIRST_TRANSACTION_DONE = 1 where id = "+ referRequest.getId();
                                    databaseRepo.updateTable(query5);
                                }

                            }

                        }

                    }

                }

            }else {

                log.error(" ->> Referral config table is Empty!!");
            }




        } else {

            log.info(" ->> Refer request table is Empty!!");
        }

        waitSchedule.endBreak();

    }

    @SneakyThrows
    public String getJsonString(ReferRequest obj){

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);

    }

}
