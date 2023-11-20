package top.kjwang.train.member.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/11/20 16:43
 * @description MemberRegisterReq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRegisterReq {

	private String mobile;

}
