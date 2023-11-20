package top.kjwang.train.member.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kjwang
 * @date 2023/11/20 15:12
 * @description MemberApplication
 */

@SpringBootApplication
@ComponentScan("top.kjwang")
public class MemberApplication {
	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}
}
