package top.kjwang.train.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kjwang.train.common.resp.CommonResp;

/**
 * @author kjwang
 * @date 2023/11/20 16:58
 * @description ControllerExceptionHandler
 */

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	/**
	 * 所有异常统一处理
	 *
	 * @param e 异常
	 * @return CommonResp
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public CommonResp<?> execeptionHandler(Exception e) throws Exception {
		CommonResp<?> commonResp = new CommonResp<>();
		LOG.error("系统异常", e);
		commonResp.setSuccess(false);
		commonResp.setMessage(e.getMessage());
		return commonResp;
	}
}
