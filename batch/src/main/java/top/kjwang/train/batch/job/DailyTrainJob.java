package top.kjwang.train.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kjwang
 * @date 2023/11/24 10:13
 * @description DailyTrainJob
 */

@DisallowConcurrentExecution
public class DailyTrainJob implements Job {
    public static final Logger LOG = LoggerFactory.getLogger(DailyTrainJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob222222 TEST开始");
        System.out.println("TestJob222222 TEST结束");
    }
}
