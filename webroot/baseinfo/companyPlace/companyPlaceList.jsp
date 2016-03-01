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
<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入单位名称" id="searchByCondition" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入单位名称':this.value;" onfocus="value=(this.value=='请输入单位名称')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<pop:JugePermissionTag ename="newSearch${modul}">
		<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="newSearch${modul}">
		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="newAdd${modul}">
		<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="newDelete${modul}">
		<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="newImport${modul},newDown${modul}">
		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag> 
			<div class="nav-sub-buttons">
			 <pop:JugePermissionTag ename="newImport${modul}">
			 <div id="importBox">
				<a id="import" href="javascript:void(0)"><span><strong
					class="ui-ico-dr"></strong>Excel导入</span></a>
			</div>
			</pop:JugePermissionTag> 
			<pop:JugePermissionTag ename="newDown${modul}">
				<a id="export" href="javascript:void(0)"><span><strong
					class="ui-ico-dc"></strong>导出Excel</span></a>
			</pop:JugePermissionTag>
			</div>
		<pop:JugePermissionTag ename="newCancelAttended${modul},newaAtended${modul}">
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="newCancelAttended${modul}">
			<a id="cancelEmphasise" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>取消关注</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="newAttended${modul}">
			<a id="reEmphasise" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>重新关注</span></a>
			</pop:JugePermissionTag>
		</div>
		<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<a id="superviseServiceTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>管理治安负责人</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="serviceRecordManagement">
			<a id="superviseRecord" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>管理巡场情况</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="newTransfer${modul}">
			<a id="transfer" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>转移</span></a>
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
<pop:formatterProperty name="operatingBrand" domainName="@com.tianque.domain.property.PropertyTypes@OPERATING_BRAND" />
<pop:formatterProperty name="operatingMode" domainName="@com.tianque.domain.property.PropertyTypes@OPERATING_MODE" />

var dialogWidth=800;
var dialogHeight=510;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();

var modulValue = $("#modulValue").val();
var modulTitle = $("#modulTitle").val();
var modulType = $("#modulType").val();
var modulKey = $("#modulKey").val();

if(modulValue=="CompanyPlace"||modulValue=="SafetyProductionKey"||modulValue=="FireSafetyKey"
		||modulValue=="SecurityKey"||modulValue=="PollutionSource"||modulValue=="Enterprise"
		||modulValue=="EnterpriseDown")
{
	$("#importBox").hide();
}

//组织机构
function onOrgChanged(orgId,isgrid){
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#companyPlaceList").setGridParam({
		url:PATH+'/baseinfo/companyPlaceManage/queryCompanyPlaceList.action',
		datatype: "json",
		page:1
	});
	$("#companyPlaceList").setPostData({
		"org.id":function(){return currentOrgId;},
		"companyPlaceVo.org.id":function(){return currentOrgId;},
		"modulKey":modulKey,
		"modul":modulValue
	});
	$("#companyPlaceList").trigger("reloadGrid");
}

function printChoose(rowid){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:PATH+'/baseinfo/commonPopulation/printTabChooseDlg.jsp',
		buttons: {
			"确定" : function(){
				print(rowid);
	   		},
		   "关闭" : function(){	
		        $(this).dialog("close");
		   }
		}
	});
}

