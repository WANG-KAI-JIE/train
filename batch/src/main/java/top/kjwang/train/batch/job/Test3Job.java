package top.kjwang.train.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author kjwang
 * @date 2023/11/23 14:03
 * @description Test3Job
 */
@DisallowConcurrentExecution
public class Test3Job implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("TestJob333333333 TEST开始");
		System.out.println("TestJob333333333 TEST结束");
	}
}