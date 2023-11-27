package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.ConfirmOrderQueryReq;
import top.kjwang.train.business.req.ConfirmOrderDoReq;
import top.kjwang.train.business.resp.ConfirmOrderQueryResp;
import top.kjwang.train.business.service.ConfirmOrderService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/confirm-order")
public class ConfirmOrderAdminController {

	@Resource
	private ConfirmOrderService confirmOrderService;

	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody ConfirmOrderDoReq req) {
		confirmOrderService.save(req);
		return new CommonResp<>();
	}

	@GetMapping("/query-list")
	public CommonResp<PageResp<ConfirmOrderQueryResp>> queryList(@Valid ConfirmOrderQueryReq req) {
		PageResp<ConfirmOrderQueryResp> list = confirmOrderService.queryList(req);
		return new CommonResp<>(list);
	}

	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable Long id) {
		confirmOrderService.delete(id);
		return new CommonResp<>();
	}

}