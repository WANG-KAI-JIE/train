package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.req.DailyTrainStationQueryAllReq;
import top.kjwang.train.business.resp.DailyTrainStationQueryResp;
import top.kjwang.train.business.service.DailyTrainStationService;
import top.kjwang.train.common.resp.CommonResp;
import java.util.List;

/**
 * @author kjwang
 * @date 2023/12/1 12:29
 * @description DailyTrainStationController
 */

@RestController
@RequestMapping("/daily-train-station")
public class DailyTrainStationController {

	@Resource
	private DailyTrainStationService dailyTrainStationService;

	@GetMapping("/query-by-train-code")
	public CommonResp<List<DailyTrainStationQueryResp>> queryByTrain(@Valid DailyTrainStationQueryAllReq req) {
		List<DailyTrainStationQueryResp> list = dailyTrainStationService.queryByTrain(req.getDate(), req.getTrainCode());
		return new CommonResp<>(list);
	}

}
