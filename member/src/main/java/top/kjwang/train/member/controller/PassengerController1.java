// package top.kjwang.train.member.controller;
//
// import jakarta.annotation.Resource;
// import jakarta.validation.Valid;
// import org.springframework.web.bind.annotation.*;
// import top.kjwang.train.common.context.LoginMemberContext;
// import top.kjwang.train.common.resp.CommonResp;
// import top.kjwang.train.common.resp.PageResp;
// import top.kjwang.train.member.req.PassengerQueryReq;
// import top.kjwang.train.member.req.PassengerSaveReq;
// import top.kjwang.train.member.resp.PassengerQueryResp;
// import top.kjwang.train.member.service.PassengerService;
//
// /**
//  * @author kjwang
//  * @date 2023/11/21 14:34
//  * @description passengerController
//  */
//
// @RestController
// @RequestMapping("/passenger")
// public class PassengerController1 {
//
// 	@Resource
// 	private PassengerService passengerService;
//
// 	@PostMapping("/save")
// 	public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
// 		passengerService.save(req);
// 		return new CommonResp<>();
// 	}
//
// 	@GetMapping("/query-list")
// 	public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
// 		req.setMemberId(LoginMemberContext.getId());
// 		PageResp<PassengerQueryResp> list = passengerService.queryList(req);
// 		return new CommonResp<>(list);
// 	}
//
// 	@DeleteMapping("/delete/{id}")
// 	public CommonResp<Object> delete(@PathVariable Long id) {
// 		passengerService.delete(id);
// 		return new CommonResp<>();
// 	}
// }
