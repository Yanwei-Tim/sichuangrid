<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String moduleName = request.getParameter("moduleName");
	request.setAttribute("moduleName", moduleName);
	request.setAttribute("lowerCaseModuleName", moduleName.substring(0,
			1).toLowerCase()
			+ moduleName.substring(1));
	request.setAttribute("upperCaseModuleName", moduleName
			.toUpperCase());
	String listNameSpace = request.getParameter("listNameSpace");
	if (null == listNameSpace || "".equals(listNameSpace)) {
		listNameSpace = "/baseinfo/"
				+ moduleName.substring(0, 1).toLowerCase()
				+ moduleName.substring(1) + "Manage";
	}
	request.setAttribute("listNameSpace", listNameSpace);

	String searchNameSpace = request.getParameter("searchNameSpace");
	if (null == searchNameSpace || "".equals(searchNameSpace)) {
		searchNameSpace = "/baseinfo/search" + moduleName;
	}
	request.setAttribute("searchNameSpace", searchNameSpace);

	request.setAttribute("moduleCName", request
			.getParameter("moduleCName"));
%>

<div class="btnbanner btnbannerData">
<div class="ui-widget autosearch"><input type="text"
	value="请输入${moduleCName }名称" name="searchText" id="searchText"
	maxlength="18" class="searchtxt" style=""
	onblur="value=(this.value=='')?'请输入${moduleCName }名称':this.value;"
	onfocus="value=(this.value=='请输入${moduleCName }名称')?'':this.value;" />
<button id="refreshSearchKey"
	class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
	<a href="javascript:;" id="fastSearchButton"><span>查询</span></a>
</div>

<div class="content">
<div class="ui-corner-all" id="nav"><pop:JugePermissionTag
	ename="add${moduleName}">
	<a id="add" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="update${moduleName}">
	<a id="update" href="javascript:void(0)"><span><strong
		class="ui-ico-xg"></strong>修改</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="view${moduleName}">
	<a id="view" href="javascript:void(0)"><span><strong
		class="ui-ico-cakan"></strong>查看</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="delete${moduleName}">
	<a id="delete" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>删除</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="search${moduleName}">
	<a id="search" href="javascript:void(0)"><span><strong
		class="ui-ico-cx"></strong>高级查询</span></a>
</pop:JugePermissionTag> <a id="reload" href="javascript:void(0)"><span><strong
	class="ui-ico-refresh"></strong>刷新</span></a> <pop:JugePermissionTag
	ename="import${moduleName}">
	<a id="import" href="javascript:void(0)"><span><strong
		class="ui-ico-dr"></strong>导入</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="down${moduleName}">
	<a id="export" href="javascript:void(0)"><span><strong
		class="ui-ico-dc"></strong>导出</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="cancelAttended${moduleName}">
	<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
		class="ui-ico-CancelAttention"></strong>注销</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="attended${moduleName}">
	<a id="reEmphasise" href="javascript:void(0)"><span><strong
		class="ui-ico-refocusOn"></strong>取消注销</span></a>
</pop:JugePermissionTag> <s:if test="'Dustbin'!=#request.moduleName">
	<div style="float: right; white-space: nowrap;"><select
		id="isLock" name="" class="S_object">
		<option value="">全部</option>
		<option value="true" selected="selected">正常</option>
		<option value="false">已注销</option>
	</select></div>
</s:if></div>
<div style="clear: both;"></div>
<div style="width: 100%;">
<table id="${lowerCaseModuleName}List">
</table>
<div id="${lowerCaseModuleName}ListPager"></div>
</div>
<div id="${lowerCaseModuleName}Dialog"></div>
<div id="noticeDialog"></div>
<div id="${lowerCaseModuleName}MaintanceDialog"></div>
<div id="helpPersonnelDialog"></div>
<div id="helpPrecordDialog"></div>
<div id="scanHeaderImage"></div>
</div>
<script>

