<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<%
	String moduleName = request.getParameter("moduleName");
	request.setAttribute("moduleName", moduleName);
	request.setAttribute("lowerCaseModuleName", moduleName
			.substring(0,1).toLowerCase()
			+ moduleName.substring(1));
	request.setAttribute("upperCaseModuleName", moduleName
			.toUpperCase());
	String listNameSpace = request.getParameter("listNameSpace");
	if (null == listNameSpace || "".equals(listNameSpace)) {
		listNameSpace = "/baseinfo/" + moduleName.substring(0,1).toLowerCase()
				+ moduleName.substring(1) + "Manage";
	}
	request.setAttribute("listNameSpace", listNameSpace);

	String searchNameSpace = request.getParameter("searchNameSpace");
	if (null == searchNameSpace || "".equals(searchNameSpace)) {
		searchNameSpace = "/baseinfo/search"+moduleName;
	}
	request.setAttribute("searchNameSpace", searchNameSpace);

	request.setAttribute("moduleCName", request
			.getParameter("moduleCName"));
%>
<div class="ui-corner-all" id="nav">
	<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
		<div class="ui-widget autosearch">
			<input class="basic-input" type="text" value="请输入部件名称或部件标识码" name="searchText" id="searchText" maxlength="18" class="searchtxt"
				 style="width:180px;" onblur="value=(this.value=='')?'请输入部件名称或部件标识码':this.value;" onfocus="value=(this.value=='请输入部件名称或部件标识码')?'':this.value;" />
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
	</div>
	<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	<pop:JugePermissionTag ename="search${moduleName}">
		<a id="search" href="javascript:void(0)"><span><strong
			class="ui-ico-cx"></strong>高级搜索</span></a>
	</pop:JugePermissionTag>
	<span class="lineBetween "></span>
	<s:if test='#parameters.partType[0]!=null'>
	</s:if>
	<s:else>
	<pop:JugePermissionTag
		ename="add${moduleName}">
		<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
	</pop:JugePermissionTag>
	</s:else>
	<pop:JugePermissionTag ename="delete${moduleName}">
		<a id="delete" href="javascript:void(0)"><span><strong
			class="ui-ico-sc"></strong>批量删除</span></a>
	</pop:JugePermissionTag>
	<!--<a href="javascript:;" class="nav-dropdownBtn"><span>导出Excel</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	 <div class="nav-sub-buttons">
	 <pop:JugePermissionTag
	ename="import${moduleName}">
	<a id="import" href="javascript:void(0)"><span><strong
		class="ui-ico-dr"></strong>Excel导入</span></a>
	</pop:JugePermissionTag><pop:JugePermissionTag ename="download${moduleName}">
		<a id="export" href="javascript:void(0)"><span><strong
			class="ui-ico-dc"></strong>导出Excel</span></a>
	</pop:JugePermissionTag>
	</div>-->
	<s:if test='#parameters.partType[0]!=null'>
	</s:if>
	<s:else>
	<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	<div class="nav-sub-buttons">
	 <pop:JugePermissionTag ename="cancelAttended${moduleName}">
		<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
			class="ui-ico-CancelAttention"></strong>取消关注</span></a>
	</pop:JugePermissionTag> <pop:JugePermissionTag ename="attended${moduleName}">
		<a id="reEmphasise" href="javascript:void(0)"><span><strong
			class="ui-ico-refocusOn"></strong>重新关注</span></a>
	</pop:JugePermissionTag>
	</div>
	</s:else>
	<s:if test='#parameters.partType[0]!=null'>
	<pop:JugePermissionTag ename="download${moduleName}">
		<a id="export" href="javascript:void(0)"><span><strong
			class="ui-ico-dc"></strong>导出Excel</span></a>
	</pop:JugePermissionTag>
	</s:if>
	<s:else>
		<pop:JugePermissionTag ename="import${moduleName},download${moduleName}">
			<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="import${moduleName}">
				<a id="import" href="javascript:void(0)"><span><strong
						class="ui-ico-dr"></strong>Excel导入</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="download${moduleName}">
				<a id="export" href="javascript:void(0)"><span><strong
						class="ui-ico-dc"></strong>导出Excel</span></a>
			</pop:JugePermissionTag>
		</div>
	</s:else>
 	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
</div>
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
	<div id="supervisorMaintanceDialog"></div>
<script>
var partType="";
var notExecute=new Array();
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organizationId": orgId,
    	"location.isEmphasis":false,
    	"partType":partType
	}

	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}${listNameSpace}/${lowerCaseModuleName}List.action",
		datatype: "json",
		page:1
	});
	$("#${lowerCaseModuleName}List").setPostData(initParam);
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}

function search${moduleName}(){
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}${searchNameSpace}/find${moduleName}sByQueryCondition.action",
		datatype: "json",
		page:1,
		mtype:"post"
	});
	var postData=$.extend({"organizationId":getCurrentOrgId()},{"partType":partType},$("#searchDustbinFrom").serializeObject());
	$("#${lowerCaseModuleName}List").setPostData(postData);
    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
}

