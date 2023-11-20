package top.kjwang.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/11/20 18:38
 * @description MemberSendCodeReq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSendCodeReq {

	@NotBlank(message = "【手机号】不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机号格式有错误")
	private String mobile;
}
