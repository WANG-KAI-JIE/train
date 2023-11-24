package top.kjwang.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.business.domain.Programmer;
import top.kjwang.train.business.domain.ProgrammerExample;
import top.kjwang.train.business.mapper.ProgrammerMapper;
import top.kjwang.train.business.req.ProgrammerQueryReq;
import top.kjwang.train.business.req.ProgrammerSaveReq;
import top.kjwang.train.business.resp.ProgrammerQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammerService {

private static final Logger LOG = LoggerFactory.getLogger(ProgrammerService.class);

@Resource
private ProgrammerMapper programmerMapper;

public void save(ProgrammerSaveReq req) {
DateTime now = DateTime.now();
Programmer programmer = BeanUtil.copyProperties(req, Programmer.class);
if (ObjectUtil.isNull(programmer.getId())) {
programmer.setId(SnowUtil.getSnowflakeNextId());
programmer.setCreateTime(now);
programmer.setUpdateTime(now);
programmerMapper.insert(programmer);
} else {
programmer.setUpdateTime(now);
programmerMapper.updateByPrimaryKey(programmer);
}
}

public PageResp<ProgrammerQueryResp> queryList(ProgrammerQueryReq req) {
    ProgrammerExample programmerExample = new ProgrammerExample();
    programmerExample.setOrderByClause("id desc");
    ProgrammerExample.Criteria criteria = programmerExample.createCriteria();

    LOG.info("查询页码：{}", req.getPage());
    LOG.info("每页条数：{}", req.getSize());
    PageHelper.startPage(req.getPage(), req.getSize());
    List<Programmer> programmerList = programmerMapper.selectByExample(programmerExample);

    PageInfo<Programmer> pageInfo = new PageInfo<>(programmerList);
    LOG.info("总行数：{}", pageInfo.getTotal());
    LOG.info("总页数：{}", pageInfo.getPages());

    List<ProgrammerQueryResp> list = BeanUtil.copyToList(programmerList, ProgrammerQueryResp.class);

        PageResp<ProgrammerQueryResp> pageResp = new PageResp<>();
            pageResp.setTotal(pageInfo.getTotal());
            pageResp.setList(list);
            return pageResp;
            }

            public void delete(Long id) {
            programmerMapper.deleteByPrimaryKey(id);
            }

            }