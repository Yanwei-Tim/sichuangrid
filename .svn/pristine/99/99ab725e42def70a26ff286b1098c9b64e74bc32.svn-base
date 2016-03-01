<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
	<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
		<div class="userState">
			<select id="displayLevel" name="displayLevel" class="basic-input" >
				<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
				<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">所有下辖</option>
				<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
			</select>
			<select id="teamClazz" name="teamClazz" class="basic-input" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
			</select>
		</div>
		<pop:JugePermissionTag ename="searchServiceRecord">
		<a id="searchServiceRecord" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
	</div>
	<span class="lineBetween "></span>
	<pop:JugePermissionTag ename="addServiceRecord">
	<a id="addServiceRecord" href="javascript:void(0)"><span>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="updateServiceRecord">
	<a id="updateServiceRecord" href="javascript:void(0)"><span>修改</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteServiceRecord">
	<a id="deleteServiceRecord" href="javascript:void(0)"><span>批量删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="exportServiceRecord">
	<a id="exportServiceRecord" href="javascript:void(0)"><span>导出</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="developPeopleLogForServiceRecord">
	<a id="developPeopleLogForServiceRecord" href="javascript:void(0)"><span>生成民情日志</span></a>
	</pop:JugePermissionTag>
	<a id="reloadServiceRecord"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="serviceRecordList"> </table>
		<div id="serviceRecordListPager"></div>
	</div>
	<div id="serviceRecordDialog"></div>
