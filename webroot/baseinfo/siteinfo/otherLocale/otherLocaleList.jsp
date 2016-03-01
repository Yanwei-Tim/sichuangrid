<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateOtherLocale'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteOtherLocale'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==1||rowData.isEmphasis=='1'||rowData.isEmphasis=="true"||rowData.isEmphasis==true){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewOtherLocale'> onclick='viewDialog(event,"+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewOtherLocale'> onclick='viewDialog(event,"+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
}

function isRiskEnterprise(el, options, rowData){
	if(true == rowData.riskEnterprise){
		return "是";
	}else{
		return "否";
	}
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
          {name:"id",index:'id',sortable:false,hidden:true},
          {name:"encryptId", index:"id",hidden:true,sortable:false, frozen :true},
 		  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
      	  {name:"name-Font",index:'name',label:'名称',frozen:true,sortable:true,formatter:nameFont,width: 200},
      	  {name:"name",index:'name',label:'名称',frozen:true,sortable:true,hidden:true,width:150},
      	  {name:"address",index:'address',sortable:true,label:'地址',width:220},
      	  {name:"type",index:'type',label:'场所类型',sortable:true,width:90,formatter:typeFormatter},
		  
      	  {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
		  {name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
		  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
      	  {name:'updateDate', sortable:true, label:'数据更新时间', width:140},
      	  {name:"organization.orgName",index:'orgInternalCode',label:'所属网格',sortable:false,width:150, hidden:true},
      	  {name:"contactPerson",index:'contactPerson',label:'联系人',sortable:true,hidden:true,width:120},
      	  {name:"mobileNumber",index:'mobileNumber',label:'联系手机',hidden:true,sortable:true,width:110,align:'center'},
      	  {name:"telephone",index:'telephone',label:'联系电话',hidden:true,sortable:true,width:140,align:'center'},
      	  {name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,hidedlg:true,width:100,align:'center'}
		 ]
};
var dialogWidth=800;
var dialogHeight=600;

function getLocationName(rowData){
	return rowData.name;
}
</script>
<jsp:include page="/baseinfo/commonPopulation/attentionLocationList.jsp">
	<jsp:param value="OtherLocale" name="moduleName"/>
	<jsp:param value="其他场所" name="moduleCName"/>
	<jsp:param value="治安负责人" name="supervisorPerson"/>
</jsp:include>
<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@OTHERLOCALE_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@OTHERLOCALE_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@OTHERLOCALE_KEY)" escape="false"/>'/>
</div>