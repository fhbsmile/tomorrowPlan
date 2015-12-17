

package com.tsystems.si.aviation.imf.tomorrowPlan.utils;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**   
 * @ClassName:  ConfigHandle   
 * @Description:TODO
 * @author: Bolo.Fang@t-systems.com  
 * @date:   2015年12月9日 上午11:41:49   
 *      
 */  
public class ConfigHandle {
	private static Configuration mappingConfig = null;
	private static String  xmlTemplate = "xmlTemplate.xml";
	private static String  xmlTemplateString ;
	/*private static Configuration zhConfig = null;
	private static Configuration caacConfig = null;
	private static List<String> airports = null;
	public static final String cnNKGString = "NKG;ZSNJ;南京;南京禄口;禄口";
	public static final String comSpilt = ",";
	public static final String codshareSpilt = ";";
        public static final String opsSpilt = "_";
	public static final int defaultSTAOffset = 0;
	public static final int defaultSTDOffset = 0;*/
	private static final Logger logger = LoggerFactory.getLogger(ConfigHandle.class);
	static {
		initConfig();
	}

	private static void initConfig() {
		try {
			mappingConfig = new PropertiesConfiguration(
					"mappingConfig.properties");
			logger.info("Load Mapping configuration file ");
		} catch (ConfigurationException e) {
			logger.error("loading Mapping configuration file failed.", e);
			logger.error("System exit!!");
			logger.error(e.getMessage(), e);
			System.exit(0);
		}
		
		URL urlTemplateXML = ConfigurationUtils.locate(xmlTemplate);
		try {
			
			xmlTemplateString = IOUtils.toString(urlTemplateXML, "utf-8");
			logger.debug("Loading XML Template [{}] Success!",xmlTemplate);
			logger.debug("\n{}",xmlTemplateString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Loading XML Template [{}]  Failed!!!,Please check the XML exist or not!",xmlTemplate);
			logger.error("System exit!!");
			logger.error(e.getMessage(), e);
			System.exit(0);
		}
	}
     public static String getXmlTemplate(){
    	 return xmlTemplateString;
     }

	public static int getAirlineIndex() {
		return mappingConfig.getInt("airline");
	}

	public static int getTripnumberIndex() {
		return mappingConfig.getInt("tripnumber");
	}
        public static int getRegistration() {
		return mappingConfig.getInt("registration");
	}
    	public static int getAircrafttypeIndex() {
    		return mappingConfig.getInt("aircrafttype");
    	}
	public static int getServicetypecodeIndex() {
		return mappingConfig.getInt("servicetypecode");
	}

	public static int getTakeoffstopIndex() {
		return mappingConfig.getInt("takeoffstop");
	}

	public static int getVar1Index() {
		return mappingConfig.getInt("var1");
	}

	public static int getVar2Index() {
		return mappingConfig.getInt("var2");
	}

	public static int getLandingstopIndex() {
		return mappingConfig.getInt("landingstop");
	}

	public static int getTakeoffdatetimeIndex() {
		return mappingConfig.getInt("takeoffdatetime");
	}

	public static int getLandingdatetimeIndex() {
		return mappingConfig.getInt("landingdatetime");
	}



}
