package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.resp.StationQueryResp;
import top.kjwang.train.business.service.StationService;
import top.kjwang.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author kjwang
 * @date 2023/11/26 18:35
 * @description TrainStationController
 */


@RestController
@RequestMapping("/station")
public class StationController {

	@Resource
	private StationService stationService;

	@GetMapping("/query-all")
	public CommonResp<List<StationQueryResp>> queryList() {
		List<StationQueryResp> list = stationService.queryAll();
		return new CommonResp<>(list);
	}
}
