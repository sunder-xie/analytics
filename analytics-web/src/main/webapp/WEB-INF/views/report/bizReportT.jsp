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
					<select data-placeholder=" " multiple
						class="col-xs-10 col-sm-5 ch-orgselect" name="orgsId" id="orgsId">
						<optgroup label="请选择统计机构"></optgroup>
					</select> 
					<a href="javascript:void(0);" id="orgs_all-select" title="全选">
						<i class="ace-icon glyphicon glyphicon-ok"></i>
					</a> 
					<a href="javascript:void(0);" id="all-select-excTeam" title="全选(不含工作组)">
						<i class="ace-icon glyphicon glyphicon-edit"></i>
					</a>
					<a href="javascript:void(0);" id="orgs_del-select" title="取消">
						<i class="ace-icon glyphicon glyphicon-remove"></i>
					</a>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-1">请选择转出资产包：</label>
				<div class="col-sm-9">
					<select data-placeholder=" " multiple
						class="col-xs-10 col-sm-5 ch-apkselect" name="apksId" id="apksId">
						<optgroup label="请选择转出资产包"></optgroup>
					</select> 
					<input id="orgCodeId" type="hidden" name="orgCodeId" value="${orgId}" >
					<a href="javascript:void(0);" id="apks_all-select" title="全选"><i
						class="ace-icon glyphicon glyphicon-ok"></i></a> <a
						href="javascript:void(0);" id="apks_del-select" title="取消"><i
						class="ace-icon glyphicon glyphicon-remove"></i></a>
				</div>
			</div>

			<div class="clearfix form-actions">
				<div id="buttonGroup" class="col-md-offset-3 col-md-9">
					<button class="btn btn-sm btn-purple" type="button" id="query">
						<i class="ace-icon fa fa-search bigger-110"></i> 查询
					</button>

					&nbsp; &nbsp; &nbsp;
					<button class="btn btn-sm btn-purple" type="reset" id="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> 重置
					</button>
					&nbsp; &nbsp; &nbsp; <a id="reportId" class="btn btn-sm btn-purple">
						<i class="glyphicon glyphicon-book"></i> 指标说明
					</a>
					&nbsp; &nbsp; &nbsp; <a id="storeId" class="btn btn-sm btn-purple"> <i
						class="glyphicon glyphicon-inbox"></i> 添加收藏
					</a>
					&nbsp; &nbsp; &nbsp; <a id="cancelStore" class="btn btn-sm btn-purple"> <i
						class="glyphicon glyphicon-inbox"></i> 取消收藏
					</a>
					&nbsp; &nbsp; &nbsp; <a id="analysisId" class="btn btn-sm btn-purple"> <i
						class="glyphicon glyphicon-inbox"></i> 多维分析
					</a>
					&nbsp; &nbsp; &nbsp; <a id="detailQueryId" class="btn btn-sm btn-purple"> <i
						class="glyphicon glyphicon-inbox"></i> 明细查询
					</a>
				</div>
			</div>
		</form>
	</div>
