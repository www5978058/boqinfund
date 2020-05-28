package com.shyb.boqinfund.config;

import com.shyb.boqinfund.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wzh
 * @date 2019/6/22 - 17:46
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("boqinfund/index");
                registry.addViewController("/about").setViewName("boqinfund/about");
                registry.addViewController("/service").setViewName("boqinfund/service");
                registry.addViewController("/team").setViewName("boqinfund/team");
                registry.addViewController("/product").setViewName("boqinfund/product");
                registry.addViewController("/contact").setViewName("boqinfund/contact");
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginInterceptor).addPathPatterns("/fund/**").excludePathPatterns("/assets/**","/webjars/**","/fund/showFundPrice");
            }
        };
    }
}
