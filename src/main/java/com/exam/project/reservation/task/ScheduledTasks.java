package com.exam.project.reservation.task;

import com.exam.project.reservation.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
public class ScheduledTasks {

    private final ReservationRepository reservationRepository;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:00:00");

    public ScheduledTasks(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    // run notification every hour from monday to saturday cron = 0 0 */1 * * 1-6"
    // alternatively cron = "*/10 * * * * *" run every 10 seconds
    @Scheduled(cron = "0 0 */1 * * 1-6")
    public void sendReservationNotifications() {
        log.info(">>> get reservation data list and send notification where timeSlot is less than 4 hours from how");
        Calendar cal = Calendar.getInstance();
        String startDate = sdt.format(cal.getTime());
        // 4 hrs from now
        cal.add(Calendar.HOUR_OF_DAY, 4);
        String endDate = sdt.format(cal.getTime());
        log.info("start time: {}", startDate);
        log.info("end time: {}", endDate);
        List<Long> reservationId = reservationRepository.findReservationIDsForNotification(startDate, endDate);
        reservationId.forEach(id -> log.info("send notification to reservation id: {}", id));
    }
}
