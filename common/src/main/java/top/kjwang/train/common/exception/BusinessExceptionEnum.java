package top.kjwang.train.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kjwang
 * @date 2023/11/20 17:39
 * @description BusinessExceptionEnum
 */

@Getter
@ToString
public enum BusinessExceptionEnum {
	MEMBER_MOBILE_EXIST("手机号已注册");

	private String desc;

	BusinessExceptionEnum(String desc) {
		this.desc = desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
