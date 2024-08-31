package com.exam.project.reservation.task;

import com.exam.project.reservation.repository.ReservationRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

@Log
@Component
public class ScheduledTasks {

    private final ReservationRepository reservationRepository;

    private static final SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:00:00");

    @Value("${app.notification.hours}")
    private int betweenNotificationsHours;

    @Value("${app.notification.enable}")
    private boolean enableReservationNotification;

    public ScheduledTasks(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    // run notification every hour from monday to saturday cron = 0 0 */1 * * 1-6"
    // alternatively cron = "*/10 * * * * *" run every 10 seconds
    // @Scheduled(cron = "0 0 */1 * * 1-6")
    //@Scheduled(cron = "*/5 * * * * *")
    @Scheduled(cron = "0 0 */1 * * 1-6")
    public void sendReservationNotifications() {
        if (!enableReservationNotification){
            return;
        }
        log.log(Level.INFO, ">>> get reservation data list and send notification where timeSlot is less than 4 hours from how");
        Calendar cal = Calendar.getInstance();
        String startDate = sdt.format(cal.getTime());
        // 4 hrs from now
        cal.add(Calendar.HOUR_OF_DAY, betweenNotificationsHours);
        String endDate = sdt.format(cal.getTime());
        log.log(Level.INFO, ">>>> start time: " + startDate);
        log.log(Level.INFO, ">>>> end time: {}" + endDate);
        log.log(Level.INFO, "hello");
        List<Long> reservationId = reservationRepository.findReservationIDsForNotification(startDate, endDate);
        reservationId.forEach(id -> log.log(Level.INFO, ">>> send notification to reservation id: " + id));
    }
}
