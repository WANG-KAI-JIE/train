package top.kjwang.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.kjwang.train.business.domain.*;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.business.mapper.DailyTrainMapper;
import top.kjwang.train.business.req.DailyTrainQueryReq;
import top.kjwang.train.business.req.DailyTrainSaveReq;
import top.kjwang.train.business.resp.DailyTrainQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainService {

	private static final Logger LOG = LoggerFactory.getLogger(DailyTrainService.class);

	@Resource
	private DailyTrainMapper dailyTrainMapper;

	@Resource
	private TrainService trainService;

	@Resource
	private DailyTrainStationService dailyTrainStationService;

	@Resource
	private DailyTrainCarriageService dailyTrainCarriageService;

	@Resource
	private DailyTrainSeatService dailyTrainSeatService;

	@Resource
	private DailyTrainTicketService dailyTrainTicketService;

	@Resource
	private SkTokenService skTokenService;

	public void save(DailyTrainSaveReq req) {
		DateTime now = DateTime.now();
		DailyTrain dailyTrain = BeanUtil.copyProperties(req, DailyTrain.class);
		if (ObjectUtil.isNull(dailyTrain.getId())) {
			dailyTrain.setId(SnowUtil.getSnowflakeNextId());
			dailyTrain.setCreateTime(now);
			dailyTrain.setUpdateTime(now);
			dailyTrainMapper.insert(dailyTrain);
		} else {
			dailyTrain.setUpdateTime(now);
			dailyTrainMapper.updateByPrimaryKey(dailyTrain);
		}
	}

	public PageResp<DailyTrainQueryResp> queryList(DailyTrainQueryReq req) {
		DailyTrainExample dailyTrainExample = new DailyTrainExample();
		dailyTrainExample.setOrderByClause("date desc,code asc");
		DailyTrainExample.Criteria criteria = dailyTrainExample.createCriteria();
		if (ObjectUtil.isNotNull(req.getDate())) {
			criteria.andDateEqualTo(req.getDate());
		}
		if (ObjectUtil.isNotEmpty(req.getCode())) {
			criteria.andCodeEqualTo(req.getCode());
		}

		LOG.info("查询页码：{}", req.getPage());
		LOG.info("每页条数：{}", req.getSize());
		PageHelper.startPage(req.getPage(), req.getSize());
		List<DailyTrain> dailyTrainList = dailyTrainMapper.selectByExample(dailyTrainExample);

		PageInfo<DailyTrain> pageInfo = new PageInfo<>(dailyTrainList);
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());

		List<DailyTrainQueryResp> list = BeanUtil.copyToList(dailyTrainList, DailyTrainQueryResp.class);

		PageResp<DailyTrainQueryResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);
		return pageResp;
	}

	public void delete(Long id) {
		dailyTrainMapper.deleteByPrimaryKey(id);
	}

	public void genDaily(Date date) {
		List<Train> trainList = trainService.selectAll();
		if (CollUtil.isEmpty(trainList)) {
			LOG.info("没有车次基础数据，任务结束");
			return;
		}
		for (Train train : trainList) {
			genDailyTrain(date, train);
		}
	}

	public void genDailyTrain(Date date, Train train) {
		// 1. 删除该车次已有的数据
		DailyTrainExample dailyTrainExample = new DailyTrainExample();
		dailyTrainExample.createCriteria()
				.andDateEqualTo(date)
				.andCodeEqualTo(train.getCode());
		dailyTrainMapper.deleteByExample(dailyTrainExample);

		// 2. 生成该车次的数据
		DateTime now = DateTime.now();
		// 属性拷贝
		DailyTrain dailyTrain = BeanUtil.copyProperties(train, DailyTrain.class);
		// 补全或修改其他属性
		dailyTrain.setId(SnowUtil.getSnowflakeNextId());
		dailyTrain.setDate(date);
		dailyTrain.setCreateTime(now);
		dailyTrain.setUpdateTime(now);
		dailyTrainMapper.insert(dailyTrain);

		// 生成每日该车次的车站数据
		dailyTrainStationService.genDaily(date, train.getCode());
		LOG.info("生成日期【{}】车次【{}】的车站信息结束", DateUtil.formatDate(date), train.getCode());

		// 生成每日该车次的车厢数据
		dailyTrainCarriageService.genDaily(date, train.getCode());
		LOG.info("生成日期【{}】车次【{}】的车厢那个信息结束", DateUtil.formatDate(date), train.getCode());

		// 生成每日该车次的座位数据
		dailyTrainSeatService.genDaily(date, train.getCode());
		LOG.info("生成日期【{}】车次【{}】的座位信息结束", DateUtil.formatDate(date), train.getCode());

		// 生成每日该车次的余票数据
		dailyTrainTicketService.genDaily(dailyTrain, date, train.getCode());

		// 生成令牌余量数据
		skTokenService.genDaily(date, train.getCode());
		LOG.info("生成日期【{}】车次【{}】的余票信息结束", DateUtil.formatDate(date), train.getCode());
	}
}