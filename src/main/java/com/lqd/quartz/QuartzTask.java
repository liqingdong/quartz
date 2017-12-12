package com.lqd.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTask extends QuartzJobBean {

    private TestDao testDao;

    public void setUserDao(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SchedulerContext sctx = null;
        try {
            sctx = context.getScheduler().getContext();
            testDao = (TestDao) sctx.get("testDao");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        testDao.testMethod("zhangsan");
        System.out.println("---分割线---");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("定时任务开始执行,现在时间是：" + sdf.format(date));
    }

}
