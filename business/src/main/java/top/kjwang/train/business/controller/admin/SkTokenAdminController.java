package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.SkTokenQueryReq;
import top.kjwang.train.business.req.SkTokenSaveReq;
import top.kjwang.train.business.resp.SkTokenQueryResp;
import top.kjwang.train.business.service.SkTokenService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/sk-token")
public class SkTokenAdminController {

@Resource
private SkTokenService skTokenService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody SkTokenSaveReq req) {
    skTokenService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<SkTokenQueryResp>> queryList(@Valid SkTokenQueryReq req) {
        PageResp<SkTokenQueryResp> list = skTokenService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                skTokenService.delete(id);
                return new CommonResp<>();
                }

                }