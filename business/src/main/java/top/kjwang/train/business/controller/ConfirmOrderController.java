package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.req.ConfirmOrderDoReq;
import top.kjwang.train.business.service.ConfirmOrderService;
import top.kjwang.train.common.resp.CommonResp;

/**
 * @author kjwang
 * @date 2023/11/27 14:29
 * @description ConfirmOrderController
 */

@RestController
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

	@Resource
	private ConfirmOrderService confirmOrderService;

	@PostMapping("/do")
	public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
		confirmOrderService.doConfirm(req);
		return new CommonResp<>();
	}
}
