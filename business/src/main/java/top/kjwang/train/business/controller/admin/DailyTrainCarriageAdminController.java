package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.DailyTrainCarriageQueryReq;
import top.kjwang.train.business.req.DailyTrainCarriageSaveReq;
import top.kjwang.train.business.resp.DailyTrainCarriageQueryResp;
import top.kjwang.train.business.service.DailyTrainCarriageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-carriage")
public class DailyTrainCarriageAdminController {

@Resource
private DailyTrainCarriageService dailyTrainCarriageService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody DailyTrainCarriageSaveReq req) {
    dailyTrainCarriageService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainCarriageQueryResp>> queryList(@Valid DailyTrainCarriageQueryReq req) {
        PageResp<DailyTrainCarriageQueryResp> list = dailyTrainCarriageService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                dailyTrainCarriageService.delete(id);
                return new CommonResp<>();
                }

                }