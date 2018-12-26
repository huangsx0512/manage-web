package com.zimmur.platform.manage.web.common;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesCommon {
	private static Properties props;
	
	static{
		Resource resource = new ClassPathResource("/config/sysconfig.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String name){
		return props==null?"":props.getProperty(name);
	}
}
