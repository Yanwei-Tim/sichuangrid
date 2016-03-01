<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
	<div class="box">
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="builddatasList"> </table>
			<div id="builddatasListPager"></div>
		</div>
	</div>
	</div>
<div id="builddatasMaintanceDialog"></div>
<div id="builddatasDialog"></div>
<div id="gisBuilddatasDialog"></div>
<div id="generatedLayoutDialog"></div>
<div id="buildLayoutDialog"></div>

<div style="display: none;">
<pop:JugePermissionTag ename="BuilddatasManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="buildingstructures" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
var dialogWidth=700;
var dialogHeight=480;
var title=$("#title").html();
$(document).ready(function(){
	$("#builddatasList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	   		{name:"id",index:"id",hidden:true},
			{name:'buildingid',index:'BUILDINGID',label:'楼宇ID',width:120,hidden:true},
			{name:'buildingname',index:'BUILDINGNAME',label:'楼宇名称',width:120},
			{name:'buildingaddress',index:'BUILDINGADDRESS',label:'楼宇地址',width:120},
			{name:'owner',index:'OWNER',label:'业主',width:120},
			{name:'responsibleperson',index:'RESPONSIBLEPERSON',label:'负责人',width:120},
			{name:'phone',index:'PHONE',label:'联系电话',width:120},
			{name:'buildingstructures',index:'BUILDINGSTRUCTURES',label:'建筑结构',formatter:buildingstructuresFormatter,width:120},
			{name:'centerx',index:'CENTERX',label:'楼宇中心坐标X',width:120,hidden:true},
			{name:'centery',index:'CENTERY',label:'楼宇中心坐标Y',width:120,hidden:true},
			{name:'isLayout',index:'isLayout',label:'是否已生成布局',formatter:layoutFormatter,width:120,hidden:true}
		],
		multiselect:true,
		ondblClickRow: viewBuilddatas
	});
	
	if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchMoreBuilddatasGrid();
		}else if('<s:property value="#parameters.isSearch"/>' == 2){
			searchOneBuilddatasGrid();
		}else{
			onOrgChangedForStat(getOrgIdForStat(),isGrid());
		}
	}
	
	
	
});

function searchOneBuilddatasGrid() {
	$("#builddatasList").setGridParam({
		url:'${path}/builddatasManage/searchBuilddatas.action',
		datatype: "json",
		page:1
	});
	$("#builddatasList").setPostData({
		"builddatasSearchVo.organization.id":getCurrentOrgId(),
		"organization.id":getCurrentOrgId(),
		"builddatasSearchVo.buildingname":$("#searchByBuildName").val()
	});
	$("#builddatasList").trigger("reloadGrid");
	$("#builddatasMaintanceDialog").dialog("close");
}

function searchMoreBuilddatasGrid() {
	var formdata = $("#maintainForm").serialize();
	if(formdata!=''){
		formdata = formdata.replace(/=/g, '":"');
		formdata = formdata.replace(/&/g, '","');
		formdata += '"';
	}
	formdata = decodeURIComponent('{"'+formdata+'}');
	
	$("#builddatasList").setGridParam({
		url: '${path}/builddatasManage/searchBuilddatas.action',
		datatype: "json",
		page:1
	});
	$("#builddatasList").setPostData(parseObj(formdata));
	$("#builddatasList").trigger("reloadGrid");
	$("#statisticsDialog").dialog("close");
}

function viewBuilddatas(rowId){
	if(rowId==null){
 		return;
	}
	$("#builddatasMaintanceDialog").createDialog({
		width: 600,
		height: 280,
		title: '查看'+title+'信息',
		url: '${path}/builddatasManage/dispatch.action?mode=view&builddatas.id=' + rowId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function onOrgChangedForStat(orgId, isgrid){
	$("#builddatasList").setGridParam({
		url:'${path}/builddatasManage/findBuilddatasByOrgInternalCode.action',
		datatype: "json",
		page:1
	});
	$("#builddatasList").setPostData({
    	"organization.id":getCurrentOrgId()
   	});
	$("#builddatasList").trigger("reloadGrid");
}
function parseObj( strData ){
	return (new Function( "return " + strData ))();
}

function layoutFormatter(el, options, rowData){
	if(rowData.isLayout != null){
		if(0 == rowData.isLayout){
			return "否";
		}else if(1 == rowData.isLayout){
			return "是";
		}
	}
}
function getOrgIdForStat(){
	var orgIdForStat = $("#orgIdForStat").val();
	if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
		return getCurrentOrgId();
	}else{
		return orgIdForStat;
	}
}
</script>