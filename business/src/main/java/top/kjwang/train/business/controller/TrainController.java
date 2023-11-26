package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.resp.TrainQueryResp;
import top.kjwang.train.business.service.TrainService;
import top.kjwang.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author kjwang
 * @date 2023/11/26 18:35
 * @description TrainController
 */

@RestController
@RequestMapping("/train")
public class TrainController {

	@Resource
	private TrainService trainService;

	@GetMapping("/query-all")
	public CommonResp<List<TrainQueryResp>> queryList() {
		List<TrainQueryResp> list = trainService.queryAll();
		return new CommonResp<>(list);
	}
}
