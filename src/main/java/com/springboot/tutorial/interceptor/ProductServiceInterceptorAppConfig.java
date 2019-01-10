package com.springboot.tutorial.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Component
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ProductServiceInterceptor productServiceInterceptor;

	@Autowired
	LocaleChangeInterceptor localeChangeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(productServiceInterceptor);
		registry.addInterceptor(localeChangeInterceptor);
	}
}
