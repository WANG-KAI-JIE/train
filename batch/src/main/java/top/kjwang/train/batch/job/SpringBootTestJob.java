// package top.kjwang.train.batch.job;
//
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// /**
//  * @author kjwang
//  * @date 2023/11/23 13:37
//  * @description SpringBootTestJob
//  */
//
// @Component
// @EnableScheduling
// public class SpringBootTestJob {
//
// 	@Scheduled(cron = "0/5 * * * * ?")
// 	private void test() {
// 		// 增加分布式锁，解决集群问题
// 		System.out.println("SpringBootTestJob Test");
// 	}
// }
