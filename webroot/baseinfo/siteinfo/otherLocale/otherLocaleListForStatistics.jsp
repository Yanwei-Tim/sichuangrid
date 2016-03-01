<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<div class="content">
    <div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="otherLocaleList"></table>
		<div id="otherLocaleListPager"></div>
	</div>
	<div id="otherLocaleDialog"></div>
	<div id="noticeDialog"></div>
	<div id="otherLocaleMaintanceDialog"></div>
</div>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>

<div style="display: none;"><pop:JugePermissionTag
	ename="otherLocaleManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
var isgridBol = false;
var title=$("#title").html();
function onOrgChangedForStat(orgId,isGrid){
	if(isGrid){
		$("#add").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
	isgridBol = isGrid;
	$("#otherLocaleList").setGridParam({
		url:'${path}/baseinfo/otherLocaleManage/otherLocaleList.action',
		datatype: "json",
		page:1
	});
	var isEmphasis = '';
	if($("#isLock").val() == undefined || $("#isLock").val() == null ){
		isEmphasis = 0;
	}else{
		isEmphasis = $("#isLock").val();
	}
	$("#otherLocaleList").setPostData({
		"organization.id":function(){
	    	return getOrgIdForStat();
		},
		"otherLocale.isEmphasis":0
	});
	$("#otherLocaleList").trigger("reloadGrid");
}

function operateFormatter(el,options,rowData){
	return "<a href='javascript:viewOtherLocale("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
} 
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewOtherLocale("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
} 
function viewOtherLocale(selectedId){
 	if(null==selectedId){
  		return;
 	}
	var rowData = $("#otherLocaleList").getRowData(selectedId);
	var id = rowData.encryptId;
 	
 	$("#otherLocaleDialog").createDialog({
 	 	width: 800,
 		height: 600,
 		title:'查看'+title+'信息',
 		url:'${path}/baseinfo/otherLocaleManage/dispatchOperateByEncrypt.action?mode=view&location.id='+id,
 		buttons: {
 	   		"关闭" : function(){
 	        	$(this).dialog("close");
 	   		}
 		}
 	});
 }
function isRiskEnterprise(el, options, rowData){
	if(true == rowData.riskEnterprise){
		return "是";
	}else{
		return "否";
	}
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
		return "<a href='javascript:viewSafetyProductionKey("+rowData.id+")'><font color='#778899'>"+rowData.name+"</font></a>";
	}
	return "<a href='javascript:viewSafetyProductionKey("+rowData.id+")'>"+rowData.name+"</a>";
}
//关注程度显示效果
function attentionExtentColor(el,options,rowData){
	var displayName=attentionExtentFormatter(el,options,rowData);
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
}
$(document).ready(function(){
	$("#otherLocaleList").jqGridFunction({
		datatype:'local',
		colModel:[
            {name:"id",index:'id',hidden:true},
            {name:"encryptId",index:'id',frozen:true,hidden:true},
		    {name:"operator", index:'id',label:'操作',formatter:operateFormatter,width:50,frozen:true,sortable:false,align:'center' },
		    {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
      	    {name:"name",index:'name',label:'名称',formatter:nameFormatter,width:180},
      	    {name:"address",index:'address',label:'地址',width:220},
      	    {name:"legalPerson", index:"legalPerson", label:'负责人',width:100},
      	    {name:"type",index:'type',label:'场所类型',width:90,formatter:typeFormatter},
      	    {name:"riskEnterprise", sortable:false, label:'是否存在隐患', formatter:isRiskEnterprise,width:100,align:"center"},
		    {name:"hiddenCases", sortable:false, label:'隐患整改情况',width:150},
		    
		    {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		    {name:'updateDate', sortable:false, label:'数据更新时间', width:160,align:"center"},
      	    {name:"organization.orgName",index:'orgInternalCode',label:'所属网格',width:200, hidden:true},
      	    {name:"contactPerson",index:'contactPerson',label:'联系人',hidden:true,width:120},
      	    {name:"mobileNumber",index:'mobileNumber',label:'联系手机',hidden:true,sortable:false,width:110},
      	    {name:"telephone",index:'telephone',label:'联系电话',hidden:true,sortable:false,width:140},
      	    {name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,hidedlg:true,width:100}
		],
		ondblClickRow: viewOtherLocale,
		loadComplete:afterLoad,
	  	width:860,
		height:425
	});

	if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchOtherLocale();
		}else if('<s:property value="#parameters.isSearch"/>' == 0){
			fastSearch();
		}else{
			onOrgChangedForStat(getOrgIdForStat(),isGrid());
		}
	}
});

function getPlaceName(){
	var placeName = "";
	$("#conditionName").length > 0?placeName = $("#conditionName").val():placeName = $("#searchByPlaceName").val();
	return placeName;
}

function fastSearch(){
	var initParam={
			"organization.id":getOrgIdForStat(),
			"searchOtherLocaleVo.organization.id":getOrgIdForStat(),
			"searchOtherLocaleVo.isEmphasis":0
			};
	if("请输入场所名称"!=$("#searchByPlaceName").val()){
		initParam={
				"organization.id":getOrgIdForStat(),
				"searchOtherLocaleVo.organization.id":getOrgIdForStat(),
				"searchOtherLocaleVo.name":$("#searchByPlaceName").val(),
				"searchOtherLocaleVo.isEmphasis":0
				};
		}
	$("#otherLocaleList").setGridParam({
		url:'${path}/baseinfo/searchOtherLocale/searchOtherLocale.action',
		datatype:'json',
		page:1
		});
	$("#otherLocaleList").setPostData(initParam);
	$("#otherLocaleList").trigger("reloadGrid");
}

function searchOtherLocale(){
	var	nameCondition=getPlaceName();
	var	addressCondition=$("#conditionAddress").val();
	var	typeCondition=$("#conditionType").val();
	var contactPersonCondition=$("#conditionContactPerson").val();
	var telephoneCondition=$("#conditionTelephone").val();
	var mobileNumberCondition=$("#conditionMobileNumber").val();
	var hasServiceTeamMember = $("#searchHasServiceTeamMember").val();
	var hasServiceRecord = $("#searchHasServiceRecord").val();
	$("#otherLocaleList").setGridParam({
		url:'${path}/baseinfo/searchOtherLocale/searchOtherLocale.action',
		datatype: "json",
		page:1
	});
	$("#otherLocaleList").setPostData({
    	"organization.id":getOrgIdForStat(),
    	"searchOtherLocaleVo.name":nameCondition,
    	"searchOtherLocaleVo.address":addressCondition,
    	"searchOtherLocaleVo.typeId":typeCondition,
    	"searchOtherLocaleVo.contactPerson":contactPersonCondition,
    	"searchOtherLocaleVo.telephone":telephoneCondition,
    	"searchOtherLocaleVo.mobileNumber":mobileNumberCondition,
    	"searchOtherLocaleVo.hasServiceTeamMember":hasServiceTeamMember,
		"searchOtherLocaleVo.hasServiceRecord":hasServiceRecord,
    	"searchOtherLocaleVo.isEmphasis":$("#isLock").val()
   	});
	$("#otherLocaleList").trigger("reloadGrid");
	$("#statisticsDialog").dialog("close");
}

function afterLoad(){
	isEmphasisFormatter();
}

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#otherLocaleList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#otherLocaleList").getRowData(idCollection[i]);
			if(ent.isEmphasis=="true"){
			$("#otherLocaleList tr[id="+idCollection[i]+"]").css('color','#778899');
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

