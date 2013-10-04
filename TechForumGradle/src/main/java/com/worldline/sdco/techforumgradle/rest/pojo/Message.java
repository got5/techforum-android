package com.worldline.sdco.techforumgradle.rest.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	private String _id;
	private String name;
	private String msg;
	private Date date;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(String date) {
		try {
			this.date = SDF.parse(date);
		} catch (ParseException e) {
			this.date = new Date();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
