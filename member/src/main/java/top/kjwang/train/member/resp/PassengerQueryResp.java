package top.kjwang.train.member.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author kjwang
 * @date 2023/11/21 16:09
 * @description PassengerQueryResp
 */

@Data
public class PassengerQueryResp {
	private Long id;

	private Long memberId;

	private String name;

	private String idCard;

	private String type;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
}
