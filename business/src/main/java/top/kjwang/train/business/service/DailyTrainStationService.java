package top.kjwang.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.kjwang.train.business.domain.TrainStation;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.business.domain.DailyTrainStation;
import top.kjwang.train.business.domain.DailyTrainStationExample;
import top.kjwang.train.business.mapper.DailyTrainStationMapper;
import top.kjwang.train.business.req.DailyTrainStationQueryReq;
import top.kjwang.train.business.req.DailyTrainStationSaveReq;
import top.kjwang.train.business.resp.DailyTrainStationQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainStationService {

	private static final Logger LOG = LoggerFactory.getLogger(DailyTrainStationService.class);

	@Resource
	private DailyTrainStationMapper dailyTrainStationMapper;

	@Resource
	private TrainStationService trainStationService;

	public void save(DailyTrainStationSaveReq req) {
		DateTime now = DateTime.now();
		DailyTrainStation dailyTrainStation = BeanUtil.copyProperties(req, DailyTrainStation.class);
		if (ObjectUtil.isNull(dailyTrainStation.getId())) {
			dailyTrainStation.setId(SnowUtil.getSnowflakeNextId());
			dailyTrainStation.setCreateTime(now);
			dailyTrainStation.setUpdateTime(now);
			dailyTrainStationMapper.insert(dailyTrainStation);
		} else {
			dailyTrainStation.setUpdateTime(now);
			dailyTrainStationMapper.updateByPrimaryKey(dailyTrainStation);
		}
	}

	public PageResp<DailyTrainStationQueryResp> queryList(DailyTrainStationQueryReq req) {
		DailyTrainStationExample dailyTrainStationExample = new DailyTrainStationExample();
		dailyTrainStationExample.setOrderByClause("id desc");
		DailyTrainStationExample.Criteria criteria = dailyTrainStationExample.createCriteria();
		if (ObjectUtil.isNotNull(req.getDate())) {
			criteria.andDateEqualTo(req.getDate());
		}
		if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
			criteria.andTrainCodeEqualTo(req.getTrainCode());
		}

		LOG.info("查询页码：{}", req.getPage());
		LOG.info("每页条数：{}", req.getSize());
		PageHelper.startPage(req.getPage(), req.getSize());
		List<DailyTrainStation> dailyTrainStationList = dailyTrainStationMapper.selectByExample(dailyTrainStationExample);

		PageInfo<DailyTrainStation> pageInfo = new PageInfo<>(dailyTrainStationList);
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());

		List<DailyTrainStationQueryResp> list = BeanUtil.copyToList(dailyTrainStationList, DailyTrainStationQueryResp.class);

		PageResp<DailyTrainStationQueryResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);
		return pageResp;
	}

	public void delete(Long id) {
		dailyTrainStationMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 生成某日所有车次信息，包括车次、车站、车厢、座位
	 *
	 * @param date 日期
	 */
	public void genDaily(Date date, String trainCode) {
		LOG.info("生成日期【{}】车次【{}】的车站信息开始", DateUtil.formatDate(date), trainCode);

		// 删除某日某车次的车站信息
		DailyTrainStationExample dailyTrainStationExample = new DailyTrainStationExample();
		dailyTrainStationExample.createCriteria()
				.andDateEqualTo(date)
				.andTrainCodeEqualTo(trainCode);
		dailyTrainStationMapper.deleteByExample(dailyTrainStationExample);

		// 查出某车次的所有的车站信息
		List<TrainStation> stationList = trainStationService.selectByTrainCode(trainCode);
		if (CollUtil.isEmpty(stationList)) {
			LOG.info("该车次没有车站基础数据，生成该车次的车站信息结束");
			return;
		}

		for (TrainStation trainStaion : stationList) {
			DateTime now = DateTime.now();
			DailyTrainStation dailyTrainStation = BeanUtil.copyProperties(trainStaion, DailyTrainStation.class);
			dailyTrainStation.setId(SnowUtil.getSnowflakeNextId());
			dailyTrainStation.setCreateTime(now);
			dailyTrainStation.setUpdateTime(now);
			dailyTrainStation.setDate(date);
			dailyTrainStationMapper.insert(dailyTrainStation);
		}
		LOG.info("生成日期【{}】车次【{}】的车站信息结束", DateUtil.formatDate(date), trainCode);
	}

}