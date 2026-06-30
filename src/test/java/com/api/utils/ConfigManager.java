package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	// wap to read the property file src/test/resources/config
	// folder/config.properties

	private static Properties prop = new Properties();
	private static String path;
	private static String env;
	
	private ConfigManager() {
		// private constructor!!!
		
		
	}

	static {
		env = System.getProperty("env","qat").trim().toLowerCase();
		
		System.out.println("Selected Environment : "+ env);
		switch (env) {
		
		
		case "dev" -> path = "config/config.dev.properties";
			
		case "qat" -> path = "config/config.qat.properties";
	
		
		case "uat" -> path = "config/config.uat.properties";
	
		default -> {
			System.out.println("Invalid environment. Loading QAT by default.");
			path = "config/config.qat.properties";
			
		}
		
		}
		
		
		
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if(input == null) {
			
			throw new RuntimeException("Cannot find the file at the path: "+ path);
			
		}
		
		
		
		try {
			
	
			prop.load(input);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}

}
