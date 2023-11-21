package top.kjwang.train.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/11/21 14:53
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
