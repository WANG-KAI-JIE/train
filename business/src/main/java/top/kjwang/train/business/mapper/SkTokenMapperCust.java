package top.kjwang.train.business.mapper;

import java.util.Date;

/**
 * @author kjwang
 * @date 2023/12/1 10:05
 * @description SkTokenMapperCust
 */
public interface SkTokenMapperCust {
	int decrease(Date date, String trainCode);
}
