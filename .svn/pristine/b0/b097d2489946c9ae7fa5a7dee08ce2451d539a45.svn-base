<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.tianque.baseInfo.companyPlace.constacts.ModulTypes"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<% 
	String modul =request.getParameter("modul") ;
	request.setAttribute("modul",modul);
	String modulType = request.getParameter("modulType");
	request.setAttribute("modulType",modulType);
	String modulKey = ModulTypes.getKeyByName(modul.toUpperCase()+"_KEY");
	request.setAttribute("modulKey",modulKey);
%>
<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入单位名称" id="searchByCondition" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入单位名称':this.value;" onfocus="value=(this.value=='请输入单位名称')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<pop:JugePermissionTag ename="newSearch${modul}">
		<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>刷新</span></a>
		<div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="companyPlaceList"></table>
		<div id="companyPlaceListPager"></div>
	</div>
	<div id="moveDataDialog"></div>
	<div id="companyPlaceMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="actualHouseDialog"></div>
	<div id="mergeActualHouseDialog"></div>
	<div id="scanHeaderImage"></div>
	<div id="qrcodeDialog"></div>
	
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

//组织机构
function onOrgChanged(orgId,isgrid){
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#companyPlaceList").setGridParam({
		url:'${path}/baseinfo/companyPlaceManage/queryCompanyPlaceList.action',
		datatype: "json",
		page:1
	});
	$("#companyPlaceList").setPostData({
		"org.id":function(){return orgId;},
		"companyPlaceVo.org.id":orgId,
		"modulKey":modulKey,
		"modul":modulValue
	});
	$("#companyPlaceList").trigger("reloadGrid");
}
//设置查询列表参数
function getPostDataForList(){
	var condition = $("#searchByCondition").val();
	var initParam = {
			"org.id":function(){return getOrgIdForStat();},
			"companyPlaceVo.org.id":function(){return getOrgIdForStat();},
			"modulKey":modulKey,
			"modul":modulValue
	}
	if(condition != '请输入单位名称'){
		initParam = $.extend(initParam,{"companyPlaceVo.name":condition});
	}
	return initParam;
}
$(document).ready(function(){
	loadList();
	$("#searchByConditionButton").click(function(event){
		fastSearchFun();
	});
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入单位名称");
	});
	$("#reload").click(function(event){
		reloadFun();
	});
	function operatorFormatter(el,options,rowData){
		var emphasis = rowData.isEmphasis;
		if(emphasis == '' || emphasis == 0 | emphasis =='0'){
			return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.cid+");'><span>查看</span></a></pop:JugePermissionTag>";
		}else{
			return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.cid+");' style='color:#888888;' ><span>查看</span></a></pop:JugePermissionTag>";
		}
	}
	function nameFormatter(el,options,rowData){
		var emphasis = rowData.isEmphasis;
		if(emphasis == '' || emphasis == 0 | emphasis =='0'){
			return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.cid+");'><span>"+rowData.name+"</span></a></pop:JugePermissionTag>";
		}else{
			return "<pop:JugePermissionTag ename='newView${modul}'><a href='javascript:;' onclick='viewCompanyPlace("+rowData.cid+");' style='color:#888888;' ><span>"+rowData.name+"</span></a></pop:JugePermissionTag>";
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
		        {name:'address',index:'address',label:'地址',width:200,sortable:true},
		        {name:'userName',index:'userName',label:'负责人',sortable:true,width:100,align:'center'},
		        {name:'telePhone',index:'telePhone',label:'联系固话',sortable:true,width:100,align:'center'},
		        {name:'mobileNumber', index:'mobileNumber',label:'联系手机',sortable:true, width:100,hidden:true,align:'center'},
		        {name:"type",index:'type',label:'类型',formatter:typeFormatter,sortable:true, width:100,align:'center'},
		        {name:"isEmphasis_title",index:'isEmphasis',label:'是否关注',sortable:true,formatter:emphasisFormatter,width:70,align:'center'},
		        {name:"isEmphasis",sortable:false,hidden:true,frozen:false,hidedlg:true},
		        {name:'hasServiceTeamMember',index:'hasServiceTeamMember',label:'有无治安负责人',sortable:false,width:100,align:"center",formatter:isHasServiceTeamMemberFormatter},
		        {name:'lastRecordTime',index:'lastRecordTime',label:"最新巡场时间",sortable:false,hidden:true,width:200,align:'center'},
		        {name:'classifiCationEn',sortable:false,hidden:true,frozen:true,hidedlg:true}        
			],
			<pop:JugePermissionTag ename="newView${modul}">
				ondblClickRow: viewCompanyPlace,
			</pop:JugePermissionTag>
			loadComplete: afterLoad,
			onSelectAll:function(aRowids,status){ },
			multiselect:false,
			height:400,
			onSelectRow:function(){setHouseInfoOperateButton();}
		});
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getOrgIdForStat(),isGrid());
		}
	}
	function isHasServiceTeamMemberFormatter(el,options,rowData){
		if(0==rowData.hasServiceTeamMember){	
			return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
		}else{
			return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/>";
		}
	}
	
	function reloadFun(){
		$("#conditionMark").val("fast_hosueCode");
		$("#searchByCondition").attr("value","请输入单位名称");
		onOrgChanged(getOrgIdForStat(),isGrid());
	}
});
/********************************/
function fastSearchFun(){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入单位名称'){ 
			$.messageBox({level:'warn',message:'请输入单位名称再查询'});
			return;
		}
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
	var postData = $("#maintainForm").serializeObject();
	//var postData = $.extend({},{"companyPlaceVo.name":condition}) ;
	$("#companyPlaceList").setGridParam({
		url:'${path}/baseinfo/companyPlaceManage/queryCompanyPlaceList.action',
		datatype: "json",
		page:1
	});
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
/****************工具方法*********************/

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#companyPlaceList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
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