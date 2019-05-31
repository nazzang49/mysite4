package com.cafe24.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.WebConfig;

//web.xml의 역할 대체
public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//AppConfig
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	//WebConfig
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	//DispatcherServlet 매핑
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	//init 과정에서 진행되는 필터
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("utf-8",true)};
	}

	//예외처리
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		DispatcherServlet dispatcherServlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
		//핸들러가 찾을 수 없는 url 경로이면 예외 발생
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
	}
	
}
