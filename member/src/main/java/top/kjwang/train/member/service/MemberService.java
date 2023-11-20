package top.kjwang.train.member.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.kjwang.train.member.mapper.MemberMapper;

/**
 * @author kjwang
 * @date 2023/11/20 16:01
 * @description MemberService
 */

@Service
public class MemberService {
	@Resource
	private MemberMapper memberMapper;

	public int count() {
		return memberMapper.count();
	}
}
