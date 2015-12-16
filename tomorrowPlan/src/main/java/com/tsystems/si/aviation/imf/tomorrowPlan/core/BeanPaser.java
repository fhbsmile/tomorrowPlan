/** 
 * Project Name:tomorrowPlan 
 * File Name:BeanPaser.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.core 
 * Date:2015年12月10日上午10:33:18
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.tsystems.si.aviation.imf.tomorrowPlan.bean.ExcelBean;
import com.tsystems.si.aviation.imf.tomorrowPlan.utils.SequenceGenerator;
import com.tsystems.si.aviation.imf.tomorrowPlan.utils.TimeHandle;
import com.tsystems.si.aviation.imf.tomorrowPlan.utils.XMLHandle;


public class BeanPaser {
	private static final Logger     logger               = LoggerFactory.getLogger(BeanPaser.class);
	private static SequenceGenerator sequenceGenerator = new SequenceGenerator();




public List<String> parseExcelBeanList(List<ExcelBean> ebList,String templateXML){
	List<String> xmlMessageList = new ArrayList<String>();
	for(int i=0;i<ebList.size();i++){
		ExcelBean eb = ebList.get(i);
		logger.info("Parse Line {}>>>>>>>>",eb.getLineNumber());
		String xmlMessage = createXmlMessage(eb,templateXML);
		xmlMessageList.add(xmlMessage);

	}
	
	return xmlMessageList;
}

public String createXmlMessage(ExcelBean eb,String templateXML){
	
	HashMap<String, String> attrabuteForXMLMap = new HashMap<String,String>();
	String nowDateString = TimeHandle.getDateTimeWithTString(new Date());
	String firstStop = eb.getTakeOffStop();
	String lastStop = eb.getLandingStop();
	String firstStopDateTime = eb.getTakeOffDateTime();
	String lastStopDateTime = eb.getLandingDateTime();
	String FlightDirection = null;
	String FlightScheduledDate =null;
	String FlightScheduledDateTime =null;
	
	if(firstStop.equals("HAK")){
		FlightDirection="D";
	}else{
		FlightDirection="A";
	}
	

	String FlightServiceType = "W/Z";
	if(eb.getServiceTypeCode().equals("0")){
		 FlightServiceType = "W/Z";
	}
	attrabuteForXMLMap.put("MessageSequenceID", sequenceGenerator.generateNextNumber());
	attrabuteForXMLMap.put("OperationMode","NEW");
	attrabuteForXMLMap.put("SendDateTime", nowDateString);
	attrabuteForXMLMap.put("CreateDateTime", nowDateString);
	attrabuteForXMLMap.put("OriginalDateTime", nowDateString);
	String FlightIdentity = eb.getAirline()+eb.getTripNumber();
	attrabuteForXMLMap.put("FlightIdentity", FlightIdentity);
	attrabuteForXMLMap.put("CreateReason", eb.getAirline());
	attrabuteForXMLMap.put("FlightDirection", FlightDirection);
	attrabuteForXMLMap.put("FlightCAACServiceType", FlightServiceType);
	attrabuteForXMLMap.put("Registration", eb.getRegistration());
	
	String ScheduledLandingDateTime =null;
	String ScheduledTakeOffDateTime =null;
	
	if(FlightDirection.equals("D")){
		FlightScheduledDate = firstStopDateTime.substring(0, 10);
		//2015-12-03 19:45:00
		FlightScheduledDateTime = firstStopDateTime.replace(" ", "T");
		ScheduledTakeOffDateTime = FlightScheduledDateTime;
	}else{
		FlightScheduledDate = lastStopDateTime.substring(0, 10);
		//2015-12-03 19:45:00
		FlightScheduledDateTime = lastStopDateTime.replace(" ", "T");
		ScheduledLandingDateTime = FlightScheduledDateTime;
	}
	attrabuteForXMLMap.put("FlightScheduledDate", FlightScheduledDate);
	attrabuteForXMLMap.put("FlightScheduledDateTime", FlightScheduledDateTime);
	
	String IATAOriginAirport =null;
	String IATAPreviousAirport =null;
	String AirportIATACode1 =null;
	String AirportIATACode2 =null;
	String AirportIATACode3 =null;
	String IATANextAirport =null;
	String IATADestinationAirport =null;
	String ScheduledPreviousAirportDepartureDateTime = null;
    if(FlightDirection.equals("D")){
    	IATADestinationAirport = lastStop;
    	if(eb.getVar1()==null && eb.getVar2()==null){
    		IATANextAirport = lastStop;
    		AirportIATACode1=lastStop;
    	}
    	if(eb.getVar1()!=null && eb.getVar2()==null){
    		IATANextAirport = eb.getVar1();
    		AirportIATACode1=eb.getVar1();
    		AirportIATACode2=lastStop;
    	}
    	if(eb.getVar1()!=null && eb.getVar2()!=null){
    		IATANextAirport = eb.getVar1();
    		AirportIATACode1=eb.getVar1();
    		AirportIATACode2=eb.getVar2();
    		AirportIATACode3=lastStop;
    	}
    }else{
    	IATAOriginAirport = firstStop;
    	ScheduledPreviousAirportDepartureDateTime = firstStopDateTime.replace(" ", "T");
    	if(eb.getVar1()==null && eb.getVar2()==null){
    		IATAPreviousAirport = firstStop;
    		AirportIATACode1=firstStop;
    	}
    	if(eb.getVar1()!=null && eb.getVar2()==null){
    		IATAPreviousAirport = eb.getVar1();
    		AirportIATACode1=firstStop;
    		AirportIATACode2=eb.getVar1();
    	}
    	if(eb.getVar1()!=null && eb.getVar2()!=null){
    		IATAPreviousAirport = eb.getVar2();
    		AirportIATACode1=firstStop;
    		AirportIATACode2=eb.getVar1();
    		AirportIATACode3=eb.getVar2();
    	}
    }
	attrabuteForXMLMap.put("IATAOriginAirport",IATAOriginAirport);
	attrabuteForXMLMap.put("IATAPreviousAirport", IATAPreviousAirport);
	attrabuteForXMLMap.put("AirportIATACode1", AirportIATACode1);
	attrabuteForXMLMap.put("AirportIATACode2", AirportIATACode2);
	attrabuteForXMLMap.put("AirportIATACode3", AirportIATACode3);
	attrabuteForXMLMap.put("IATANextAirport", IATANextAirport);
	attrabuteForXMLMap.put("IATADestinationAirport", IATADestinationAirport);
    

		
	attrabuteForXMLMap.put("ScheduledPreviousAirportDepartureDateTime", ScheduledPreviousAirportDepartureDateTime);
	
	attrabuteForXMLMap.put("ScheduledLandingDateTime", ScheduledLandingDateTime);
	
	attrabuteForXMLMap.put("ScheduledTakeOffDateTime", ScheduledTakeOffDateTime);

	String xmlMessage = XMLHandle.makeXMLMessage(attrabuteForXMLMap, templateXML);
	logger.debug("\n"+xmlMessage);
	return xmlMessage;
}
}
