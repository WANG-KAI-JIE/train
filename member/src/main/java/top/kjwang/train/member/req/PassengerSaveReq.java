package top.kjwang.train.member.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kjwang
 * @date 2023/11/21 14:30
 * @description PassengerSaveReq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerSaveReq {
	private Long id;

	private Long memberId;

	@NotBlank(message = "【名字】不能为空")
	private String name;

	@NotBlank(message = "【身份证】不能为空")
	private String idCard;

	@NotBlank(message = "【旅客类型】不能为空")
	private String type;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
}
