<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
#commonPopulation {
	width: 99.8%;
	margin-bottom: 5px;
	padding: 10px 0;
	background: #EAF4FD;
	border: 1px solid #A6C9E2;
}

.container_24 .x-form-field-wrap .x-form-trigger {
	top: 2px;
}

#commonPopulation .areaName {
	float: left;
	width: 65px;
	display: block;
}

.x-form-field-wrap {
	float: left;
}

#ext-gen7 .x-form-arrow-trigger{*top:-1px;}

#commonPopulation .Explain {
	line-height: 25px;
	padding: 0 0 0 30px;
}

#commonPopulation .Explain .systemButton {
	float: left;
	width: 60px;
	height: 25px;
	display: inline-block;
	*display:inline;
	*zoom:1;
	margin:0 2px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font: 12px/25px "\5b8b\4f53";
	color: #fff;
	text-align: center;
	background: #69A1DB;
}

.form-lb1 {
	font-weight: bold;
	color: #333;
}

.dashline {
	margin: 5px;
	border-bottom: 1px dashed #A6C9E2;
}

.form-text {
	width: 112px;
}

#birthdayStrart,#birthdayEnd{border:1px solid #7F9DB9;}
</style>
<input type="hidden" id="currOrgLevel"
	value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().orgLevel.id"/>" />
<div style="width: 100%;">
	<table id="searchResultList"></table>
	<div id="searchResultListPager"></div>
	<div id="searchDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />

function search(){
	var conditions = $("#search_text").val();
	$("#searchResultList").setGridParam({
		url:"${path}/integrativeQuery/populationIntegrativeQuery/queryPopulationForWorkBench.action",
		datatype: "json",
		page:1
	});
	$("#searchResultList").setPostData({
		"searchText":conditions,
		"currOrgId":USER_ORG_ID
	});
	
	$("#searchResultList").trigger("reloadGrid");
}


function idCardNoFont(el,options,rowData){
	if(!el){return ""}
	if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
		return "<font color='#778899'>"+rowData.idCardNo+"</font>";
	}else{
		return "<font color='#000'>"+rowData.idCardNo+"</font>";
	}
}
function operateFormatter(el, options, rowData){
	return "<a href='javascript:viewPopulationInfo("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
}

function actualPopulationName(el,options,rowData){
	if(el=="floatingPopulations"){
		return "流动人口";
	}
	if(el=="householdstaffs"){
		return "户籍人口";
	}
	if(el=="unsettledpopulations"){
		return "未落户人口";
	}
	if(el=="overseapersonnel"){
		return "境外人员";
	}
	
}

function viewPopulationInfo(dataId){
	var rowId=  $("#searchResultList").getGridParam("selrow");
	var row=$("#searchResultList").getRowData(rowId);
	var url;
	if((row.sid).indexOf("householdstaffs")>=0){
		url='${path}/baseinfo/householdStaff/dispath.action?mode=view&population.id='+row.id;
	}
	if((row.sid).indexOf("floatingPopulations")>=0){
		url='${path}/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id='+row.id;
	}  
	if((row.sid).indexOf("unsettledpopulations")>=0){
		url='${path}/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id='+row.id;
	}
	if((row.sid).indexOf("overseapersonnel")>=0){
		url='${path}/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+row.id;
	}

	$("#searchDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看人口信息',
		modal : true,
		url:url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

$("#searchResultList").jqGridFunction({
	mtype:'post',
	width: 900,
	height: 400,
	datatype: "local",
		colModel:[
		{name:"sid",index:'sid',width:50,frozen:true,hidedlg:true,sortable:false,hidden:true,key:true},
		{name:"id", index:"id", hidden:true,frozen : true},
		{name:"操作",index:'sid',width:50,formatter:operateFormatter,frozen:true,hidedlg:true,sortable:false},
		{name:'name',index:"name",label:"姓名",hidedlg:true,width:120,frozen:true},
		{name:'idCardNo',index:'idCardNo', label:'证件号码', formatter:idCardNoFont, width:130,hidedlg:true,frozen:true},
		{name:"gender", label:"性别",index:"gender",width:50, formatter:genderFormatter,align:"center"},
		{name:"birthday", index:'birthday', label:'出生日期', width:80 },
		{name:'nation',index:"nation",label:"民族",formatter:nationFormatter,width:90},
		{name:'nativePoliceStation',index:"nativePoliceStation",label:"户籍地",width:200,sortable:false},
		{name:'currentAddress', sortable:false, label:'常住地址', width:150 },
		{name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:200},
		{name:'actualPopulationType',index:'actualPopulationType',formatter:actualPopulationName, label:'实口类型', width:90,sortable:false }

	], 
	ondblClickRow:function(data){
		viewPopulationInfo();
	}

});
jQuery("#searchResultList").jqGrid('setFrozenColumns');

$(document).ready(function(){
	search();
});


</script>
