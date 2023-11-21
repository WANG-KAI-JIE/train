package top.kjwang.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.member.req.PassengerSaveReq;
import top.kjwang.train.member.service.PassengerService;

/**
 * @author kjwang
 * @date 2023/11/21 14:34
 * @description passengerController
 */

@RestController
@RequestMapping("/passenger")
public class passengerController {

	@Resource
	private PassengerService passengerService;

	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
		passengerService.save(req);
		return new CommonResp<>();
	}
}
