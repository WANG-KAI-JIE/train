package top.kjwang.train.member.service;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.kjwang.train.common.exception.BusinessException;
import top.kjwang.train.common.exception.BusinessExceptionEnum;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.member.domain.Member;
import top.kjwang.train.member.domain.MemberExample;
import top.kjwang.train.member.mapper.MemberMapper;
import top.kjwang.train.member.req.MemberRegisterReq;

import java.util.List;

/**
 * @author kjwang
 * @date 2023/11/20 16:14
 * @description MemberService
 */

@Service
public class MemberService {

	@Resource
	private MemberMapper memberMapper;

	public int count() {
		return Math.toIntExact(memberMapper.countByExample(null));
	}

	public long register(MemberRegisterReq req) {
		String mobile = req.getMobile();
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria().andMobileEqualTo(mobile);
		List<Member> list = memberMapper.selectByExample(memberExample);

		if (CollUtil.isNotEmpty(list)) {
			throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
		}

		Member member = new Member();
		member.setId(SnowUtil.getSnowflakeNextId());
		member.setMobile(mobile);
		memberMapper.insert(member);
		return member.getId();
	}
}
