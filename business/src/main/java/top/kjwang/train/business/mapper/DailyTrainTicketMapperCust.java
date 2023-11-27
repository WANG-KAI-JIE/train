package top.kjwang.train.business.mapper;

import java.util.Date;

/**
 * @author kjwang
 * @date 2023/11/27 15:47
 * @description DailyTrainTicketMapperCust
 */

public interface DailyTrainTicketMapperCust {
	void updateCountBySell(Date date
			, String trainCode
			, String seatTypeCode
			, Integer minStartIndex
			, Integer maxStartIndex
			, Integer minEndIndex
			, Integer maxEndIndex);
}
