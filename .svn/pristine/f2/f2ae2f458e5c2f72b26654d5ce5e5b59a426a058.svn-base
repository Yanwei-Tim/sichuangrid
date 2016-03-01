<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
<pop:formatterProperty name="level" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITAL_LEVEL" />
<pop:formatterProperty name="kind" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND" />
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateHospital'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteHospital'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;'   onclick='viewHospital("+rowData.id+")'  ><font color='#778899'>"+rowData.hospitalName+"</font></a>";
	}
	return "<a href='javascript:;'  onclick='viewHospital("+rowData.id+")'  >"+rowData.hospitalName+"</a>";
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
   	       	{name:"id",index:"id",hidden:true},
   	    	{name:"encryptId",index:"id",frozen:true,hidden:true},
   	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
    		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	        {name:"hospitalName-Font",index:'hospitalName',label:'医院名称',sortable:true,formatter:nameFont},
	        {name:"hospitalName",hidedlg:true,hidden:true},
	       //{name:'anotherName',index:'anotherName',label:'医院别名'},
	        {name:'director',index:'director',sortable:true,label:'医院院长'},
	        {name:'address',index:'address',sortable:true,label:'医院地址'},
	        //{name:'level',index:'level',label:'医院等级',formatter:levelFormatter},
	       //{name:'medicalInsurance',index:'medicalInsurance',label:'是否医保'},
	        {name:'affiliatedUnit',index:'affiliatedUnit',sortable:true,label:'所属单位'},
	        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格', hidden:true},
	        //{name:'contactName',index:'contactName',label:'联系人',sortable:false},
	        {name:'telephone',index:'telephone',label:'联系电话',sortable:true},
	        {name:'mobileNumber',index:'mobileNumber',label:'联系手机',sortable:true},
	        {name:'email',index:'email',label:'电子邮箱',sortable:true,hidden:true},
	        {name:'fax',index:'fax',label:'传真',sortable:true,hidden:true},
	        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
	        {name:'kind',index:'kind',label:'医院性质',formatter:kindFormatter,hidden:true},
	        {name:'type',index:'type',label:'医院类型',formatter:typeFormatter,hidden:true}
	   	]
};

function placeAddressColorFormatter(el,options,rowData){
		if(rowData.placeAddress !=null && rowData.placeAddress !="null"){
			if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
				return "<font color='#778899'>"+rowData.placeAddress+"</font>";
			}
			return "<font color='#000'>"+rowData.placeAddress+"</font>";
		}else{
			return "";
		}
	}
	function getLocationName(rowData){
			return rowData.hospitalName;
	}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonLocationList.jsp">
	<jsp:param value="Hospital" name="moduleName"/>
	<jsp:param value="医院" name="moduleCName"/>
	<jsp:param value="1" name="isNew"/>
	<jsp:param value="治安负责人" name="supervisorPerson"/>
</jsp:include>
<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@HOSPITAL_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@HOSPITAL_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@HOSPITAL_KEY)" escape="false"/>'/>
</div>