package com.orienttech.statics.web.controller.templateMng;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.orienttech.statics.commons.base.BaseController;
import com.orienttech.statics.commons.base.Result;
import com.orienttech.statics.commons.datatables.DataTablesPage;
import com.orienttech.statics.commons.security.SessionUser;
import com.orienttech.statics.commons.utils.FileUtils;
import com.orienttech.statics.commons.utils.PropertiesConstants;
import com.orienttech.statics.dao.entity.submission.TblReportTemplate;
import com.orienttech.statics.service.model.submission.TaskReceiverBo;
import com.orienttech.statics.service.model.sysmng.MenuBo;
import com.orienttech.statics.service.model.usermng.OrgDeptBo;
import com.orienttech.statics.service.templateMng.TemplateMngService;
import com.orienttech.statics.service.usermng.OrgDeptService;
@RequestMapping("/templateMng")
@Controller
public class TemplateMngController extends BaseController {
	
	@Autowired
	private OrgDeptService orgDeptService;
	@Autowired
	private TemplateMngService templateMngService;
	
	/**
	 * 根据报表类型加载不同的页面
	 * @param type	JSP名称
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(@RequestParam(defaultValue="")String type,Model model){
		SessionUser sUser = (SessionUser) SecurityUtils.getSubject().getPrincipal();
		String loginName = sUser.getUserName();
		
		model.addAttribute("loginName", loginName);
		return "/templateMng/"+type;
	}
	
	@RequestMapping("/templateEdit/{id}")
	public String templateEdit(Model model, @PathVariable("id") String id) {
		
		TblReportTemplate tblReportTemplate = templateMngService.queryTemplateById(id);
		String receiver = tblReportTemplate.getBusinessExaminePeople();
		model.addAttribute("callback_receivers", receiver);
		model.addAttribute("moduleName", "templateMng");
		model.addAttribute("template", tblReportTemplate);
		
		return "/templateMng/templateCreate";
	}
	
	/**
	 * 不分页查询所有机构
	 * 
	 * @param draw
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findAllOrg", method = RequestMethod.POST)
	@ResponseBody
	public Result findAllOrg() {
		//List<OrgDeptBo> orgList = orgDeptService.findOrgDeptListByOrgId("0");
		List<OrgDeptBo> orgList = orgDeptService.findOrgDeptListByOrgId("00");// by  dz
		return orgList.size() <= 0 ? failure() : success(orgList);
	}
	
	/**
	 * 新增模板(暂存)
	 */
	@RequestMapping(value = "/addReportTemplate", method = RequestMethod.POST)
	public String addReportTemplate(TblReportTemplate tblReportTemplate,@RequestParam(value = "uploadFile", required = false) MultipartFile myfile,HttpServletRequest request ,HttpServletResponse response) {
		
		logger.info("新增模板："+tblReportTemplate.getName());
		
		String sOs = request.getParameter("saveOrSubmit");
		
		String receiver = request.getParameter("receiver");
		if(receiver != null && receiver !=""){
			receiver = receiver.substring(0, receiver.length()-1);
		}
		
		try {
			SessionUser user=getSessionUser();
//			tblReportTemplate.setCreatePeople(user.getLoginName()+"");
			tblReportTemplate.setCreatePeople(user.getUserName()+"");
			tblReportTemplate.setBusinessExaminePeople(receiver);
			templateMngService.add(tblReportTemplate,myfile,sOs);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				PrintWriter pw = response.getWriter();
				pw.write("creatFail");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		try {
			PrintWriter pw = response.getWriter();
			pw.write("creatSuccess");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 发送审核
	 */
	@RequestMapping(value = "/sendAudit", method = RequestMethod.POST)
	public String sendAudit(TblReportTemplate tblReportTemplate,
			@RequestParam(value = "uploadFile", required = false) MultipartFile myfile, 
			HttpServletResponse response,HttpServletRequest request) {
		
		logger.info("发送审核：报表名称===============》》"+tblReportTemplate.getName());
		
		String receivers = request.getParameter("receivers");
		receivers = receivers.substring(0, receivers.length()-1);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			if(myfile!=null && !myfile.isEmpty()){
				logger.info("myfile.getName()==="+myfile.getName());
		    }
			SessionUser user=getSessionUser();
//			tblReportTemplate.setCreatePeople(user.getLoginName()+"");
			tblReportTemplate.setCreatePeople(user.getUserName()+"");
			templateMngService.sendAudit(tblReportTemplate,user,myfile,receivers);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				PrintWriter pw = response.getWriter();
				pw.write("sendFail");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		try {
			PrintWriter pw = response.getWriter();
			pw.write("sendSuccess");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 验证模板编号是否存在
	 */
	@RequestMapping(value = "/validateTemplateNo", method = RequestMethod.POST)
	@ResponseBody
	public boolean validateTemplateNo(String no, String id) {
		//logger.info("id================="+id);
		return templateMngService.getCountByNo(no, id);
	}
	
	/**
	 * 验证模板名称是否存在
	 */
	@RequestMapping(value = "/validateTemplateName", method = RequestMethod.POST)
	@ResponseBody
	public boolean validateTemplateName(String name, String id) {
		return templateMngService.getCountByName(name, id);
	}
	/**
	 * 根据条件查询模板
	 * @param pageNumber
	 * @param pageSize
	 * @param sEcho
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param reportName
	 * @return
	 */

	@RequestMapping(value="/queryTemplates",method=RequestMethod.POST)
	@ResponseBody
	public DataTablesPage queryTemplates(@RequestParam("start") Integer pageNumber,
			@RequestParam("length") Integer pageSize, Integer sEcho,
			String beginTime, String endTime, String status,String cycle, String reportName) {
		Page<Object[]> page = templateMngService.queryTemplate(pageNumber/pageSize + 1, pageSize, beginTime, endTime, status,cycle, reportName);
		List<Object[]> list = page.getContent();
		for(Object[] ob : list){
//			ob[4] = templateMngService.queryUserNameByLoginName(ob[4]);
			ob[5] = templateMngService.queryUserNameByLoginName(ob[5]);
			ob[6] = templateMngService.queryUserNameByLoginName(ob[6]);
			ob[7] = templateMngService.queryUserNameByLoginName(ob[7]);
		 }
		return new DataTablesPage(sEcho, page);
	}
	
	@RequestMapping(value = "/queryTemplateById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TblReportTemplate queryTemplateById(@PathVariable("id") String templateId) {
		TblReportTemplate tblReportTemplate = templateMngService.queryTemplateById(templateId);
		String orgName = templateMngService.queryOrgNameByOrgId(tblReportTemplate.getSubmitOrg());
		String roleName = templateMngService.queryRoleNameByRoleId(tblReportTemplate.getCheckRole());
		tblReportTemplate.setSubmitOrg(orgName);
		tblReportTemplate.setCheckRole(roleName);
		return tblReportTemplate;
	}
	/**
	 * 根据id修改模板
	 * @param templateId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/updateExcute/{id}/{state}", method = RequestMethod.GET)
	@ResponseBody
	public String updateExcute(@PathVariable("id") String templateId, @PathVariable("state") String state) {
		 boolean result = templateMngService.updateExcute(templateId,state);
		 if(result){
			 return "success";
		 }else{
			 return "failure";
		 }
	}
	
	/**
	 * 判断文件是否存在
	 * @param fileId
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/fileIsExists")
	@ResponseBody
	public String fileIsExists(String filePath,HttpServletRequest req,HttpServletResponse resp){
		logger.info("开始验证文件是否存在..............."+filePath);
		//TblReportTemplate tblReportTemplate = templateMngService.queryTemplateById(filePath);
		//String filePath = tblReportTemplate.getPath();
		filePath = PropertiesConstants.getString(PropertiesConstants.USER_HOME) + 
				PropertiesConstants.getString(PropertiesConstants.DATA_SUBMIT)+File.separator+filePath;
		//logger.info("filePath。。。。。。。"+filePath);
		File file=new File(filePath);
		if(FileUtils.isExists(file)){
			return "";
		}else{
			return "文件不存在！";
		}
	}
	
	/**
	 * 下载Excel
	 * 
	 * @param filePath  文件绝对路径
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downLoadExcelById", method = RequestMethod.GET)
	@ResponseBody
	public Result downLoadExcelById(String fileId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TblReportTemplate tblReportTemplate = templateMngService.queryTemplateById(fileId);
		String filePath = tblReportTemplate.getPath();
		filePath = PropertiesConstants.getString(PropertiesConstants.USER_HOME)
				+ PropertiesConstants.getString(PropertiesConstants.DATA_SUBMIT)
				+ File.separator + filePath;
		String fileName = filePath.substring(filePath.lastIndexOf("/"));
		if(filePath!=null && !"".equals(filePath) && filePath.length()>0 && filePath.lastIndexOf("/")>0){
			fileName = filePath.substring(filePath.lastIndexOf("/"));
		}else{
			logger.info("filePath.........................");
			return failure("文件不存在");
			
		}
		fileName = tblReportTemplate.getName()+".xlsx";
		logger.info("文件下载...");
		try {
			logger.info("filePath........................."+filePath);
			File file = new File(filePath);
			if (FileUtils.isExists(file)) {
				logger.info("isExists........................."+file.exists());
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				FileUtils.downloadFile(new File(filePath),fileName, request,
						response);
			} else {
				logger.info("isNotExists........................."+file.exists());
				logger.debug("文件不存在！");
				return failure("文件不存在");
				
			}
		} catch (IOException e) {
			logger.debug("错误：" + e.getMessage());
		}
		return success("下载成功");
		
		
	}

	/**
	 * 下载Excel
	 * 
	 * @param fileName  文件名
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadExcel", method=RequestMethod.GET)
	@ResponseBody
	public Result downLoadExcel(String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String filePath = PropertiesConstants.getString(PropertiesConstants.USER_HOME)
				+ PropertiesConstants
						.getString(PropertiesConstants.HISTORY_REPORT_PATH)
				+ File.separator +fileName;
		logger.info(fileName);
		logger.info("文件下载...");
		Result result = new Result(true);
		try {
			File file = new File(filePath);
			if (FileUtils.isExists(file)) {
				FileUtils.downloadFile(new File(filePath), fileName, request,
					response);
				logger.info("文件下载成功");
				result.setMsg("下载成功！");
			} else {
				logger.info("文件不存在");
				logger.debug("文件不存在！");
				result.setSuccess(false);
				result.setMsg("文件不存在！");
			}
		} catch (Exception e) {
			logger.debug("错误：" + e.getMessage());
			result.setMsg("下载失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping(value = "/getRoleId", method = RequestMethod.GET)
	@ResponseBody
	public String getRoleId(){
		SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getPrincipal();
		String loginName = sessionUser.getLoginName();
		return templateMngService.queryRoleBySsoId(loginName);//通过用户工号查询角色id
	}
	
	/**
	 * 加载组织机构
	 * @return
	 */
	@RequestMapping("/loadOrgDepts")
	@ResponseBody
	public List<OrgDeptBo> loadOrgDeptsData(){
		SessionUser user=getSessionUser();
		return orgDeptService.findOrgDeptListByOrgId("00");
	}
	
	/*
	 * 下一环节处理人
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/findtaskReceiverMenuList", method = RequestMethod.POST)
	public void findFirstDashboardList(HttpServletRequest request,HttpServletResponse response) {
		
		String receiver = request.getParameter("receiver");
//		receiver = receiver.substring(0, receiver.length()-1);
		
		PrintWriter out = null;
		String orgId=request.getParameter("orgId");
		try {
			out = response.getWriter();
			List<TaskReceiverBo> menuList = templateMngService.findTaskReceiverMenuTreeList(orgId);
			for (int i = 0; i < menuList.size(); i++) {
				TaskReceiverBo bo = menuList.get(i);
				//默认第一个节点展开
				bo.setOpen(i == 0 ? true : false);
				//回显已选择的处理人
				if(bo.getSsoId().equalsIgnoreCase(receiver)){
					bo.setChecked(true);
				}else{
					bo.setChecked(false);
				}
			}
			JSONArray array = JSONArray.fromObject(menuList);
			out.print(array);
		} catch (IOException e) {
			out.print("");
		}
	}
		/*
		 * 下一环节处理人(带机构参数）
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/findtaskReceiverMenuListByRoleAndOrgId", method = RequestMethod.POST)
		@ResponseBody
		public void findtaskReceiverMenuListByRoleAndOrgId(HttpServletRequest request,HttpServletResponse response) {
			Subject currentUser = SecurityUtils.getSubject();
			SessionUser sUser = (SessionUser) currentUser.getPrincipal();
			PrintWriter out = null;
			String roleId=request.getParameter("roleId");
			String orgId=request.getParameter("orgId");
			try {
				out = response.getWriter();
				List<TaskReceiverBo> menuList = templateMngService.findTaskReceiverMenuTreeList(orgId);
				for (int i = 0; i < menuList.size(); i++) {
					menuList.get(i).setOpen(i == 0 ? true : false);// 默认第一个节点展开
				}
				JSONArray array = JSONArray.fromObject(menuList);
				out.print(array);
			} catch (IOException e) {
				out.print("");
			}
	}
	
	
}
