package com.orienttech.statics.service.model.submission;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orienttech.statics.commons.utils.CommonHelper;

public class TblReportSubmitVO  implements Serializable{
	private int id;
	private String orgId;
	private String submitState;
	private String subTime;
	private String path;
	private String submitorgExamineTime;
	
	private String orgName;
	
	public TblReportSubmitVO(Object[] objs) {
		super();
		int i=0;
		this.id=CommonHelper.toInt(objs[i++]);
		this.orgId=CommonHelper.toStr(objs[i++]);
		this.orgName=CommonHelper.toStr(objs[i++]);
		this.submitState=CommonHelper.toStr(objs[i++]);
		this.submitorgExamineTime=CommonHelper.toStr(objs[i++]);
		this.path=CommonHelper.toStr(objs[i++]);
	}
	
	

	public String getSubmitorgExamineTime() {
		return submitorgExamineTime;
	}
	public void setSubmitorgExamineTime(String submitorgExamineTime) {
		this.submitorgExamineTime = submitorgExamineTime;
	}
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSubmitState() {
		return submitState;
	}

	public void setSubmitState(String submitState) {
		this.submitState = submitState;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
