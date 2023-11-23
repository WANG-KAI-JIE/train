package top.kjwang.train.batch.req;

import lombok.Data;

/**
 * @author kjwang
 * @date 2023/11/23 13:45
 * @description CronJobReq
 */
@Data
public class CronJobReq {
	private String group;

	private String name;

	private String description;

	private String cronExpression;
}