package top.kjwang.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/11/20 18:54
 * @description MemberLoginReq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginReq {
	@NotBlank(message = "【手机号】不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
	private String mobile;

	@NotBlank(message = "【短信验证码】不能为空")
	private String code;
}
