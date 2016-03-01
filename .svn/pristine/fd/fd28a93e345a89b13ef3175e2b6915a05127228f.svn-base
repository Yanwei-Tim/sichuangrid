TQ.commonPart = function (dfop){
	
	$(document).ready(function(){
		if(dfop.partTypeValue!=null){
			partType = dfop.partTypeValue
		}
		function toggleButtonState(){
		  	var selectedIds=$("#"+dfop.lowerCaseModuleNameValue+"List").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		  	setDeleteButtonEnabled(selectedRowCount>0);
		  	setOtherButtonEnabled(selectedRowCount==1);
		  	toggleEmphasisButton();
		}
		function selectedRowIsnotEmphasis(domain){
			return 	domain.isEmphasis==1;
		}
		function getSelectedIdLast(){
			var selectedId;
			var selectedIds = $("#"+dfop.lowerCaseModuleNameValue+"List").jqGrid("getGridParam", "selarrrow");
			for(var i=0;i<selectedIds.length;i++){
				selectedId = selectedIds[i];
			}
			return selectedId;
		}
		
		loadGridFunction();
		
		jQuery("#"+dfop.lowerCaseModuleNameValue+"List").jqGrid('setFrozenColumns');
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId(),isGrid());
			checkExport();
		}
		$("#add").click(function(){
			if (!isGrid()){
				$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			if (getCurrentOrgId()==null||getCurrentOrgId()==""){
				$.notice({level:"warn", message:"请先选择一个部门"});
			}else{
				$("#"+dfop.lowerCaseModuleNameValue+"Dialog").createFrameDialog(
					$.extend(
						{
							model :"add",
							title:"新增"+dfop.moduleCNameValue+"",
							width: dialogWidth,
							height: dialogHeight,
							data:[
								{title:'基本信息',url:''+dfop.listNameSpaceValue+'/dispatchOperate.action?mode=add&dailogName='+dfop.lowerCaseModuleNameValue+'Dialog&organizationId='+getCurrentOrgId(),buttons:{save:true,saveContinue:true}}
							]
						},
						function(){if(buttonFunctions)return buttonFunctions.add}
				));
			}
		});
		$("#searchText").focus(function(){
	           this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入部件名称或部件标识码");
			//search(getCurrentOrgId());
		});
		$("#update").click(function(){
			var selectedIds = $("#"+dfop.lowerCaseModuleNameValue+"List").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){return;}
			var selectedId = getSelectedIdLast();

			if(selectedId==null){
				 return;
			}
			updateOperator(selectedId);
		});

		$("#cancelEmphasise").click(function(event){
				if($(this).attr("disabled")=="disabled"){
					return;
				}
				var selectedId =getSelectedIds();
				var cancelEmphasise=new Array();
				var temp=new Array();
				if(selectedId == null || selectedId.length<=0){
					$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行取消关注！"});
					return;
				}
				for(var i=0;i<selectedId.length;i++){
					var rowData = $("#"+dfop.lowerCaseModuleNameValue+"List").getRowData(selectedId[i]);
					if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
						cancelEmphasise.push(selectedId[i]);
						}else{
							temp.push(selectedId[i]);
							}
					}
				selectedId=cancelEmphasise;
				if(selectedId==null||selectedId.length==0){
					$.messageBox({level:'warn',message:"没有可取消关注的数据！"});
					return;
				}
				var selectedIds="";
				for(var i=0;i<selectedId.length;i++){
					selectedIds+=selectedId[i]+",";
				}
				if(selectedIds.length=2){
					selectedIds=selectedId;
				}
				
				var encryptIds=deleteOperatorByEncrypt(""+dfop.lowerCaseModuleNameValue+"",selectedIds,"encryptId");
				$("#"+dfop.lowerCaseModuleNameValue+"Dialog").createDialog({
					width:450,
					height:210,
					title:'取消关注提示',
					modal:true,
					url:PATH+'/baseinfo/commonPopulation/emphasiseConditionDlg.jsp?locationIds='+encryptIds+'&isEmphasis=true&dailogName='+dfop.lowerCaseModuleNameValue+'&temp='+temp,
					buttons: {
					   "保存" : function(event){
						   $("#emphasisForm").submit();
					   },
					   "关闭" : function(){
					        $(this).dialog("close");
					   }
					}
				});
			//}
		});

		$("#reEmphasise").click(function(){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId = getSelectedIds();
			var reEmphasise=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再重新关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var rowData = $("#"+dfop.lowerCaseModuleNameValue+"List").getRowData(selectedId[i]);
				if(rowData.isEmphasis==true||rowData.isEmphasis=="true"){
					reEmphasise.push(selectedId[i]);
					}else{
						temp.push(selectedId[i]);
						}
				}
			selectedId=reEmphasise;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可重新关注的数据！"});
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds+=selectedId[i]+",";
			}
			if(selectedIds.length = 2){
				selectedIds=selectedId;
			}
			
			var encryptIds=deleteOperatorByEncrypt(""+dfop.lowerCaseModuleNameValue+"",selectedIds,"encryptId");
			$.ajax({
				url:PATH+""+dfop.listNameSpaceValue+"/updateEmphasiseByEncryptId.action",
				data:{
					"locationIds": encryptIds,
					"location.isEmphasis":0
				},
				success:function(responseData){
					if(null==temp || temp.length==0){
						$.messageBox({message:""+dfop.moduleCNameValue+"重新关注成功 ！ "});
					}else{
						$.messageBox({level:'warn',message:"除选中的红色数据外,其余"+dfop.moduleCNameValue+"重新关注成功 ！ "});
					}
					notExecute=temp;
					$("#"+dfop.lowerCaseModuleNameValue+"List").trigger("reloadGrid");
					toggleButtonState();
					checkExport();
				}
			});
		});
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteOperator(allValue);
		});

		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			eval('view'+dfop.moduleNameValue+'('+selectedId+')');
		});

		$("#reload").click(function(){
			$("#searchText").attr("value","请输入部件名称或部件标识码");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		$("#search").click(function(event){
			$("#"+dfop.lowerCaseModuleNameValue+"Dialog").createDialog({
				width:650,
				height:320,
				title:''+dfop.moduleCNameValue+'查询-请输入查询条件',
				url:PATH+''+dfop.listNameSpaceValue+'/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
						eval('search'+dfop.moduleNameValue+'()');
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});

		$("#import").click(function(event){
			$("#noticeDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据导入",
				url:PATH+"/common/import.jsp?isNew=1&dataType="+dfop.lowerCaseModuleNameValue+"&dialog=noticeDialog&startRow=6&templates="+dfop.upperCaseModuleNameValue+"&listName="+dfop.lowerCaseModuleNameValue+"List",
				buttons:{
					"导入" : function(event){
						$("#mForm").submit();
					},
				   	"关闭" : function(){
				    	$(this).dialog("close");
				   	}
				},
				shouldEmptyHtml:false
			});
		});
		exportFunction();
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
	})
			

	function disableButtons(){
	  	setDeleteButtonEnabled(false);
	  	setOtherButtonEnabled(false);
	  	toggleEmphasisButton();
	}

	function setDeleteButtonEnabled(enabled){
// 		if (enabled){
// 			$("#delete").buttonEnable();
// 		}else{
// 			$("#delete").buttonDisable();
// 		}
	}

	function setOtherButtonEnabled(enabled){
		if (enabled){
			$("#update").buttonEnable();
			$("#view").buttonEnable();
		}else{
			$("#update").buttonDisable();
			$("#view").buttonDisable();
		}
	}

	function checkExport(){
// 		if($("#${lowerCaseModuleName}List").getGridParam("records")>0
// 				|| $("#${lowerCaseModuleName}List").getGridParam("selrow")!=null){
// 			$("#export").buttonEnable();
// 		}else{
// 			$("#export").buttonDisable();
// 		}
	}
	function getSelectedIds(){
		var selectedIds = $("#"+dfop.lowerCaseModuleNameValue+"List").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
				
}