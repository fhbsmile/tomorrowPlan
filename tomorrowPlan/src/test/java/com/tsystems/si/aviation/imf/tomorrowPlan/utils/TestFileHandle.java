/** 
 * Project Name:tomorrowPlan 
 * File Name:TestFileHandle.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.utils 
 * Date:2015年12月9日下午2:39:43
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsystems.si.aviation.imf.tomorrowPlan.bean.ExcelBean;

public class TestFileHandle {
	private static final Logger     logger               = LoggerFactory.getLogger(TestFileHandle.class);
	private String excelPath="./file/海航_1203_海口次日计划.xls";
	
	@Test
	public void testReadExcelToList(){
		
		try {
			FileHandle.readExcelToList(excelPath, 3, 13);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseListToExcelBean(){
		List<List<String>> rowsList = new ArrayList<List<String>>();
		try {
			rowsList = FileHandle.readExcelToList(excelPath, 3, 13);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ExcelBean> ebList = FileHandle.ParseListToExcelBean(rowsList);
		
		for(int i=0;i<ebList.size();i++){
			logger.info(ebList.get(i).toString());
		}
		
	}
}
