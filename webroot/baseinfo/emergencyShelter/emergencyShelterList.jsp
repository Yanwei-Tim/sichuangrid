<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="content">
<div class="ui-corner-all" id="nav">
	<pop:JugePermissionTag ename="addEmergencyShelter">
		<a id="add" href="javascript:;"><span>新增</span></a>
	</pop:JugePermissionTag> 
	<pop:JugePermissionTag ename="updateEmergencyShelter">
		<a id="update" href="javascript:void(0)"><span>修改</span></a>
	</pop:JugePermissionTag> 
	<pop:JugePermissionTag ename="viewEmergencyShelter">
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
	</pop:JugePermissionTag> 
	<pop:JugePermissionTag ename="deleteEmergencyShelter">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag> 
	<pop:JugePermissionTag ename="searchEmergencyShelter">
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
	</pop:JugePermissionTag> 
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a> 
	<pop:JugePermissionTag ename="cancelAttendedEmergencyShelter">
		<a id="cancelEmphasise" href="javascript:void(0)"><span>注销</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="attendedEmergencyShelter">
		<a id="reEmphasise" href="javascript:void(0)"><span>取消注销</span></a>
	</pop:JugePermissionTag>
<!-- 
<div style="float: right; white-space: nowrap;"><select
	id="isLock" name="" class="S_object">
	<option value="">全部</option>
	<option value="true" selected="selected">正常</option>
	<option value="false">已注销</option>
</select></div>
 -->
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
<table id="emergencyShelterList">
</table>
<div id="emergencyShelterListPager"></div>
</div>
<div id="emergencyShelterDialog"></div>
<div id="noticeDialog"></div>
<div id="emergencyShelterMaintanceDialog"></div>
<div id="helpPersonnelDialog"></div>
<div id="helpPrecordDialog"></div>
<div id="scanHeaderImage"></div>
</div>

