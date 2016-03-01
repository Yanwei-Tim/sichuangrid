TQ.lowerEvaluateResultList = function (dfop){
	function totalScoreFormatter(el,options,rowData){
		if(rowData["totalScore"] == "" || rowData["totalScore"] == null){
			return "暂无评分结果";
		}
		return rowData["totalScore"]
	}
	function evaluateResultFormatter(el,options,rowData){
		if(rowData.evaluateResult == null || rowData.evaluateResult == ""){
			return "无";
		}
		return rowData.evaluateResult;
	}
	function reloadGrid(){
		$("#evaluateList").setGridParam({
			url:PATH+"/evaluate/evaluateManage/findAllLowerEvaluateResultList.action",
			datatype: "json",
			page:1
		});
		$("#evaluateList").setPostData({
			"evaluate.organization.id":dfop.organizationId,
			"evaluate.year":$('#selectYear option:selected').text()
	   	});
		$("#evaluateList").trigger("reloadGrid");
	}
	function stateFormatter(state){
		if(state == 2){
			return "已上报";
		}else{
			return "未上报";
		}
	}
	$(document).ready(function(){
		var obj = new Date();
		var thisYear = obj.getFullYear();
		$("#selectYear").val(thisYear);
		$("#selectYear").change(function(){
			$("#selectState").val("0");
			reloadGrid();
		});
		$("#selectState").change(function(){
			var state = $("#selectState").val();
			$("#evaluateList").setPostData(
					{"evaluate.organization.id":dfop.organizationId,
					  "state" : $("#selectState").val(),
					  "evaluate.year" : $("#selectYear").val()});
			$("#evaluateList").trigger("reloadGrid");
		});
		$("#urge").click(function(){
			var rowIds = $("#evaluateList").getGridParam('selarrrow');
			if(rowIds.length == 0){
				$.messageBox({message:"请选择记录!",level:"warn"});
				return;
			}
			var isReport = false;
			for(var i = 0 ; i < rowIds.length ; i ++){
				var rowData = $("#evaluateList").getRowData(rowIds[i]);
				if(rowData.state == "已上报"){
					isReport = true;
				}
			}
			if(isReport){
				$.messageBox({message:"已上报单位不需要催报!",level:"warn"});
				return;
			}
			var str="";
			var j = 0;
			for(var i = 0 ; i < rowIds.length ; i ++){
				if(rowIds[i] == "" || rowIds[i] == null){
					j++;
					continue;
				}
				var rowData = $("#evaluateList").getRowData(rowIds[i]);
				if(i == j){
					str = str + "?checkBoxSelect="+rowData["organization.id"];
					str = str + "&evaluateIds="+rowData.id;
					continue;
				}else{
					str = str + "&checkBoxSelect="+rowData["organization.id"];
					str = str + "&evaluateIds="+rowData.id;
				}
			}
			$.confirm({
		 		level: "info",//TODO 确认 warn 警告,alert,info
			    message: "确定对选定未上报部门进行催报么？",
			    title:"催报",
			    okFunc: function(){
					$.ajax({
						url:PATH+"/evaluate/evaluateManage/urgeEvaluate.action"+str,
						success:function(data){
							$.messageBox({message:"进行催报成功!"});
							$("#evaluateList").trigger("reloadGrid");
				        }
					});
				},
			    cancelFunc:false,
			    cancelBtnName:"取消",
			    okbtnName :"确定"
			});
		})
		$("#evaluateList").jqGridFunction({
			url:PATH+"/evaluate/evaluateManage/findAllLowerEvaluateResultList.action",
			postData:{"evaluate.organization.id":dfop.organizationId,
					  "evaluate.year":$("#selectYear").val()},
			datatype: "json",
			colModel:[
		    	{name:"id", index:"id", hidden:true,sortable:false},
		    	{name:"organization.orgName" ,label:"机构名称" , width:30 , align:"center",sortable:true},
		    	{name:"organization.id",hidden:true,sortable:true},
	  			{name:"title", index:"title", label:"标题" , width:30, align: "center" ,sortable:true},
	  			{name:"templateTotalScore", label:"模板分" , width:30 , index:"templateTotalScore" , align : "center",sortable:true},
	  			{name:"totalScore", label:"考核成绩",index:"totalScore",width:30,formatter: totalScoreFormatter , align : "center",sortable:true},
	  			{name:"evaluateResult",label:"考核等级",width:20,index:"evaluateResult",formatter: evaluateResultFormatter ,align : "center",sortable:true},
		    	{name:"state" , label:"考核状态",width:15,formatter: stateFormatter ,align : "center",sortable:true}
		  	],
		  	multiselect:true,
			shrinkToFit:true,
			showColModelButton:false,
			loadComplete:function(){$("input[name='checkBoxSelect']").click(function(){
					i = 0 
					$("input[name='checkBoxSelect']").each(function(){
						if($(this).attr("checked") == true){
							//$("#urge").buttonEnable();
							i++;
						}
					})
					if(i == 0){
						//$("#urge").buttonDisable();
					}	
				})
			}
		});
		
	});
}