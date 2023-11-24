package top.kjwang.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kjwang
 * @date 2023/11/24 10:19
 * @description BusinessFeign
 */

@FeignClient(name = "business", url = "http://127.0.0.1:8002/business")
public interface BusinessFeign {

    @GetMapping("/hello")
    String getHello();
}
