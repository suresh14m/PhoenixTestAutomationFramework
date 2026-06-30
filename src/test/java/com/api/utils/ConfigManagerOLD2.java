package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD2 {
	// wap to read the property file src/test/resources/config
	// folder/config.properties

	private static Properties prop = new Properties();
	
	private ConfigManagerOLD2() {
		// private constructor!!!
		
		
	}

	static {
		//operation of loading property file in the memory
		// because it is static block it will execute once during _ Class Loading Time!	
		
		File configFile = new File(System.getProperty("user.dir") +File.separator+ "src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader(configFile);
			// load the prpperties using load()
			prop.load(fileReader);

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
