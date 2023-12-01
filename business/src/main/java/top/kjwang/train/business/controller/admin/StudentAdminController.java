package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.StudentQueryReq;
import top.kjwang.train.business.req.StudentSaveReq;
import top.kjwang.train.business.resp.StudentQueryResp;
import top.kjwang.train.business.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/student")
public class StudentAdminController {

	@Resource
	private StudentService studentService;

	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody StudentSaveReq req) {
		studentService.save(req);
		return new CommonResp<>();
	}

	@GetMapping("/query-list")
	public CommonResp<PageResp<StudentQueryResp>> queryList(@Valid StudentQueryReq req) {
		PageResp<StudentQueryResp> list = studentService.queryList(req);
		return new CommonResp<>(list);
	}

	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable Long id) {
		studentService.delete(id);
		return new CommonResp<>();
	}

	@GetMapping("/queryBySchool/{school}")
	public CommonResp<PageResp<StudentQueryResp>> queryBySchool(@PathVariable String school) {
		studentService.queryBySchool(school);
		return new CommonResp<>();
	}

}