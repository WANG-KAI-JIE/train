package top.kjwang.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.kjwang.train.common.resp.CommonResp;

import java.util.Date;

/**
 * @author kjwang
 * @date 2023/11/24 10:19
 * @description BusinessFeign
 */

@FeignClient(name = "business", url = "http://127.0.0.1:8002/business")
// @FeignClient(name = "business")
public interface BusinessFeign {

    @GetMapping("/hello")
    String getHello();

    @GetMapping("/admin/daily-train/gen-daily/{date}")
    CommonResp<Object> genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
