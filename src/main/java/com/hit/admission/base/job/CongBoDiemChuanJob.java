package com.hit.admission.base.job;

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

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Thực hiện công việc của bạn trong đây
        System.out.println("Công bố điểm chuẩn...");
        
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
