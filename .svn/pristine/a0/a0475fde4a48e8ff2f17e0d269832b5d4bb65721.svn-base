TQ.evaluateResultList = function (dfop){
	function totalScoreFormatter(el,options,rowData){
		if(rowData["totalScore"]==null){
			return "";
		}
		if(rowData["evaluateResult"]==null &&　rowData["totalScore"]!=null){
			return rowData["totalScore"];
		}
	    return rowData["evaluateResult"]+"("+rowData["totalScore"]+")";
	}
	$(document).ready(function(){
		$("#evaluateList").jqGridFunction({
			url:PATH+"/evaluate/evaluateManage/evaluateResultList.action",
			postData:{"evaluate.organization.id":$.getCurrentOrgId(),
					  "evaluate.year":$("#getEvaluateYear").val()+""
					  },
			datatype: "json",
			colModel:[
		    	{name:"id", index:"id", hidden:true,sortable:false},
	  			{name:"title", index:"title", label:"标题",sortable:true},
	  			{name:"totalScore", label:"考核成绩",index:"totalScore",formatter: totalScoreFormatter,sortable:true},
	  			{name:"isPigeonhole",sortable:false,index:"isPigeonhole", label:"归档", width:100,formatter:function(el,options,rowData){if(rowData.state==dfop.evaluateState){return "已归档";}else{return "未归档";}}}
		  	],
	    	ondblClickRow:function (){
	    		if(dfop.viewEvaluateHistory=='true'){
	    			doubleClickTable();
				}
			},
			//onSelectRow:selectRow,
			shrinkToFit:true,
			showColModelButton:false
		});
		$("#getEvaluateYear").change(function(event){
			var year = $("#getEvaluateYear").val()+"";
			if(null == year || "" == year){
				$("#evaluateList").setPostData({
					"evaluate.organization.id" : $.getCurrentOrgId()
				})
			}else{
				$("#evaluateList").setPostData({
					"evaluate.organization.id" : $.getCurrentOrgId(),
					"evaluate.year" : year
				})
			}
			$("#evaluateList").trigger("reloadGrid");
		});
		$("#view").click(function(event){
			var rowId = $("#evaluateList").getGridParam("selrow");
			if(rowId == null){
				$.messageBox({level:'warn',message:'没有选中任何记录！'});
				return;
			}
			var evaluate =  $("#evaluateList").getRowData(rowId);
			$("#evaluateDialog").createDialog({
				width: 850,
				height: 600,
				title:"查看年度评估信息",
				url:PATH+'/evaluate/viewYearsEvaluateResultDlg.jsp?id='+evaluate.id+'&title='+evaluate.title+'&totalScoreLevel=' + evaluate.totalScore +'&orgId='+$.getCurrentOrgId(),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		$("#search").click(function(event){
			$("#evaluateDialog").createDialog({
				width: 300,
				height: 200,
				title:"历年考核结果查询-请输入查询条件",
				url:PATH+'/evaluate/searchEvaluateDlg.jsp?orgId='+$.getCurrentOrgId(),
				buttons: {
					"查询" : function(event){
						refreshEvaluateList();
		   				$(this).dialog("close");
		   				//$("#view").buttonDisable();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		$("#reload").click(function(){
			$("#evaluateList").setPostData({
				  "evaluate.organization.id":$.getCurrentOrgId(),
				  "evaluate.year":prevYear+""})
			$("#evaluateList").trigger("reloadGrid");
			$("#getEvaluateYear").val(prevYear);
		});
		var obj = new Date();
		var prevYear = obj.getFullYear();
		$("#getEvaluateYear").val(prevYear);
		if($("#getEvaluateYear").val() != prevYear){
			$("#getEvaluateYear").val(prevYear + 1);
		}
		function refreshEvaluateList(){
			var evaluateYear=$("#evaluateYear").val();
			var evaluateTitle=$("#evaluateTitle").val();
			if(evaluateYear == "" && evaluateTitle == ""){
				evaluateYear =  $("#getEvaluateYear").val();
			}
			if(evaluateYear == "" && evaluateTitle != ""){
				evaluateYear = $("#getEvaluateYear").val();
			}
			$("#evaluateList").setPostData({
				"evaluate.year":evaluateYear,
				"evaluate.title":evaluateTitle,
				"evaluate.organization.id":$.getCurrentOrgId()
			});
			$("#evaluateList").trigger("reloadGrid");
			var isHave = false;
			$("option" , $("#getEvaluateYear")).each(function(){
				if($(this).text() == evaluateYear){
					isHave = true;
					$("#getEvaluateYear").val(evaluateYear);
					return;
				}
			});
			if(!isHave){
				$("#getEvaluateYear").val("");
			}
		}
		function doubleClickTable(){
			$("#view").click();
		}
	});
}