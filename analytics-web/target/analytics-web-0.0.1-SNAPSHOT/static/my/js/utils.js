(function(window,document,$){
	var utils={
		open:function(url){
			//console.log(url);
			window.open(url,"newwindow","height=600,width=1200,top=50,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no");
		},
		openReport:function(searchObj,startDate,endDate,orgCd,pkgCd,year,month,fundIds){
			var userName = $("#userNameOfCognos").val();
			var password = $("#pswOfCognos").val();
			var url=$$cognos + "/ibmcognos/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/launch.xts&ui.tool=CognosViewer&CAMUsername="+ userName +"&CAMPassword="+password+"&CAMNamespace=unirpt&ui.action=run&run.prompt=false";
			if(!searchObj){
				console.log("searchObj is null");
			}
			this.userLog(searchObj);
			var params=[];
			params.push(url);
			params.push("ui=h2m1");
			params.push("ui.object="+searchObj);
			if(this.isNotNull(startDate)){
				params.push("p_PdateB="+startDate);
			}
			if(this.isNotNull(endDate)){
				params.push("p_PdateE="+endDate);
			}
			if(this.isNotNull(orgCd)){
				params.push("p_Porgcd="+orgCd);
			}
			if(this.isNotNull(pkgCd)){
				params.push("p_Ppkgcd="+pkgCd);
			}
			if(this.isNotNull(fundIds)){
				params.push("p_cashsource="+fundIds);
			}
			if(this.isNotNull(year)){
				params.push("p_Pyear="+year);
			}
			if(this.isNotNull(month)){
				params.push("p_Pmonth="+month);
			}
			this.open(params.join("&"));
		},
		openReportOfSingleOrg:function(searchObj,startDate,endDate,orgCd){
			var userName = $("#userNameOfCognos").val();
			var password = $("#pswOfCognos").val();
			var url=$$cognos + "/ibmcognos/cgi-bin/cognos.cgi?b_action=xts.run&m=portal/launch.xts&ui.tool=CognosViewer&CAMUsername="+ userName +"&CAMPassword="+password+"&CAMNamespace=unirpt&ui.action=run&run.prompt=false";
			if(!searchObj){
				console.log("searchObj is null");
			}
			var params=[];
			params.push(url);
			params.push("ui=h2m1");
			params.push("ui.object="+searchObj);
			if(startDate){
				params.push("p_PdateB="+startDate);
			}
			if(endDate){
				params.push("p_PdateE="+endDate);
			}
			if(orgCd){
				params.push("p_Porgcd="+orgCd);
			}
			this.open(params.join("&"));
		},
		userLog:function(searchObj){
			var data={"searchObj":searchObj};
			$.post($$ctx+"/bizReport/log",data);
		},isNotNull:function(val){
			return val!=null||val!=undefined;
		},
		//待办统计数字
		todoListToltalNum:function(){
			//var todoListToltalNum = $('#todoListToltalNum', window.opener.document).val();
			$.post($$ctx + "/todoList/findTodoListToltalNum",function(r_data){
				console.log($('#todoListToltalNum', window.opener.document));
				$('#todoListToltalNum', window.opener.document).html(r_data);
				console.log($('#todoListToltalNum', window.opener.document).html());
			})
		}
	}
	window.utils=utils;
})(window,document,jQuery);