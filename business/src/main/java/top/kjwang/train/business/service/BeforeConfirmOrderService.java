package top.kjwang.train.business.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.kjwang.train.business.enums.RedisKeyPreEnum;
import top.kjwang.train.business.enums.RocketMQTopicEnum;
import top.kjwang.train.business.mapper.ConfirmOrderMapper;
import top.kjwang.train.business.req.ConfirmOrderDoReq;
import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.exception.BusinessException;
import top.kjwang.train.common.exception.BusinessExceptionEnum;

import java.util.concurrent.TimeUnit;

/**
 * @author kjwang
 * @date 2023/12/1 10:58
 * @description BeforeConfirmOrderService
 */

@Service
public class BeforeConfirmOrderService {

	private static final Logger LOG = LoggerFactory.getLogger(BeforeConfirmOrderService.class);

	@Resource
	private ConfirmOrderMapper confirmOrderMapper;

	@Resource
	private DailyTrainTicketService dailyTrainTicketService;

	@Resource
	private DailyTrainCarriageService dailyTrainCarriageService;

	@Resource
	private DailyTrainSeatService dailyTrainSeatService;

	@Resource
	private AfterConfirmOrderService afterConfirmOrderService;

	@Resource
	public RocketMQTemplate rocketMQTemplate;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private SkTokenService skTokenService;

	@SentinelResource(value = "beforeDoConfirm", blockHandler = "beforeDoConfirmBlock")
	public void beforeDoConfirm(ConfirmOrderDoReq req) {

		// 校验令牌余量
		boolean validSkToken = skTokenService.validSkToken(req.getDate(), req.getTrainCode(), LoginMemberContext.getId());
		if (validSkToken) {
			LOG.info("令牌校验通过");
		} else {
			LOG.info("令牌校验不通过");
			throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_SK_TOKEN_FAIL);
		}

		// 获取车次锁
		String lockKey = RedisKeyPreEnum.CONFIRM_ORDER + "-" + DateUtil.formatDate(req.getDate()) + "-" + req.getTrainCode();
		// setIfAbsent就是对应redis的setnx
		Boolean setIfAbsent = redisTemplate.opsForValue().setIfAbsent(lockKey, lockKey, 10, TimeUnit.SECONDS);
		if (Boolean.TRUE.equals(setIfAbsent)) {
			LOG.info("恭喜，抢到锁了！lockKey：{}", lockKey);
		} else {
			// 只是没抢到锁，并不知道票抢完了没，所以提示稍候再试
			LOG.info("很遗憾，没抢到锁！lockKey：{}", lockKey);
			throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_LOCK_FAIL);
		}

		// 发送MQ排队购票
		String reqJson = JSON.toJSONString(req);
		LOG.info("排队购票，发送mq开始，消息：{}", reqJson);
		rocketMQTemplate.convertAndSend(RocketMQTopicEnum.CONFIRM_ORDER.getCode(), reqJson);
		LOG.info("排队购票，发送mq结束");

	}

	/**
	 * 降级方法，需包含限流方法的所有参数和BlockException参数
	 *
	 * @param req
	 * @param e
	 */
	public void beforeDoConfirmBlock(ConfirmOrderDoReq req, BlockException e) {
		LOG.info("购票请求被限流：{}", req);
		throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_FLOW_EXCEPTION);
	}
}