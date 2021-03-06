package com.orienttech.statics.service.report.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognos.developer.schemas.bibus._3.ReportService_PortType;
import com.orienttech.statics.commons.dynamicquery.DynamicQuery;
import com.orienttech.statics.commons.utils.CommonHelper;
import com.orienttech.statics.dao.ReportChartCoordinateDao;
import com.orienttech.statics.dao.entity.ReportChartCoordinate;
import com.orienttech.statics.dao.entity.financialreport.ReportMonthOrg;
import com.orienttech.statics.service.cognos.ReportRunnerService;
import com.orienttech.statics.service.cognos.entity.ReportParam;
import com.orienttech.statics.service.model.report.MonthlyLoanBusiNotificationBo;
import com.orienttech.statics.service.report.MonthlyLoanBusiNotificationService;
import com.orienttech.statics.service.usermng.OrgDeptService;

@Service
public class MonthlyLoanBusiNotificationServiceImpl implements MonthlyLoanBusiNotificationService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private OrgDeptService orgDeptService;
	@Autowired
	private ReportRunnerService reportRunnerService;
	@Autowired
	private ReportChartCoordinateDao reportChartCoordinateDao;
	
	/**
	 * 查询本期数据
	 * @param bo
	 * @param condition
	 * @return
	 */
	@Override
	public MonthlyLoanBusiNotificationBo searchCurMonthDatas(String busiDate, String orgId){
		
		StringBuffer sb = new StringBuffer("select t.report_id,t.busi_month,t.rank_num,t.org_id,t.org_name,"
				+ "t.apply_user_id,t.apply_user_name,t.amt1,t.amt2,t.amt3,t.amt4,t.amt5,t.amt6,t.memo"
				+ " from REPORT_MONTH_ORG t where t.busi_month=?1 and t.org_id=?2"
				+ " order by t.rank_num");
		MonthlyLoanBusiNotificationBo bo = new MonthlyLoanBusiNotificationBo();
		
		List<Object[]> objsList = dynamicQuery.nativeQuery(Object[].class, sb.toString(), busiDate, orgId);
		
		//统计日期
		bo.setBusiDate(busiDate);
		//机构Id
		bo.setOrgId(orgId);
		//要生成word的名称
		String time = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		bo.setOutFileName(time);
		//当前系统日期
		String curYear = time.substring(0, 4);
		String curMonth = time.substring(4, 5).equals("0")?time.substring(5, 6):time.substring(4, 6);
		String curDay = time.substring(6, 7).equals("0")?time.substring(7, 8):time.substring(6, 8);
		bo.setCurDate(curYear+"年"+curMonth+"月"+curDay+"日");
		
		if(objsList.size()==0 || objsList==null){
			return null;
		}
		
		//所选机构名称
		String orgName = CommonHelper.toStr(objsList.get(0)[4]);
		bo.setOrgName(orgName);
		
//		List<BigDecimal> tbl4list = new ArrayList<BigDecimal>();
		//TODO
		for(Object[] objs: objsList){
			String reportId = CommonHelper.toStr(objs[0]);
			String busiMonth = CommonHelper.toStr(objs[1]);
			Integer rankNum = CommonHelper.toInt(objs[2]);
			String org_Id = CommonHelper.toStr(objs[3]);
			String org_Name =  CommonHelper.toStr(objs[4]);
			String applyUserId =  CommonHelper.toStr(objs[5]);
			String applyUserName =  CommonHelper.toStr(objs[6]);
			BigDecimal amt1 =  CommonHelper.toBigDecimal(objs[7]);
			BigDecimal amt2 =  CommonHelper.toBigDecimal(objs[8]);
			BigDecimal amt3 =  CommonHelper.toBigDecimal(objs[9]);
			BigDecimal amt4 =  CommonHelper.toBigDecimal(objs[10]);
			BigDecimal amt5 =  CommonHelper.toBigDecimal(objs[11]);
			BigDecimal amt6 =  CommonHelper.toBigDecimal(objs[12]);
			String memo = CommonHelper.toStr(objs[13]);
			//第一段
			if( reportId.equalsIgnoreCase("A001")){
				bo.setBusiNumber1(amt1);
			}
			if( reportId.equalsIgnoreCase("A001")){
				bo.setBusiNumber2(amt5);
			}
			if( reportId.equalsIgnoreCase("A004")){
				bo.setBusiNumber3(amt1);
			}
			if( reportId.equalsIgnoreCase("A005")){
				bo.setBusiNumber4(amt1);
			}
			if( reportId.equalsIgnoreCase("A001")){
				bo.setBusiNumber5(amt2);
			}
			if( reportId.equalsIgnoreCase("A001")){
				bo.setBusiNumber6(amt6);
			}
			if( reportId.equalsIgnoreCase("A004")){
				bo.setBusiNumber7(amt2);
			}
			if( reportId.equalsIgnoreCase("A005")){
				bo.setBusiNumber8(amt2);
			}
			if( reportId.equalsIgnoreCase("A001")){
				bo.setBusiNumber9(amt3);
			}
			if( reportId.equalsIgnoreCase("A004")){
				bo.setBusiNumber10(amt3);
			}
			if( reportId.equalsIgnoreCase("A005")){
				bo.setBusiNumber11(amt3);
			}
			if( reportId.equalsIgnoreCase("A006")){
				bo.setBusiNumber12(amt3);
			}
			//第二段
			if( reportId.equalsIgnoreCase("B001")){
				bo.setBusiNumber13(amt1);
			}
			if( reportId.equalsIgnoreCase("B008")){
				bo.setBusiNumber14(amt1);
			}
			if( reportId.equalsIgnoreCase("B005")){
				bo.setBusiNumber15(amt1);
			}
			if( reportId.equalsIgnoreCase("B006")){
				bo.setBusiNumber16(amt1);
			}
			if( reportId.equalsIgnoreCase("B007")){
				bo.setBusiNumber17(amt1);
			}
			if( reportId.equalsIgnoreCase("B001")){
				bo.setBusiNumber18(amt2);
			}
			if( reportId.equalsIgnoreCase("B008")){
				bo.setBusiNumber19(amt2);
			}
			if( reportId.equalsIgnoreCase("B005")){
				bo.setBusiNumber20(amt2);
			}
			if( reportId.equalsIgnoreCase("B006")){
				bo.setBusiNumber21(amt2);
			}
			if( reportId.equalsIgnoreCase("B007")){
				bo.setBusiNumber22(amt2);
			}
			if( reportId.equalsIgnoreCase("B001")){
				bo.setBusiNumber23(amt3);
			}
			if( reportId.equalsIgnoreCase("B008")){
				bo.setBusiNumber24(amt3);
			}
			if( reportId.equalsIgnoreCase("B005")){
				bo.setBusiNumber25(amt3);
			}
			if( reportId.equalsIgnoreCase("B006")){
				bo.setBusiNumber26(amt3);
			}
			if( reportId.equalsIgnoreCase("B007")){
				bo.setBusiNumber27(amt3);
			}
			if( reportId.equalsIgnoreCase("B009")){
				bo.setBusiNumber28(amt1);
			}
			//第三段
			if( reportId.equalsIgnoreCase("C001")){
				bo.setBusiNumber29(amt1);
			}
			if( reportId.equalsIgnoreCase("C004")){
				bo.setBusiNumber30(amt1);
			}
			if( reportId.equalsIgnoreCase("C005")){
				bo.setBusiNumber31(amt1);
			}
			if( reportId.equalsIgnoreCase("C001")){
				bo.setBusiNumber32(amt2);
			}
			if( reportId.equalsIgnoreCase("C004")){
				bo.setBusiNumber33(amt2);
			}
			if( reportId.equalsIgnoreCase("C005")){
				bo.setBusiNumber34(amt2);
			}
			if( reportId.equalsIgnoreCase("C001")){
				bo.setBusiNumber35(amt3);
			}
			if( reportId.equalsIgnoreCase("C004")){
				bo.setBusiNumber36(amt3);
			}
			if( reportId.equalsIgnoreCase("C005")){
				bo.setBusiNumber37(amt3);
			}
			if( reportId.equalsIgnoreCase("C001")){
				bo.setBusiNumber38(amt4);
			}
			if( reportId.equalsIgnoreCase("C004")){
				bo.setBusiNumber39(amt4);
			}
			if( reportId.equalsIgnoreCase("C005")){
				bo.setBusiNumber40(amt4);
			}
			//第四段 逾期贷款情况
			if( reportId.equalsIgnoreCase("C006")){
				bo.setBusiNumber41(amt1);
			}
			if( reportId.equalsIgnoreCase("C009")){
				bo.setBusiNumber42(amt1);
			}
			if( reportId.equalsIgnoreCase("C010")){
				bo.setBusiNumber43(amt1);
			}
			if( reportId.equalsIgnoreCase("C006")){
				bo.setBusiNumber44(amt2);
			}
			if( reportId.equalsIgnoreCase("C009")){
				bo.setBusiNumber45(amt2);
			}
			if( reportId.equalsIgnoreCase("C010")){
				bo.setBusiNumber46(amt2);
			}
			
			if( reportId.equalsIgnoreCase("C006")){
				bo.setBusiNumber47(amt3);
			}
			if( reportId.equalsIgnoreCase("C009")){
				bo.setBusiNumber48(amt3);
			}
			if( reportId.equalsIgnoreCase("C010")){
				bo.setBusiNumber49(amt3);
			}
			if( reportId.equalsIgnoreCase("C006")){
				bo.setBusiNumber50(amt4);
			}
			if( reportId.equalsIgnoreCase("C009")){
				bo.setBusiNumber51(amt4);
			}
			if( reportId.equalsIgnoreCase("C010")){
				bo.setBusiNumber52(amt4);
			}
			//第五段
			if( reportId.equalsIgnoreCase("D001")){
				bo.setBusiNumber53(amt1);
			}
			if( reportId.equalsIgnoreCase("D001")){
				bo.setBusiNumber54(amt2);
			}
			if( reportId.equalsIgnoreCase("D002")){
				bo.setBusiNumber55(amt1);
			}
			if( reportId.equalsIgnoreCase("D002")){
				bo.setBusiNumber56(amt2);
			}
			if( reportId.equalsIgnoreCase("D003")){
				bo.setBusiNumber57(amt1);
			}
			if( reportId.equalsIgnoreCase("D003")){
				bo.setBusiNumber58(amt2);
			}
			if( reportId.equalsIgnoreCase("D004")){
				bo.setBusiNumber59(amt1);
			}
			if( reportId.equalsIgnoreCase("D004")){
				bo.setBusiNumber60(amt2);
			}
			
			if( reportId.equalsIgnoreCase("D005")){
				bo.setBusiNumber61(amt1);
			}
			if( reportId.equalsIgnoreCase("D005")){
				bo.setBusiNumber62(amt2);
			}
			if( reportId.equalsIgnoreCase("D006")){
				bo.setBusiNumber63(amt1);
			}
			if( reportId.equalsIgnoreCase("D006")){
				bo.setBusiNumber64(amt2);
			}
			if( reportId.equalsIgnoreCase("D007")){
				bo.setBusiNumber65(amt1);
			}
			if( reportId.equalsIgnoreCase("D007")){
				bo.setBusiNumber66(amt2);
			}
			if( reportId.equalsIgnoreCase("D008")){
				bo.setBusiNumber67(amt1);
			}
			if( reportId.equalsIgnoreCase("D008")){
				bo.setBusiNumber68(amt2);
			}
			
			ReportMonthOrg tableData = new ReportMonthOrg();
			tableData.setReportId(reportId);
			tableData.setRankNum(rankNum);
			tableData.setOrgName(org_Name);
			tableData.setOrgId(org_Id);
			tableData.setBusiMonth(busiMonth);
			tableData.setApplyUserId(applyUserId);
			tableData.setApplyUserName(applyUserName);
			tableData.setAmt1(amt1);
			tableData.setAmt2(amt2);
			tableData.setAmt3(amt3);
			tableData.setAmt4(amt4);
			tableData.setAmt5(amt5);
			tableData.setAmt6(amt6);
			tableData.setMemo(memo);
			// 表1
			if(reportId.equalsIgnoreCase("A001")){
				bo.setTableData1001(tableData);
			}
			if(reportId.equalsIgnoreCase("A002")){
				bo.setTableData1002(tableData);
			}
			if(reportId.equalsIgnoreCase("A003")){
				bo.setTableData1003(tableData);
			}
			if(reportId.equalsIgnoreCase("A004")){
				bo.setTableData1004(tableData);
			}
			if(reportId.equalsIgnoreCase("A005")){
				bo.setTableData1005(tableData);
			}
			//表2
			if(reportId.equalsIgnoreCase("B001")){
				bo.setTableData2001(tableData);
			}
			if(reportId.equalsIgnoreCase("B002")){
				bo.setTableData2002(tableData);
			}
			if(reportId.equalsIgnoreCase("B003")){
				bo.setTableData2003(tableData);
			}
			if(reportId.equalsIgnoreCase("B004")){
				bo.setTableData2004(tableData);
			}
			if(reportId.equalsIgnoreCase("B005")){
				bo.setTableData2005(tableData);
			}
			if(reportId.equalsIgnoreCase("B006")){
				bo.setTableData2006(tableData);
			}
			if(reportId.equalsIgnoreCase("B007")){
				bo.setTableData2007(tableData);
			}
			//表3
			if(reportId.equalsIgnoreCase("C001")){
				bo.setTableData3001(tableData);
			}
			if(reportId.equalsIgnoreCase("C002")){
				bo.setTableData3002(tableData);
			}
			if(reportId.equalsIgnoreCase("C003")){
				bo.setTableData3003(tableData);
			}
			if(reportId.equalsIgnoreCase("C004")){
				bo.setTableData3004(tableData);
			}
			if(reportId.equalsIgnoreCase("C005")){
				bo.setTableData3005(tableData);
			}
			/*******TODO 新增 表4 表5  东方邦信总公司7月份逾期贷款整体情况******/
			if(reportId.equalsIgnoreCase("B001")){
				bo.setTableData44001(tableData);
			}
			if(reportId.equalsIgnoreCase("B002")){
				bo.setTableData44002(tableData);
			}
			if(reportId.equalsIgnoreCase("B003")){
				bo.setTableData44003(tableData);
			}
			if(reportId.equalsIgnoreCase("B005")){
				bo.setTableData44004(tableData);
			}
			if(reportId.equalsIgnoreCase("B006")){
				bo.setTableData44005(tableData);
			}
			if(reportId.equalsIgnoreCase("C006")){
				bo.setTableData44006(tableData);
			}
			if(reportId.equalsIgnoreCase("C007")){
				bo.setTableData44007(tableData);
			}
			if(reportId.equalsIgnoreCase("C008")){
				bo.setTableData44008(tableData);
			}
			if(reportId.equalsIgnoreCase("C009")){
				bo.setTableData44009(tableData);
			}
			if(reportId.equalsIgnoreCase("C010")){
				bo.setTableData440010(tableData);
			}

			//表6
			if(reportId.equalsIgnoreCase("D001")){
				bo.setTableData4001(tableData);
			}
			if(reportId.equalsIgnoreCase("D002")){
				bo.setTableData4002(tableData);
			}
			if(reportId.equalsIgnoreCase("D003")){
				bo.setTableData4003(tableData);
			}
			if(reportId.equalsIgnoreCase("D004")){
				bo.setTableData4004(tableData);
			}
			if(reportId.equalsIgnoreCase("D005")){
				bo.setTableData4005(tableData);
			}
			if(reportId.equalsIgnoreCase("D006")){
				bo.setTableData4006(tableData);
			}
			if(reportId.equalsIgnoreCase("D007")){
				bo.setTableData4007(tableData);
			}
			if(reportId.equalsIgnoreCase("D008")){
				bo.setTableData4008(tableData);
			}
			//表7
			if(reportId.equalsIgnoreCase("E001")){
				bo.setTableData5001(tableData);
			}
			if(reportId.equalsIgnoreCase("E002")){
				bo.setTableSum5001(amt1);
				bo.setTableSum5002(amt2);
				bo.setTableSum5003(amt3);
			}
			//表8
			if(reportId.equalsIgnoreCase("E003")){
				bo.setTableData6001(tableData);
			}
			if(reportId.equalsIgnoreCase("E004")){
				bo.setTableSum6001(amt1);
				bo.setTableSum6002(amt2);
			}
			//表9
			if(reportId.equalsIgnoreCase("E005")){
				bo.setTableData7001(tableData);
			}
			if(reportId.equalsIgnoreCase("E006")){
				bo.setTableSum7001(amt1);
				bo.setTableSum7002(amt2);
				bo.setTableSum7003(amt3);
			}
			//表10
			if(reportId.equalsIgnoreCase("E007")){
				bo.setTableData8001(tableData);
			}
			if(reportId.equalsIgnoreCase("E008")){
				bo.setTableSum8001(amt1);
				bo.setTableSum8002(amt2);
			}
			//表11
			if(reportId.equalsIgnoreCase("E009")){
				bo.setTableData9001(tableData);
			}
			if(reportId.equalsIgnoreCase("E010")){
				bo.setTableSum9001(amt1);
				bo.setTableSum9002(amt2);
			}
			//表12
			if(reportId.equalsIgnoreCase("E011")){
				bo.setTableData10001(tableData);
			}
			if(reportId.equalsIgnoreCase("E012")){
				bo.setTableSum10001(amt1);
				bo.setTableSum10002(amt2);
				bo.setTableSum10003(amt3);
			}
			//表13
			if(reportId.equalsIgnoreCase("E015")){
				bo.setTableData12001(tableData);
			}
			if(reportId.equalsIgnoreCase("E016")){
				bo.setTableSum12001(amt1);
				bo.setTableSum12002(amt2);
				bo.setTableSum12003(amt3);
			}
			//表14
			if(reportId.equalsIgnoreCase("E013")){
				bo.setTableData11001(tableData);
			}
			if(reportId.equalsIgnoreCase("E014")){
				bo.setTableSum11001(amt1);
				bo.setTableSum11002(amt2);
				bo.setTableSum11003(amt3);
				bo.setTableSum11004(amt4);
			}
			
		}
		return bo;
	}
	
	@Override
	public Map<String, Object> toGetMap(MonthlyLoanBusiNotificationBo bo) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		String busiYear = bo.getBusiDate().substring(0,4);//今年
		String lbusiYear =String.valueOf(Integer.parseInt(bo.getBusiDate().substring(0,4))-1);//去年
		String busiMonth = "";//本月
		if (bo.getBusiDate().substring(4,5).equals("0")){
			busiMonth = bo.getBusiDate().substring(5,6);
		}else{
			busiMonth = bo.getBusiDate().substring(4,6);
		}
