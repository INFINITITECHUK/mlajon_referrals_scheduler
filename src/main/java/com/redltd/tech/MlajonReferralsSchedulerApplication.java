package com.redltd.tech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MlajonReferralsSchedulerApplication implements CommandLineRunner {

    @Autowired
    private ScheduleProcess scheduleProcess;

    public static void main(String[] args) {
        SpringApplication.run(MlajonReferralsSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application Running...");
        loopMethod();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void loopMethod(){

        while (true){

            try { scheduleProcess.getStart();
            }catch (Exception e){ log.error("Error -> "+e); }

        }

    }

}
