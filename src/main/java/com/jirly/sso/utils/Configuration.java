package com.jirly.sso.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static Configuration instance = new Configuration();
	private static Properties prop = null;
	static {
		try {
			InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("sysconfig.properties");
			prop = new Properties();
			prop.load(inputStream);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Configuration getInstance(){
		return instance;
	}

	public static String getProperty(String key, String defaultValue){
		if(defaultValue == null) defaultValue = "";
		
		if(prop==null || key == null) return defaultValue;
		
		String value = prop.getProperty(key);
		if(value == null) return defaultValue;
		
		return value;
	}
	public static String getProperty(String key){
		return getProperty(key,"");
	}
}
