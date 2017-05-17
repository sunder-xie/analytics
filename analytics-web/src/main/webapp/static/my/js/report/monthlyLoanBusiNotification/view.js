define(function(require, exports, module) {
	var model = require("./model");
	var view = Backbone.View.extend({
		el: "div.ajaxContent",
		initialize:function(){
			this.model=new model();
		},
		events:{
			'click #query'  : 'query',//生成word
			'click #reset' : 'reset',//重置
		},
		render:function(){
			this.initPageParams();
		},
		initPageParams: function(){
			//年
			var curYear = $('#curYear').val();
			$('#year').val(curYear);
			//月
			var curMonth = $('#curMonth').val();
			$('#month').val(curMonth);
		},
		query:function(){
			var viewSelf = this;
			$.ajax({
				type:'GET',
				url:$$ctx + "/monthlyLoanBusiNotification/searchByCondition",//程序生成word
				data:{
					year:$('#year').val(),
					month:$('#month').val(),
					orgId:$('#orgId').val()
				},
				success:function(result){
					if(result.data){
						//生成word另存为
						//window.location.href=$$ctx + "/riskMonthReport/download?outFileName="+result.data.outFileName;
						window.location.href=$$ctx + "/monthlyLoanBusiNotification/download?outFileName="+result.data.outFileName;
					}else{
						bootbox.alert("<span style='font-size:16px;'>文件未生成.</span>");
					}
				}
			});
		},
		reset:function(){
			$('#year').val([]);
			$('#month').val([]);
			$("#year").trigger("chosen:updated");
			$("#month").trigger("chosen:updated");
		}
		
	});
	module.exports = view;
})