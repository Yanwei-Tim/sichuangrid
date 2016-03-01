<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.tianque.baseInfo.companyPlace.constacts.ModulTypes"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>

<% 
	String modul =request.getParameter("modul") ;
	request.setAttribute("modul",modul);
	String modulType = request.getParameter("modulType");
	request.setAttribute("modulType",modulType);
	String modulKey = ModulTypes.getKeyByName(modul.toUpperCase()+"_KEY");
	request.setAttribute("modulKey",modulKey);
%>

<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="companyPlaceList"></table>
		<div id="companyPlaceListPager"></div>
	</div>
	<div id="companyPlaceMaintanceDialog"></div>
	
	<input name="modulValue" id="modulValue" value="${modul }" type="hidden">
	<input name="modulType" id="modulType" value="${modulType }" type="hidden">
	<input name="modulKey" id="modulKey" value="${modulKey }" type="hidden">
	<input name="modulTitle" id="modulTitle" value="<s:property value="@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@getTypeByName(#request.modul)"/>" type="hidden"/> 
	<input name="companyPlaceId" id="cids_id" value="" type="hidden">
</div>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" />
<pop:formatterProperty name="attention" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

var dialogWidth=800;
var dialogHeight=510;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();

var modulValue = $("#modulValue").val();
var modulTitle = $("#modulTitle").val();
var modulType = $("#modulType").val();
var modulKey = $("#modulKey").val();


//设置查询列表参数
function getPostDataForList(){
	var condition = $("#searchByCondition").val();
	var initParam = {
			"org.id":function(){return currentOrgId;},
			"companyPlaceVo.org.id":function(){return currentOrgId;},
			"modulKey":modulKey,
			"modul":modulValue
	}
	if(condition != '请输入单位场所名称'){
		initParam = $.extend(initParam,{"companyPlaceVo.name":condition});
	}
	return initParam;
}
$(document).ready(function(){
	loadList();
	function operatorFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.id+")'><span>查看</span></a>  </pop:JugePermissionTag>";
	}
	function nameFormatter(el,options,rowData){
		var emphasis = rowData.isEmphasis;
		if(emphasis == '' || emphasis == 0 | emphasis =='0'){
			return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.cid+");'></pop:JugePermissionTag><span>"+rowData.name+"</span><pop:JugePermissionTag ename='newView${modul}'></a></pop:JugePermissionTag>";
		}else{
			return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.cid+");' style='color:#888888;' ></pop:JugePermissionTag><span>"+rowData.name+"</span><pop:JugePermissionTag ename='newView${modul}'></a></pop:JugePermissionTag>";
		}
	}
	function attentFormatter(el,options,rowData){
		var displayName = attentionData[el];
		if(displayName=='undefined'||displayName=='')
			return '';
		else if(displayName=='严重')
			return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
		else if(displayName=='中等')
			return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
		else if(displayName=='一般')
			return '<span>一般：<span style="color:#99cc00;">█</span></span>';
		else
			return '';
		return el+"";
	}
	function emphasisFormatter(el,options,rowData){
		if(rowData.isEmphasis == '0' || rowData.isEmphasis == 0 || rowData.isEmphasis ==''){
			return "是";
		}else{
			return "否";
		}
	}
	function loadList(){
		$("#companyPlaceList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		   	    {name:"cid",index:"id",sortable:false,hidden:true,frozen:true},
		   	 	{name:"id",index:"baseId",sortable:false,hidden:true,frozen:true},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true,frozen:true},
		        {name:"operator", index:'cid', label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		        {name:"attentionextent.id",index:'attentionextent',label:'关注程度',formatter:attentFormatter,sortable:true, width:95},
		        {name:'name_title',index:'name',label:'名称',formatter:nameFormatter,sortable:true,width:150},
		        {name:'name',sortable:false,hidden:true,frozen:false,hidedlg:true},
		        {name:"org.orgName",index:'org.orgName',sortable:false,label:'所属网格',align:'center',width:150,hidden:true},
		        {name:'address',index:'address',label:'地址',width:200,sortable:true},
		        {name:'userName',index:'userName',label:'负责人',sortable:true,width:100,align:'center'},
		        {name:'telePhone',index:'telePhone',label:'联系固话',sortable:true,width:100,align:'center'},
		        {name:'mobileNumber', index:'mobileNumber',label:'联系手机',sortable:true, width:100,hidden:true,align:'center'},
		        {name:"type",index:'type',label:'类型',formatter:typeFormatter,sortable:true, width:100,align:'center'},
		        {name:"isEmphasis_title",index:'isEmphasis',label:'是否关注',sortable:true,formatter:emphasisFormatter,width:70,align:'center'},
		        {name:"isEmphasis",sortable:false,hidden:true,frozen:false,hidedlg:true},
		        {name:'hasServiceTeamMember',index:'hasServiceTeamMember',label:'有无治安负责人',sortable:false,width:100,align:"center",formatter:isHasServiceTeamMemberFormatter},
		        {name:'lastRecordTime',index:'lastRecordTime',label:"最新巡场时间",sortable:false,hidden:true,width:200,align:'center'},
		        {name:'classifiCationEn',sortable:false,hidden:true,frozen:true,hidedlg:true},
		        {name:"isEmphasisDate",sortable:true,label:"注销时间",align:'center',hidden:true,width:100},
			    {name:"isEmphasisReason",sortable:true,label:"注销原因",hidden:true,width:100},
			    {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			    {name:'updateDate', sortable:true, align:'center', label:'数据更新时间', width:140}
			],
			<pop:JugePermissionTag ename="newView${modul}">
				ondblClickRow: viewCompanyPlace,
			</pop:JugePermissionTag>
			loadComplete: afterLoad,
			onSelectAll:function(aRowids,status){ },
			multiselect:true,
			height:440,
			onSelectRow:function(){setHouseInfoOperateButton();}
		});
		if('${searchType}'=='fast'){
			fastSearchFun();
		}
		if('${searchType}'=='advanced'){
			searchCompanyPlaceInfo();
		}
	}
	function isHasServiceTeamMemberFormatter(el,options,rowData){
		if(0==rowData.hasServiceTeamMember){	
			return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
		}else{
			return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/>";
		}
	}
	
});
/********************************/
function fastSearchFun(){
		$("#companyPlaceList").setGridParam({
			url:'${path}/baseinfo/companyPlaceManage/queryCompanyPlaceList.action',
			datatype: "json",
			page:1
		});
		$("#companyPlaceList").setPostData(getPostDataForList());
		$("#companyPlaceList").trigger("reloadGrid");
}

