package com.workspaceit.pmc.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TempFileCleanerScheduler {
    int count = 0;
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        int tmpC = (++count);
        System.out.println("SCHEDULER STARTED "+tmpC);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("SCHEDULER ENDS "+tmpC);

    }
}
