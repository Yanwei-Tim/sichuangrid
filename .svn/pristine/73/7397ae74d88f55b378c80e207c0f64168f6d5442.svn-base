<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="cloakroom" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updatePublicPlace'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deletePublicPlace'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPublicPlace'> onclick='viewPublicPlace("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.placeName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPublicPlace'> onclick='viewPublicPlace("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.placeName+"</a>";
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
	          {name:"category",index:"category",label:"公共场所类别",sortable:true,width:100},
	          {name:"registrationNumber",index:"registrationNumber",label:"备案登记号码",sortable:true,width:100,hidden:true},
	          {name:"openingDate",index:"licenseDate",label:"开业时间",sortable:true,width:100,align:'center'},
	          
	          {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			  {name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	          {name:'updateDate', sortable:true, label:'数据更新时间', width:140},
	          {name:"licenseDate",index:"licenseDate",label:"领取执照时间",width:100,sortable:true,hidden:true,align:'center'},
	          {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",sortable:false,hidden:true},
	          {name:"placeLevel",index:"placeLevel",label:"场所等级",sortable:true,width:100,hidden:true},
	          {name:"buildingStructure",index:"buildingStructure",label:"建筑物结构",sortable:true,width:100,hidden:true},
	          {name:"totalArea",index:"totalArea",label:"总面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:true,width:100,hidden:true},
	          {name:"operationArea",index:"operationArea",label:"营业面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:true,width:110,hidden:true},
	          {name:"cloakroom",index:"cloakroom",formatter:cloakroomFormatter,label:"小件寄存处",sortable:true,width:100,hidden:true,align:'center'},
	          {name:"vouchedPeopleCount",index:"vouchedPeopleCount",label:"核定人数(人)",sortable:true,width:100,hidden:true},
	          {name:"privateRoomCount",index:"privateRoomCount",label:"包间数(间)",sortable:true,width:100,hidden:true},
	          {name:"surrounding",index:"surrounding",label:"周围环境",sortable:true,width:100,hidden:true},
	          {name:"passageway",index:"passageway",label:"通道口",sortable:true,width:100,hidden:true},
	          {name:"innerStructure",index:"innerStructure",label:"内部结构",sortable:true,width:100,hidden:true},
	          {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
	          {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
	          {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100,hidden:true},
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
	<jsp:param value="PublicPlace" name="moduleName"/>
	<jsp:param value="公共场所" name="moduleCName"/>
	<jsp:param value="治安负责人" name="supervisorPerson"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>
<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY)" escape="false"/>'/>
</div>

