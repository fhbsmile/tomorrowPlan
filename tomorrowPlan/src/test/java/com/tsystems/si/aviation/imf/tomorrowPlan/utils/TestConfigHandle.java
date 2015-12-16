/** 
 * Project Name:tomorrowPlan 
 * File Name:TestConfigHandle.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.utils 
 * Date:2015年12月9日下午2:27:31
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConfigHandle {
	private static final Logger     logger               = LoggerFactory.getLogger(TestConfigHandle.class);
	@Test
	public void testGet(){
		logger.info("Get Registration index: {}",ConfigHandle.getRegistration());
	}
}
