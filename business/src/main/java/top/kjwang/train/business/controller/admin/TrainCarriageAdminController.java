package top.kjwang.train.business.controller.admin;

import top.kjwang.train.common.context.LoginMemberContext;
import top.kjwang.train.common.resp.CommonResp;
import top.kjwang.train.common.resp.PageResp;
import top.kjwang.train.business.req.TrainCarriageQueryReq;
import top.kjwang.train.business.req.TrainCarriageSaveReq;
import top.kjwang.train.business.resp.TrainCarriageQueryResp;
import top.kjwang.train.business.service.TrainCarriageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-carriage")
public class TrainCarriageAdminController {

@Resource
private TrainCarriageService trainCarriageService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody TrainCarriageSaveReq req) {
    trainCarriageService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainCarriageQueryResp>> queryList(@Valid TrainCarriageQueryReq req) {
        PageResp<TrainCarriageQueryResp> list = trainCarriageService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                trainCarriageService.delete(id);
                return new CommonResp<>();
                }

                }