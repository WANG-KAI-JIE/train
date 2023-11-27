package top.kjwang.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.member.req.TicketQueryReq;
import top.kjwang.train.member.resp.TicketQueryResp;
import top.kjwang.train.member.service.TicketService;

/**
 * @author kjwang
 * @date 2023/11/27 16:32
 * @description TicketController
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Resource
	private TicketService ticketService;

	@GetMapping("/query-list")
	public CommonResp<PageResp<TicketQueryResp>> query(@Valid TicketQueryReq req) {
		CommonResp<PageResp<TicketQueryResp>> commonResp = new CommonResp<>();
		req.setMemberId(LoginMemberContext.getId());
		PageResp<TicketQueryResp> pageResp = ticketService.queryList(req);
		commonResp.setContent(pageResp);
		return commonResp;
	}

}