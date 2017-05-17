<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../commons/res/taglibs.jsp"%>

<style>
small {
	font-family: "Microsoft YaHei";
}
</style>
<style type="text/css">
	.t_dialog {
	width: 100%;
	display: none;
	}
	.fileInput{width:102px;height:34px; background:blue
	;}
	.upfile{}
	.upFileBtn{position:absolute;width:102px;height:34px;cursor:pointer;color:#fff;background:#428bca;border:none;}
</style>
<div class="ajax-content">
	<div class="page-header">
		<h1>
			资金拆借表 <small> <i class="ace-icon fa fa-angle-double-right"></i>资金拆借表</small>
			
		</h1>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<form action="${ctx}/updateDatas/uploadExcel" class="form-horizontal" id="reportForm" name="reportForm" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="name"> </label>
							<div class="col-sm-4">
								<div class="fileInput left">
						          <input type="file" multiple="" name="uploadFile" id="uploadFile" class="upfile" onchange="getPath()" style="position:absolute;left:47px;line-height:34px;filter:alpha(opacity=0); opacity:0"/>
						          <input class="upFileBtn" type="button" value="选择文件" onclick="document.getElementById('uploadFile').click()" />
						        </div>
								<label id="filenameOfliulan" style="position:absolute;left:115px;top:8px;background:#fff;z-index:999">
									<input type="hidden" name="file-format" id="id-file-format" class="ace" />
								</label>
							</div>
							<div class="col-sm-4">
								<button class="btn btn-sm btn-primary" type="button" role="uploadExcel">
									<i class="ace-icon fa fa-file-text"></i> 上传录入
								</button>
							</div>
						</div>
						<div class="clearfix form-actions">
							<!-- <div class="col-md-offset-4  col-md-6">
								<button class="btn btn-sm btn-primary" type="button" role="uploadExcel">
									<i class="ace-icon fa fa-file-text"></i> 上传文件
								</button>
								<button class="btn btn-sm btn-primary" type="button" role="importDatas">
									<i class="ace-icon fa fa-refresh"></i>更新录入
								</button>
								<button class="btn btn-sm btn-primary" type="button" role="exportExcel">
									<i class="ace-icon fa fa-cloud-download"></i> 导出数据
								</button>
							</div> -->
							<span class="my-button-group"> 
								<!-- <button class="btn btn-sm btn-primary" type="button" role="importDatas">
									<i class="ace-icon fa fa-refresh"></i>更新录入
								</button> -->
								<a id="btn-add" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-plus"></i>新增</a>
								<button class="btn btn-sm btn-primary" type="button" role="exportExcel">
									<i class="ace-icon fa fa-cloud-download"></i> 导出数据
								</button>
								
							</span>
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<table id="tbl" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>机构名称</th>
										<th>拆借金额</th>
										<th>拆借余额</th>
										<th>资金来源与运用</th>
										<th>融资方式</th>
										<th>操作时间</th>
										<th id="operate" width="8%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改 -->
	<div id="modify-user" class="modal" data-backdrop="static" tabindex="-1">
		<div class="modal-dialog" style="width:60%">
			<div class="modal-content" >
				<form id="user-form"  class="form-horizontal" name="user-form" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger">资金拆借表</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="rowNum" >序号</label>
									<div class="col-sm-4">
										<input type="text" id="rowNum" name="rowNum" class="form-control" readonly="readonly"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="orgName">机构名<span class="red">*</span></label>
									<div class="col-sm-4">
										<!-- <input type="text" id="orgName" name="orgName" class="form-control" /> -->
										<input type="hidden" id="orgId" value=""/>
										<select id="orgName" name="orgName" class="form-control" >
											<option value="10001">东方邦信</option>
											<option value="10002">东方资本</option>
											<option value="10003">东方金科</option>
											<c:forEach items="${boList }" var="bo">
												<option value="${bo.id }">${bo.shortDescription }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="lendForm">拆借形式<span class="red">*</span></label>
									<div class="col-sm-4">
										<!-- <input type="text" id="lendForm" name="lendForm" class="form-control" /> -->
										<select id="lendForm" name="lendForm" class="form-control" >
											<option value="拆入">拆入</option>
											<option value="拆出">拆出</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="lendAmt">拆借金额</label>
									<div class="col-sm-4">
										<input type="text" id="lendAmt" name="lendAmt" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="lengBalance">拆借余额<span class="red">*</span></label>
									<div class="col-sm-4">
										<input type="text" id="lengBalance" name="lengBalance" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="financingForm">融资方式<span class="red">*</span></label>
									<div class="col-sm-4">
										<input type="text" id="financingForm" name="financingForm" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="platformInstitution">平台机构</label>
									<div class="col-sm-4">
										<input type="text" id="platformInstitution" name="platformInstitution" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="cashSourceName">资金来源与运用<span class="red">*</span></label>
									<div class="col-sm-4">
										<input type="text" id="cashSourceName" name="cashSourceName" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="beginDate">起息日</label>
									<div class="col-sm-4">
										<!-- <input type="text" id="beginDate" name="beginDate" class="form-control" /> -->
										<input id="beginDate" class="datepicker" type="text" readonly="readonly" placeholder="请选择起息日期"> 
										<i class="ace-icon fa fa-calendar"></i>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="arriveDate">到期日<span class="red">*</span></label>
									<div class="col-sm-4">
										<!-- <input type="text" id="arriveDate" name="arriveDate" class="form-control" /> -->
										<input id="arriveDate" class="datepicker" type="text" readonly="readonly" placeholder="请选择到期日期"> 
										<i class="ace-icon fa fa-calendar"></i>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="contractTerm">期限</label>
									<div class="col-sm-4">
										<input type="text" id="contractTerm" name="contractTerm" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="contractRate">利率</label>
									<div class="col-sm-4">
										<input type="text" id="contractRate" name="contractRate" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="incomeExpense">预期收益与支出</label>
									<div class="col-sm-4">
										<input type="text" id="incomeExpense" name="incomeExpense" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="fundingSources">资金来源渠道</label>
									<div class="col-sm-4">
										<input type="text" id="fundingSources" name="fundingSources" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="guaranteeMode">担保方式</label>
									<div class="col-sm-4">
										<input type="text" id="guaranteeMode" name="guaranteeMode" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="guarantor">担保方</label>
									<div class="col-sm-4">
										<input type="text" id="guarantor" name="guarantor" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="memo">备注</label>
									<div class="col-sm-4">
										<input type="text" id="memo" name="memo" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<input type="hidden" id="operateTime" value=""/>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" role="btn-doEdit" class="btn btn-sm btn-primary">
							<i class="ace-icon fa fa-save"></i> 保存
						</button>
						<button name="btn-cancel" type="button" class="btn btn-sm" data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 新增 -->
	<div id="add-user" class="modal" data-backdrop="static" tabindex="-1">
		<div class="modal-dialog" style="width:60%">
			<div class="modal-content" >
				<form id="add-form"  class="form-horizontal" name="add-form" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger">资金拆借表</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="orgName_add">机构名<span class="red">*</span></label>
									<div class="col-sm-4">
										<select id="orgName_add" name="orgName" class="form-control" >
											<option value="10001">东方邦信</option>
											<option value="10002">东方资本</option>
											<option value="10003">东方金科</option>
											<c:forEach items="${boList }" var="bo">
												<option value="${bo.id }">${bo.shortDescription }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="lendForm_add">拆借形式<span class="red">*</span></label>
									<div class="col-sm-4">
										<select id="lendForm_add" name="lendForm" class="form-control" >
											<option value="拆入">拆入</option>
											<option value="拆出">拆出</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="lendAmt_add">拆借金额</label>
									<div class="col-sm-4">
										<input type="text" id="lendAmt_add" name="lendAmt" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="lengBalance_add">拆借余额<span class="red">*</span></label>
									<div class="col-sm-4">
										<input type="text" id="lengBalance_add" name="lengBalance" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="financingForm_add">融资方式<span class="red">*</span></label>
									<div class="col-sm-4">
										<input type="text" id="financingForm_add" name="financingForm" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="platformInstitution_add">平台机构</label>
									<div class="col-sm-4">
										<input type="text" id="platformInstitution_add" name="platformInstitution" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="cashSourceName_add">资金来源与运用<span class="red">*</span></label>
									<div class="col-sm-4">
										<input type="text" id="cashSourceName_add" name="cashSourceName" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="beginDate_add">起息日</label>
									<div class="col-sm-4">
										<!-- <input type="text" id="beginDate_add" name="beginDate" class="form-control" /> -->
										<input id="beginDate_add" class="datepicker" type="text" readonly="readonly" placeholder="请选择起息日期"> 
										<i class="ace-icon fa fa-calendar"></i>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="arriveDate_add">到期日<span class="red">*</span></label>
									<div class="col-sm-4">
										<!-- <input type="text" id="arriveDate_add" name="arriveDate" class="form-control" /> -->
										<input id="arriveDate_add" class="datepicker" type="text" name="arriveDate" readonly="readonly" placeholder="请选择到期日期"> 
										<i class="ace-icon fa fa-calendar"></i>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="contractTerm_add">期限</label>
									<div class="col-sm-4">
										<input type="text" id="contractTerm_add" name="contractTerm" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="contractRate_add">利率</label>
									<div class="col-sm-4">
										<input type="text" id="contractRate_add" name="contractRate" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="incomeExpense_add">预期收益与支出</label>
									<div class="col-sm-4">
										<input type="text" id="incomeExpense_add" name="incomeExpense" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="fundingSources_add">资金来源渠道</label>
									<div class="col-sm-4">
										<input type="text" id="fundingSources_add" name="fundingSources" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="guaranteeMode_add">担保方式</label>
									<div class="col-sm-4">
										<input type="text" id="guaranteeMode_add" name="guaranteeMode" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="guarantor_add">担保方</label>
									<div class="col-sm-4">
										<input type="text" id="guarantor_add" name="guarantor" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="memo_add">备注</label>
									<div class="col-sm-4">
										<input type="text" id="memo_add" name="memo" class="form-control" />
									</div>
								</div>
								
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" role="btn-doAdd" class="btn btn-sm btn-primary">
							<i class="ace-icon fa fa-save"></i> 保存
						</button>
						<button name="btn-cancel" type="button" class="btn btn-sm" data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="progressDiv" class="modal" data-backdrop="static" tabindex="-1">
		
	</div>
</div>

<script src="${res}/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${res}/js/date-time/bootstrap-datepicker-cn.js"></script>
<script src="${res}/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="${res}/js/date-time/daterangepicker.min.js"></script>
<script type="text/javascript">
	function getPath(){
		$(document).ready(function () {
			$('#filenameOfliulan').html($('#uploadFile').val());
		});
	}
</script>
<script type="text/javascript">
	ace.load_ajax_scripts([], function() {
	});
	seajs.use("${my}/js/updateDatas/main", function(main) {
		$(".datepicker").datepicker({
			minView : "month", //选择日期后，不会再跳转去选择时分秒 
			format : "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
			language : 'zh-CN', //汉化 
			autoclose : true
		//选择日期后自动关闭 
		});
		/* $("#arriveDate_add").datepicker({
			minView : "month", //选择日期后，不会再跳转去选择时分秒 
			format : "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
			language : 'zh-CN', //汉化 
			autoclose : true
		//选择日期后自动关闭 
		}); */
		main.init();
	});
</script>