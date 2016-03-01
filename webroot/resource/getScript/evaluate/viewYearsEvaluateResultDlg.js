TQ.viewYearsEvaluateResultDlg = function (dfop){
	function getTreeGrid(){
		$("#itemsList").jqTreeGrid({
		   	 url: PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action?evaluate.id="+dfop.evaluateId+" ",
			 height : 350,
			 width : 820,
		   	 colModel:[
		   		{name:"content",width:80,label:"考核评估细则",sortable:false},
		   		{name:"standardScore",label:"标准分",align:"center",width:20,sortable:false},
		   		{name:"selfAssessmentScore",label:"自评分",width:20,align:"center",sortable:false},
		   		{name:"score",label:"考核评分",align:"center",width:20},
		   		{name:"scoreReason",label:"评分依据",width:20,sortable:false},
		   		{name:"id", width:0	,key:true,sortable:false,hidden:true}
		   	 ],
		   	 treeReader : {
		   	   	level_field: "treelevel",
				parent_id_field: "parentId",
				leaf_field: "leaf",
				expanded_field:"expanded"
		   	 },
			loadComplete:function(){
				calculateTotalScore();
				$("#score1").val($("#standardEvaluateTotalScore").text());
				$("#score8").val(0);

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
			}
		});
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
	
	function viewDailyLogInfo(rowid,dailyDirectoryId){
		if(rowid==null){
	 		return;
		}
		$.post(PATH+"/evaluate/evaluateManage/dailogSize.action?workingRecord.id="+rowid+"&workingRecord.dailyDirectory.id="+dailyDirectoryId+"&evaluate.organization.id="+dfop.organizationId+" ",
				null,function(data){
			if(data != null) {
				var dataJson = data.split('_');
		$("#evaluateResultDailog").createDialog({
			width:eval(dataJson[0]),
			height:eval(dataJson[1]),
			title:'查看台帐',
			modal : true,
			url:PATH+"/evaluate/evaluateManage/redirectForWorkRecord.action?workingRecord.id="+rowid+"&workingRecord.dailyDirectory.id="+dailyDirectoryId+"&evaluate.organization.id="+dfop.organizationId+" ",
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
	
	function calculateTotalScore(){
		var totalScore=0;
		var evaluateScore=0;
		var selfEvaluateScore=0;
		var checkboxs=$("#itemsList tr.ui-row-ltr");
		checkboxs.each(function(i,n){
			if($(this).attr("id")){
				var rowData = $("#itemsList").getRowData($(this).attr("id"));
				if(rowData.treelevel==1 || $(rowData.treelevel).text()==1){
					if("" != rowData.standardScore && null != rowData.standardScore){
						totalScore=totalScore+parseFloat(rowData.standardScore);
						if("" !=rowData.score && null != rowData.score)
							evaluateScore=evaluateScore + parseFloat(rowData.score);
						if("" !=rowData.selfAssessmentScore && null != rowData.selfAssessmentScore)
							selfEvaluateScore = selfEvaluateScore + parseFloat(rowData.selfAssessmentScore)
					}
				}
			}
		})
		$("#standardEvaluateTotalScore").text(totalScore);
		$("#scoreEvaluateTotalScore").text(evaluateScore);
		$("#selfEvaluateTotalScore").text(selfEvaluateScore);
	}
	$(document).ready(function(){
		getTreeGrid();
	});
}