<script type="text/javascript">
var gridOption={
	colModel:[
        {name:"id",index:"id",hidden:true,frozen:true},
	    {name:"name",id:"name",label:'场所名称',formatter:companyNameFormatter,width:150,frozen:true},
	    {name:"address",label:'场所地址',align:'center',width:80},
	    {name:"longAititude",label:'经纬度',align:'center',width:150},
	    {name:"siteType",label:'场地类型',align:'center',width:150},
	    {name:"area",label:'面积',align:'center',width:80},
	    {name:"fullPersonNum",label:'可容纳人数',align:'center',width:150},
	    {name:"aroundVillageNum",label:'周边社区/村数量',align:'center',width:100},
	    {name:"aroundPersonNum",label:'周边人口',align:'center',width:100},
	    {name:"organization.orgName",label:'所属网格',align:'center',width:100,hidden:true},
	    {name:"identificationNum",label:'标识',align:'center',width:100},
	    {name:"imageSymbolNum",label:'图形符号',align:'center',width:100,hidden:true},
	    {name:"pointNum",label:'指示',align:'center',width:100,hidden:true},
	    {name:"remark",sortable:false,label:"备注",hidden:true,width:100},
	    {name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
	    {name:"logOutTime",sortable:false,label:"注销时间",align:'center',hidden:true,width:100},
	    {name:"logOutReason",sortable:false,label:"注销原因",align:'center',hidden:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=600;	

function companyNameFormatter(el,options,rowData){
	if(rowData.isEmphasis==false||rowData.isEmphasis=='false'){
		return "<font color='#778899'>"+rowData.name+"</font>";
	}
	return "<font color='#000'>"+rowData.name+"</font>";
}
jQuery.validator.addMethod("fund", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,4})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});

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
	$("#emergencyShelterList").setGridParam({
		url:"${path}/baseinfo/emergencyShelterManage/emergencyShelterList.action",
		datatype: "json",
		page:1
	});
	$("#emergencyShelterList").setPostData(initParam);
	$("#emergencyShelterList").trigger("reloadGrid");
}
function checkExport(){
	if($("#emergencyShelterList").getGridParam("records")>0
			|| $("#emergencyShelterList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
$(function(){
	
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

	function toggleEmphasisButton(){
		var selectedIds=$("#emergencyShelterList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<=0){
			$("#reEmphasise").buttonDisable();
			$("#cancelEmphasise").buttonDisable();		
			return;
		}
		var reEmphasis=false;
		var cancelEmphasis=false;
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#emergencyShelterList").getRowData(selectedIds[i]);
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
	function toggleButtonState(){
	  	var selectedIds=$("#emergencyShelterList").jqGrid("getGridParam", "selarrrow");
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
		var selectedIds = $("#emergencyShelterList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#emergencyShelterList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#emergencyShelterList").getRowData(idCollection[i]);
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
		var selectedIds = $("#emergencyShelterList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function viewDialog(id){
		$("#emergencyShelterDialog").createDialog({
			width:dialogWidth,
			height:580,
			title:"查看应急避难场所信息",
			url:"${path}/baseinfo/emergencyShelterManage/dispatchOperate.action?mode=view&location.id="+id,
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
	function searchEmergencyShelter(){
		$("#emergencyShelterList").setGridParam({
			url:"${path}${searchNameSpace}/findEmergencySheltersByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchEmergencyShelterForm").serializeObject());
		if($("#isLock").val()==""){
		}else{
			postData = $.extend(postData,{"searchEmergencyShelterVo.isEmphasis":$("#isLock").val()});
		}
		$("#emergencyShelterList").setPostData(postData);
	    $("#emergencyShelterList").trigger("reloadGrid");
	}
	function search(orgId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入场所名称') return;
		var initParam = {
				"organizationId":orgId
			}
		if($("#isLock").val()==""){
			initParam = {
					 "organizationId":orgId,
					 "searchEmergencyShelterVo.unitName":conditions
				}
		}else{
			initParam = {
					 "organizationId":orgId,
					 "searchEmergencyShelterVo.unitName":conditions,
					 "searchEmergencyShelterVo.isEmphasis":$("#isLock").val()
				}
		}
		$("#emergencyShelterList").setGridParam({
			url:"${path}${searchNameSpace}/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#emergencyShelterList").setPostData(initParam);
		$("#emergencyShelterList").trigger("reloadGrid");	
	}

	$("#emergencyShelterList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
	  	multiselect:true,
	  	onSelectAll:function(data){if(toggleButtonState){toggleButtonState(data);}},
    	loadComplete: function(data){if(afterLoad){afterLoad(data);}},
		<pop:JugePermissionTag ename="viewEmergencyShelter">
        	ondblClickRow: function(data){if(doubleClickTable){doubleClickTable(data);}},
		</pop:JugePermissionTag>
		onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}toggleEmphasisButton();}
	});
	jQuery("#emergencyShelterList").jqGrid('setFrozenColumns');
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
			$("#emergencyShelterDialog").createFrameDialog(
				$.extend(
					{
						model :"add",
						title:"新增应急避难场所信息",
						width: dialogWidth,
						height: dialogHeight,
						data:[
							{title:'基本信息',url:'/baseinfo/emergencyShelterManage/dispatchOperate.action?mode=add&dailogName=emergencyShelterDialog&orgId='+getCurrentOrgId(),buttons:{save:true,saveContinue:true}}
						]
					},
					function(){if(buttonFunctions)return buttonFunctions.add}
				)
			);
		}
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
			$("#emergencyShelterDialog").createFrameDialog(
					$.extend(
						{
							model :"edit",
							title:"修改应急避难场所信息",
							width: dialogWidth,
							height: dialogHeight,
							data:[
							   {title:'基本信息',url:'/baseinfo/emergencyShelterManage/dispatchOperate.action?location.id='+selectedId+'&mode=edit&dailogName=emergencyShelterDialog',buttons:{save:true}}
							]
						},
						function(){if(buttonFunctions)return buttonFunctions.update}
					)
				);
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
			$("#emergencyShelterDialog").createDialog({
				width:450,
				height:210,
				title:'注销提示',
				modal:true,
				url:'${path}/baseinfo/commonPopulation/emphasiseConditionDlg.jsp?locationIds='+selectedIds+'&isEmphasis=false&dailogName=emergencyShelter&cName=应急避难场所',
				buttons: {
				   "保存" : function(event){
					   $("#emphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
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
			url:"${path}/baseinfo/emergencyShelterManage/updateEmphasiseById.action",
			data:{
				"locationIds": selectedIds,
				"location.isEmphasis":true
			},
			success:function(responseData){
				$("#emergencyShelterList").trigger("reloadGrid");
				toggleButtonState();
				checkExport();
				$.messageBox({message:"应急避难场所取消注销成功 ！ "});
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
					url:"${path}/baseinfo/emergencyShelterManage/deleteEmergencyShelterByIds.action?locationIds="+allValue,
					success:function(data){
					    $.messageBox({message:"已经成功删除该应急避难场所信息!"});
					    disableButtons();
					    checkExport();
						$("#emergencyShelterList").trigger("reloadGrid");
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
		$("#emergencyShelterDialog").createDialog({
			width:650,
			height:280,
			title:'应急避难场所查询-请输入查询条件',
			url:'${path}/baseinfo/emergencyShelterManage/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
			buttons: {
		   		"查询" : function(event){
					searchEmergencyShelter();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});
});
</script>