<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../commons/res/taglibs.jsp"%>
<%@include file="../commons/page_header.jsp"%>
<div class="ajaxContent">
<div class="row">
	<div class="col-xs-12">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-1">请选择统计机构：</label>
				<div class="col-sm-9">
					<select name="orgid" id="orgId" class="col-sm-5">
						<c:if test="${user.premLevel=='1' }">
							<option value="">全部</option>
						</c:if>
						<c:forEach items="${orgs}" var="org">
							<option value="${org.id }">${org.desc }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="clearfix form-actions">
				<div id="buttonGroup" class="col-md-offset-3 col-md-9">
					<button id="query" class="btn btn-sm btn-purple" type="button" role="query">
						<i class="ace-icon fa fa-search bigger-110"></i> 查询
					</button>&nbsp; &nbsp; &nbsp;
					<button class="btn btn-sm btn-purple" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> 重置
					</button>&nbsp; &nbsp; &nbsp; 
					<a id="reportId" class="btn btn-sm btn-purple"> 
						<i class="glyphicon glyphicon-book"></i> 指标说明
					</a>&nbsp; &nbsp; &nbsp; 
					<a id="storeId" class="btn btn-sm btn-purple"> 
						<i class="glyphicon glyphicon-inbox"></i> 添加收藏
					</a>&nbsp; &nbsp; &nbsp; 
					<a id="cancelStore" class="btn btn-sm btn-purple"> 
						<i class="glyphicon glyphicon-inbox"></i> 取消收藏
					</a>&nbsp; &nbsp; &nbsp; 
					<a id="analysisId" class="btn btn-sm btn-purple"> 
						<i class="glyphicon glyphicon-inbox"></i> 多维分析
					</a>&nbsp; &nbsp; &nbsp; 
					<a id="detailQueryId" class="btn btn-sm btn-purple"> 
						<i class="glyphicon glyphicon-inbox"></i> 明细查询
					</a>
				</div>
			</div>
		</form>
	</div>
</div>

<%@include file="../reportShows/reportShows.jsp"%>
</div>
<script type="text/javascript">
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	var arrStr = document.cookie.split(";");
			for(var i=0; i<arrStr.length; i++){
				var temp = arrStr[i].split("=");
				if(temp[0].trim() == "orgSingleId"){
					$("#orgId").val(temp[1]);
				}
			}
	ace.load_ajax_scripts([], function() {
	})
	$("#query").click(function() {
		var orgId = $('#orgId').val();
		var date = new Date();
		date.setDate(date.getDate() + 10);
		document.cookie = "orgSingleId="+orgId+";expires=" + date.toGMTString();
		utils.openReportOfSingleOrg("${menu.url}", null, null, orgId);
	});
</script>