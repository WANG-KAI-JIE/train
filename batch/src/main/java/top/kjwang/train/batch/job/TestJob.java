package top.kjwang.train.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author kjwang
 * @date 2023/11/23 13:41
 * @description TestJob
 */
@DisallowConcurrentExecution
public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("TestJob TEST 开始");
		//try {
		//    Thread.sleep(3000);
		//} catch (InterruptedException e) {
		//    e.printStackTrace();
		//}
		System.out.println("TestJob TEST 结束");
	}
}
