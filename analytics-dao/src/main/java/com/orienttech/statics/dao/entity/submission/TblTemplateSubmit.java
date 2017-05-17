/**
 * 数据报送表
 * @author dz
 */
package com.orienttech.statics.dao.entity.submission;

import static com.orienttech.statics.commons.utils.Contants.TJ_SCHEMA;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.orienttech.statics.commons.entity.BaseEntity;
import com.orienttech.statics.commons.utils.CommonHelper;

@Entity
@Table(name = "TBL_TEMPLATE_SUBMIT", schema = TJ_SCHEMA)
public class TblTemplateSubmit extends BaseEntity{
	private static final long serialVersionUID = -3943743020630752201L;
	
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@SequenceGenerator(name="SEQ_TEMPLATE_SUBMIT" , sequenceName="SEQ_TEMPLATE_SUBMIT",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TEMPLATE_SUBMIT")
	@Column(name="ID")
	private Integer id;						//报送编号
	@Column(name="TEMPLATE_ID")
	private Integer templateId;				//报送模版编号
	@Column(name="ORG_ID",length=100)
	private String orgId;					//填表机构
	@Column(name="SUBMIT_STATE",length=100)
	private String submitState;				//提交状态：未报送-0；已上报-已报送；
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;					//开始时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;					//结束时间
	@Column(name="PATH",length=100)
	private String path;					//文件名
	@Column(name="REAL_PATH",length=100)
	private String realPath;			    //服务器存放路径
	@Column(name="CYCLE",length=100)
	private String cycle;					//报表周期
	@Column(name="WORKFLOW_ID",length=100)
	private String workflowId;				//流程Id
	@Column(name="SUBMITORG_EXAMINE_PEOPLE",length=100)
	private String submitorgExaminePeople;	//报送部门审核人
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitorgExamineTime;		//报送部门审核人审核时间
	@Column(name="RELEASE_PEOPLE",length=100)
	private String releasePeople;			//发布人
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseTime;				//发布时间
	@Column(name="SUB_PEOPLE",length=100)
	private String subPeople;				//上报人
	@Temporal(TemporalType.TIMESTAMP)
	private Date subTime;					//上报时间
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSubTime;
	@Column(name="TOTAL_SUB_TIMES",length=100)
	private String totalSubTimes;
	@Column(name="LATE_DAYS",length=100)
	private String lateDays;
	
	
	public String getSubmitStateStr(){
		String submitStateStr = "";
		if("0".equals(submitState)){
			submitStateStr = "未报送";
		}else if("1".equals(submitState)){
			submitStateStr = "已报送";
		}else if("3".equals(this.submitState)){
			submitStateStr = "已退回";
		}
		return submitStateStr;
	}
	public TblTemplateSubmit(Object[] objs){
			super();
			int i=0;
			this.orgId=CommonHelper.toStr(objs[i++]);
			this.submitState=CommonHelper.toStr(objs[i++]);
			this.subTime=(Date)(objs[i++]);
			this.path=CommonHelper.toStr(objs[i++]);
	}
	public TblTemplateSubmit(){
		super();
	}
	public String getSubTimeStr() {
		return CommonHelper.date2Str(subTime, CommonHelper.DF_DATE);
	}
	public String getFirstSubTimeStr() {
		return CommonHelper.date2Str(firstSubTime, CommonHelper.DF_DATE);
	}
	
	public Date getFirstSubTime() {
		return firstSubTime;
	}
	public void setFirstSubTime(Date firstSubTime) {
		this.firstSubTime = firstSubTime;
	}
	public String getTotalSubTimes() {
		return totalSubTimes;
	}
	public void setTotalSubTimes(String totalSubTimes) {
		this.totalSubTimes = totalSubTimes;
	}
	public String getLateDays() {
		return lateDays;
	}
	public void setLateDays(String lateDays) {
		this.lateDays = lateDays;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getSubmitorgExaminePeople() {
		return submitorgExaminePeople;
	}
	public void setSubmitorgExaminePeople(String submitorgExaminePeople) {
		this.submitorgExaminePeople = submitorgExaminePeople;
	}
	public Date getSubmitorgExamineTime() {
		return submitorgExamineTime;
	}
	public void setSubmitorgExamineTime(Date submitorgExamineTime) {
		this.submitorgExamineTime = submitorgExamineTime;
	}
	public String getReleasePeople() {
		return releasePeople;
	}
	public void setReleasePeople(String releasePeople) {
		this.releasePeople = releasePeople;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Date getSubTime() {
		return subTime;
	}
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	public String getSubPeople() {
		return subPeople;
	}
	public void setSubPeople(String subPeople) {
		this.subPeople = subPeople;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	
}
