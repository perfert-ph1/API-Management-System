package com.apiSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
//
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/register").setViewName("site/register");
//            }
//        };
//        return webMvcConfigurer;
//    }
}
