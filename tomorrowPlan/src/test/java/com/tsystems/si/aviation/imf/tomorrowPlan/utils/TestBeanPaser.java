/** 
 * Project Name:tomorrowPlan 
 * File Name:TestBeanPaser.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.utils 
 * Date:2015年12月10日下午9:28:27
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsystems.si.aviation.imf.tomorrowPlan.bean.ExcelBean;
import com.tsystems.si.aviation.imf.tomorrowPlan.core.BeanPaser;

public class TestBeanPaser {
	private static final Logger     logger               = LoggerFactory.getLogger(TestBeanPaser.class);
	
	private String excelPath;
	private List<ExcelBean> ebList;
	
	@Before
	public void setUp(){
		//add comment test
		excelPath="./file/首航航班计划通知单2015-12-3xls.xls";
		//excelPath="./file/南航预报通知单2015-12-03.xls";
		
		List<List<String>> rowsList = new ArrayList<List<String>>();
		//git add comments
		try {
			rowsList = FileHandle.readExcelToList(excelPath, 3, 14);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ebList = FileHandle.ParseListToExcelBean(rowsList);
	}
	
	@Test
	public void testParseExcelBeanList(){
		BeanPaser bp = new BeanPaser();
		bp.parseExcelBeanList(ebList, ConfigHandle.getXmlTemplate());
	}
}
