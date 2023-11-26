package top.kjwang.train.batch.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import top.kjwang.train.batch.feign.BusinessFeign;
import top.kjwang.train.common.resp.CommonResp;

import java.util.Date;

/**
 * @author kjwang
 * @date 2023/11/24 10:13
 * @description DailyTrainJob
 */

@DisallowConcurrentExecution
public class DailyTrainJob implements Job {
    public static final Logger LOG = LoggerFactory.getLogger(DailyTrainJob.class);

    @Resource
    private BusinessFeign businessFeign;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 增加日志流水号
        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));
        LOG.info("生成2天后的车次定时任务开始");
        Date now = new Date();
        DateTime dateTime = DateUtil.offsetDay(now, 2);
        Date offsetDay = dateTime.toJdkDate();
        CommonResp<Object> commonResp = businessFeign.genDaily(offsetDay);
        LOG.info("commonResp:{}", commonResp);
        LOG.info("生成2天后的车次定时任务结束");
    }
}
