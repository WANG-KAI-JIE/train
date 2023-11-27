package top.kjwang.train.business.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.kjwang.train.common.interceptor.MemberInterceptor;

/**
 * @author kjwang
 * @date 2023/11/27 14:58
 * @description SpringMvcConfig
 */

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

	@Resource
	MemberInterceptor memberInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(memberInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/business/hello");
	}
}