//搜索
function searchCompanyPlaceInfo() {	
	$("#companyPlaceList").setGridParam({
		url:'${path}/baseinfo/companyPlaceManage/queryCompanyPlaceList.action',
		datatype: "json",
		page:1
	});
	var postData = $.extend({"org.id":getCurrentOrgId(),"companyPlaceVo.org.id":getCurrentOrgId()},$("#maintainForm").serializeObject());
	$("#companyPlaceList").setPostData(postData);
	$("#companyPlaceList").trigger("reloadGrid");
}


/********************************/
function getSelectedIds(){
	var selectedRowIds = $("#companyPlaceList").jqGrid("getGridParam", "selarrrow");
	return selectedRowIds;
}

function afterLoad(){
	setHouseInfoOperateButton();
}
function setHouseInfoOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("companyPlaceList");
	var count = $("#companyPlaceList").jqGrid("getGridParam","records");
	if(selectedCounts == count && count > 0){
		jqGridMultiSelectState("companyPlaceList", true);
	}else{
		jqGridMultiSelectState("companyPlaceList", false);
	}
}

function viewCompanyPlace(rowid){
	if(rowid==null){
 		return;
	}
	var companyPlace =  $("#companyPlaceList").getRowData(rowid);
	$("#companyPlaceMaintanceDialog").createDialog({
		width: 825,
		height: 580,
		title:'查看'+modulTitle+'信息',
		modal : true,
		url:'${path}/baseinfo/companyPlace/companyPlaceViewDlg.jsp?cid='+companyPlace.encryptId+'&modulKey='+modulKey+'&key='+companyPlace.classifiCationEn+'&name='+companyPlace.name,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}



function viewCompanyPlacePopulation(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo =  $("#companyPlaceList").getRowData(rowid);
	$("#companyPlaceMaintanceDialog").createDialog({
		width: 820,
		height: 530,
		title:'查看',
		modal : true,
		url:'${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.id='+houseInfo.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


/****************工具方法*********************/

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#companyPlaceList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}


</script>