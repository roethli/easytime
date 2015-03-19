package ch.roethli.timeclock.java;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

public class Time {

	private Date currenttime;
	private Date currentdate;
	private SimpleDateFormat timeformat;
	private SimpleDateFormat dateformat;


public Time()
{
	this.currentdate = new Date();
	this.currenttime = new Date();
	

}


public String getCurrenttime() {
	this.timeformat = new SimpleDateFormat("hh:mm");
	return this.timeformat.format(currenttime);
}


public void setCurrenttime(Date currenttime) {
	this.currenttime = currenttime;
}


public String getCurrentdate() {
	this.dateformat = new SimpleDateFormat("dd.MM.yyyy");
	return this.dateformat.format(currentdate);
}



}