//		String lbusiMonth = String.valueOf(Integer.parseInt(busiMonth)-1);//上月
		String lbusiMonth = "";
		String busiYear2 = busiYear;
		if(busiMonth.equals("1")){
			busiYear2 = lbusiYear;
			lbusiMonth = "12";
		} else {
			lbusiMonth = String.valueOf(Integer.parseInt(busiMonth)-1);
		}
//		String nbusiMonth = String.valueOf(Integer.parseInt(busiMonth)+1);//下月
		String nbusiMonth = "";
		String busiYear3 = busiYear;
		if(busiMonth.equals("12")){
			busiYear3 = String.valueOf(Integer.parseInt(bo.getBusiDate().substring(0,4))+1);//第二年
			nbusiMonth = "1";
		} else {
			nbusiMonth = String.valueOf(Integer.parseInt(busiMonth)+1);//下月
		}
		
		String orgName = bo.getOrgName();
		String orgName1 = "";
		String orgName2 = "";
		String orgName3 = "";
		String orgName4 = "";
		String orgName5 = "";
		if(!bo.getOrgId().equals("99999")){
//			orgName1 = "邦信小贷";
			orgName2 = "邦信小贷公司";
			orgName3 = "小贷公司";
			orgName4 = "小贷";
			orgName5 = "公司";
			if(bo.getOrgId().equals("61561")){//天津鼎信特殊
				orgName1 = "小贷";
			}else{
				orgName1 = "邦信小贷";
			}
		}
		
		String numberFlag1 = bo.getBusiNumber3().signum()<0 ? "下降" : "增长";
		String numberFlag2 = bo.getBusiNumber4().signum()<0 ? "下降" : "增长";
		String numberFlag3 = bo.getBusiNumber7().signum()<0 ? "下降" : "增长";
		String numberFlag4 = bo.getBusiNumber8().signum()<0 ? "下降" : "增长";
		String numberFlag5 = bo.getBusiNumber10().signum()<0 ? "下降" : "增长";
		String numberFlag6 = bo.getBusiNumber11().signum()<0 ? "下降" : "增长";
		String numberFlag7 = bo.getBusiNumber15().signum()<0 ? "下降" : "增长";
		String numberFlag8 = bo.getBusiNumber16().signum()<0 ? "下降" : "增长";
		String numberFlag9 = bo.getBusiNumber17().signum()<0 ? "下降" : "增长";
		String numberFlag10 = bo.getBusiNumber20().signum()<0 ? "下降" : "增长";
		String numberFlag11 = bo.getBusiNumber21().signum()<0 ? "下降" : "增长";
		String numberFlag12 = bo.getBusiNumber22().signum()<0 ? "下降" : "增长";
		String numberFlag13 = bo.getBusiNumber25().signum()<0 ? "下降" : "增长";
		String numberFlag14 = bo.getBusiNumber26().signum()<0 ? "下降" : "增长";
		String numberFlag15 = bo.getBusiNumber27().signum()<0 ? "下降" : "增长";
		String numberFlag16 = bo.getBusiNumber30().signum()<0 ? "下降" : "增长";
		String numberFlag17 = bo.getBusiNumber31().signum()<0 ? "下降" : "增长";
		String numberFlag18 = bo.getBusiNumber33().signum()<0 ? "下降" : "增长";
		String numberFlag19 = bo.getBusiNumber34().signum()<0 ? "下降" : "增长";
		String numberFlag20 = bo.getBusiNumber36().signum()<0 ? "下降" : "增长";
		String numberFlag21 = bo.getBusiNumber37().signum()<0 ? "下降" : "增长";
		String numberFlag22 = bo.getBusiNumber39().signum()<0 ? "下降" : "增长";
		String numberFlag23 = bo.getBusiNumber40().signum()<0 ? "下降" : "增长";
		String numberFlag24 = bo.getBusiNumber42().signum()<0 ? "下降" : "增长";
		String numberFlag25 = bo.getBusiNumber43().signum()<0 ? "下降" : "增长";
		String numberFlag26 = bo.getBusiNumber45().signum()<0 ? "下降" : "增长";
		String numberFlag27 = bo.getBusiNumber46().signum()<0 ? "下降" : "增长";
		String numberFlag28 = bo.getBusiNumber48().signum()<0 ? "下降" : "增长";
		String numberFlag29 = bo.getBusiNumber49().signum()<0 ? "下降" : "增长";
		String numberFlag30 = bo.getBusiNumber51().signum()<0 ? "下降" : "增长";
		String numberFlag31 = bo.getBusiNumber52().signum()<0 ? "下降" : "增长";
		
		//TODO
		dataMap.put("curDate",bo.getCurDate());//系统当前日期
		dataMap.put("busiYear",busiYear);//今年
		dataMap.put("busiYear2",busiYear2);//1月时
		dataMap.put("busiYear3",busiYear3);//12月时
		dataMap.put("lbusiYear",lbusiYear);//去年
		dataMap.put("busiMonth",busiMonth);//本月
		dataMap.put("lbusiMonth",lbusiMonth);//上月
		dataMap.put("nbusiMonth",nbusiMonth);//下月
		dataMap.put("orgName",orgName);//所选机构名称
		dataMap.put("orgName1",orgName1);//所选机构名称
		dataMap.put("orgName2",orgName2);//所选机构名称
		dataMap.put("orgName3",orgName3);//所选机构名称
		dataMap.put("orgName4",orgName4);//所选机构名称
		dataMap.put("orgName5",orgName5);//所选机构名称
		//增长下降
		dataMap.put("numberFlag1",numberFlag1);
		dataMap.put("numberFlag2",numberFlag2);
		dataMap.put("numberFlag3",numberFlag3);
		dataMap.put("numberFlag4",numberFlag4);
		dataMap.put("numberFlag5",numberFlag5);
		dataMap.put("numberFlag6",numberFlag6);
		dataMap.put("numberFlag7",numberFlag7);
		dataMap.put("numberFlag8",numberFlag8);
		dataMap.put("numberFlag9",numberFlag9);
		dataMap.put("numberFlag10",numberFlag10);
		dataMap.put("numberFlag11",numberFlag11);
		dataMap.put("numberFlag12",numberFlag12);
		dataMap.put("numberFlag13",numberFlag13);
		dataMap.put("numberFlag14",numberFlag14);
		dataMap.put("numberFlag15",numberFlag15);
		dataMap.put("numberFlag16",numberFlag16);
		dataMap.put("numberFlag17",numberFlag17);
		dataMap.put("numberFlag18",numberFlag18);
		dataMap.put("numberFlag19",numberFlag19);
		dataMap.put("numberFlag20",numberFlag20);
		dataMap.put("numberFlag21",numberFlag21);
		dataMap.put("numberFlag22",numberFlag22);
		dataMap.put("numberFlag23",numberFlag23);
		dataMap.put("numberFlag24",numberFlag24);
		dataMap.put("numberFlag25",numberFlag25);
		dataMap.put("numberFlag26",numberFlag26);
		dataMap.put("numberFlag27",numberFlag27);
		dataMap.put("numberFlag28",numberFlag28);
		dataMap.put("numberFlag29",numberFlag29);
		dataMap.put("numberFlag30",numberFlag30);
		dataMap.put("numberFlag31",numberFlag31);
		//第一段
		dataMap.put("busiNumber1",bo.getBusiNumber1().abs());
		dataMap.put("busiNumber2",bo.getBusiNumber2().abs());
		dataMap.put("busiNumber3",bo.getBusiNumber3().abs());
		dataMap.put("busiNumber4",bo.getBusiNumber4().abs());
		dataMap.put("busiNumber5",bo.getBusiNumber5().abs());
		dataMap.put("busiNumber6",bo.getBusiNumber6().abs());
		dataMap.put("busiNumber7",bo.getBusiNumber7().abs());
		dataMap.put("busiNumber8",bo.getBusiNumber8().abs());
		dataMap.put("busiNumber9",bo.getBusiNumber9().abs());
		dataMap.put("busiNumber10",bo.getBusiNumber10().abs());
		dataMap.put("busiNumber11",bo.getBusiNumber11().abs());
		dataMap.put("busiNumber12",bo.getBusiNumber12().abs());
		//第二段
		dataMap.put("busiNumber13",bo.getBusiNumber13().abs());
		dataMap.put("busiNumber14",bo.getBusiNumber14().abs());
		dataMap.put("busiNumber15",bo.getBusiNumber15().abs());
		dataMap.put("busiNumber16",bo.getBusiNumber16().abs());
		dataMap.put("busiNumber17",bo.getBusiNumber17().abs());
		dataMap.put("busiNumber18",bo.getBusiNumber18().abs());
		dataMap.put("busiNumber19",bo.getBusiNumber19().abs());
		dataMap.put("busiNumber20",bo.getBusiNumber20().abs());
		dataMap.put("busiNumber21",bo.getBusiNumber21().abs());
		dataMap.put("busiNumber22",bo.getBusiNumber22().abs());
		dataMap.put("busiNumber23",bo.getBusiNumber23().abs());
		dataMap.put("busiNumber24",bo.getBusiNumber24().abs());
		dataMap.put("busiNumber25",bo.getBusiNumber25().abs());
		dataMap.put("busiNumber26",bo.getBusiNumber26().abs());
		dataMap.put("busiNumber27",bo.getBusiNumber27().abs());
		dataMap.put("busiNumber28",bo.getBusiNumber28().abs());
		//第三段
		dataMap.put("busiNumber29",bo.getBusiNumber29().abs());
		dataMap.put("busiNumber30",bo.getBusiNumber30().abs());
		dataMap.put("busiNumber31",bo.getBusiNumber31().abs());
		dataMap.put("busiNumber32",bo.getBusiNumber32().abs());
		dataMap.put("busiNumber33",bo.getBusiNumber33().abs());
		dataMap.put("busiNumber34",bo.getBusiNumber34().abs());
		dataMap.put("busiNumber35",bo.getBusiNumber35().abs());
		dataMap.put("busiNumber36",bo.getBusiNumber36().abs());
		dataMap.put("busiNumber37",bo.getBusiNumber37().abs());
		dataMap.put("busiNumber38",bo.getBusiNumber38().abs());
		dataMap.put("busiNumber39",bo.getBusiNumber39().abs());
		dataMap.put("busiNumber40",bo.getBusiNumber40().abs());
		//第四段
		dataMap.put("busiNumber41",bo.getBusiNumber41().abs());
		dataMap.put("busiNumber42",bo.getBusiNumber42().abs());
		dataMap.put("busiNumber43",bo.getBusiNumber43().abs());
		dataMap.put("busiNumber44",bo.getBusiNumber44().abs());
		dataMap.put("busiNumber45",bo.getBusiNumber45().abs());
		dataMap.put("busiNumber46",bo.getBusiNumber46().abs());
		dataMap.put("busiNumber47",bo.getBusiNumber47().abs());
		dataMap.put("busiNumber48",bo.getBusiNumber48().abs());
		dataMap.put("busiNumber49",bo.getBusiNumber49().abs());
		dataMap.put("busiNumber50",bo.getBusiNumber50().abs());
		dataMap.put("busiNumber51",bo.getBusiNumber51().abs());
		dataMap.put("busiNumber52",bo.getBusiNumber52().abs());
		dataMap.put("busiNumber53",bo.getBusiNumber53().abs());
		dataMap.put("busiNumber54",bo.getBusiNumber54().abs());
		dataMap.put("busiNumber55",bo.getBusiNumber55().abs());
		dataMap.put("busiNumber56",bo.getBusiNumber56().abs());
		
		dataMap.put("busiNumber57",bo.getBusiNumber57().abs());
		dataMap.put("busiNumber58",bo.getBusiNumber58().abs());
		dataMap.put("busiNumber59",bo.getBusiNumber59().abs());
		dataMap.put("busiNumber60",bo.getBusiNumber60().abs());
		dataMap.put("busiNumber61",bo.getBusiNumber61().abs());
		dataMap.put("busiNumber62",bo.getBusiNumber62().abs());
		dataMap.put("busiNumber63",bo.getBusiNumber63().abs());
		dataMap.put("busiNumber64",bo.getBusiNumber64().abs());
		dataMap.put("busiNumber65",bo.getBusiNumber65().abs());
		dataMap.put("busiNumber66",bo.getBusiNumber66().abs());
		dataMap.put("busiNumber67",bo.getBusiNumber67().abs());
		dataMap.put("busiNumber68",bo.getBusiNumber68().abs());
		
		
		//表1
		dataMap.put("tbl1001", bo.getTableData1001());
		dataMap.put("tbl1002", bo.getTableData1002());
		dataMap.put("tbl1003", bo.getTableData1003());
		dataMap.put("tbl1004", bo.getTableData1004());
		dataMap.put("tbl1005", bo.getTableData1005());
		//表2
		dataMap.put("tbl2001", bo.getTableData2001());
		dataMap.put("tbl2002", bo.getTableData2002());
		dataMap.put("tbl2003", bo.getTableData2003());
		dataMap.put("tbl2004", bo.getTableData2004());
		dataMap.put("tbl2005", bo.getTableData2005());
		dataMap.put("tbl2006", bo.getTableData2006());
		dataMap.put("tbl2007", bo.getTableData2007());
		//表3
		dataMap.put("tbl3001", bo.getTableData3001());
		dataMap.put("tbl3002", bo.getTableData3002());
		dataMap.put("tbl3003", bo.getTableData3003());
		dataMap.put("tbl3004", bo.getTableData3004());
		dataMap.put("tbl3005", bo.getTableData3005());
		//表4，表5
		dataMap.put("tbl44001", bo.getTableData44001());
		dataMap.put("tbl44002", bo.getTableData44002());
		dataMap.put("tbl44003", bo.getTableData44003());
		dataMap.put("tbl44004", bo.getTableData44004());
		dataMap.put("tbl44005", bo.getTableData44005());
		dataMap.put("tbl44006", bo.getTableData44006());
		dataMap.put("tbl44007", bo.getTableData44007());
		dataMap.put("tbl44008", bo.getTableData44008());
		dataMap.put("tbl44009", bo.getTableData44009());
		dataMap.put("tbl440010", bo.getTableData440010());
		//表6
		dataMap.put("tbl4001", bo.getTableData4001());
		dataMap.put("tbl4002", bo.getTableData4002());
		dataMap.put("tbl4003", bo.getTableData4003());
		dataMap.put("tbl4004", bo.getTableData4004());
		dataMap.put("tbl4005", bo.getTableData4005());
		dataMap.put("tbl4006", bo.getTableData4006());
		dataMap.put("tbl4007", bo.getTableData4007());
		dataMap.put("tbl4008", bo.getTableData4008());
		//表7
		dataMap.put("tbl5001", bo.getTableData5001());
		dataMap.put("tblSum5001", bo.getTableSum5001());
		dataMap.put("tblSum5002", bo.getTableSum5002());
		dataMap.put("tblSum5003", bo.getTableSum5003());
		//表8
		dataMap.put("tbl6001", bo.getTableData6001());
		dataMap.put("tblSum6001", bo.getTableSum6001());
		dataMap.put("tblSum6002", bo.getTableSum6002());
		//表9
		dataMap.put("tbl7001", bo.getTableData7001());
		dataMap.put("tblSum7001", bo.getTableSum7001());
		dataMap.put("tblSum7002", bo.getTableSum7002());
		dataMap.put("tblSum7003", bo.getTableSum7003());
		//表10
		dataMap.put("tbl8001", bo.getTableData8001());
		dataMap.put("tblSum8001", bo.getTableSum8001());
		dataMap.put("tblSum8002", bo.getTableSum8002());
		//表11
		dataMap.put("tbl9001", bo.getTableData9001());
		dataMap.put("tblSum9001", bo.getTableSum9001());
		dataMap.put("tblSum9002", bo.getTableSum9002());
		//表12
		dataMap.put("tbl10001", bo.getTableData10001());
		dataMap.put("tblSum10001", bo.getTableSum10001());
		dataMap.put("tblSum10002", bo.getTableSum10002());
		dataMap.put("tblSum10003", bo.getTableSum10003());
		//表13
		dataMap.put("tbl12001", bo.getTableData12001());
		dataMap.put("tblSum12001", bo.getTableSum12001());
		dataMap.put("tblSum12002", bo.getTableSum12002());
		dataMap.put("tblSum12003", bo.getTableSum12003());
		//表14
		dataMap.put("tbl11001", bo.getTableData11001());
		dataMap.put("tblSum11001", bo.getTableSum11001());
		dataMap.put("tblSum11002", bo.getTableSum11002());
		dataMap.put("tblSum11003", bo.getTableSum11003());
		dataMap.put("tblSum11004", bo.getTableSum11004());
		
		return dataMap;
	}

	@Override
	public List<ReportChartCoordinate> findReportChartCoordinateAllList() {
		
		return reportChartCoordinateDao.findAllList();
	}
	
	@Override
	public ReportService_PortType loginCognos() throws InterruptedException {
		return reportRunnerService.loginCognos();
	}
	
	@Override
	public boolean runReportBatch(String searchPath, String outfileDir,
			String outfileName, List<ReportParam> params, ReportService_PortType repService,Long rowId) throws InterruptedException {
		return reportRunnerService.runReportBatch(searchPath, outfileDir, outfileName, params, repService, rowId);
	}
	
	
}