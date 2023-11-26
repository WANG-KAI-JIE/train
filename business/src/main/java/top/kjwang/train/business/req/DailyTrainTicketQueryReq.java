package top.kjwang.train.business.req;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import top.kjwang.train.common.req.PageReq;

import java.util.Date;


@Getter
@Setter
public class DailyTrainTicketQueryReq extends PageReq {
	/**
	 * 日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	/**
	 * 日期编号
	 */
	private String trainCode;

	/**
	 * 出发站
	 */
	private String start;

	/**
	 * 到达站
	 */
	private String end;

	@Override
	public String toString() {
		return "DailyTrainTicketQueryReq{" +
				"} " + super.toString();
	}
}