TQ.evaluate = function (dfop){
	function viewDailyLogInfo(rowid,dailyDirectoryId){
		if(rowid==null){
	 		return;
		}
		$.post(PATH+"/evaluate/evaluateManage/dailogSize.action?workingRecord.id="+rowid+"&workingRecord.dailyDirectory.id="+dailyDirectoryId+"&evaluate.organization.id="+$.getCurrentOrgId(),
				null,function(data){
			if(data != null) {
				var dataJson = data.split('_');
		$("#evaluateResultDailog").createDialog({
			width:eval(dataJson[0]),
			height:eval(dataJson[1]),
			title:'查看台帐',
			modal : true,
			url:PATH+"/evaluate/evaluateManage/redirectForWorkRecord.action?workingRecord.id="+rowid+"&workingRecord.dailyDirectory.id="+dailyDirectoryId+"&evaluate.organization.id="+$.getCurrentOrgId(),
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
				dialyLogTypesResult = dialyLogTypesResult +'<input type="hidden" name="fujian' + rowData.id + '" value="' + rowData.dailyLogs[i].id + '" />';
			}
		}
		return dialyLogTypesResult;
	}
	
	var boo=false;
	function evaluateTotalScore(){
		if(firstEntry == 0){
			boo = true;
			firstEntry = firstEntry + 1;
			$("#evaluateResultForm").ajaxSubmit({
				success:function(data){
				}
			})
		}
		if(boo){
			var totalScore=0;
			$.each($("tr",$("#itemsList")),function(i,n){
				if($(n).attr("id")){
					var rowData = $("#itemsList").getRowData($(n).attr("id"));
					if(rowData.score!=null && rowData.score!="" && !isNaN(rowData.score) && (rowData.treelevel==1 || $(rowData.treelevel).text()==1)){
						totalScore = totalScore + parseFloat(rowData.score);
					}
				}
			});
			//模板总分减去totalScore;
			$("#evaluateTotalScore").text(totalScore);
			boo=false;
		}
	}
	
	
	function validateLeafScore(rowData){
		if((rowData.leaf && null == rowData.standardScore) || (rowData.leaf && "" == rowData.standardScore) || (rowData.leaf && "undefined" == rowData.standardScore)){
			return true;
		}
		return false;
	}
	function judgeSpecialCharacter(a, text){
		var str = "[@/'\"#$%&^*]+";
		var reg = new RegExp(str);
		if(reg.test(a))
		{
			$.messageBox({message:text+"，不能输入特殊字符!",level:"error"})
			return false;
		}
		return true;
	}
	function isHaveScore(){
		if($("#standardSum").text() == ""){
			$("#standardSum").text(0);
		}
		if($("#selfSum").text() == ""){
			$("#selfSum").text(0);
		}
		if($("#evaluateTotalScore").text() == ""){
			$("#evaluateTotalScore").text(0)
		}

	}

	function initOption(){
		$("#getEvaluatesTitle").append("<option>请选择目录</option>");
	}
	
	$(document).ready(function(){
		$("#itemsList").jqTreeGrid({
		   	 url: PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action",
			 postData:{"evaluate.id" : dfop.evaluateId + ""},
			 treeReader : {
				level_field: "treelevel",
				parent_id_field: "parentId",
				leaf_field: "leaf",
				expanded_field:"expanded"
			 },
			 caption:'<div id="scoreShow"><strong>标准总分：<span id="standardSum">'+dfop.templateTotalScore+'</span></strong><strong>自评总分：<span id="selfSum">'+dfop.selfAssessmentTotalScore+'</span></strong><strong>考核总分：<span id="evaluateTotalScore">'+dfop.totalScoreValue+'</span></strong></div>',
		   	 colModel:[
		   		{name:"content",label:"考核评估细则",sortable:true},
		   		{name:"standardScore",label:"标准分",width:20,sortable:true},
		   		{name:"score",width:20,hidden:true,sortable:true},
			   	{name:"selfAssessmentScore",label:"自评分",width:20,sortable:true},
		   		{name:"summary",label:"自评说明",width:20,sortable:true	},
			   	{name:"dialyLogTypes",label:"自评依据",width:20,sortable:true,formatter: dialyLogTypesFormatter},
		   		{name:"scoreInput",label:"评分",width:15,sortable:true
				   	,formatter:function(el,options,rowData){
			   		var score=rowData.score;
			   		if(!score){score="0";}
			   		if(validateLeafScore(rowData))
			   			if(rowData.isPigeonhole!=1){
				   			if(score == ""){
								return "<input style='width:30px' maxlength='6' name='scores' onkeydown='sumGrade()' value='"+0+"'/>"
						   	}else{
			   					return "<input style='width:30px' maxlength='6' name='scores' onkeydown='sumGrade()' value='"+score+"'/>";
						   	}
					   	}else{
							return score;
				   		}else return score;
			   		}
			   	},
			   	{name:"scoreReason",label:"评分依据",width:70,sortable:true
				   	,formatter:function(el,options,rowData){
				   		var scoreReason=rowData.scoreReason;
						if(!scoreReason){scoreReason =""}
						if(validateLeafScore(rowData)){
							return "<input style='width:90%;' maxlength='60' name='scoreReason' value='"+scoreReason+"'/>";
						}
						return scoreReason;

			   		}
			   	},
			   	{name:"id", width:0	,key:true,sortable:false,hidden:true
				   	,formatter:function(el,options,rowData){if(validateLeafScore(rowData)) return "<input name='detailedRuleIds' type='hidden' value='"+rowData.id+"'/>"; else return "";}
			   	}
		   	 ],
			loadComplete:function(){
				isHaveScore();
				evaluateTotalScore();
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
				$("input[name='scores']").each(function(){
					$(this).change(function(){
						if(judgeScore(this)){
							var rowDataId = $(this).parent().parent().attr("id");

							var rowData = $("#itemsList").getRowData(rowDataId);
							var parentRowData = $("#itemsList").getRowData(rowData.parentId);

							var parentStandardScore = parseFloat(parentRowData.standardScore);
							var rowDataStandardScore = parseFloat($(this).val());

							var absParentScoreInput= Math.abs(parseFloat(parentRowData.scoreInput));
							var absRowDataScoreInput = Math.abs($(this).val());
							if(absParentScoreInput < absRowDataScoreInput){
								$.messageBox({"message":"扣的分过大","level":"warn"});
								$(this).val(0);
								$("#evaluateResultForm").ajaxSubmit({
										success:function(data){
										$.dialogLoading("close");
										boo=true;
										$("#itemsList").trigger("reloadGrid");
									}
								})
								$(this).focus();
								isCorrectScore = false;
								validateSaveSuccess = true;
								return;
							}
							isCorrectScore = true;
							if(validateSaveSuccess){
								validateSaveSuccess = false;
								return;
							}
						}else{
							$(this).focus();
						}
					});
				});

				$("input[name='scoreReason']").each(function(){
					$(this).keyup(function(){
						if(!judgeSpecialCharacter($(this).val(), "评分依据")){
							$(this).val("");
						}
					})
				});

				$("#active").click();
			}
		});
		initOption();

		$.ajax({
			async:false,
			url:PATH+"/evaluate/evaluateManage/findReportEvaluatesByOrgIdAndYearAndState.action",
			data:{
				"evaluate.year" : $("#getEvaluateYear").val(),
				"evaluate.organization.id" : $.getCurrentOrgId()
			},
			success:function(responseData){
				$("#getEvaluatesTitle").empty();
				initOption();

				if(null == responseData){
					$("#itemsList").setPostData({"evaluate.id": ""});
					$("#itemsList").trigger("reloadGrid");
					//$("#saveEvaluate").buttonDisable();
					//$("#pigeonholeEvaluate").buttonDisable();
					$("#standardSum").text(0);
				 	$("#selfSum").text(0);
				 	$("#evaluateTotalScore").text(0);
					return;
				}
				//$("#saveEvaluate").buttonEnable();
				//$("#pigeonholeEvaluate").buttonEnable();
				//$("#evaluateBack").buttonEnable();
				for(var i = 0 ; i < responseData.length ; i ++){
					if(i == 0){
						$("#standardSum").text(responseData[i].templateTotalScore)
					}
					var title = responseData[i].title;
				 	var id = responseData[i].id;
				 	var str = '<option value=' + id + '>' + title + '</option>';
				 	$("#getEvaluatesTitle").append(str);
				 	if(i == 0){
					 	$("#standardSum").text(responseData[i].templateTotalScore);
					 	$("#selfSum").text(responseData[i].selfAssessmentTotalScore);
					 	$("#evaluateTotalScore").text(responseData[i].totalScore);
				 	}
				}
				$("#itemsList").setPostData({"evaluate.id": $("#getEvaluatesTitle").val()+""});
				$("#itemsList").trigger("reloadGrid");
				$("#evaluateId").val($("#getEvaluatesTitle").val());
				$("#getEvaluatesTitle").change();
			}
		});

		var ss = dfop.evaluateId
		$("#getEvaluatesTitle").val(ss);
		//$("#evaluateBack").buttonDisable()
		if(ss > 0 ){
			//$("#evaluateBack").buttonEnable()
		}
		//$("#getEvaluateYear").val(<s:property value="#getReportedEvaluateResultByOrgId.evaluate.year" />+"");
		$("#getEvaluateYear").change(function(){
			evaluateYearFun();
		});
		function evaluateYearFun(){
			$.ajax({
				async:false,
				url:PATH+"/evaluate/evaluateManage/findReportEvaluatesByOrgIdAndYearAndState.action",
				data:{
					"evaluate.year" : $("#getEvaluateYear").val(),
					"evaluate.organization.id" : $.getCurrentOrgId()
				},
				success:function(responseData){
					$("#getEvaluatesTitle").empty();
					if(null == responseData){
						$("#itemsList").setPostData({"evaluate.id": ""});
						$("#itemsList").trigger("reloadGrid");
						//$("#saveEvaluate").buttonDisable();
						//$("#pigeonholeEvaluate").buttonDisable();
						$("#standardSum").text(0);
					 	$("#selfSum").text(0);
					 	$("#evaluateTotalScore").text(0);
						return;
					}
					//$("#saveEvaluate").buttonEnable();
					//$("#pigeonholeEvaluate").buttonEnable();
					//$("#evaluateBack").buttonEnable();
					for(var i = 0 ; i < responseData.length ; i ++){
						if(i == 0){
							$("#standardSum").text(responseData[i].templateTotalScore)
						}
						var title = responseData[i].title;
					 	var id = responseData[i].id;
					 	var str = '<option value=' + id + '>' + title + '</option>';
					 	$("#getEvaluatesTitle").append(str);
					 	if(i == 0){
						 	$("#standardSum").text(responseData[i].templateTotalScore);
						 	$("#selfSum").text(responseData[i].selfAssessmentTotalScore);
						 	$("#evaluateTotalScore").text(responseData[i].totalScore);
					 	}
					}
					if($("#getEvaluatesTitle").val() == null || $("#getEvaluatesTitle").val() == ""){
						$("#itemsList").setPostData({"evaluate.id": ""});
						$("#itemsList").trigger("reloadGrid");
					}else{
						$("#itemsList").setPostData({"evaluate.id": $("#getEvaluatesTitle").val()+""});
						$("#itemsList").trigger("reloadGrid");
					}
					$("#evaluateId").val($("#getEvaluatesTitle").val());
				}
			});
		}
		$("#getEvaluatesTitle").change(function(){
			$("#evaluateId").val($("#getEvaluatesTitle").val())
			$("#itemsList").setPostData({"evaluate.id":$("#getEvaluatesTitle").val()});
			if($("#getEvaluatesTitle").val() == null || $("#getEvaluatesTitle").val() == ""){
				$("#itemsList").setPostData({"evaluate.id" : ""});
			}
			$("#itemsList").trigger("reloadGrid");
			$("#standardSum").text(0);
			$("#evaluateTotalScore").text(0);
			$("#selfSum").text(0);
			$.ajax({
					async:false,
					url:PATH+"/evaluate/evaluateManage/getEvaluateById.action",
					data:{
						"evaluate.id" : $("#getEvaluatesTitle").val()
					},
					success:function(data){
						//标准总分
						$("#standardSum").text(data.templateTotalScore);
						//总分
						$("#evaluateTotalScore").text(data.totalScore);
						//自评总分
						$("#selfSum").text(data.selfAssessmentTotalScore);

					}
			});
		});
		$("#evaluateBack").click(function(){
			var evaluateId = $("#getEvaluatesTitle").val();
			if($("#getEvaluatesTitle").val() == "请选择目录" || $("#getEvaluatesTitle").val() ==null || $("#getEvaluatesTitle").val() == ""){
				$.messageBox({message:"没有数据!",level:"warn"})
				return;
			}
			$.confirm({
					title:"确认回退",
					message:"确认回退么？",
					width:400,
					okFunc:function(){
						$.ajax({
							url:PATH+"/evaluate/evaluateManage/backEvaluate.action",
							data:{
								"evaluate.id":evaluateId
							},
							success:function(responseData){
								if(responseData == true){
									$.messageBox({message:"回退成功！"})
									var curMenu=$("#accordion a.cur");
									var baseUrl=curMenu.attr("baseUrl");
									var leaderUrl="";
									baseLoad(curMenu,baseUrl,leaderUrl);
									return;
								}else{
									$.messageBox({message: "回退失败!"});
								}
							}
						})
					}
		    })

		});
		$("#saveEvaluate").click(function(){
			if($("#saveEvaluate").attr("disabled")  == "true"){
				return;
			}
			if($("#getEvaluatesTitle").val() == "请选择目录" || $("#getEvaluatesTitle").val() ==null || $("#getEvaluatesTitle").val() == ""){
				$.messageBox({message:"没有数据!",level:"warn"})
				return;
			}
			if(isCorrectScore){
				var scores = $("input[name='scores']");
				var nowSum = 0 ;
				for(var i=0;i<scores.length;i++){
					if(judgeScore(scores[i]) == false){
						return;
					}
					//判断是否所评分树总和要大于父节点的分数
					nowSum = nowSum + parseFloat($(scores[i]).val());
				}
				//判断扣去的总分;
				if(Math.abs(nowSum) > parseFloat($("#standardSum").text())){
					$.messageBox({level:"warn",message:"扣去分数总和请不要大于标准总分!"});
					return;
				}
				$.dialogLoading("open");
					$("#evaluateResultForm").ajaxSubmit({
						success:function(data){
						$.dialogLoading("close");
						$.messageBox({message:"已经成功保存!"});
						boo=true;
						$("#itemsList").trigger("reloadGrid");
					}
				})
			}
		});
		$("#viewDailyLog").click(function(){
			$("#evaluateResultDailog").createDialog({
				width:900,
				height:500,
				title:"台帐信息",
				modal:false,
				resizable:false,
				url:PATH+"/workingRecord/dailyYearManage/dailyLogForEvaluateByParentId.action?currentOrganization.id="+$.getCurrentOrgId(),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		$("#pigeonholeEvaluate").click(function(){
			if($("#pigeonholeEvaluate").attr("disabled") == "true"){
				return;
			}
			if($("#getEvaluatesTitle").val() == "请选择目录" || $("#getEvaluatesTitle").val() ==null || $("#getEvaluatesTitle").val() == ""){
				$.messageBox({message:"没有数据!",level:"warn"})
				return;
			}
			$.confirm({
				title:"确认归档",
				message:"该考核结果归档后将不能修改，确认归档？",
				width: 300,
				okFunc:addPigeonhole
			});
		});
	});
	
	function reloadGrid(){
		$("#itemsList").setGridParam({
			url: PATH+"/evaluate/evaluateManage/getDetailedRules.action",
			postData: {
				"evaluate.id":dfop.evaluateId
			}
		});
		$("#itemsList").trigger("reloadGrid");
	}
	
	
	
	
	
	function sumSelfGrade(){
		var scores = $("input[name*='selfScores']");
		var sum = 0;
		scores.each(function(){
				if(!$(this).attr("value") == ""){
					sum = sum + parseInt($(this).attr("value"));
				}
			}
		);
		$("#selfSum").text(sum);
	}
	
}
//计算考核总分
function sumGrade(){
	var scores = $("input[name*='scores']");
	var sum = 0;
	scores.each(function(){
			if(!$(this).attr("value") == ""){
				sum = sum + parseInt($(this).attr("value"));
			}
		}
	);
	//$("#sum").val(sum);
}
