package top.kjwang.train.member.feign;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.common.req.MemberTicketReq;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.member.service.TicketService;

/**
 * @author kjwang
 * @date 2023/11/27 16:12
 * @description FeignTicketController
 */

@RestController
@RequestMapping("/feign/ticket")
public class FeignTicketController {

	@Resource
	private TicketService ticketService;

	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody MemberTicketReq req) throws Exception {
		ticketService.save(req);
		return new CommonResp<>();
	}

}