</div>
<%@include file="../reportShows/reportShows.jsp"%>
</div>
<script src="${res}/js/chosen.jquery.min.js"></script>
<link rel="stylesheet" href="${res}/css/chosen.css" type="text/css"></link>
<script type="text/javascript">
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	ace.load_ajax_scripts([], function() {
		loadOrg();
		loadApk();
	});
	$("#query").click(function() {
		var orgsId = $('#orgsId').val();
		var apksId = $('#apksId').val();
	    var orgsId=$('#orgsId').val();
		var orgId="";
		var orgCodeId=$('#orgCodeId').val();
		var date = new Date();
		date.setDate(date.getDate() + 10);
		document.cookie = "orgId="+orgsId+";expires=" + date.toGMTString();
		document.cookie = "apksId="+apksId+";expires=" + date.toGMTString();
		if(orgCodeId=='10001'){
		   orgId = $('#orgsId').val();
		var orgs = new Array();
		if (orgsId == null) {
			orgs = "";
		} else {
			for (var i = 0; i < orgsId.length; i++) {
				//orgs[i]="<"+orgsId[i]+">";
				orgs[i] = orgsId[i];
			}
		}
		var apks = new Array();
		if (apksId == null) {
			apks = "";
		} else {
			for (var i = 0; i < apksId.length; i++) {
				apks[i] = "<"+apksId[i]+">";
			}
		}
		utils.openReport("${menu.url}",null,null,orgId,apks);
		}else{
		   if($('#orgsId').val()!=null&&$('#orgsId').val()!=''){
		   orgId = $('#orgsId').val();
		   var orgs = new Array();
		if(orgsId == null){
			orgs= "";
		}else{
			for(var i =0;i<orgsId.length;i++){
				//orgs[i]="<"+orgsId[i]+">";
				orgs[i]=orgsId[i];
			}
		}
		var apksId=$('#apksId').val();
		var apks = new Array();
		if(apksId == null){
			apks= "";
		}else{
			for(var i =0;i<apksId.length;i++){
				apks[i]="<"+apksId[i]+">";
			}
		}
		utils.openReport("${menu.url}",null,null,orgId,apks);
		   }else{
		   $.post("${ctx}/bizReport/loadOrgDeptsData",function(r,status){
		     if (status == "success") {
                  $.each(r,function(i,item){
                  orgId=orgId+item.id+',';
                  });
                  orgId=orgId.substring(0,orgId.length-1);
                 var orgs = new Array();
		if(orgsId == null){
			orgs= "";
		}else{
			for(var i =0;i<orgsId.length;i++){
				//orgs[i]="<"+orgsId[i]+">";
				orgs[i]=orgsId[i];
			}
		}
		var apksId=$('#apksId').val();
		var apks = new Array();
		if(apksId == null){
			apks= "";
		}else{
			for(var i =0;i<apksId.length;i++){
				apks[i]="<"+apksId[i]+">";
			}
		}
		utils.openReport("${menu.url}",null,null,orgId,apks);
		     }
		   });
		      }    
		}
	});
	//统计机构
	function loadOrg() {
		$.post("${ctx}/bizReport/loadOrgDeptsData", function(orgr, status) {
			if (status == "success") {
				var orgids = [];
				var selectedIds = [];
				var arrStr = document.cookie.split(";");
				for(var i=0; i<arrStr.length; i++){
					var temp = arrStr[i].split("=");
					if(temp[0].trim() == "orgId"){
						var arr = temp[1].split(",");
						for(var j=0; j<arr.length; j++){
							selectedIds.push(arr[j]);
						}
					}
				}
				$.each(orgr, function(i, orgitem) {
					$(
							"<option value='"+orgitem.id+"'>" + orgitem.desc
									+ "</option>").appendTo(
							".ch-orgselect optgroup");
					orgids.push(orgitem.id);
				});
				$("#orgsId").val(selectedIds);
				var config = {
					'.ch-orgselect' : {},
					'.ch-select-deselect' : {
						allow_single_deselect : true
					},
					'.ch-select-no-single' : {
						disable_search_threshold : 10
					},
					'.ch-select-no-results' : {
						no_results_text : 'Oops, nothing found!'
					},
					'.ch-select-width' : {
						width : "100%"
					}
				};
				for ( var selector in config) {
					$(selector).chosen(config[selector]);
				}
				$('.ch-orgselect').addClass('tag-input-style');
				//全选
				$("#orgs_all-select").click(function() {
					$("#orgsId").val(orgids);
					$("#orgsId").trigger("chosen:updated");
				});
				//全不选
				$("#orgs_del-select").click(function() {
					$("#orgsId").val([]);
					$("#orgsId").trigger("chosen:updated");
				});
				//重置
				$("#reset").click(function() {
					$("#orgsId").val([]);
					$("#orgsId").trigger("chosen:updated");
				});
			}
		}, "json");
	}
	//统计机构(不含工作组)
	$.post("${ctx}/bizReport/loadOrgDeptsDataExcTeam", function(r, status) {
		if (status == "success") {
			var ids = [];
			var $$premLevel = ${user.premLevel};
			if ($$premLevel == '1') {
// 				$("<option value='' selected>全部</option>").appendTo(".chorg-select");
			}
			var selectedIds = [];
			var arrStr = document.cookie.split(";");
			for(var i=0; i<arrStr.length; i++){
				var temp = arrStr[i].split("=");
				if(temp[0].trim() == "orgId"){
					var arr = temp[1].split(",");
					for(var j=0; j<arr.length; j++){
						selectedIds.push(arr[j]);
					}
				}else if(temp[0].trim() == "startDate"){
					$("#startDate").val(temp[1]);
				}else if(temp[0].trim() == "endDate"){
					$("#endDate").val(temp[1]);
				}else if(temp[0].trim() == "year"){
					$("#year").val(temp[1]);
				}
			}
			$.each(r, function(i, item) {
				/* $("<option value='"+item.id+"'>" + item.desc + "</option>")
						.appendTo(".chorg-select"); */
				ids.push(item.id);
			});
			$("#orgsId").val(selectedIds);
			var config = {
				'.chorg-select' : {},
				'.chorg-select-deselect' : {
					allow_single_deselect : true
				},
				'.chorg-select-no-single' : {
					disable_search_threshold : 10
				},
				'.chorg-select-no-results' : {
					no_results_text : 'Oops, nothing found!'
				},
				'.chorg-select-width' : {
					width : "100%"
				}
			}
			for ( var selector in config) {
// 				$(selector).chosen(config[selector]);
			}
			$('.chorg-select').addClass('tag-input-style');
			//全选(不含工作组)
			$("#all-select-excTeam").click(function() {
				$("#orgsId").val(ids);
				$("#orgsId").trigger("chosen:updated");
			})
			//全不选
			$("#del-select").click(function() {
				$("#orgsId").val([]);
				$("#orgsId").trigger("chosen:updated");
			})
		}
	}, "json");
	
	function loadApk() {
		$.post("${ctx}/bizReport/loadApksData", function(apkr, status) {
			if (status == "success") {
				var apkids = [];
				var apksIds = [];
				var arrStr = document.cookie.split(";");
				for(var i=0; i<arrStr.length; i++){
					var temp = arrStr[i].split("=");
					if(temp[0].trim() == "apksId"){
						var arr = temp[1].split(",");
						for(var j=0; j<arr.length; j++){
							apksIds.push(arr[j]);
						}
					}
				}
				$.each(apkr, function(i, apkitem) {
					$(
							"<option value='"+apkitem.id+"'>" + apkitem.name
									+ "</option>").appendTo(
							".ch-apkselect optgroup");
					apkids.push(apkitem.id);
				});
				$("#apksId").val(apksIds);
				var config = {
					'.ch-apkselect' : {},
					'.ch-select-deselect' : {
						allow_single_deselect : true
					},
					'.ch-select-no-single' : {
						disable_search_threshold : 10
					},
					'.ch-select-no-results' : {
						no_results_text : 'Oops, nothing found!'
					},
					'.ch-select-width' : {
						width : "100%"
					}
				}
				for ( var selector in config) {
					$(selector).chosen(config[selector]);
				}
				$('.ch-apkselect').addClass('tag-input-style');
				//全选
				$("#apks_all-select").click(function() {
					$("#apksId").val(apkids);
					$("#apksId").trigger("chosen:updated");
				})
				//全不选
				$("#apks_del-select").click(function() {
					$("#apksId").val([]);
					$("#apksId").trigger("chosen:updated");
				})
				//重置
				$("#reset").click(function() {
					$("#apksId").val([]);
					$("#apksId").trigger("chosen:updated");
				});
			}
		}, "json");
	};
</script>