function onOrgChanged(orgId,isgrid){
	if (isGrid()){
		$("#add").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
	var initParam = {
			"organizationId":orgId
		}
	if($("#isLock").val()==""){
		initParam = {
				"organizationId":orgId
			}
	}else{
		initParam = {
				"organizationId":orgId,
		    	"location.isEmphasis":$("#isLock").val()
			}
	}
	
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}${listNameSpace}/${lowerCaseModuleName}List.action",
		datatype: "json",
		page:1
	});
	$("#${lowerCaseModuleName}List").setPostData(initParam);
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}
function checkExport(){
	if($("#${lowerCaseModuleName}List").getGridParam("records")>0
			|| $("#${lowerCaseModuleName}List").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
$(function(){

	function toggleEmphasisButton(){
		var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<=0){
			$("#reEmphasise").buttonDisable();
			$("#cancelEmphasise").buttonDisable();		
			return;
		}
		var reEmphasis=false;
		var cancelEmphasis=false;
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedIds[i]);
			if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
				reEmphasis=true;
			}
			if(rowData.isEmphasis==true || rowData.isEmphasis=="true"){
				cancelEmphasis=true;
			}
		}
		$("#reEmphasise,#cancelEmphasise").buttonDisable();
		if (!reEmphasis || cancelEmphasis){
			$("#reEmphasise").buttonDisable();
		}else{
			$("#reEmphasise").buttonEnable();
		}
		if(!cancelEmphasis || reEmphasis){
			$("#cancelEmphasise").buttonDisable();		
		}else{
			$("#cancelEmphasise").buttonEnable();
		}
	}

	function disableButtons(){
	  	setDeleteButtonEnabled(false);
	  	setOtherButtonEnabled(false);
	  	toggleEmphasisButton();
	}
	function setDeleteButtonEnabled(enabled){
		if (enabled){
			$("#delete").buttonEnable();
		}else{
			$("#delete").buttonDisable();
		}
	}
	
	function setOtherButtonEnabled(enabled){
		if (enabled){
			$("#update").buttonEnable();
			$("#view").buttonEnable();
			$("#shift").buttonEnable();
		}else{
			$("#update").buttonDisable();
			$("#view").buttonDisable();
			$("#shift").buttonDisable();
		}
	}
	//切换按钮状态
	function toggleButtonState(){
	  	var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
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
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#${lowerCaseModuleName}List").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#${lowerCaseModuleName}List").getRowData(idCollection[i]);
				if(ent.isEmphasis==false || ent.isEmphasis=="false"){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}
	function afterLoad(){
		isEmphasisFormatter();
		disableButtons();
		checkExport();
	}
	function getSelectedIds(){
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function viewDialog(id){
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:dialogWidth,
			height:580,
			title:"查看${moduleCName}信息",
			url:"${path}${listNameSpace}/dispatchOperate.action?mode=view&location.id="+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function doubleClickTable(){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			 return;
		}
		viewDialog(selectedId);
	}
	function search${moduleName}(){
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}${searchNameSpace}/find${moduleName}sByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#search${moduleName}Form").serializeObject());
		if($("#isLock").val()==""){
		}else{
			postData = $.extend(postData,{"search${moduleName}Vo.isEmphasis":$("#isLock").val()});
		}
		$("#${lowerCaseModuleName}List").setPostData(postData);
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}
	function search(orgId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入单位名称') return;
		var initParam = {
				"organizationId":orgId
			}
		if($("#isLock").val()==""){
			initParam = {
					 "organizationId":orgId,
					 "search${moduleName}Vo.unitName":conditions
				}
		}else{
			initParam = {
					 "organizationId":orgId,
					 "search${moduleName}Vo.unitName":conditions,
					 "search${moduleName}Vo.isEmphasis":$("#isLock").val()
				}
		}
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}${searchNameSpace}/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#${lowerCaseModuleName}List").setPostData(initParam);
		$("#${lowerCaseModuleName}List").trigger("reloadGrid");	
	}

	$("#${lowerCaseModuleName}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
	  	multiselect:true,
	  	onSelectAll:function(data){if(toggleButtonState){toggleButtonState(data);}},
    	loadComplete: function(data){if(afterLoad){afterLoad(data);}},
		<pop:JugePermissionTag ename="view${moduleName}">
        	ondblClickRow: function(data){if(doubleClickTable){doubleClickTable(data);}},
		</pop:JugePermissionTag>
		onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}toggleEmphasisButton();}
	});
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
		checkExport();
	}
	$("#add").click(function(){
		if (!isGrid()){
			return;
		}
		if (getCurrentOrgId()==null||getCurrentOrgId()==""){
			$.notice({level:"warn", message:"请先选择一个部门"});
		}else{
			if('${moduleName}'=="ActualCompany"){
				$("#${lowerCaseModuleName}Dialog").createFrameDialog(
						$.extend(
							{
								model :"add",
								title:"新增${moduleCName}信息",
								width: dialogWidth,
								height: dialogHeight,
								data:[
									{title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?mode=add&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId(),buttons:{next:true,save:true}},
									{title:'重点要害部位',url:'${listNameSpace}/dispatchOperate.action?mode=addKeyCrucialPosition&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId(),buttons:{prev:true,next:true,save:true}},
									{title:'防范设施信息',url:'${listNameSpace}/dispatchOperate.action?mode=addPreventionFacilities&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId(),buttons:{prev:true,save:true,saveContinue:true}}
								]
							},
							function(){if(buttonFunctions)return buttonFunctions.add}
						)
					);
			}else if('${moduleName}'=="LaborEmploymentUnit"){
				$("#${lowerCaseModuleName}Dialog").createFrameDialog(
						$.extend(
							{
								model :"add",
								title:"新增${moduleCName}信息",
								width: dialogWidth,
								height: dialogHeight,
								data:[
									{title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?mode=add&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId(),buttons:{next:true,save:true}},
									{title:'业务数据',url:'${listNameSpace}/dispatchOperate.action?mode=addBusinessData&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId(),buttons:{prev:true,save:true,saveContinue:true}}
								]
							},
							function(){if(buttonFunctions)return buttonFunctions.add}
						)
					);
			}else{
			$("#${lowerCaseModuleName}Dialog").createFrameDialog(
				$.extend(
					{
						model :"add",
						title:"新增${moduleCName}信息",
						width: dialogWidth,
						height: dialogHeight,
						data:[
							{title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?mode=add&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId(),buttons:{save:true,saveContinue:true}}
						]
					},
					function(){if(buttonFunctions)return buttonFunctions.add}
				)
			);
		}}
	});
	$("#isLock").change(function(){
		$("#searchText").attr("value","");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
	$("#update").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			 return;
		}
		if('${moduleName}'=="ActualCompany"){
		$("#${lowerCaseModuleName}Dialog").createFrameDialog(
			$.extend(
				{
					model :"edit",
					title:"修改${moduleCName}信息",
					width: dialogWidth,
					height: dialogHeight,
					data:[
					   {title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?location.id='+selectedId+'&mode=edit&dailogName=${lowerCaseModuleName}Dialog',buttons:{next:true,save:true}},
					   {title:'重点要害部位',url:'${listNameSpace}/dispatchOperate.action?location.id='+selectedId+'&mode=editKeyCrucialPosition&dailogName=${lowerCaseModuleName}Dialog',buttons:{prev:true,next:true,save:true}},
					   {title:'防范设施信息',url:'${listNameSpace}/dispatchOperate.action?location.id='+selectedId+'&mode=editPreventionFacilities&dailogName=${lowerCaseModuleName}Dialog',buttons:{prev:true,save:true}}
					]
				},
				function(){if(buttonFunctions)return buttonFunctions.update}
			)
		);
		}else if('${moduleName}'=="LaborEmploymentUnit"){
			$("#${lowerCaseModuleName}Dialog").createFrameDialog(
					$.extend(
						{
							model :"edit",
							title:"修改${moduleCName}信息",
							width: dialogWidth,
							height: dialogHeight,
							data:[
								{title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?location.id='+selectedId+'&mode=edit&dailogName=${lowerCaseModuleName}Dialog',buttons:{next:true,save:true}},
								{title:'业务数据',url:'${listNameSpace}/dispatchOperate.action?location.id='+selectedId+'&mode=editBusinessData&dailogName=${lowerCaseModuleName}Dialog',buttons:{prev:true,save:true}}
							]
						},
						function(){if(buttonFunctions)return buttonFunctions.update}
					)
				);
		}else{
			$("#${lowerCaseModuleName}Dialog").createFrameDialog(
					$.extend(
						{
							model :"edit",
							title:"修改${moduleCName}信息",
							width: dialogWidth,
							height: dialogHeight,
							data:[
							   {title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?location.id='+selectedId+'&mode=edit&dailogName=${lowerCaseModuleName}Dialog',buttons:{save:true}}
							]
						},
						function(){if(buttonFunctions)return buttonFunctions.update}
					)
				);
		}
	});

	$("#cancelEmphasise").click(function(event){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId =getSelectedIds();
			if(selectedId == null || selectedId.length<=0){
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds=selectedId[i]+",";
			}
			if(selectedIds.length=2){
				selectedIds=selectedId;
				}
			$("#${lowerCaseModuleName}Dialog").createDialog({
				width:450,
				height:210,
				title:'注销提示',
				modal:true,
				url:'${path}/baseinfo/commonPopulation/emphasiseConditionDlg.jsp?locationIds='+selectedIds+'&isEmphasis=false&dailogName=${lowerCaseModuleName}&cName=${moduleCName}',
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
		if(selectedId == null || selectedId.length<=0){
			return;
		}
		var selectedIds="";
		for(var i=0;i<selectedId.length;i++){
			selectedIds+=selectedId[i]+",";
		}
		$.ajax({
			url:"${path}${listNameSpace}/updateEmphasiseById.action",
			data:{
				"locationIds": selectedIds,
				"location.isEmphasis":true
			},
			success:function(responseData){
				$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				toggleButtonState();
				checkExport();
				$.messageBox({message:"${moduleCName}取消注销成功 ！ "});
			}
		});
	});
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			 return;
		}
		var str="确定要删除吗?一经删除将无法恢复";
		$.confirm({
			title:"确认删除",
			message:str,
			okFunc: function() {
				$.ajax({
					url:"${path}${listNameSpace}/delete${moduleName}ByIds.action?locationIds="+allValue,
					success:function(data){
					    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
					    disableButtons();
					    checkExport();
						$("#${lowerCaseModuleName}List").trigger("reloadGrid");
					}
				});
			}
		});
	});
	
	$("#view").click(function(){
		doubleClickTable();
	});
	$("#reload").click(function(){
		$("#searchText").attr("value","");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#search").click(function(event){
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:650,
			height:280,
			title:'${moduleCName}查询-请输入查询条件',
			url:'${path}${listNameSpace}/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
			buttons: {
		   		"查询" : function(event){
					search${moduleName}();
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
			url:"${path}/common/import.jsp?dataType=${lowerCaseModuleName}&dialog=noticeDialog&startRow=6&templates=${upperCaseModuleName}",
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
	$("#export").click(function(event){
		if($("#${lowerCaseModuleName}List").getGridParam("records")>0){
			var postData = $("#${lowerCaseModuleName}List").getPostData();
			if($("#isLock").val()!=null && $("#isLock").val()!="" ){
				$("#${lowerCaseModuleName}List").setPostData($.extend(postData,{"search${moduleName}Vo.isEmphasis":$("#isLock").val()}));
			}
			$("#${lowerCaseModuleName}MaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出${moduleCName}信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'${lowerCaseModuleName}List',
					downloadUrl:'${path}${searchNameSpace}/download${moduleName}.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			return;
		}
	});
	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});
})
</script>