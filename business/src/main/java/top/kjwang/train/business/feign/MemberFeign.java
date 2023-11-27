package top.kjwang.train.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.kjwang.train.common.req.MemberTicketReq;
import top.kjwang.train.common.resp.CommonResp;

/**
 * @author kjwang
 * @date 2023/11/27 16:14
 * @description MemberFeign
 */
// @FeignClient(name = "member", url = "http://127.0.0.1:8001")
@FeignClient(name = "member")
public interface MemberFeign {

	@GetMapping("/member/feign/ticket/save")
	CommonResp<Object> save(@RequestBody MemberTicketReq req);

}