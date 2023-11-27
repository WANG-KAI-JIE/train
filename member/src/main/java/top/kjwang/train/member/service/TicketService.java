package top.kjwang.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.member.domain.Ticket;
import top.kjwang.train.member.domain.TicketExample;
import top.kjwang.train.member.mapper.TicketMapper;
import top.kjwang.train.member.req.TicketQueryReq;
import top.kjwang.train.member.req.TicketSaveReq;
import top.kjwang.train.member.resp.TicketQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

private static final Logger LOG = LoggerFactory.getLogger(TicketService.class);

@Resource
private TicketMapper ticketMapper;

public void save(TicketSaveReq req) {
DateTime now = DateTime.now();
Ticket ticket = BeanUtil.copyProperties(req, Ticket.class);
if (ObjectUtil.isNull(ticket.getId())) {
ticket.setId(SnowUtil.getSnowflakeNextId());
ticket.setCreateTime(now);
ticket.setUpdateTime(now);
ticketMapper.insert(ticket);
} else {
ticket.setUpdateTime(now);
ticketMapper.updateByPrimaryKey(ticket);
}
}

public PageResp<TicketQueryResp> queryList(TicketQueryReq req) {
    TicketExample ticketExample = new TicketExample();
    ticketExample.setOrderByClause("id desc");
    TicketExample.Criteria criteria = ticketExample.createCriteria();

    LOG.info("查询页码：{}", req.getPage());
    LOG.info("每页条数：{}", req.getSize());
    PageHelper.startPage(req.getPage(), req.getSize());
    List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);

    PageInfo<Ticket> pageInfo = new PageInfo<>(ticketList);
    LOG.info("总行数：{}", pageInfo.getTotal());
    LOG.info("总页数：{}", pageInfo.getPages());

    List<TicketQueryResp> list = BeanUtil.copyToList(ticketList, TicketQueryResp.class);

        PageResp<TicketQueryResp> pageResp = new PageResp<>();
            pageResp.setTotal(pageInfo.getTotal());
            pageResp.setList(list);
            return pageResp;
            }

            public void delete(Long id) {
            ticketMapper.deleteByPrimaryKey(id);
            }

            }