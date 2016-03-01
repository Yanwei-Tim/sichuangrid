<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>

<script type="text/javascript">
<pop:formatterProperty name="kind" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" />
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateSchool'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteSchool'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewSchool'> onclick='viewDialog(event,"+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.chineseName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewSchool'> onclick='viewDialog(event,"+rowData.id+")' </pop:JugePermissionTag> >"+rowData.chineseName+"</a>";
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
            {name:'id', index:'id', hidden:true },
	    	{name:'encryptId', index:'id', frozen:true,hidden:true },
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
	    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
  			{name:'chineseName-Font',index:'chineseName',label:'学校名称',frozen:true,formatter:nameFont,width:200,sortable:true},
  			{name:'chineseName',index:'chineseName',label:'学校名称',frozen:true,hidden:true,width:200,sortable:true},
  			{name:'address',index:'address',label:'学校地址',sortable:true,width:200},
  			{name:'president',index:'president',label:'校长',width:100,sortable:true},
  			{name:'englishName',index:"englishName",label:'英文名称',width:200,hidden:true,sortable:true},
  			{name:"organization.orgName", index:"orgInternalCode",label:"所属网格",width:150, hidden:true},
  			{name:'kind',index:'kind',label:'学校性质',sortable:true, formatter:kindFormatter,width:100,align:'center'},
  			{name:'type',index:'type',label:'学校类型',sortable:true, formatter:typeFormatter,width:100},
 			{name:'personLiable', index:'contactName',label:'法制副校长',sortable:true,width:100},
 			
 			{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
 			{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
  			{name:'personLiableTelephone',index:'telephone', label:'联系电话',align:'center',hidden:true,sortable:true,width:130},
  			{name:'personLiableMobileNumber',index:'mobileNumber', label:'联系手机',align:'center',sortable:true,hidden:true,width:100},
  			{name:"webSite",index:"webSite",label:"学校网址",hidden:true,width:150,sortable:true},
  			{name:"fax",index:"fax",label:"传真",hidden:true,width:150,sortable:true},
  			{name:"email",index:"email",label:"电子邮箱",hidden:true,width:150,sortable:true},
  			{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
	  	]
};
var dialogWidth=800;
var dialogHeight=600;

function getLocationName(rowData){
	return rowData.chineseName;
}
</script>
<jsp:include page="/baseinfo/commonPopulation/attentionLocationList.jsp">
	<jsp:param value="School" name="moduleName"/>
	<jsp:param value="学校" name="moduleCName"/>
	<jsp:param value="治安负责人" name="supervisorPerson"/>
</jsp:include>
<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@SCHOOL_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@SCHOOL_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@SCHOOL_KEY)" escape="false"/>'/>
</div>