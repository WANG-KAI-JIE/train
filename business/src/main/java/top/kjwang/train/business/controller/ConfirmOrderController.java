package top.kjwang.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.train.business.req.ConfirmOrderDoReq;
import top.kjwang.train.business.service.BeforeConfirmOrderService;
import top.kjwang.train.business.service.ConfirmOrderService;
import top.kjwang.train.common.resp.CommonResp;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

/**
 * @author kjwang
 * @date 2023/11/27 14:29
 * @description ConfirmOrderController
 */

@RestController
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

	private static final Logger LOG = LoggerFactory.getLogger(ConfirmOrderController.class);


	@Resource
	private ConfirmOrderService confirmOrderService;

	@Resource
	private BeforeConfirmOrderService beforeConfirmOrderService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	// 接口的资源名称不要和接口路径一致，会导致限流后走不到降级方法中
	@SentinelResource(value = "confirmOrderDo", blockHandler = "doConfirmBlock")
	@PostMapping("/do")
	public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
		// 图形验证码校验
		String imageCodeToken = req.getImageCodeToken();
		String imageCode = req.getImageCode();
		String imageCodeRedis = redisTemplate.opsForValue().get(imageCodeToken);
		LOG.info("从redis中获取到的验证码：{}", imageCodeRedis);
		if (ObjectUtils.isEmpty(imageCodeRedis)) {
			return new CommonResp<>(false, "验证码已过期", null);
		}
		// 验证码校验，忽略大小写
		if (!imageCodeRedis.equalsIgnoreCase(imageCode)) {
			return new CommonResp<>(false, "验证码不正确", null);
		} else {
			// 验证通过后，移除验证码
			redisTemplate.delete(imageCodeToken);
		}
		beforeConfirmOrderService.beforeDoConfirm(req);
		return new CommonResp<>();
	}
}