<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../commons/res/taglibs.jsp"%>
<%@ include file="../commons/res/_ctx.jsp" %>
<div id="reportDetail" class="modal fade bs-example-modal-lg"
	data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="form-horizontal">
				<div class="modal-header">
					<button id="close-detail" type="button" class="close"
						data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class="glyphicon glyphicon-book"></i> 指标说明
					</h4>
				</div>
				</br> <input id="functionId" type="hidden" value="">
				<div style="border: 2px">
					&nbsp;&nbsp;&nbsp;<label>报表说明：</label>
					<div>
						<p style="margin-left:40px;margin-right:40px">
							<span style="width: 65%">
								<textarea id="indexShows" name="indexShows" style="width: 100%;height:150px" readonly="readonly"></textarea>
							</span>
						</p>
					</div>
					<!-- <textarea id="indexShows" name="indexShows" readonly="readonly"
						style="width: 70%"> </textarea> -->
				</div>
				<!-- begin -->
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								&nbsp;&nbsp;&nbsp;<label>指标信息：</label>
<!-- 								<div id="scrollId" class="col-xs-12" style="height:350px;overflow-x:hidden!important;overflow-y:auto!important; position:relative;top:0px!important;left:0px;"> -->
									<table id="index-tbl" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th width="10%">序号</th>
												<th width="30%">指标名称</th>
												<th width="60%">指标含义</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
<!-- 								</div> -->
							</div>
						</div>
					</div>
				</div>
				<div style="height:80px"></div>
				<!-- end -->
			</div>
		</div>
	</div>
	<input id="analysisUrl" type="hidden" name="analysisUrl" value="${menu.analysisUrl}" >
	<input id="queryUrl" type="hidden" name="queryUrl" value="${menu.queryUrl}" >
</div>
<script type="text/javascript">
	var href = document.location.href;
	var arr = href.split("=");
	for(var i=0; i <arr.length; i++){
		if(i==arr.length-1){
			$("#functionId").val(arr[i]);
		}
	}
	seajs.use("${my}/js/reportShows/main", function(main) {
		main.init();
	});
</script>