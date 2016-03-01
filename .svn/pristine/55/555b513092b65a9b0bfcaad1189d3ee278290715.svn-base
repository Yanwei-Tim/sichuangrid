<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>

<script type="text/javascript">
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateDangerousChemicalsUnit'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");' ><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteDangerousChemicalsUnit'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewDangerousChemicalsUnit'>  onclick='viewDangerousChemicalsUnit("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.unitName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewDangerousChemicalsUnit'> onclick='viewDangerousChemicalsUnit("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.unitName+"</a>";
}
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

var gridOption = {
	colModel:[
		{name:"id", index:"id", hidden:true},
		{name:"encryptId", index:"id",frozen:true, hidden:true},
    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
		{name:"unitName-Font", index:"unitName",label:"单位名称",frozen:true,formatter:nameFont,sortable:true,width:200},
		{name:"unitName", index:"unitName",label:"单位名称",frozen:true,hidden:true,width:200,sortable:true},
		{name:"unitAddress",label:'单位地址', sortable:true,width:200},
		{name:"superintendent", label:"负责人",sortable:true,index:"superintendent",width:100},
		{name:"unitType",label:"单位类别", index:"unitType", width:150,sortable:true},
		{name:"commodityType",label:"货物类别", width:150,sortable:true},
		
		{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		{name:"organization.orgName", index:"orgInternalCode", width:150,label:"所属网格",hidden:true},
		{name:"telephone",label:"联系电话", width:120,sortable:true,hidden:true,align:'center'},
		{name:"copyScope",label:"副本许可范围", width:150,sortable:true,hidden:true},
		{name:"storageDevice",label:"存储设备", width:150,sortable:true,hidden:true},
		{name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
		{name:"logOutReason",label:"注销原因", width:100,sortable:true,hidden:true},
		{name:"logOutTime",label:"注销时间", width:100,sortable:false,hidden:true,align:'center'},
		{name:"remark",sortable:true,label:"备注",hidden:true,width:100}
	]
};
var dialogWidth=810;
var dialogHeight=640;

function getLocationName(rowData){
	return rowData.unitName;
}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonLocationList.jsp">
	<jsp:param value="DangerousChemicalsUnit" name="moduleName"/>
	<jsp:param value="危险化学品单位" name="moduleCName"/>
	<jsp:param value="治安负责人" name="supervisorPerson"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>
<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@DANGEROUSCHEMICALSUNIT_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@DANGEROUSCHEMICALSUNIT_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@DANGEROUSCHEMICALSUNIT_KEY)" escape="false"/>'/>
</div>