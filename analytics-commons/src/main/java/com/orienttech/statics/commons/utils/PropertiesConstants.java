package com.orienttech.statics.commons.utils;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesConstants {

	/**
	 * 门户URL
	 */
	private final static String PORTAL_URL = "portal.url";
	/**
	 * 默认开启集群
	 */
	private final static String CLUSTER_OPEN = "quartz.clusterOpen";
	/**
	 * 默认开启调度
	 */
	private final static String QUARTZ_OPEN = "quartz.open";
	/**
	 * 
	 */
	public final static String PORTAL_JUMP = "shiro.portalJump";

	public final static String HISTORY_REPORT_PATH = "sys.historyReportPath";
	public final static String TEMP_REPORT_PATH = "sys.tempReportPath";
	private final static String DB_TJ_SCHEMA = "sys.dbTjSchema";
	
	public final static String TEMP_IMG_PATH="sys.tempImagePath";
	public final static String TEMPLATE_PATH = "sys.templatePath";
	public final static String WORD_PATH = "sys.wordPath";
	
	public final static String DATA_SUBMIT="sys.dataSubmit";
	public final static String DATA_MOBILE="sys.mobilePath";//分析报告url
	public final static String DATA_UPDATE_DATAS="sys.updateDatas";//资金拆借表url
	public final static String DATA_MONITOR_MNG="sys.monitorMng";//异常监控下载url
	
	public final static String USER_HOME="user.home";//System.getProperty("user.home")
	public final static String USER_MONITOR="user.monitor";//异常监控拼接link
	
	public final static String COGNOS_SERVICE_HOST="cognos.serverHost";
	public final static String COGNOS_SERVER_PORT="cognos.serverPort";
	public final static String COGNOS_USER_NAME="cognos.userName";
	public final static String COGNOS_USER_PASSWORD="cognos.userPassword";
	public final static String COGNOS_USER_NAMESPACE="cognos.userNamespace";

	private static Properties properties = null;

	public static String getString(String key) {
		if (properties == null) {
			properties = Utils
					.loadPropertiesFileFromClassPath("application.properties");
		}
		return properties.getProperty(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	/**
	 * 获取门户URL
	 * 
	 * @return
	 */
	public static String getPortalUrl() {
		return getString(PORTAL_URL);
	}

	/**
	 * 获取数据库
	 * 
	 * @return
	 */
	public static String getDbTjSchema() {
		return StringUtils
				.defaultString(getString(DB_TJ_SCHEMA), "cognos_data");// 默认数据库名称
	}
	/**
	 * 获取集群开关。默认on表示开启集群
	 * @return
	 */
	public static String getClusterOpen() {
		return getString(CLUSTER_OPEN);
	}
	/**
	 * 获取调度开关。默认on表示开启调度
	 * @return
	 */
	public static String getQuartzOpen() {
		return getString(QUARTZ_OPEN);
	}

}
