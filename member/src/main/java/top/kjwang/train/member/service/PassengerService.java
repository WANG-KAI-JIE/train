package top.kjwang.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.member.domain.Passenger;
import top.kjwang.train.member.mapper.PassengerMapper;
import top.kjwang.train.member.req.PassengerSaveReq;

/**
 * @author kjwang
 * @date 2023/11/21 14:32
 * @description PassengerService
 */

@Service
public class PassengerService {

	@Resource
	private PassengerMapper passengerMapper;

	public void save(PassengerSaveReq req) {
		DateTime now = DateTime.now();
		Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
		passenger.setMemberId(LoginMemberContext.getId());
		passenger.setId(SnowUtil.getSnowflakeNextId());
		passenger.setCreateTime(now);
		passenger.setUpdateTime(now);
		passengerMapper.insert(passenger);
	}
}
