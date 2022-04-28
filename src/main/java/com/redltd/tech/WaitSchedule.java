package com.redltd.tech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@Slf4j
public class WaitSchedule {

    @Value("${wait.time}")
    private long X;

    public synchronized void endBreak() {

        log.info("Process Start After "+X+" Minutes");
        log.info("Process Waiting Time ==> " + Calendar.getInstance().getTime());
        try {
            this.wait(X* 60000L);
            log.info("Process Starting Time ==> " + Calendar.getInstance().getTime());
        } catch (InterruptedException e) {
            log.error("Error -> "+e);
        }

    }

}
