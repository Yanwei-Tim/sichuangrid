TQ.regionalBuildInfoList = function (dfop){
	var dialogWidth=800;
	var dialogHeight=600;
	
	function toggleRegionalBuildInfoButtonState(){
	  	var selectedIds=$("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	$(document).ready(function() {
		
		
		initRegionalBuildInfoButtonsEnable();
		function initRegionalBuildInfoButtonsEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#addRegionalBuildInfo,#updateRegionalBuildInfo,#deleteRegionalBuildInfo,#claimRegionalBuildInfo").hide();
			}
		}
		
		 //生成组织活动列表
		$("#regionalBuildInfoList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			colModel:[
		    	{name:"id", index:"id", hidden:true,frozen:true},
		    	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	{name:"organization.orgName", index:"organization.orgInternalCode",label:"所属组织",width:200, frozen:true},
	  			{name:"serviceItem", index:"serviceItem",label:"服务项目名称",width:200, frozen : true},
	  			{name:"advancementInfo", index:"advancementInfo", label:"推进情况", width:200, align:"center"},
	  			{name:"regionalBuildInfoAttachFiles", formatter:formatterRegionalBuildInfoAttachFiles, label:'附件', width:150, frozen:true},
	  			{name:'', index:'',label:'认领情况', formatter:claimFormatter,width:100, align:'center', hidden:false}	
		  	],
		  	multiselect:true,
		  	onSelectAll:toggleRegionalBuildInfoButtonState,
	    	loadComplete: function(){afterLoad();showRegionalBuildInfoAttachFile();},
	    	ondblClickRow:function (rowid){
				if(dfop.viewRegionalBuildInfo=='true'){
					viewRegionalBuildInfo(rowid);
				}
			},
			onSelectRow:toggleRegionalBuildInfoButtonState,
			height:$(".ui-layout-center").height()-320
		});
		jQuery("#regionalBuildInfoList").jqGrid('setFrozenColumns');
		 
		 $("#addRegionalBuildInfo").click(function(){
			 
			 if(''==$("#partyOrgInfoId").val()||typeof($("#partyOrgInfoId").val())=='undefined'){
					$.messageBox({level:'warn',message:"请先填写区域党建信息！"});
					return;
				}
				if(getCurrentOrgId() != USER_ORG_ID){
					$.messageBox({level:'warn',message:"您不能给其他层级添加数据！"});
					return;
				}
			 $("#regionalBuildInfoDialog").createDialog({
					width:650,
					height:450,
					title:'新增区域联建情况记录',
					url:dfop.path+'/partyBuilding/regionalBuildInfoManage/dispatchOperate.action?mode=add&regionalBuildInfo.organization.id='+getCurrentOrgId(),
					buttons: {
				   		"保存" : function(event){
				   			$("#maintainRegionalBuildInfoForm").submit();
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
					 
		 });
		 
		  $("#updateRegionalBuildInfo").click(function(){
			 var selectedIds = $("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
				if(selectedIds==null || selectedIds.length>1){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
					return;
				}
				var selectedId = getRegionalBuildInfoSelectedIdLast();
				if(selectedId==null){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
					 return;
				}
				var rowData = $("#regionalBuildInfoList").getRowData(selectedIds);
			 $("#regionalBuildInfoDialog").createDialog({
					width:650,
					height:400,
					title:'修改区域联建情况记录',
					url:dfop.path+'/partyBuilding/regionalBuildInfoManage/dispatchOperateByEncrypt.action?mode=edit&regionalBuildInfo.organization.id='+getCurrentOrgId()+'&id='+rowData.encryptId,
					buttons: {
				   		"保存" : function(event){
				   			$("#maintainRegionalBuildInfoForm").submit();
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
		 });
		 
		$("#viewRegionalBuildInfo").click(function(){
			 var selectedIds = $("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
				if(selectedIds==null || selectedIds.length>1){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
					return;
				}
				var selectedId = getRegionalBuildInfoSelectedIdLast();
				if(selectedId==null){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
					 return;
				}
				
				viewRegionalBuildInfo(selectedId);
		 });
		 
		$("#deleteRegionalBuildInfo").click(function(){
			var rowIds = $("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length ==0){
				$.messageBox({level:"warn",message:'没有选中任何记录，可供删除！'});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("regionalBuildInfo",rowIds,"encryptId");
			var str="确定要删除吗?一经删除将无法恢复";
			$.confirm({
				title:"确认删除",
				message:str,
				okFunc: function() {
					$.ajax({
						url:dfop.path+"/partyBuilding/regionalBuildInfoManage/deleteRegionalBuildInfoByEncrypt.action",
							type:'post',
							data:{
								'deleteIds':encryptIds
							},	
						success:function(data){
							$.messageBox({message:"已经成功删除该区域联建情况信息!"});
								$("#regionalBuildInfoList").trigger("reloadGrid");
						}
					});
				}
			});
			 
		 });
		
		$("#searchRegionalBuildInfo").click(function(){
			 $("#regionalBuildInfoDialog").createDialog({
					width:550,
					height:200,
					title:'查询区域联建情况记录-请输入查询条件',
					url:dfop.path+'/partyBuilding/regionalBuildInfoManage/dispatchOperate.action?mode=search&regionalBuildInfoVo.organization.id='+getCurrentOrgId(),
					buttons: {
				   		"查询" : function(event){
				   				searchRegionalBuildInfo();
				   				$(this).dialog("close");
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
		 });
		
		
		$("#claimRegionalBuildInfo").click(function(){
			var selectedIds = $("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行认领！"});
				return;
			}
			var selectedId = getRegionalBuildInfoSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行认领！"});
				 return;
			}
			var rowData=  $("#regionalBuildInfoList").getRowData(selectedIds);
		 $("#regionalBuildInfoDialog").createDialog({
				width:550,
				height:200,
				title:'区域联建情况认领',
				url:dfop.path+'/partyBuilding/regionalBuildInfoManage/dispatchOperateByEncrypt.action?mode=claim&claimMode=add&regionalBuildInfoId='+rowData.encryptId,
				buttons: {
			   		"保存" : function(event){
			   			$("#claimRegionalBuildInfoForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
				 
		});
		 
		$("#reloadRegionalBuildInfo").click(function() {
			if(getCurrentOrgId()!="" && getCurrentOrgId() != null) {
				onOrgChanged(getCurrentOrgId());
			}
		}).click(); 
		
	});
	
	//改变组织机构树时加载列表
	function onOrgChanged(orgId){
		$("#regionalBuildInfoList").setGridParam({
			url:dfop.path+"/partyBuilding/regionalBuildInfoManage/regionalBuildInfoList.action",
			datatype: "json",
			page:1
		});
		$("#regionalBuildInfoList").setPostData({
			"regionalBuildInfoVo.organization.id":getCurrentOrgId()
	   	});
		$("#regionalBuildInfoList").trigger("reloadGrid");
		
	}
	//附件显示	
		function formatterRegionalBuildInfoAttachFiles(el,options,rowData){
		if(rowData.regionalBuildInfoAttachFiles.length>0){
				$("#regionalBuildInfoList").data(String(rowData.id), rowData.regionalBuildInfoAttachFiles);
				return "<div style='clear:both' align='center'><a href='javascript:;' id='regionalBuildInfo_"+rowData.id+"' style='color:#666666' class='popUpMore' regionalBuildInfoId='"+rowData.id+"' >附件>></a></div>";
			
			} 
			return "";
		}
	
	
	
	function afterLoad(){
		
	}
	
	function parseObj(strData) {
		return (new Function("return " + strData))();
	}
	
	function searchRegionalBuildInfo(){
		var formdata = $("#searchRegionalBuildInfoForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');
		search(parseObj(formdata));
	}
	function search(postData){
		$("#regionalBuildInfoList").setGridParam({
			url:dfop.path+'/partyBuilding/regionalBuildInfoManage/regionalBuildInfoList.action',
			datatype: "json",
			page:1
		});
		$("#regionalBuildInfoList").setPostData($.extend(postData,{type:"chlid"}));
		$("#regionalBuildInfoList").trigger("reloadGrid");
	}
	
	function viewRegionalBuildInfo(selectedId){
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		var rowData = $("#regionalBuildInfoList").getRowData(selectedId);
		$("#regionalBuildInfoDialog").createDialog({
			width:650,
			height:300,
			title:'查看区域联建情况记录',
			url:dfop.path+'/partyBuilding/regionalBuildInfoManage/dispatchOperateByEncrypt.action?mode=view&regionalBuildInfo.organization.id='+getCurrentOrgId()+'&id='+rowData.encryptId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	 
	
	function getRegionalBuildInfoSelectedIdLast() {
		var selectedId;
		var selectedIds = $("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	} 
	
	function showRegionalBuildInfoAttachFile(){
			$.each($(".popUpMore"), function(i, n){
				$.ajax({
					url:dfop.path+"/partyBuilding/regionalBuildInfoManage/getRegionalBuildInfoById.action?regionalBuildInfo.id="+$(n).attr("regionalBuildInfoId"),
					success:function(data){
						var popMoreData = [];
						for(var j = 0; j < data.regionalBuildInfoAttachFiles.length; j++){
							popMoreData[j]={
									id:data.regionalBuildInfoAttachFiles[j].id, 
									url:dfop.path+'/partyBuilding/regionalBuildInfoManage/downloadRegionalBuildInfoAttachFiles.action?regionalBuildInfoAttachFile.id='+data.regionalBuildInfoAttachFiles[j].id, 
									text:data.regionalBuildInfoAttachFiles[j].fileName,
									clickFun:function(){}
								};
						}
						$(n).popUpMore({data: popMoreData});
					}
				});
			}); 
		}
}
function claimFormatter(el,options,rowData){
	if(rowData.isClaim){
		return "<a href='javascript:maintainClaimRegionalBuildInfo("+rowData.id+")'><span>详情</span></a>";
	} 
	return "";
	
}
function maintainClaimRegionalBuildInfo(selectId){
	if(selectId==null){
		return;
	}
	var rowData=  $("#regionalBuildInfoList").getRowData(selectId);
	$("#regionalBuildInfoDialog").createDialog({
		width:550,
		height:350,
		title:'区域联建认领情况详情',
	 		url:"/partyBuilding/regionalBuildInfoManage/dispatchOperateByEncrypt.action?mode=list&regionalBuildInfoId="+rowData.encryptId,
		buttons: {
	   		"关闭" : function(){
	   			$("#regionalBuildInfoList").trigger("reloadGrid");
	        	$(this).dialog("close");
	   		}
		}
	});
}