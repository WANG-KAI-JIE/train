package top.kjwang.train.member.req;

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

	private String name;

	private String idCard;

	private String type;

	private Date createTime;

	private Date updateTime;
}
