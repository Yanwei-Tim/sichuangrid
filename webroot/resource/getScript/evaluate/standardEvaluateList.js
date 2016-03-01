TQ.standardEvaluateList = function (dfop){
	var showValidateMessage = true;
	function isActiveFormatter(el,options,rowData){
		if(rowData.state==dfop.evaluateState){
			return "已启用";
		}else{
			return "未启用";
		}
	}
	function isCheckBox(el,options,rowData){
		if(rowData.state == dfop.evaluateState){
			return '';
		}else{
			return '<input type="checkbox" name="checkBoxSelect" value= '+ rowData.id + ' />';
		}
	}
	var validateIsResult=false;
	function getEvaluateDetailedInfo(evaluateId){
			validateIsResult=false;
			$.ajax({
				url:PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action?evaluate.id="+evaluateId,
				async:false,
				success:function(data){
					var leafEnt = data.rows;
					for(var i=0;i<leafEnt.length;i++){
						var ent = leafEnt[i];
						if(ent.leaf == 'true' || ent.leaf==true){
				            if(ent.standardScore != "" && ent.standardScore != undefined){
				            	validateIsResult = true;
				            	return;
				            }
						}
					}
		        }
			});
	}
	function validateIsActiveFun(id){
		getEvaluateDetailedInfo(id);
		if(validateIsResult =='true' || validateIsResult == true){
			return true;
		}
		return false;
	}

	$(document).ready(function(){
		var obj = new Date();
		var thisYear = obj.getFullYear();
		$("#getEvaluateYear").val(thisYear);
		$("#getEvaluateYear").change(function(){
			$("#evaluateList").setPostData({
				"evaluate.organization.id":dfop.organizationId,
				"evaluate.year":$("#getEvaluateYear").val()
			});
			$("#evaluateList").trigger("reloadGrid");
		});
		$("#evaluateList").jqGridFunction({
			url:PATH+"/evaluate/evaluateManage/standardEvaluateList.action",
			postData:{"evaluate.organization.id":dfop.organizationId,
					  "evaluate.year":$("#getEvaluateYear").val()
					},
			datatype: "json",
			colModel:[
		    	{name:"id", index:"id", hidden:true,sortable:false},
		    	{name:"encryptId",index:'id',frozen:true,hidden:true},
	  			{name:"year", index:"year",label:"年度",width:30,sortable:true},
	  			{name:"title", index:"title", label:"标题",sortable:true},
	  			{name:"templateTotalScore", label:"标准总分",index:"templateTotalScore",width:20,sortable:true},
	  			{name:"state", label:"启用",index:"state",width:20,formatter:isActiveFormatter,sortable:true}
		  	],
			ondblClickRow:function (){
	    		if(dfop.viewEvaluate=='true'){
	    			viewFun();
				}
			},
			shrinkToFit:true,
			showColModelButton:false
		});
		$("#add").click(function(event){
			addFun();
		});
		$("#update").click(function(event){
			updateFun();
		});
		$("#delete").click(function(event){
			deleteFun();
		});
		$("#active").click(function(event){
			activeFun();
		});
		$("#unactive").click(function(event){
			unactiveFun();
		});
		$("#view").click(function(event){
			viewFun();
		});
		$("#search").click(function(event){
			searchFun();
		});
		function addFun(){
			var rowId = $("#evaluateList").jqGrid('getGridParam','selrow');
			var rowData = $("#evaluateList").getRowData(rowId);
			$("#evaluateDialog").createDialog({
				width: 750,
				height: 600,
				title:"新增考核标准",
				url:PATH+'/evaluate/createStandardEvaluateDlg.jsp?year=' + rowData.year + '&evaluateId=' + rowData.id,
				buttons: {
					"保存" : function(){
						if(checkByTitle($("#evaluatetitle").val(),$("#evaluateyear").val())){
							$.messageBox({message:"请核对考核标题不能重复!",level : "error"});
							return;
						}
						$("#maintainRuleSettingsForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
		
		function deleteFun(){
			//单条删除
			var rowId = $("#evaluateList").jqGrid('getGridParam','selrow');
			if(rowId == null){
				$.messageBox({level: 'warn',message: "请选择一条记录"});
				return;
			}
			var rowData = $("#evaluateList").getRowData(rowId);
			if(rowId==undefined || rowData.state=='已启用'){
				$.messageBox({level:'warn',message:'该标准已启用不能删除！'});
				return;
			}
			var evaluate =  $("#evaluateList").getRowData(rowId);
			if(evaluate.isActive=="已启用"){
				$.messageBox({level:'warn',message:"该标准已启用不能删除！"});
				return ;
			}
			$.confirm({
				message:"确定删除该考核模板吗？",
				okFunc: function(){
					deleteEvaluateById(rowId);
				}
			});
			
		}
		function deleteEvaluateById(id){
			var rowData = $("#evaluateList").getRowData(id);
			$.ajax({
				url:PATH+'/evaluate/evaluateManage/deleteEvaluateByIdByEncrypt.action?evaluate.id='+rowData.encryptId,
				success:function(data){
					if(data == true){
						$("#evaluateList").delRowData(id);
						$.messageBox({ message:"成功删除该考核标准!"});
						return;
					}
		            $.messageBox({message:data,level:'error'});
		        }
			});
		}
		function deleteEvaluatesByIds(str){
			$.dialogLoading("open");
			$.ajax({
				url:PATH+"/evaluate/evaluateManage/deleteMultiEvaluateByIds.action"+str,
				success:function(data){
					if(data){
						$("#evaluateList").trigger("reloadGrid");
						$.dialogLoading("close");
						$.messageBox({message: "已经成功删除多条考核标准"});
					}else{
						$.messageBox({level: 'error',message: "考核删除失败!"});
						$.dialogLoading("close");
					}
				}
			});
		}
		function activeFun(){
			var rowId = $("#evaluateList").jqGrid('getGridParam','selrow');
			if(rowId == null){
				$.messageBox({level: 'warn',message: "请选择一条记录"});
				return;
			}
			var rowData = $("#evaluateList").getRowData(rowId);
			if(rowId==undefined || rowData.state=='已启用'){
				$.messageBox({message:"考核标准已启用！",level:'warn'})
				return;
			}
			if(validateIsActiveFun(rowId)){
				$.messageBox({level:'warn',message:'考核标准缺少考核细则，请修改'});
				return;
			}
			jugeActiveEvaluate(rowId);
			return;
		}
		function unactiveFun(){
				var rowId = $("#evaluateList").jqGrid('getGridParam','selrow');
				if(rowId == null){
					$.messageBox({level: 'warn',message: "请选择一条记录"});
					return;
				}
				var rowData = $("#evaluateList").getRowData(rowId);
				if(rowId==undefined || rowData.state=='未启用'){
					$.messageBox({message:"考核标准未启用，不能停用！",level:'warn'})
					return;
				}
				$.ajax({
					url:PATH+"/evaluate/evaluateManage/validateUnactive.action?evaluate.id="+rowId,
					async:false,
					success:function(data){
						if(data){
							$.ajax({
								url:PATH+"/evaluate/evaluateManage/popedomEvaluateUsedByEvaluateId.action",
								data:{
									"evaluate.id":rowId
								},
								async:false,
								success:function(data){
									function unActive(){
										$.ajax({
											url:PATH+"/evaluate/evaluateManage/unActiveEvaluate.action?evaluate.id="+rowId,
											success:function(data){
												if(data){
													$("#evaluateList").trigger("reloadGrid");
												//	$("#evaluateList").setRowData( rowid, { state: "<s:property value='@com.tianque.service.state.EvaluateState@NONE'/>"});
													$.messageBox({message: "已经成功停用考核标准"});
												}else{
													$.messageBox({message: "考核标准停用失败", level: "error"	});
												}
											}
										});
									}
									var boo=data;
									if(boo){
										$.confirm({
											width : 350,
											message:"已经有辖区开始使用，是否停用该标准？",
											okFunc: function(){
												unActive();
											}
										});
									}else{
										$.confirm({
											width : 350,
											message:"是否真的停用该考核标准？",
											okFunc: function(){
												unActive();
											}
										});
									}
								}
							});
						}else{
							$.messageBox({
								message:"停用的模板包含归档的考核,无法停用",level:"error"
							});
							return;
						}
					}
				})
		}
		function viewFun(){
			var rowId = $("#evaluateList").getGridParam("selrow");
			if(rowId == null){
				$.messageBox({level: 'warn',message: "请选择一条记录"});
				return;
			}
			var rowData =  $("#evaluateList").getRowData(rowId);
			$("#evaluateDialog").createDialog({
				width: 860,
				height: 600,
				title:"查看考核标准",
				url:PATH+'/evaluate/viewStandardEvaluateDlg.jsp?id='+rowData.encryptId+"&title="+encodeURIComponent(rowData.title),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
		function searchFun(){
			$("#evaluateDialog").createDialog({
				width: 400,
				height: 200,
				title:"考核标准查询-请输入查询条件",
				url:PATH+'/evaluate/searchEvaluateDlg.jsp?orgId='+dfop.organizationId,
				buttons: {
					"查询" : function(event){
						searchEvaluates();
		   				$(this).dialog("close");

		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
		$("#refresh").click(function(){
			var date = new Date();
			$("#getEvaluateYear").val(date.getFullYear());
			$("#evaluateList").setPostData({"evaluate.organization.id":dfop.organizationId,
					"evaluate.year":$("#getEvaluateYear").val()
			});
			$("#evaluateList").trigger("reloadGrid")
		});
		function checkByTitle(title,year){
			var result=false;
			var orgid=dfop.organizationId;
			$.ajax({
				url:PATH+"/evaluate/evaluateManage/standardEvaluateList.action?evaluate.organization.id="+orgid,
				async:false,
				success:function(data){
					var rowDatas=data.rows;
					for(var i=0;i<rowDatas.length;i++){
						if(rowDatas[i].title==title && rowDatas[i].year==year){
							result=true;
						}
					}
				}
			});
			return result;
		}
		function validateBlank(){
			var rowIds = $("#evaluateList").getGridParam('selarrrow');
			if(rowIds.length > 1){
				for(var i = 0 ; i < rowIds.length ; i ++){
					var rowData = $("#evaluateList").getRowData(rowIds[i]);
					if(parseFloat(rowData.templateTotalScore) == 0){
						return false;
					}
				}
			}
			return true;
		}
		function searchEvaluates(){
			var evyear =$("#evaluateYear").val();
			var evtitle=$("#evaluateTitle").val();
			var isHave = false;
			$("option" , $("#getEvaluateYear")).each(function(){
				if($(this).text() == evyear){
					isHave = true;
					$("#getEvaluateYear").val(evyear);
					return;
				}
			});
			if(!isHave){
				$("#getEvaluateYear").val("");
			}
			$("#evaluateList").setGridParam({
				url:PATH+"/evaluate/evaluateManage/standardEvaluateList.action",
				postData: {
					"evaluate.year":evyear,
					"evaluate.title":evtitle,
					"condition":$("#selectState").val(),
					"evaluate.organization.id":dfop.organizationId
				}
			});
			$("#evaluateList").trigger("reloadGrid");
		}
		function activeEvaluate(rowid){
			$.dialogLoading("open");

			$.ajax({
				url:PATH+"/evaluate/evaluateManage/activeStandardEvaluate.action?evaluate.id="+rowid,
				success:function(data){
					if(data == true){
						$("#evaluateList").setRowData( rowid, { state:dfop.evaluateState});
						$.messageBox({ message:"启用考核标准成功!"});
						$("#evaluateList").trigger("reloadGrid");
						$.dialogLoading("close");
						return;
					}
					$.dialogLoading("close");
		            $.messageBox({message:data,level: "error"});
		        }
			});
		}
		function jugeActiveEvaluate(rowid){
			var rowData = $("#evaluateList").getRowData(rowid);
			if(rowData.templateTotalScore == 0){
				$.messageBox({message:"考核标准无细则，无法启用!",level:"error"})
				$.dialogLoading("close");
				return;
			}

			$.confirm({
				message:"确定启用此考核标准吗？",
				okFunc: function(){
					activeEvaluate(rowid);
				}
			});
		}
	});
}

