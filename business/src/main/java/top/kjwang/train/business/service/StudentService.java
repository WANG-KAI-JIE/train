package top.kjwang.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.common.util.SnowUtil;
import top.kjwang.train.business.domain.Student;
import top.kjwang.train.business.domain.StudentExample;
import top.kjwang.train.business.mapper.StudentMapper;
import top.kjwang.train.business.req.StudentQueryReq;
import top.kjwang.train.business.req.StudentSaveReq;
import top.kjwang.train.business.resp.StudentQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

	private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

	@Resource
	private StudentMapper studentMapper;

	public void save(StudentSaveReq req) {
		DateTime now = DateTime.now();
		Student student = BeanUtil.copyProperties(req, Student.class);
		if (ObjectUtil.isNull(student.getId())) {
			student.setId(SnowUtil.getSnowflakeNextId());
// student.setCreateTime(now);
// student.setUpdateTime(now);
// studentMapper.insert(student);
		} else {
// student.setUpdateTime(now);
			studentMapper.updateByPrimaryKey(student);
		}
	}

	public PageResp<StudentQueryResp> queryList(StudentQueryReq req) {
		StudentExample studentExample = new StudentExample();
		studentExample.setOrderByClause("id desc");
		StudentExample.Criteria criteria = studentExample.createCriteria();

		LOG.info("查询页码：{}", req.getPage());
		LOG.info("每页条数：{}", req.getSize());
		PageHelper.startPage(req.getPage(), req.getSize());
		List<Student> studentList = studentMapper.selectByExample(studentExample);

		PageInfo<Student> pageInfo = new PageInfo<>(studentList);
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());

		List<StudentQueryResp> list = BeanUtil.copyToList(studentList, StudentQueryResp.class);

		PageResp<StudentQueryResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);
		return pageResp;
	}

	public void delete(Long id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	public void queryBySchool(String school) {
		studentMapper.queryBySchool(school);
	}

}