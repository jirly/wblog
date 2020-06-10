package com.jirly.sso.utils;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static final Logger logger = Logger.getLogger(Configuration.class);
	private static Configuration instance = new Configuration();
	private static Properties prop = null;
	static {
		try {
			InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("sysconfig.properties");
			prop = new Properties();
			prop.load(inputStream);
		} catch (Exception e) {
			logger.error(e);
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
