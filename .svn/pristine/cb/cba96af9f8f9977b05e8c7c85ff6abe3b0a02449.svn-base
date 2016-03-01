<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="keyType"
value="<s:property value="#parameters.keyType"/>" />
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="SafetyProductionKey" name="moduleName"/>
</jsp:include>
<%
String keyType = request.getParameter("keyType");
String moduleCName = "";
String moduleName = "";
String enameModuleName="";

if(keyType != null && !"".equals(keyType)){
	if("safetyProductionKey".equals(keyType)) {
		moduleCName = "安全生产重点";
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	} else if("enterpriseKey".equals(keyType)) {
		moduleCName = "规上企业";
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1,keyType.length()-3);
		enameModuleName = moduleName;
	}else if("enterpriseDownKey".equals(keyType)) {
		moduleCName = "规下企业";
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1,keyType.length()-7);
		//fateson add 管理治安 和 寻常的时候需要用到 获取准确的人员类型
		enameModuleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1,keyType.length()-3);
	}
}
request.setAttribute("moduleName",moduleName);
request.setAttribute("moduleCName",moduleCName);
request.setAttribute("keyType",keyType);
request.setAttribute("enameModuleName",enameModuleName);
%>
<script type="text/javascript">
	<pop:formatterProperty name="industry" domainName="@com.tianque.domain.property.PropertyTypes@BUSINESSTYPE" />
	<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE" />
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	function isRiskEnterprise(el, options, rowData){
		if(true == rowData.riskEnterprise){
			return "是";
		}else{
			return "否";
		}
	}          
	function operatorFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='update${enameModuleName}'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+")'><span>修改</span></a> </pop:JugePermissionTag><pop:JugePermissionTag ename='delete${enameModuleName}'>| <a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+")'><span>删除</span></a> </pop:JugePermissionTag>";

	}
	function nameFont(el,options,rowData){
		if(null == rowData.name) {
			return "&nbsp;&nbsp;"
		}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
			return '<a href="javascript:;" <pop:JugePermissionTag ename="view${enameModuleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="red">'+rowData.name+'</font></a>';
		}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
			return '<a href="javascript:;"  <pop:JugePermissionTag ename="view${enameModuleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="#778899">'+rowData.name+'</font></a>';
		}
		return '<a href="javascript:;" <pop:JugePermissionTag ename="view${enameModuleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font></a>';
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
	
	var gridOption ={
		colModel:[
			{name:"id", index:"id",sortable:false, hidden:true },
			{name:"encryptId", index:"id",sortable:false,frozen:true, hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center'},
	    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
			{name:"name-Font", index:"name", label: '企业名称',frozen:true,sortable:true,formatter:nameFont,width:200},
			{name:"name", index:"name", label: '企业名称',sortable:true,frozen:true,hidden:true,width:200},
			{name:"type", index:"type", label:'企业类型', sortable:true,formatter:typeFormatter,width:90,align:'center'},
			{name:"businessLicense",sortable:true, label: '工商执照号码',width:100},
			{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:150,sortable:false, hidden:true},
			{name:"legalPerson", index:"legalPerson", sortable:true,label:'法人代表',width:100},
			{name:"industry", index:"industry", sortable:true,label:'所属行业', formatter:industryFormatter,width:200},
			{name:"riskEnterprise",sortable:false, label:'是否危化企业',align:'center',formatter:isRiskEnterprise,width:100},
			
			{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			{name:'updateDate', sortable:true, label:'数据更新时间', width:140},
			{name:"hiddenCases", sortable:true, label:'隐患情况',hidden:true,width:200},
			{name:"employeeAmount",sortable:true,label:"员工数",hidden:true,width:100},
			{name:"partyMemberAmount",sortable:true,label:"党员数",hidden:true,width:100},
			{name:"areaString",label:"面积",sortable:false,hidden:true,width:100},
			{name:"fax",sortable:true,label:"传真",hidden:true,width:150},
			{name:"telephone",sortable:true,label:"法人联系电话",hidden:true,width:120},
			{name:"mobileNumber",sortable:true,label:"法人手机号码",hidden:true,width:100},
			{name:"address",sortable:true,label:"企业地址",hidden:true,width:200},
			{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
		]
	};	
	var dialogWidth=810;
	var dialogHeight=600;

	function getLocationName(rowData){
		return rowData.name;
	}
</script>

<jsp:include page="/baseinfo/commonPopulation/attentionLocationList.jsp">
	<jsp:param value="/baseinfo/safetyProductionKeyManage" name="listNameSpace"/>
	<jsp:param value="/baseinfo/searchSafetyProductionKey" name="searchNameSpace"/>
	<jsp:param value="${moduleName}" name="moduleName"/>
	<jsp:param value="${moduleCName}" name="moduleCName"/>	
	<jsp:param value="治安负责人" name="supervisorPerson"/>
	<jsp:param value="${keyType}" name="keyType"/>
	<jsp:param value="${enameModuleName}" name="enameModuleName"/>
</jsp:include>
<div style="display:none;">
<%if ("Enterprise".equals(moduleName)&&"Enterprise".equals(enameModuleName)){%>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@ENTERPRISEKEY_KEY"/>'/>
<%}else if("EnterpriseDown".equals(enameModuleName)){%>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@ENTERPRISEDOWNKEY_KEY"/>'/>
<%}else{%>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@SAFETYPRODUCTIONKEY_KEY"/>'/>
<%} %>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@ENTERPRISEKEY_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@ENTERPRISEKEY_KEY)" escape="false"/>'/>
</div>