function view${moduleName}(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId);
	var id = rowData.encryptId;
	
	if(id==null){
		 return;
	}
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看${moduleCName}信息",
		url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?mode=view&location.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#${lowerCaseModuleName}List").getDataIDs();
	for(var i=0;i<idCollection.length;i++){
		var ent =  $("#${lowerCaseModuleName}List").getRowData(idCollection[i]);
		if(ent.isEmphasis==true || ent.isEmphasis=="true"){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}
function afterLoad(){
	isEmphasisFormatter();
	disableButtons();
	checkExport();
	changeColor();
}
function disableButtons(){
  	setDeleteButtonEnabled(false);
  	setOtherButtonEnabled(false);
  	toggleEmphasisButton();
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
function setDeleteButtonEnabled(enabled){
//		if (enabled){
//			$("#delete").buttonEnable();
//		}else{
//			$("#delete").buttonDisable();
//		}
}
function toggleEmphasisButton(){
	var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length<1){
//			$("#reEmphasise").buttonDisable();
//			$("#cancelEmphasise").buttonDisable();
		return;
	}
	if(selectedIds.length==1){
		var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedIds[0]);
		if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
//				$("#cancelEmphasise").buttonDisable();
//				$("#reEmphasise").buttonEnable();
		}else{
//				$("#cancelEmphasise").buttonEnable();
//				$("#reEmphasise").buttonDisable();
			}
		}
	if(selectedIds.length>1){
//			$("#reEmphasise").buttonEnable();
//			$("#cancelEmphasise").buttonEnable();
		}
	/*var reEmphasis=false;
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
	}*/
}
function checkExport(){
//		if($("#${lowerCaseModuleName}List").getGridParam("records")>0
//				|| $("#${lowerCaseModuleName}List").getGridParam("selrow")!=null){
//			$("#export").buttonEnable();
//		}else{
//			$("#export").buttonDisable();
//		}
}
function changeColor(){
	if(notExecute==null||notExecute.length==0){
		return;
	}
	for(var i=0;i<notExecute.length;i++){
		var rowData=$("#${lowerCaseModuleName}List").getRowData(notExecute[i]);
		$("#"+notExecute[i]).css('background','red');
		$("#${lowerCaseModuleName}List").setSelection(notExecute[i])
	}
	notExecute.splice(0,notExecute.length);
}


function updateOperator(selectedId){
	var ent =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
	if(ent.isEmphasis==true||ent.isEmphasis=='true'){
		 $.messageBox({level:'warn',message:"该${moduleCName}信息已经取消关注，不能修改!"});
		 return;
	}
  var encryptId=ent.encryptId;

	$("#${lowerCaseModuleName}Dialog").createFrameDialog(
			$.extend(
				{
					model :"edit",
					title:"修改${moduleCName}",
					width: dialogWidth,
					height: dialogHeight,
					data:[
					     {title:'基本信息',url:'${listNameSpace}/dispatchOperateByEncrypt.action?location.id='+encryptId+'&mode=edit&dailogName=${lowerCaseModuleName}Dialog',buttons:{save:true}}
					     ]
				},
				function(){if(buttonFunctions)return buttonFunctions.update}
			)
		);
}

function deleteOperator(allValue){

    var id=deleteOperatorByEncrypt("${lowerCaseModuleName}",allValue,"encryptId");
	var str="确定要删除吗?一经删除将无法恢复";
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				url:"${path}${listNameSpace}/delete${moduleName}ByIds.action",
				type:"post",
				data:{
					"locationIds":id
				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
				    disableButtons();
				    checkExport();
					$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				}
			});
		}
	});
}

function toggleButtonState(){
	  	var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	  	setDeleteButtonEnabled(selectedRowCount>0);
	  	setOtherButtonEnabled(selectedRowCount==1);
	  	toggleEmphasisButton();
}

function search(orgId){
	var conditions = $("#searchText").val();
	if(conditions == '请输入部件名称或部件标识码') return;
	var	initParam = {
			 "organizationId":orgId,
			 "search${moduleName}Vo.fastSearchKeyWords":conditions,
			 "search${moduleName}Vo.isEmphasis":0,
			 "partType":partType
			 
	}
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}${searchNameSpace}/fastSearch.action",
		datatype: "json",
		page:1
	});
	$("#${lowerCaseModuleName}List").setPostData(initParam);
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}

function loadGridFunction(){
	$("#${lowerCaseModuleName}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
	  	multiselect:true,
	  	onSelectAll:function(data){if(toggleButtonState){toggleButtonState(data);}},
    	loadComplete: function(data){if(afterLoad){afterLoad(data);}},
		<pop:JugePermissionTag ename="view${moduleName}">
        	ondblClickRow: view${moduleName},
		</pop:JugePermissionTag>
		onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}toggleEmphasisButton();}
	});
}
function exportFunction(){
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
		   				exceldownloadSubmitForm();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
			return;
		}
	});
}

function callback(){
	var dfop = {
		partTypeValue:'${parameters.partType[0]}',
    	lowerCaseModuleNameValue:'${lowerCaseModuleName}',
    	upperCaseModuleNameValue:'${upperCaseModuleName}',
    	searchNameSpaceValue:'${searchNameSpace}',
    	moduleNameValue:'${moduleName}',
    	addModule:'<pop:JugePermissionTag ename="add${moduleName}">true</pop:JugePermissionTag>',
    	viewModule:'<pop:JugePermissionTag ename="view${moduleName}">true</pop:JugePermissionTag>',
    	updateModule:'<pop:JugePermissionTag ename="update${moduleName}">true</pop:JugePermissionTag>',
    	deleteModule:'<pop:JugePermissionTag ename="delete${moduleName}">true</pop:JugePermissionTag>',
    	searchModule:'<pop:JugePermissionTag ename="search${moduleName}">true</pop:JugePermissionTag>',
    	listNameSpaceValue:'${listNameSpace}',
    	moduleCNameValue:'${moduleCName}',
    	isEmphasis:'$("#${lowerCaseModuleName}List").setPostData($.extend(postData,{"search${moduleName}Vo.isEmphasis":$("#isLock").val()}));'
    }
    TQ.commonPart(dfop)
}
$.cacheScript({
    url:'/resource/getScript/baseinfo/commonPopulation/commonPart.js',
    callback: callback
})
</script>