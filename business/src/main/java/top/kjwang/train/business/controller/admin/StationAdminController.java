package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.StationQueryReq;
import top.kjwang.train.business.req.StationSaveReq;
import top.kjwang.train.business.resp.StationQueryResp;
import top.kjwang.train.business.service.StationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/station")
public class StationAdminController {

@Resource
private StationService stationService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody StationSaveReq req) {
    stationService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> list = stationService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                stationService.delete(id);
                return new CommonResp<>();
                }

                }