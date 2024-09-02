package com.example.postmantestapi.config;

import com.example.postmantestapi.filter.EncryptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> encryptionFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new EncryptionFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 필터 적용
        registrationBean.setOrder(1); // 필터 순서 설정 (낮을수록 우선 적용)

        return registrationBean;
    }
}
