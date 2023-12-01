package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.req.SeatSellReq;
import top.kjwang.train.business.resp.SeatSellResp;
import top.kjwang.train.business.service.DailyTrainSeatService;
import top.kjwang.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author kjwang
 * @date 2023/12/1 12:35
 * @description SeatSellController
 */

@RestController
@RequestMapping("/seat-sell")
public class SeatSellController {

	@Resource
	private DailyTrainSeatService dailyTrainSeatService;

	@GetMapping("/query")
	public CommonResp<List<SeatSellResp>> query(@Valid SeatSellReq req) {
		List<SeatSellResp> seatList = dailyTrainSeatService.querySeatSell(req);
		return new CommonResp<>(seatList);
	}

}
