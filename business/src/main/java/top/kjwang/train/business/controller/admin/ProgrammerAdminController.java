package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.ProgrammerQueryReq;
import top.kjwang.train.business.req.ProgrammerSaveReq;
import top.kjwang.train.business.resp.ProgrammerQueryResp;
import top.kjwang.train.business.service.ProgrammerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/programmer")
public class ProgrammerAdminController {

@Resource
private ProgrammerService programmerService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody ProgrammerSaveReq req) {
    programmerService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<ProgrammerQueryResp>> queryList(@Valid ProgrammerQueryReq req) {
        PageResp<ProgrammerQueryResp> list = programmerService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                programmerService.delete(id);
                return new CommonResp<>();
                }

                }