package top.kjwang.train.member.config;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author kjwang
 * @date 2023/11/20 15:12
 * @description MemberApplication
 */

@SpringBootApplication
@ComponentScan("top.kjwang")
@Mapper
public class MemberApplication {
	private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MemberApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！！！");
		LOG.info("测试地址：\thttp://127.0.0.1:{}/hello", env.getProperty("server.port"));
	}
}
