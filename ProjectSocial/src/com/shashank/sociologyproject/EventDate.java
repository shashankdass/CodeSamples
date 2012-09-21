package com.shashank.sociologyproject;
//package com.Feilds;

import java.util.Date;

public class EventDate {

	private String startDayOfTheWeek;
	private Date startDate;
	private String endDayOfTheWeek;
	private Date endDate;
	
	public String getStartDayOfTheWeek() {
		return startDayOfTheWeek;
	}
	public void setStartDayOfTheWeek(String startDayOfTheWeek) {
		this.startDayOfTheWeek = startDayOfTheWeek;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDayOfTheWeek() {
		return endDayOfTheWeek;
	}
	public void setEndDayOfTheWeek(String endDayOfTheWeek) {
		this.endDayOfTheWeek = endDayOfTheWeek;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
