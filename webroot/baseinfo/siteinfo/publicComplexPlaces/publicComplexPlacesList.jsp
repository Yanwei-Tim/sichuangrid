<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_TYPE" />
<pop:formatterProperty name="detailedType" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_DETAILEDTYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updatePublicComplexPlaces'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deletePublicComplexPlaces'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPublicComplexPlaces'> onclick='viewPublicComplexPlaces("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.placeName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPublicComplexPlaces'> onclick='viewPublicComplexPlaces("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.placeName+"</a>";
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
	var gridOption={
			colModel:[
	          {name:"id", index:"id",sortable:false,hidden:true, frozen :true},
	          {name:"encryptId", index:"id",hidden:true,sortable:false, frozen :true},
	      	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
	      	  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	          {name:"placeName-Font",index:"placeName",label:"场所名称",width:200,sortable:true,frozen:true,formatter:nameFont},
	          {name:"placeName",index:"placeName",label:"场所名称",width:100,sortable:true,frozen:true,hidden:true},
	          {name:"placeAddress",index:"placeAddress",label:"场所地址",width:200,sortable:true,frozen:true,formatter:placeAddressColorFormatter},
	          {name:"manager",index:"manager",label:"负责人",sortable:true,width:100,hidden:false},
	          {name:"type",index:"type",label:"公共复杂场所类型",sortable:true,width:100,formatter:typeFormatter},
	          {name:"detailedType",index:"detailedType",label:"公共复杂场所细类",sortable:true,width:100,formatter:detailedTypeFormatter},
	          {name:"managerMobile",index:"managerMobile",label:"负责人手机",sortable:true,width:100,hidden:true},
	          {name:"managerTelephone",index:"managerTelephone",label:"负责人电话",sortable:true,width:100,align:'center'},
	          
	          {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			  {name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	          {name:'updateDate', sortable:true, label:'数据更新时间', width:140},
	          {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",sortable:false,hidden:true},
	          {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
	          {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
	          {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100,hidden:true},
	          {name:"hiddenCases",index:"hiddenCases",label:"存在隐患",sortable:true,width:200,hidden:true},
	          {name:"hiddenRectification",index:"hiddenRectification",label:"隐患整改情况",sortable:true,width:200,hidden:true},
	          {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true}
				]
			};
	var dialogWidth=810;
	var dialogHeight=600;

	function placeAddressColorFormatter(el,options,rowData){
		if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
			return "<font color='#778899'>"+rowData.placeAddress+"</font>";
		}
		return "<font color='#000'>"+rowData.placeAddress+"</font>";
	}
	function getLocationName(rowData){
		return rowData.placeName;
	}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonLocationList.jsp">
	<jsp:param value="PublicComplexPlaces" name="moduleName"/>
	<jsp:param value="公共场复杂所" name="moduleCName"/>
	<jsp:param value="治安负责人" name="supervisorPerson"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>
<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@PUBLICCOMPLEXPLACES_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@PUBLICCOMPLEXPLACES_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@PUBLICCOMPLEXPLACES_KEY)" escape="false"/>'/>
</div>

