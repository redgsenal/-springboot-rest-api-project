package com.exam.project.reservation.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    // run notification every hour from monday to saturday cron = "0 * * * 1-6"
    // alternatively cron = "*/10 * * * * *" run every 10 seconds
    @Scheduled(cron = "0 0 */1 * * 1-6")
    public void sendReservationNotifications() {
        log.info("get reservation data list and send notification where timeSlot is less than 4 hours from how");
    }
}
