package top.kjwang.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.member.req.MemberLoginReq;
import top.kjwang.train.member.req.MemberRegisterReq;
import top.kjwang.train.member.req.MemberSendCodeReq;
import top.kjwang.train.member.resp.MemberLoginResp;
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

	@PostMapping("/register")
	public CommonResp<Long> register(@Valid MemberRegisterReq req) {
		long register = memberService.register(req);
		return new CommonResp<>(register);
	}

	@PostMapping("/send-code")
	public CommonResp<Long> sendCode(@Valid MemberSendCodeReq req) {
		memberService.sendCode(req);
		return new CommonResp<>();
	}

	@PostMapping("/login")
	public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
		MemberLoginResp resp = memberService.login(req);
		return new CommonResp<>(resp);
	}
}
