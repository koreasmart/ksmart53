package ksmybatis.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ksmybatis.admin.common.interceptor.CommonInterceptor;
import ksmybatis.admin.login.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{

	private final CommonInterceptor commonInterceptor; 
	private final LoginInterceptor loginInterceptor; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/admin/**");
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/manage/**")
				.excludePathPatterns("/admin")
				.excludePathPatterns("/admin/login")
				.excludePathPatterns("/admin/login/loginPro")
				.excludePathPatterns("/admin/logout");
				
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}














