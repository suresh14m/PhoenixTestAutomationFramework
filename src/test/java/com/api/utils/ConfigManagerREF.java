package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class ConfigManagerREF {
	//wap to read the property file src/test/resources/config folder/config.properties
	
	public static void main(String[] args) throws IOException   {
		
		
		Properties prop = new Properties();
		
		//load the prpperties using load()
		
		File configFile = new File(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");
		FileReader fileReader  = new FileReader(configFile);
		
		prop.load(fileReader);
		
		System.out.println(prop.getProperty("BASE_URI"));
		
		
	}
	

}
