package com.hit.admission.job;

import com.hit.admission.controller.AdmissionController;
import java.time.LocalDate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 *
 * @author Huy Doan
 */
public class CongBoDiemChuanJob implements Job {
    
    private final AdmissionController admissionController = new AdmissionController();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        admissionController.handleAdmissonAndSendMail(LocalDate.now().getYear());
        // Lấy đối tượng Scheduler từ JobExecutionContext
        Scheduler scheduler = context.getScheduler();
        try {
            // Dừng scheduler
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
