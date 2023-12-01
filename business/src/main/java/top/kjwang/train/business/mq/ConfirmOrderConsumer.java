package top.kjwang.train.business.mq;

/**
 * @author kjwang
 * @date 2023/12/1 11:11
 * @description ConfirmOrderConsumer
 */

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.kjwang.train.business.req.ConfirmOrderDoReq;
import top.kjwang.train.business.service.ConfirmOrderService;

@Service
@RocketMQMessageListener(consumerGroup = "default", topic = "CONFIRM_ORDER")
public class ConfirmOrderConsumer implements RocketMQListener<MessageExt> {

	private static final Logger LOG = LoggerFactory.getLogger(ConfirmOrderConsumer.class);

	@Resource
	private ConfirmOrderService confirmOrderService;

	@Override
	public void onMessage(MessageExt messageExt) {
		byte[] body = messageExt.getBody();
		LOG.info("ROCKETMQ 收到消息：{}", new String(body));
		ConfirmOrderDoReq req = JSON.parseObject(new String(body), ConfirmOrderDoReq.class);
		confirmOrderService.doConfirm(req);
	}
}