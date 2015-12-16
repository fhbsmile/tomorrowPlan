/** 
 * Project Name:tomorrowPlan 
 * File Name:ExcelBean.java 
 * Package Name:com.tsystems.si.aviation.imf.tomorrowPlan.bean 
 * Date:2015年12月9日上午10:58:51
 * version:v1.0
 * Copyright (c) 2015, Bolo.Fang@t-systems.com All Rights Reserved. 
 * 
 */ 


package com.tsystems.si.aviation.imf.tomorrowPlan.bean;



public class ExcelBean {
private int lineNumber;
private String airline;
private String tripNumber;
private String registration;
private String aircraftType;
private String serviceTypeCode;
private String takeOffStop;
private String var1;
private String var2;
private String landingStop;
private String takeOffDateTime;
private String landingDateTime;
public int getLineNumber() {
	return lineNumber;
}
public void setLineNumber(int lineNumber) {
	this.lineNumber = lineNumber;
}
public String getAirline() {
	return airline;
}
public void setAirline(String airline) {
	this.airline = airline;
}
public String getTripNumber() {
	return tripNumber;
}
public void setTripNumber(String tripNumber) {
	this.tripNumber = tripNumber;
}
public String getRegistration() {
	return registration;
}
public void setRegistration(String registration) {
	this.registration = registration;
}



public String getAircraftType() {
	return aircraftType;
}
public void setAircraftType(String aircraftType) {
	this.aircraftType = aircraftType;
}
public String getServiceTypeCode() {
	return serviceTypeCode;
}
public void setServiceTypeCode(String serviceTypeCode) {
	this.serviceTypeCode = serviceTypeCode;
}
public String getTakeOffStop() {
	return takeOffStop;
}
public void setTakeOffStop(String takeOffStop) {
	this.takeOffStop = takeOffStop;
}
public String getVar1() {
	return var1;
}
public void setVar1(String var1) {
	this.var1 = var1;
}
public String getVar2() {
	return var2;
}
public void setVar2(String var2) {
	this.var2 = var2;
}
public String getLandingStop() {
	return landingStop;
}
public void setLandingStop(String landingStop) {
	this.landingStop = landingStop;
}
public String getTakeOffDateTime() {
	return takeOffDateTime;
}
public void setTakeOffDateTime(String takeOffDateTime) {
	this.takeOffDateTime = takeOffDateTime;
}
public String getLandingDateTime() {
	return landingDateTime;
}
public void setLandingDateTime(String landingDateTime) {
	this.landingDateTime = landingDateTime;
}
@Override
public String toString() {
	return String
			.format("ExcelBean [lineNumber=%s, airline=%s, tripNumber=%s, registration=%s, aircraftType=%s, serviceTypeCode=%s, takeOffStop=%s, var1=%s, var2=%s, landingStop=%s, takeOffDateTime=%s, landingDateTime=%s]",
					lineNumber, airline, tripNumber, registration,
					aircraftType, serviceTypeCode, takeOffStop, var1, var2,
					landingStop, takeOffDateTime, landingDateTime);
}


}
