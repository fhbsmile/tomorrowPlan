/** 
 * Project Name:tomorrowPlan 
 * File Name:FileHandle.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.utils 
 * Date:2015年12月8日下午2:04:02
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsystems.si.aviation.imf.tomorrowPlan.bean.ExcelBean;


public class FileHandle {
	private static final Logger     logger               = LoggerFactory.getLogger(FileHandle.class);
	 public static List readExcelToList(String excelPath, int firstRowNum,int Maxcoulmn) throws InvalidFormatException, IOException {
			List<List<String>> rowsList = new ArrayList<List<String>>();
			File source = new File(excelPath);
			Workbook workbook = null;
			DataFormatter formatter = null;
			FormulaEvaluator evaluator = null;
			Sheet sheet = null;
			Row row = null;
			int lastRowNum = 0;
			FileInputStream fis = null;
			try {
				System.out.println("Opening workbook [" + source.getName() + "]");

				fis = new FileInputStream(source);

				// Open the workbook and then create the FormulaEvaluator and
				// DataFormatter instances that will be needed to, respectively,
				// force evaluation of forumlae found in cells and create a
				// formatted String encapsulating the cells contents.
				workbook = WorkbookFactory.create(fis);
				evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				formatter = new DataFormatter(true);
			} catch (InvalidFormatException e) {
				logger.error("Invalid Format!", e);
				throw new InvalidFormatException(e.getMessage());
			} catch (IOException e) {
				logger.error("IOException!", e);
				throw new InvalidFormatException(e.getMessage());
			} finally {
				if (fis != null) {
					fis.close();
				}
			}

			int numSheets = workbook.getNumberOfSheets();

			// and then iterate through them.
			//for (Integer i = 0; i < numSheets; i++) {
				for (Integer i = 0; i < 1; i++) {
				// Get a reference to a sheet and check to see if it contains
				// any rows.
				sheet = workbook.getSheetAt(i);
				if (sheet.getPhysicalNumberOfRows() > 0) {

					// Note down the index number of the bottom-most row and
					// then iterate through all of the rows on the sheet starting
					// from the very first row - number 1 - even if it is missing.
					// Recover a reference to the row and then call another method
					// which will strip the data from the cells and build lines
					// for inclusion in the resylting CSV file.
					lastRowNum = sheet.getLastRowNum();
					for (Integer j = 0; j <= lastRowNum; j++) {
						row = sheet.getRow(j);
						Cell cell = null;
						int lastCellNum = 0;
						ArrayList<String> rowList = new ArrayList<String>(Maxcoulmn);

						// Check to ensure that a row was recovered from the sheet as it is
						// possible that one or more rows between other populated rows could be
						// missing - blank. If the row does contain cells then...
							if (row != null) {
		                            logger.info("Parse Line: {}",j);
								// Get the index for the right most cell on the row and then
								// step along the row from left to right recovering the contents
								// of each cell, converting that into a formatted String and
								// then storing the String into the csvLine ArrayList.
								lastCellNum = row.getLastCellNum();
								for (int n = 0; n <= lastCellNum; n++) {
									cell = row.getCell(n);
									if (cell == null) {
										rowList.add("");
									} else {
										if (cell.getCellType() != Cell.CELL_TYPE_FORMULA) {
											rowList.add(formatter.formatCellValue(cell).trim());
										} else {
											rowList.add(formatter.formatCellValue(cell, evaluator).trim());
										}
										
										//rowList.add(cell.getStringCellValue());
									}
								}
								//logger.debug("Row List Size : "+ rowList.size());
								while(rowList.size()<Maxcoulmn)
								{
									rowList.add("");
								}
								rowsList.add(rowList);
							}
					  }
				}
			}
			
			for(int i =0;i<rowsList.size();i++)
			{
				ArrayList<String> rowList = (ArrayList<String>) rowsList.get(i);
				StringBuffer sb= new StringBuffer();
				for(int j=0;j<rowList.size();j++)
				{
					sb.append(rowList.get(j)).append(",");
				}
               logger.info("Line {} info:{}",new Object[]{i,sb.toString()});
			}
			
			
			
			return rowsList;
		}
	 
     public static List<ExcelBean> ParseListToExcelBean(List<List<String>> rowsList){
    	 List<ExcelBean> ebList = new ArrayList<ExcelBean>();
    	 
    	 for(int i =0;i<rowsList.size();i++)
			{   
    		    ExcelBean eb = new ExcelBean();
    		    eb.setLineNumber(i);
    		    boolean validateResult = true;
				ArrayList<String> rowList = (ArrayList<String>) rowsList.get(i);
				String airline = rowList.get(ConfigHandle.getAirlineIndex());						
				String tripnumber = rowList.get(ConfigHandle.getTripnumberIndex());						
				String registration = rowList.get(ConfigHandle.getRegistration());
				String aircrafttype = rowList.get(ConfigHandle.getAircrafttypeIndex());
				String servicetypecode = rowList.get(ConfigHandle.getServicetypecodeIndex());
				String takeoffstop = rowList.get(ConfigHandle.getTakeoffstopIndex());
				String var1 = rowList.get(ConfigHandle.getVar1Index());
				String var2 = rowList.get(ConfigHandle.getVar2Index());
				String landingstop = rowList.get(ConfigHandle.getLandingstopIndex());
				String takeoffdatetime = rowList.get(ConfigHandle.getTakeoffdatetimeIndex());
				String landingdatetime = rowList.get(ConfigHandle.getLandingdatetimeIndex());
				 
				if(StringUtils.isEmpty(airline)||StringUtils.isEmpty(tripnumber)||StringUtils.isEmpty(registration)||StringUtils.isEmpty(takeoffstop)||StringUtils.isEmpty(landingstop)||StringUtils.isEmpty(takeoffdatetime)||StringUtils.isEmpty(landingdatetime)){
					logger.info("Ignore line {}:airline,tripnumber,registration,takeoffstop,landingstop,takeoffdatetime or landingdatetime is null!",i);
					continue;
				}else{
					//validate all the attribute value.
					if(validateAirline(airline)){
						eb.setAirline(airline);
					}else{
						validateResult = false;
						logger.error("Ignore line {}: airline '{}' validate failed.",new Object[]{i,airline});
					}
					
					if(validateTripnumber(tripnumber)){
						eb.setTripNumber(tripnumber);
					}else{
						validateResult = false;
						logger.error("Ignore line {}: tripnumber '{}' validate failed.",new Object[]{i,tripnumber});
					}
					
					if(validateRegistration(registration)){
						eb.setRegistration("B"+registration);
					}else{
						eb.setRegistration(registration);
					}
					
					if(validateAircraftType(aircrafttype)){
						eb.setAircraftType(aircrafttype);
					}else{
						
					}
					if(validateServiceTypeCode(servicetypecode)){

							eb.setServiceTypeCode(servicetypecode);

					}else{
						//set default servicetypecode 0
						eb.setServiceTypeCode("0");
					}
					
					if(validateStopIATACode(takeoffstop)){
						eb.setTakeOffStop(takeoffstop);
					}else{
						validateResult = false;
						logger.error("Ignore line {}: landingstop '{}' validate failed.",new Object[]{i,takeoffstop});
					}
					if(var1!=null && validateStopIATACode(var1)){
							eb.setVar1(var1);
					}
					
					
					if(var1!=null && validateStopIATACode(var2)){
						eb.setVar1(var2);
				    }
					
					if(validateStopIATACode(landingstop)){
						eb.setLandingStop(landingstop);
					}else{
						validateResult = false;
						logger.error("Ignore line {}: landingstop '{}' validate failed.",new Object[]{i,landingstop});
					}
					
					if(validateDateTime(takeoffdatetime)){
						String s1= TimeHandle.formatTomorrowPlanDateTime(takeoffdatetime);
						//for test
						//s1= s1.replace("12-03", "12-17");
						//s1= s1.replace("12-04", "12-18");
						eb.setTakeOffDateTime(s1);
					}else{
						validateResult = false;
						logger.error("Ignore line {}: takeoffdatetime '{}' validate failed.",new Object[]{i,takeoffdatetime});
					}
					
					if(validateDateTime(landingdatetime)){
						String s2= TimeHandle.formatTomorrowPlanDateTime(landingdatetime);
						//s2= s2.replace("12-03", "12-17");
						//s2= s2.replace("12-04", "12-18");
						eb.setLandingDateTime(s2);
					}else{
						validateResult = false;
						logger.error("Ignore line {}: landingdatetime '{}' validate failed.",new Object[]{i,landingdatetime});
					}
				}
				
				
	               if(validateResult){
	            	   ebList.add(eb); 
	               }
			}

					
    	 
    	 return ebList;
     }
     
     public static String makeXMLMessage(HashMap<String,String> attrabuteForXMLMap,String xmlTemplate){
 		String xmlMessage = xmlTemplate;
     	for (Map.Entry<String, String> entry : attrabuteForXMLMap.entrySet()) {

 			   String attrbuteName = entry.getKey();
 			   String attrbuteValue = entry.getValue();
 	    		
 				  if(attrbuteValue!=null){
 					  if(attrbuteName.equals("SendDateTime") && attrbuteName.equals("MessageSequenceID") && attrbuteName.equals("OriginalDateTime") && attrbuteName.equals("OperationMode") && attrbuteName.equals("CreateDateTime")){
 						  logger.info("                    {}={}",new Object[]{attrbuteName,attrbuteValue});
 					  }
 					  
 					  xmlMessage = StringUtils.replaceOnce(xmlMessage, "@"+attrbuteName+"@", attrbuteValue);
 				  }else{
 					  //logger.warn("Take care! attribute {}  is null, please check!", attrbuteName);
 				  }
 			  }
 			//logger.debug("XML org: {}",xmlMessage);
 			if(xmlMessage!=null){
 				//按配置中的xsl过滤
 				xmlMessage = XMLHandle.XMLformat(xmlMessage);
 				//logger.debug("After filter:\n{}",xmlMessage);
 				//去掉空节点和无用节点
 				try {
 					xmlMessage = XMLHandle.rmNoUseAndEmptyNode(xmlMessage);
 					xmlMessage = XMLHandle.XMLformat(xmlMessage);
 					//logger.debug("After rm No used:\n{}",xmlMessage);
 				} catch (DocumentException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				xmlMessage = RemoveEmptyTransformer.xmlFilter(xmlMessage);
 				xmlMessage = XMLHandle.XMLformat(xmlMessage);
 				
 				
 				//插入数据库
 				logger.info("XML ready:\n{}",xmlMessage);
 			 }
 		return xmlMessage;
 	}
	 
	 public static boolean validateAirline(String airline){
		 String airlinePattern = "[A-Z0-9]{2}";
		 return airline.matches(airlinePattern);
	}
	 public static boolean validateTripnumber(String tripnumber){
		 String tripnumberPattern = "[A-Z0-9]{3,5}";
		 return tripnumber.matches(tripnumberPattern);
	}
	 
	 public static boolean validateAircraftType(String aircrafttype){
		 String aircrafttypePattern = "[A-Z0-9]{3,5}";
		 return aircrafttype.matches(aircrafttypePattern);
	}
	 public static boolean validateRegistration(String registration){
		 String registrationPattern = "[0-9]{3,5}";
		 return registration.matches(registrationPattern);
	}
	 public static boolean validateServiceTypeCode(String servicetypecode){
		 String servicetypecodePattern = "[0-9]{1}";
		 return servicetypecode.matches(servicetypecodePattern);
	}
	 
	 public static boolean validateStopIATACode(String stop){
		 String stopPattern = "[A-Z]{3}";
		 return stop.matches(stopPattern);
	}
	 //2015-12-03 20:45:00
	 public static boolean validateDateTime(String dateTime){
		 String dateTimePattern = "[0-9]{2,4}[/|-][0-9]{1,2}[/|-][0-9]{1,2}[\\s]+[0-9]{1,2}:[0-9]{1,2}[:]{0,1}[0-9]{0,2}";
		 return dateTime.matches(dateTimePattern);
	}
}
