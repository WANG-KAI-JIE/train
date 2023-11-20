package top.kjwang.train.member.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/11/20 18:57
 * @description MemberLoginResp
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResp {
	private Long id;

	private String mobile;

	private String token;
}
