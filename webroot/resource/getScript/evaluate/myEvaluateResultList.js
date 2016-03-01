TQ.myEvaluateResultList = function (dfop){

	function totalScoreFormatter(el,options,rowData){
		if(rowData["totalScore"]==null){
			return "";
		}
		if(rowData["evaluateResult"]==null &&　rowData["totalScore"]!=null){
			return rowData["totalScore"];
		}
	    return rowData["evaluateResult"]+"("+rowData["totalScore"]+")";
	}
	function searchEvaluateList(){
		var evaluateYear=$("#evaluateYear").val();
		var evaluateTitle=$("#evaluateTitle").val();
		$("#evaluateList").setPostData({
			"evaluate.year":evaluateYear,
			"evaluate.title":evaluateTitle,
			"evaluate.organization.id":dfop.organizationId
		});
		$("#evaluateList").trigger("reloadGrid");
	}
	$(document).ready(function(){
		var obj = new Date();
		var thisYear = obj.getFullYear();
		$("#getEvaluateYear").val(thisYear);
		$("#evaluateList").jqGridFunction({
			url:PATH+"/evaluate/evaluateManage/myEvaluateResultList.action",
			postData:{"evaluate.organization.id":dfop.organizationId,
					  "evaluate.year" : $("#getEvaluateYear").val()},
			datatype: "json",
			colModel:[
		    	{name:"id", index:"id", hidden:true,sortable:false},
	  			{name:"title", index:"title", align:"center" , label:"标题",sortable:true},
	  			{name:"templateTotalScore" , index:"templateTotalScore" ,align:"center" , label:"标准分",sortable:true},
	  			{name:"totalScore", label:"考核成绩",index:"totalScore",align:"center",sortable:true},
	  			{name:"evaluateResult",label:"考核等级" , index:"evaluateResult" ,align:"center",sortable:true}
		  	],
			ondblClickRow:function (){
	    		if(dfop.viewDruggy=='true'){
	    			viewFun();
				}
			},
			shrinkToFit:true,
			showColModelButton:false
		});
		$("#getEvaluateYear").change(function(){
			$("#evaluateList").setPostData({
				"evaluate.organization.id":dfop.organizationId,
				"evaluate.year" : $("#getEvaluateYear").val()
			});
			$("#evaluateList").trigger("reloadGrid")
		});
		$("#view").click(function(event){
			viewFun();
		});
		$("#search").click(function(event){
			$("#evaluateDialog").createDialog({
				width: 400,
				height: 200,
				title:"我的考核查询-请输入查询条件",
				url:PATH+'/evaluate/searchEvaluateDlg.jsp?orgId='+dfop.orgId,
				buttons: {
					"查询" : function(event){
						searchEvaluateList();
		   				$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		$("#reload").click(function(event){
			$("#evaluateList").setPostData({"evaluate.organization.id":dfop.organizationId,
				  "evaluate.year" : $("#getEvaluateYear").val()});
			$("#evaluateList").trigger("reloadGrid");
		});
	});
	function viewFun(){
		var rowId = $("#evaluateList").getGridParam("selrow");
		if(rowId == null){
			$.messageBox({message:"请选择记录！",level : "warn"});
			return;
		}
		var evaluate =  $("#evaluateList").getRowData(rowId);
		$("#evaluateDialog").createDialog({
			width: 850,
			height: 550,
			title:"查看年度评估信息",
			url:PATH+'/evaluate/viewEvaluateResultDlg.jsp?id='+evaluate.id+'&title='+evaluate.title,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}

	function reloadGrid(){
		$("#evaluateList").setGridParam({
			url:PATH+"/evaluate/evaluateManage/evaluateResultList.action",
			postData: {
				"evaluate.organization.id":dfop.organizationId,
				"page":1
			}
		});
		$("#evaluateList").trigger("reloadGrid");
	}
}
