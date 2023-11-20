package top.kjwang.train;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kjwang
 * @date 2023/11/20 15:04
 * @description TestController
 */

@RestController
public class TestController {
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
}
