package top.kjwang.train.member.controller;

import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.member.service.MemberService;

/**
 * @author kjwang
 * @date 2023/11/20 16:04
 * @description MemberController
 */

@RestController
@RequestMapping("/member")
@MapperScan("top.kjwang.train.*.mapper")
public class MemberController {
	@Resource
	private MemberService memberService;

	@GetMapping("/count")
	public Integer count() {
		return memberService.count();
	}
}
