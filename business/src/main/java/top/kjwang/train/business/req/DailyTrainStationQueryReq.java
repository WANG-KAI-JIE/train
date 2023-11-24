package top.kjwang.train.business.req;

import org.springframework.format.annotation.DateTimeFormat;
import top.kjwang.train.common.req.PageReq;

import java.util.Date;

public class DailyTrainStationQueryReq extends PageReq {

	public String getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private String trainCode;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@Override
	public String toString() {
		return "DailyTrainStationQueryReq{" +
				"} " + super.toString();
	}
}