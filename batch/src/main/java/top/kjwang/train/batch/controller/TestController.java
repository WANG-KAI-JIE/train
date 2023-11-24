package top.kjwang.train.batch.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.batch.feign.BusinessFeign;

/**
 * @author kjwang
 * @date 2023/11/22 08:40
 * @description TestController
 */

@RestController
@Slf4j
public class TestController {

	@Resource
	private BusinessFeign businessFeign;

	@GetMapping("/hello")
	public String hello() {
		log.info("business hello: {}", businessFeign.getHello());
		return "Hello World!\nBatch!";
	}
}
