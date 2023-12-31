package top.kjwang.train.batch.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author kjwang
 * @date 2023/11/23 13:45
 * @description SchedulerConfig
 */

@Configuration
public class SchedulerConfig {

	@Resource
	private MyJobFactory myJobFactory;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJobFactory(myJobFactory);
		factory.setStartupDelay(2);
		return factory;
	}
}