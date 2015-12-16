/** 
 * Project Name:tomorrowPlan 
 * File Name:TestTimeHandle.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.utils 
 * Date:2015年12月14日上午11:59:17
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTimeHandle {
	 private static final Logger     logger               = LoggerFactory.getLogger(TestTimeHandle.class);
	
	@Test
	public void testFormatTomorrowPlanDateTime(){
		String org = "2015/12-3  06:40:00";
		String now = TimeHandle.formatTomorrowPlanDateTime(org);
		
		logger.info("org:{},now:{}",new Object[]{org,now});
	}
}