var printTitleStr='';
function print(rowid){
	printTitleStr= modulTitle+"";
	var houseInfo =  $("#companyPlaceList").getRowData(rowid);
	$("#companyPlaceMaintanceDialog").createDialog({
		width: 800,
		height: 580,
		title:'打印'+modulTitle+'信息',
		modal : true,
		url:PATH+'/baseinfo/actualHouseManage/dispatchOperateByEncrypt.action?mode=print&houseId='+houseInfo.encryptId,
		buttons: {
			  "打印" : function(){
				$("#printSpaceDiv").printArea();
	        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function operatorFormatter(el,options,rowData){
	var emphasis = rowData.isEmphasis;
	if(emphasis == '' || emphasis == 0 || emphasis =='0'){
	return "<pop:JugePermissionTag ename='newUpdate${modul}'><a href='javascript:;' onclick='updateCompanyPlace(event,"+rowData.cid+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='newDelete${modul}'><a href='javascript:;' onclick='deleteCompanyPlace(event,"+(rowData.cid)+");'><span>删除</span></a></pop:JugePermissionTag>";
	}else{
		return "<pop:JugePermissionTag ename='newDelete${modul}'><a href='javascript:;' onclick='deleteCompanyPlace(event,"+(rowData.cid)+");'><span>删除</span></a></pop:JugePermissionTag>";
	}
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
}
function emphasisFormatter(el,options,rowData){
	if(rowData.isEmphasis == '0' || rowData.isEmphasis == 0 || rowData.isEmphasis ==''){
		return "是";
	}else{
		return "否";
	}
}

function isHasServiceTeamMemberFormatter(el,options,rowData){
	if(0==rowData.hasServiceTeamMember){	
		return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
	}else{
		return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/>";
	}
}
function callback(){
    var dfop = {
       	hasViewCompanyPlace: '<pop:JugePermissionTag ename="newView${modul}">true</pop:JugePermissionTag>',
       	hasUpdateCompanyPlace:'<pop:JugePermissionTag ename="newUpdate${modul}">true</pop:JugePermissionTag>',
       	hasDeleteCompanyPlace:'<pop:JugePermissionTag ename="newDelete${modul}">true</pop:JugePermissionTag>',
       	HasServiceTeamMember:'<s:property value="@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING"/>',
    	noHasServiceTeamMember:'<s:property value="@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING"/>',
    	templates:'${modulKey}',
    	colModel:[
		   		{name:"cid",index:"id",sortable:false,hidden:true,frozen:true},
		   	 	//{name:"id",index:"baseId",sortable:false,hidden:true,frozen:true},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true,frozen:true},
		        {name:"operator", index:'cid', label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		        {name:"attentionextent.id",index:'attentionextent',label:'关注程度',formatter:attentFormatter,sortable:true, width:95},
		        {name:'name_title',index:'name',label:'名称',formatter:nameFormatter,sortable:true,width:150},
		        <s:if test="'Logistics'.equals(#parameters.modul[0])">
		        {name:'operatingBrand.id',index:'operatingBrand',label:'经营品牌',formatter:operatingBrandFormatter,sortable:true,width:95,align:'center'},
		        {name:'operatingMode.id',index:'operatingMode',label:'经营方式',formatter:operatingModeFormatter,sortable:true,width:95,align:'center'},
		        </s:if>
		        {name:'name',sortable:false,hidden:true,frozen:false,hidedlg:true},
		        {name:"org.orgName",index:'org.orgName',sortable:false,label:'所属网格',align:'center',width:150,hidden:true},
		        {name:'address',index:'address',label:'地址',width:200,sortable:true},
		        <s:if test="!'Logistics'.equals(#parameters.modul[0])">
		        <s:if test="!'PartyGovernmentOrganCompany'.equals(#parameters.modul[0])">
		        {name:'userName',index:'userName',label:'负责人',sortable:true,width:100,align:'center'},
		        {name:'mobileNumber', index:'mobileNumber',label:'联系手机',sortable:true, width:100,hidden:true,align:'center'},
		        </s:if>
		        {name:'telePhone',index:'telePhone',label:'联系固话',sortable:true,width:100,align:'center'},
		        </s:if>
		        <s:else>
		        {name:'telePhone',index:'telePhone',label:'联系电话',sortable:true,width:100,align:'center'},
		        </s:else>
		        {name:"type",index:'type',label:'类型',formatter:typeFormatter,sortable:true, width:100,align:'center'},
		        {name:"isEmphasis_title",index:'isEmphasis',label:'是否关注',sortable:true,formatter:emphasisFormatter,width:70,align:'center'},
		        {name:"isEmphasis",sortable:false,hidden:true,frozen:false,hidedlg:true},
		        {name:'hasServiceTeamMember',index:'hasServiceTeamMember',label:'有无治安负责人',sortable:false,width:100,align:"center",formatter:isHasServiceTeamMemberFormatter},
		        {name:'lastRecordTime',index:'lastRecordTime',label:"最新巡场时间",sortable:false,hidden:true,width:200,align:'center'},
		        {name:'classifiCationEn',sortable:false,hidden:true,frozen:true,hidedlg:true},
		        {name:"isEmphasisDate",sortable:true,label:"注销时间",align:'center',hidden:true,width:100},
			    {name:"isEmphasisReason",sortable:true,label:"注销原因",hidden:true,width:100},
			    {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			    {name:'updateDate', sortable:true, align:'center', label:'数据更新时间', width:140},
			    {name:"pollution",index:"pollution",sortable:false,hidden:true,frozen:true}
			]
    }
    TQ.companyPlaceList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/baseinfo/companyPlace/formPage/companyPlaceList.js',
    callback: callback
})
</script>