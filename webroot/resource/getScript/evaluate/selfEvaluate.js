TQ.selfEvaluate = function (dfop){
	function toggleButtons(){
		if(dfop.evaluateState==dfop.evaluateStateValue){
			$("#scoreReport").buttonDisable();
			$("#selfScore").buttonDisable();
			return;
		}else{
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			var rowData = $("#itemsList").getRowData(rowId);
			if($("#evaluateState").val()>=dfop.evaluateStateValue || rowId==undefined || rowData.treelevel == undefined || !(validateScoreButton(rowData.id))){
				$("#selfScore").buttonDisable();
			}else{
				$("#selfScore").buttonEnable();
			}
		}
	}
	
	function selectRow(){
		//toggleButtons();
	}
	function viewDailyLogInfo(rowid,dailyDirectoryId){
		if(rowid==null){
	 		return;
		}
	$.post(PATH+"/evaluate/evaluateManage/dailogSize.action?workingRecord.id="+rowid+"&workingRecord.dailyDirectory.id="+dailyDirectoryId+"&evaluate.organization.id="+dfop.organizationId,
			null,function(data){
		if(data != null) {
			var dataJson = data.split('_');
			$("#evaluateDialog").createDialog({
				width:eval(dataJson[0]),
				height:eval(dataJson[1]),
				title:'查看台帐',
				modal : true,
				url:PATH+"/evaluate/evaluateManage/redirectForWorkRecord.action?workingRecord.id="+rowid+"&workingRecord.dailyDirectory.id="+dailyDirectoryId+"&evaluate.organization.id="+dfop.organizationId,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});

		}else{
		$.messageBox({
			message:'查看失败',
			level: "error"
		});
		}
		},'json');
	}
	
	function dialyLogTypesFormatter(el,options,rowData){
		var dialyLogTypesResult="";
		for(var i=0;i<rowData.dailyLogs.length&&i<3;i++){
			dialyLogTypesResult=dialyLogTypesResult+"<div style='clear:both'><a href='javascript:viewDailyLogInfo("+rowData.dailyLogs[i].id+","+rowData.dailyLogs[i].dailyDirectory.id+")'>"+rowData.dailyLogs[i].name+"</a></div>";
		}
		if(rowData.dailyLogs.length>3){
			dialyLogTypesResult=dialyLogTypesResult+"<div style='clear:both'><a href='javascript:;' thisId='detailedRule_"+rowData.id+"' class='popUpMore' detailedRuleId='"+rowData.id+"'  dailyDirectoryId=''>更多</a></div>";
		}
		if(rowData.dailyLogs.length > 0){
			for(var i = 0 ; i < rowData.dailyLogs.length ; i ++){
				dialyLogTypesResult = dialyLogTypesResult +'<input type="hidden" name="fujian' + rowData.id + '" value="' + rowData.dailyLogs[i].dailyLogType+'_'+rowData.dailyLogs[i].id + '" />';
			}
		}
		return dialyLogTypesResult;
	}
	
	
	function isHaveScore(){
		if($("#selfAssessmentTotalScore").text() == ""){
			$("#selfAssessmentTotalScore").text(0);
		}
		if($("#templateTotalScore").text() == "" || $("#templateTotalScore").text()==null){
			$("#templateTotalScore").text(0);
		}
	}
	function isOverParent(){
		var rowData = $("#itemsList").getRowData($("#itemsList").getGridParam("selrow"));
		var parentScore = parseFloat($("#itemsList").getRowData(rowData.parentId).selfAssessmentScore);
		if(parentScore == 0 ){
			$.messageBox({message:"上层分数已经扣完!",level:"error"});
		}
	}
	

	
	$(document).ready(function(){
		var ss = dfop.evaluateId;
		$("#getEvaluatesTitle").val(ss);

		isHaveScore();
		$("#getEvaluateYear").val(dfop.evaluateYear)

		$("#getEvaluateYear").change(function(){
			getEvaluateYearChangeFun();
		});
		$("#getEvaluatesTitle").change(function(){
			getEvaluatesTitleChangeFun();
		});
		$("#selfScore").click(function(){
			selfScoreFun();
		});
		$("#scoreReport").click(function(){
			scoreReportFun();
		});
		function getEvaluateYearChangeFun(){
			$.ajax({
				async:false,
				url:PATH+"/evaluate/evaluateManage/findSelfEvaluatesByOrgIdAndYearAndState.action",
				data:{
					"evaluate.year" : $("#getEvaluateYear").val(),
					"evaluate.organization.id" : dfop.organizationId
				},
				success:function(responseData){
					$("#getEvaluatesTitle").empty();
					if(responseData == null){
						$("#itemsList").setPostData({"evaluate.id":""});
						$("#itemsList").trigger("reloadGrid");
						return;
					}
					for(var i = 0 ; i < responseData.length ; i ++){
						if(i == 0){
							$("#templateTotalScore").text(responseData[i].templateTotalScore);
						}
						var title = responseData[i].title;
					 	var id = responseData[i].id;
					 	var str = '<option value=' + id + '>' + title + '</option>';
					 	$("#getEvaluatesTitle").append(str);
					}
					if($("#getEvaluatesTitle").val() == null || $("#getEvaluatesTitle").val() == ""){
						$("#itemsList").setPostData({"evaluate.id":""});
						$("#itemsList").trigger("reloadGrid");
					}else{
						$("#itemsList").setPostData({"evaluate.id":$("#getEvaluatesTitle").val()});
						$("#itemsList").trigger("reloadGrid");
					}
					if($("#getEvaluatesTitle").val() == "" || $("#getEvaluatesTitle").val() == null){
						$("#templateTotalScore").text(0)
						$("#selfAssessmentTotalScore").text(0)
					}
				}
			});
			$.ajax({
				async:false,
				url:PATH+'/evaluate/evaluateManage/getCountSelfAssessmentScore.action',
				data:{"evaluate.id":$("#getEvaluatesTitle").val()},
				success:function(data){
					if(data){
						//$("#scoreReport").buttonEnable();
					}else{
						//$("#scoreReport").buttonDisable();
					}
				}
			});
		}
		function getEvaluatesTitleChangeFun(){
			$("#itemsList").setPostData({"evaluate.id":$("#getEvaluatesTitle").val()});
			$("#itemsList").trigger("reloadGrid");
			$.ajax({
				async:false,
				url:PATH+'/evaluate/evaluateManage/getCountSelfAssessmentScore.action',
				data:{"evaluate.id":$("#getEvaluatesTitle").val()},
				success:function(data){
					if(data){
						//$("#scoreReport").buttonEnable();
					}else{
						//$("#scoreReport").buttonDisable();
					}
				}
			});
		}
		function selfScoreFun(){
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			if(rowId==undefined || rowId=="" || rowId==null){
				$.messageBox({message:"请选择具体细则进行自评！",level:"warn"});
				return;
			}
			var rowData;
			if("" == rowId || null == rowId){
				var trs = $("#itemsList").find("tr");
				for(var i=1; i<trs.length; i++){
					if(validateScoreButton($(trs[i]).attr("id"))){
						$("#"+$(trs[i]).attr("id")).click();
						rowId = $(trs[i]).attr("id");
						break;
					}
				}
			}else{
				rowData = $("#itemsList").getRowData(rowId);
			}
			if(rowId==undefined || rowId=="" || !(validateScoreButton(rowId))){
				$.messageBox({message:"请选择具体细则进行自评！",level:"warn"});
				return;
			}
			$("#evaluateDialog").createDialog({
				width:500,
				height:400,
				title:"自评",
				url:'/evaluate/maintainSelfEvaluateDlg.jsp?detailedRuleId='+rowId,
				buttons: {
					"上一条" : function(event){
						previousDetailedRule($("#itemsList").getGridParam("selrow"));
		   			},
					"保存并继续" : function(event){
						$("#maintainForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
		function scoreReportFun(){
			if(dfop.nullEvaluate=='true'){
				return ;
			}
			$.ajax({
				async:false,
				url:PATH+'/evaluate/evaluateManage/getCountSelfAssessmentScore.action',
				data:{"evaluate.id":$("#getEvaluatesTitle").val()},
				success:function(data){
					if(data){
						$.confirm({
							title:"确认上报",
							message:"该自评结果上报后将不能修改，确认上报？",
							width: 300,
							okFunc: scoreReportOkFun
						});
					}else{
						$.messageBox({ message:"没有可上报的自评",level:"warn"});
					}
				}
			});

		}
		function scoreReportOkFun(){
			$.ajax({
				url:PATH+'/evaluate/evaluateManage/reportSelfAssessmentResult.action?evaluate.id='+$("#getEvaluatesTitle").val(),
				data:{
					"evaluate.selfAssessmentTotalScore" : function(){return $("#selfAssessmentTotalScore").text()}
				},
				success:function(data){
					if(data == true){
						$.messageBox({ message:"自评上报成功"});
						var curMenu=$("#accordion a.cur");
						var baseUrl=curMenu.attr("baseUrl");
						var leaderUrl="";
						baseLoad(curMenu,baseUrl,leaderUrl);
						return;
					}
					$.messageBox({message:data,level: "error"});
		        }
			});
		}
		var width = screen.width-$(".ui-layout-west").outerWidth()-$(".center-left").outerWidth()-24;
		if ($.browser.msie) {
			width = screen.width-$(".ui-layout-west").outerWidth()-$(".center-left").outerWidth()-43;
			if($.browser.version=="7.0"){
				width = screen.width-$(".ui-layout-west").outerWidth()-$(".center-left").outerWidth()-30;
			}
			if($.browser.version=="6.0"){
				width = screen.width-$(".ui-layout-west").outerWidth()-$(".center-left").outerWidth()-16;
			}
		}
		var height = $(".ui-layout-west").outerHeight()-$(".path:visible").outerHeight()-$("#nav:visible").outerHeight()-$(".submenu:visible").outerHeight()-75;
		$("#itemsList").jqTreeGrid({
		   	 url: PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action",
		   	 postData: {"evaluate.id":dfop.evaluateId+""},
			 height : height,
			 width : width,
		   	 colModel:[
		   		{name:"content",label:"考核评估细则",sortable:true},
		   		{name:"standardScore",label:"标准分",width:20,sortable:true,align:'center'},
		   		{name:"selfAssessmentScore",label:"自评分",width:20,sortable:true,align:'center'},
		   		{name:"summary",label:"自评说明",width:60,sortable:true},
		   		{name:"id", index:"id", hidden:true, key:true,sortable:true}
		   	 ],
		   	 treeReader : {
		   	   	level_field: "treelevel",
				parent_id_field: "parentId",
				leaf_field: "leaf",
				expanded_field:"expanded"
		   	 },
		   	 caption:'<div id="scoreShow"><strong>标准总分：<span id="templateTotalScore">'+dfop.templateTotalScoreValue+'</span></strong><strong>自评总分：<span id="selfAssessmentTotalScore"></span></strong></div>',

			 loadComplete:function(){
				updateTotalSelfAssessmentScore();
				if(selectedRowId!=null && selectedRowId!="")
					$("#itemsList").setSelection(selectedRowId);

				if($("#getEvaluatesTitle").val()!=null){
					$.ajax({
						url:PATH+"/evaluate/evaluateManage/getEvaluateById.action?evaluate.id=" + $("#getEvaluatesTitle").val(),
						success:function(data){
							$("#templateTotalScore").text(data.templateTotalScore);
						}
					});
				}
				$.each($(".popUpMore"),function(i,n){
					$.ajax({
						url:PATH+"/evaluate/detailedRuleManage/findDailyLogsByDetailedRuleId.action?detailedRule.id="+$(n).attr("detailedRuleId"),
						success:function(data){
							var popMoreData=[];
							if(data != null && data.length > 0){

								for(var j=0; j<data.length; j++){
									popMoreData[j]={dailyDirectoryid:data[j].dailyDirectory.id,id:data[j].id, url:'#' , text:data[j].name, clickFun:function(id){viewDailyLogInfo($(this).attr("thisId"), $(this).attr("dailyDirectoryid"));}};
								}
								$(n).popUpMore({data: popMoreData,openNew:false});
							}
						}
					});
				});
			 },
			 onSelectRow:selectRow
	   });
	});
	
	function reloadGrid(){
		$("#itemsList").setGridParam({
			url: PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action",
			postData: {
				"evaluate.id":dfop.evaluateId
			}
		});
		$("#itemsList").trigger("reloadGrid");
	}
	
}