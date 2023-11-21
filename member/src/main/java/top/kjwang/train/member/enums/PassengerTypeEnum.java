package top.kjwang.train.member.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

/**
 * @author kjwang
 * @date 2023/11/21 14:24
 * @description PassengerTypeEnum
 */

@Getter
public enum PassengerTypeEnum {
	AUDIT("1","成人"),
	CHILD("2","儿童"),
	STUDENT("3","学生");

	private String code;
	private String desc;

	PassengerTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static List<HashMap<String, String>> getEnumList() {
		List<HashMap<String, String>> list = new ArrayList<>();
		for (PassengerTypeEnum anEnum : EnumSet.allOf(PassengerTypeEnum.class)) {
			HashMap<String, String> map = new HashMap<>();
			map.put("code", anEnum.code);
			map.put("desc", anEnum.desc);
			list.add(map);
		}
		return list;
	}
}
