package com.brianthetall.frameworks.mvc;

import java.lang.String;
import java.lang.Class;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletException;

//public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
public class AnnotationContainerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
	protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
	protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
	protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}