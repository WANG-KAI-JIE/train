package top.kjwang.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.kjwang.train.business.domain.TrainCarriage;
import top.kjwang.train.business.enums.SeatColEnum;
import top.kjwang.train.business.mapper.TrainCarriageMapper;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.business.domain.DailyTrainCarriage;
import top.kjwang.train.business.domain.DailyTrainCarriageExample;
import top.kjwang.train.business.mapper.DailyTrainCarriageMapper;
import top.kjwang.train.business.req.DailyTrainCarriageQueryReq;
import top.kjwang.train.business.req.DailyTrainCarriageSaveReq;
import top.kjwang.train.business.resp.DailyTrainCarriageQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainCarriageService {

	private static final Logger LOG = LoggerFactory.getLogger(DailyTrainCarriageService.class);

	@Resource
	private DailyTrainCarriageMapper dailyTrainCarriageMapper;

	@Resource
	private TrainCarriageService trainCarriageService;

	public void save(DailyTrainCarriageSaveReq req) {
		DateTime now = DateTime.now();

		// 自动计算出列数和座位数
		List<SeatColEnum> seatColEnums = SeatColEnum.getColsByType(req.getSeatType());
		req.setColCount(seatColEnums.size());
		req.setSeatCount(req.getColCount() * req.getRowCount());

		DailyTrainCarriage dailyTrainCarriage = BeanUtil.copyProperties(req, DailyTrainCarriage.class);
		if (ObjectUtil.isNull(dailyTrainCarriage.getId())) {
			dailyTrainCarriage.setId(SnowUtil.getSnowflakeNextId());
			dailyTrainCarriage.setCreateTime(now);
			dailyTrainCarriage.setUpdateTime(now);
			dailyTrainCarriageMapper.insert(dailyTrainCarriage);
		} else {
			dailyTrainCarriage.setUpdateTime(now);
			dailyTrainCarriageMapper.updateByPrimaryKey(dailyTrainCarriage);
		}
	}

	public PageResp<DailyTrainCarriageQueryResp> queryList(DailyTrainCarriageQueryReq req) {
		DailyTrainCarriageExample dailyTrainCarriageExample = new DailyTrainCarriageExample();
		dailyTrainCarriageExample.setOrderByClause("date desc,train_code asc,`index` asc");
		DailyTrainCarriageExample.Criteria criteria = dailyTrainCarriageExample.createCriteria();
		if (ObjectUtil.isNotNull(req.getDate())) {
			criteria.andDateEqualTo(req.getDate());
		}
		if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
			criteria.andTrainCodeEqualTo(req.getTrainCode());
		}

		LOG.info("查询页码：{}", req.getPage());
		LOG.info("每页条数：{}", req.getSize());
		PageHelper.startPage(req.getPage(), req.getSize());
		List<DailyTrainCarriage> dailyTrainCarriageList = dailyTrainCarriageMapper.selectByExample(dailyTrainCarriageExample);

		PageInfo<DailyTrainCarriage> pageInfo = new PageInfo<>(dailyTrainCarriageList);
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());

		List<DailyTrainCarriageQueryResp> list = BeanUtil.copyToList(dailyTrainCarriageList, DailyTrainCarriageQueryResp.class);

		PageResp<DailyTrainCarriageQueryResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);
		return pageResp;
	}

	public void delete(Long id) {
		dailyTrainCarriageMapper.deleteByPrimaryKey(id);
	}

	public void genDaily(Date date, String trainCode) {
		LOG.info("生成日期【{}】车次【{}】的车厢信息开始", DateUtil.formatDate(date), trainCode);

		// 删除某日某车次的车厢信息
		DailyTrainCarriageExample dailyTrainCarriageExample = new DailyTrainCarriageExample();
		dailyTrainCarriageExample.createCriteria()
				.andDateEqualTo(date)
				.andTrainCodeEqualTo(trainCode);
		dailyTrainCarriageMapper.deleteByExample(dailyTrainCarriageExample);

		// 查出某车次的所有车厢信息
		List<TrainCarriage> carriageList = trainCarriageService.selectByTrainCode(trainCode);
		if (CollUtil.isEmpty(carriageList)) {
			LOG.info("该车次没有车厢基础数据，生成该车次的车厢信息结束");
		}

		for (TrainCarriage trainCarriage : carriageList) {
			DateTime now = DateTime.now();
			DailyTrainCarriage dailyTrainCarriage = BeanUtil.copyProperties(trainCarriage, DailyTrainCarriage.class);
			dailyTrainCarriage.setId(SnowUtil.getSnowflakeNextId());
			dailyTrainCarriage.setCreateTime(now);
			dailyTrainCarriage.setUpdateTime(now);
			dailyTrainCarriage.setDate(date);
			dailyTrainCarriageMapper.insert(dailyTrainCarriage);
		}
		LOG.info("生成日期【{}】车次【{}】的车厢信息结束", DateUtil.formatDate(date), trainCode);
	}

}