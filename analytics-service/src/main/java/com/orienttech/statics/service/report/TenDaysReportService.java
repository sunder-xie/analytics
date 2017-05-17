package com.orienttech.statics.service.report;

import java.util.Map;

import com.orienttech.statics.service.model.report.FactLoanReportPeriodBo;

/**   
 * 类名称：TenDaysReportService
 * 类描述 ：
 * 创建人:wangxy
 * 创建时间：2015年7月20日 下午4:11:32  
 * 修改人：wangxy
 * 修改时间：
 * 修改备注：
 * 版本： V1.0
 */
public interface TenDaysReportService {
	/**
	 * 查询数据,封装Bo
	 * @param condition
	 * @author wangxy 20150718
	 * @return
	 */
	FactLoanReportPeriodBo searchByCondition(String condition);
	/**
	 * 将BO组装成Map
	 * @param bo
	 * @author wangxy 20150721
	 * @return
	 */
	public Map<String, Object> toGetMap(FactLoanReportPeriodBo bo);
}
