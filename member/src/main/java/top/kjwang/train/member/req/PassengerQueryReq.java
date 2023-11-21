package top.kjwang.train.member.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.kjwang.train.common.req.PageReq;

/**
 * @author kjwang
 * @date 2023/11/21 16:01
 * @description PassengerQueryReq
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PassengerQueryReq extends PageReq {
	private Long memberId;
}
