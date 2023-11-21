package top.kjwang.train.${module}.controller;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.${module}.req.${Domain}QueryReq;
import top.kjwang.train.${module}.req.${Domain}SaveReq;
import top.kjwang.train.${module}.resp.${Domain}QueryResp;
import top.kjwang.train.${module}.service.${Domain}Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${do_main}")
public class ${Domain}Controller {

    @Resource
    private ${Domain}Service ${domain}Service;

	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody ${Domain}SaveReq req) {
		${domain}Service.save(req);
		return new CommonResp<>();
	}

    @GetMapping("/query-list")
    public CommonResp<PageResp<${Domain}QueryResp>> queryList(@Valid ${Domain}QueryReq req) {
		req.setMemberId(LoginMemberContext.getId());
		PageResp<${Domain}QueryResp> list = ${domain}Service.queryList(req);
		return new CommonResp<>(list);
	}

	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable Long id) {
		${domain}Service.delete(id);
		return new CommonResp<>();
	}
}