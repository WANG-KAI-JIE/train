package top.kjwang.train.business.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

/**
 * @author kjwang
 * @date 2023/11/22 09:03
 * @description TrainTypeEnum
 */

@Getter
public enum TrainTypeEnum {
	G("G", "高铁", new BigDecimal("1.2")),
	D("D", "动车", new BigDecimal("1")),
	K("K", "快速", new BigDecimal("0.8"));

	private String code;

	private String desc;

	/**
	 * 票价比例，例：1.1，则票价= 1.1 * 每公里单价（SeatTypeEnum.price）公里 （station.km）
	 */
	private BigDecimal priceRate;

	TrainTypeEnum(String code, String desc, BigDecimal priceRate) {
		this.code = code;
		this.desc = desc;
		this.priceRate = priceRate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPriceRate(BigDecimal priceRate) {
		this.priceRate = priceRate;
	}

	@Override
	public String toString() {
		return "TrainTypeEnum{" +
				"code='" + code + '\'' +
				", desc='" + desc + '\'' +
				", priceRate=" + priceRate +
				'}';
	}

	public static List<HashMap<String, String>> getEnumList() {
		List<HashMap<String, String>> list = new ArrayList<>();
		for (TrainTypeEnum anEnum : EnumSet.allOf(TrainTypeEnum.class)) {
			HashMap<String, String> map = new HashMap<>();
			map.put("code", anEnum.code);
			map.put("desc", anEnum.desc);
		}
		return list;
	}
}
