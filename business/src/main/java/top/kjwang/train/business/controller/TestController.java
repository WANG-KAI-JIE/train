package top.kjwang.train.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kjwang
 * @date 2023/11/22 08:40
 * @description TestController
 */

@RestController
public class TestController {

	@SentinelResource("hello")
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!\nBusiness!";
	}
}
