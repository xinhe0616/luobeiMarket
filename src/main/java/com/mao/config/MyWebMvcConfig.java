package com.mao.config;

import com.mao.interpret.AdminLoginInterpret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    AdminLoginInterpret adminLoginInterpret;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(adminLoginInterpret);
//                .addPathPatterns("/admin/**");
    }
}
