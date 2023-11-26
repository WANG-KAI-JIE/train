package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.req.DailyTrainTicketQueryReq;
import top.kjwang.train.business.resp.DailyTrainTicketQueryResp;
import top.kjwang.train.business.service.DailyTrainTicketService;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;

/**
 * @author kjwang
 * @date 2023/11/26 18:35
 * @description DailyTrainTicketController
 */


@RestController
@RequestMapping("/daily-train-ticket")
public class DailyTrainTicketController {

	@Resource
	private DailyTrainTicketService dailyTrainTicketService;

	@GetMapping("/query-list")
	public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
		PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
		return new CommonResp<>(list);
	}
}