</div>
<script type="text/javascript">
function checkedOrgId(orgID){
	if(orgID != USER_ORG_ID){
		$.messageBox({
			message:"当前用户只能操作所在层级的组织！",
			level: "warn"
		 });
		return false;
	}else{
		return true;
	}
}
function formatterAttachFile(el,options,rowData){
	if(rowData.attachments.length>0){
		$("#serviceRecordList").data(String(rowData.id), rowData.attachments);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}
function operateFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateServiceRecord'> <a href='javascript:;' onclick='updateOperator(event,"+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='deleteServiceRecord'> | <a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function updateOperator(event,selectedId){
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$("#serviceRecordDialog").createDialog({
		width: 600,
		height: 400,
		title: '修改服务记录',
		url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=edit&serviceRecord.id='+selectedId,
		buttons: {
	   		"保存" : function(event){
				$("#serviceRecord_form").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function developPeopleLogOperator(selectedId) {
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$("#serviceRecordDialog").createDialog({
		width: 720,
		height: 600,
		title: '由服务记录转成民情日志',
		url:'${path}/baseinfo/serviceRecordManage/preDevelopPeopleLog.action?dialogName=serviceRecordDialog&serviceRecord.id='+selectedId,
		buttons: {
	   		"保存" : function(event){
				$("#maintainPeopleLogForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function deleteOperator(event,selectedIds){
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"该服务记录信息确定要删除吗?",
		okFunc: function() {
			$.ajax({
				url:"${path}/baseinfo/serviceRecordManage/deleteServiceRecordByIds.action?recordIds="+selectedIds,
				success:function(data){
					$("#serviceRecordList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除服务记录信息!"});
				    afterLoad();
				}
			});
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
$(document).ready(function(){
	<pop:formatterProperty name="manageTeam.teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
	<pop:formatterProperty name="manageTeam.teamClazz" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
	$("#serviceRecordList").jqGridFunction({
		datatype: "local",
		colNames:['id',"操作",'服务时间','服务人员','所在团队名称','团队大类','团队类别','服务对象','内容','附件'],
		colModel:[
			{name:'id',index:'id',sortable:false,hidden:true},
			{name:"operation",index:"id",sortable:false,formatter:operateFormatter,width:80,align:"center"},
			{name:'occurDate',sortable:true,width:100,align:"center"},
			{name:'serviceMembers',sortable:false, width:150},
			{name:'manageTeam.name',sortable:false, width:150},
			{name:'manageTeam.teamClazz',sortable:false,formatter:teamClazzFormatter, width:120},
			{name:'manageTeam.teamType',sortable:false,formatter:teamTypeFormatter, width:120},
			{name:'serviceObjects',sortable:false, width:150},
			{name:'serviceContent',sortable:false, width:150},
			{name:'attachments',sortable:false, width:200, formatter:formatterAttachFile}
		],
		loadComplete: afterLoad,
		multiselect:true,
		onSelectRow:selectRow,
		ondblClickRow: viewServiceRecord
	});
	loadRecords();
	$("#displayLevel").change(function(event){
		loadRecords();
	});
	$("#teamClazz").change(function(event){
		loadRecords();
	});
	$("#searchServiceRecord").click(function(){
		$("#serviceRecordDialog").createDialog({
			width: 650,
			height: 280,
			title:'服务记录查询-请输入查询条件',
			url:'${path}/baseinfo/serviceTeamManage/serviceRecord/searchServiceRecord.jsp',
			buttons: {
				"查询" : function(event){
					searchSuperviseRecords();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#addServiceRecord").click(function(){
		if(!checkedOrgId(getCurrentOrgId())){
			return;
		}
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 400,
			title: '新增服务记录',
			url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=add',
			buttons: {
				"保存" : function(event){
					$("#serviceRecord_form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#updateServiceRecord").click(function(){
		var selectedId = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条数据再进行修改！"});
			return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行修改！"});
			return;
		}
		updateOperator(event,selectedId);
	});
	$("#deleteServiceRecord").click(function(){
		var selectedIds = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedIds || selectedIds.length == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行删除！"});
			return;
		}
		deleteOperator(event,selectedIds);
	});
	$("#viewServiceRecord").click(function(){
		var selectedId = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条数据再进行查看！"});
			return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行查看！"});
			return;
		}
		viewServiceRecord(selectedId);
	});
	$("#exportServiceRecord").click(function(){
		if ($("#serviceRecordList").getGridParam("records")>0){
			var postData = $("#serviceRecordList").getPostData();
			$("#serviceRecordList").setPostData($.extend(postData,{'searchServiceRecordVo.displayLevel':$("#displayLevel").val(),'searchServiceRecordVo.organization.id':$("#currentOrgId").val(),"searchServiceManageTeamVo.displayLevel":$("#displayLevel").val()}));
			$("#serviceRecordDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出服务记录信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'serviceRecordList',
					downloadUrl:'${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordsForTeam.action'
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
			$.messageBox({level:'warn',message:"没有可导出的数据！"});
			return;
		}
	});
	$("#developPeopleLogForServiceRecord").click(function(){
		var selectedId = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条数据再进行生成民情日志！"});
			return;
		}
		if(selectedId.length > 1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行生成民情日志！"});
			return;
		}
		developPeopleLogOperator(selectedId);
	});
	$("#reloadServiceRecord").click(function(){
		loadRecords();
	});
	function afterLoad() {
		loadFiles();
	}
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("serviceRecordList");
		var count = $("#serviceRecordList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("serviceRecordList", true);
		}else{
			jqGridMultiSelectState("serviceRecordList", false);
		}
	}
	function viewServiceRecord(selectedId) {
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 400,
			title: '查看服务记录信息',
			url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=view&serviceRecord.id='+selectedId,
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function loadData() {
		var listParam = {
			'searchServiceRecordVo.teamClazz':$("#teamClazz").val(),
			'searchServiceRecordVo.displayLevel':$("#displayLevel").val(),
			'searchServiceRecordVo.organization.id':$("#currentOrgId").val()
		};
		$("#serviceRecordList").setGridParam({
			url:'${path}/baseinfo/searchServiceRecordManage/searchServiceRecords.action',
			datatype: "json",
			page:1
		});
		$("#serviceRecordList").setPostData(listParam);
		$("#serviceRecordList").trigger("reloadGrid");
	}
	function searchSuperviseRecords() {
		var data = $("#serviceRecord_form").serializeArray();
		$("#serviceRecordList").setGridParam({
			url:"${path}/baseinfo/searchServiceRecordManage/searchServiceRecords.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var dataJson={};
		for(i=0;i<data.length;i++){
 			if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		$("#serviceRecordList").setPostData($.extend({'searchServiceRecordVo.displayLevel':$("#displayLevel").val(),'searchServiceRecordVo.organization.id':$("#currentOrgId").val()},dataJson));
	    $("#serviceRecordList").trigger("reloadGrid");
	}
	function loadRecords() {
		loadData();
	}
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			var popMoreData = [];
			$.each($("#serviceRecordList").data($(n).attr("serviceRecordId")),function(ind, fn){
				popMoreData[ind]={id:fn.id, url:'${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId='+fn.id, text:fn.fileName,clickFun:function(){}};
			});
			$(n).popUpMore({data: popMoreData});
		});
	}
});
</script>