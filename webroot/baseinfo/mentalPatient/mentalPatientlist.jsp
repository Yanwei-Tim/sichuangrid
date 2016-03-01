<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="MentalPatient" name="moduleName"/>
</jsp:include>
<script type="text/javascript">

var dialogWidth=800;
var dialogHeight=600;

<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="dangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="psychosisType" domainName="@com.tianque.domain.property.PropertyTypes@PSYCHOSIS_TYPES" />
<pop:formatterProperty name="treatmentState" domainName="@com.tianque.domain.property.PropertyTypes@TREATMENTSTATE" />
function isTreat(el, options, rowData){
	if(true==rowData.treat){
		return "是";
	}else{
		return "否";
	}
}



function dangerLevelColor(el,options,rowData){
	var displayName=dangerLevelFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='高')
		return '<span>高：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='中')
		return '<span>中：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='低')
		return '<span>低：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}
var gridOption = {
	colModel:[
	   	{name:"id", index:"id", sortable:false,hidden:true,frozen : true},
	   	{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	   	{name:"organization.id", index:"organization.id", sortable:false,hidden:true,hidedlg:true},
	   	{name:"operation",index:"id",label:"操作",formatter:operateFormatter,width:90,frozen : true,sortable:false,align:"center"},
		{name:'dangerLevel',label:"危险程度",index:"dangerLevel",width:100,sortable:false,formatter:dangerLevelColor},
		{name:"name", label:"姓名",formatter:nameFont,index:"name",width:100,sortable:false,frozen : true},
	    {name:"gender", label:"性别",index:"gender",width:50,align:"center",sortable:false, formatter:genderFormatter},
		{name:"idCardNo", label:"身份证号码",align:"center", index:"idCardNo",sortable:false, width:160,frozen : true},
		{name:"organization.orgName",label:"所属网格", index:"orgInternalCode", sortable:false,width:150,hidden:true},
		{name:"usedName",label:"曾用名", width:100, sortable:false,hidden:true},
		{name:'telephone',label:"联系电话",sortable:false,align:"center",width:110,hidden:true},
		{name:'mobileNumber',label:"联系手机",sortable:false,align:"center",width:100, hidden:true},
		{name:"birthday",label:"出生日期", width:100,align:"center",sortable:false,hidden:true},
		{name:"workUnit",label:"工作单位或就读学校",width:200, sortable:false,hidden:true},
		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,sortable:false,width:150,hidden:true},
		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100, sortable:false,hidden:true},
		{name:"nation",label:"民族",formatter:nationFormatter, width:100, sortable:false,hidden:true},
		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,align:"center",width:100, sortable:false,hidden:true},
		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
		{name:"stature",label:"身高(cm)",sortable:false,align:"center", width:80,hidden:true},
		{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
		{name:"email",label:"电子邮件", width:150, sortable:false,hidden:true},
		{name:"province",label:"户籍地", width:150,sortable:false,formatter:householdRegisterFormatter, hidden:true},
		{name:"nativePlaceAddress",label:"户籍详址",width:200,sortable:false, hidden:true},
		{name:'nativePoliceStation',label:'户籍派出所',sortable:false,index:'nativePoliceStation',width:120,hidden:true},
		{name:"currentAddress",label:"常住地址", width:200,sortable:false},
		{name:"otherAddress",label:"临时居所", width:200, sortable:false,hidden:true},
		{name:'psychosisName',label:"患病名称",sortable:false,width:100,hidden:true},
		{name:'psychosisType',label:"精神病类型",sortable:false,width:100,hidden:true, formatter:psychosisTypeFormatter},
		{name:'treat',label:"是否接受过治疗",index:"treat",align:'center',width:90,formatter:isTreat,sortable:false,hidden:true},
		{name:"cureDepartment",label:"康复机构",sortable:false, width:100,hidden:true},
		{name:'diseaseTime',label:"发病时间",sortable:false,width:100,hidden:true},
		{name:'treatmentState',label:"治疗状态",sortable:false,width:100,hidden:true, formatter:treatmentStateFormatter},
		{name:'recoveryTime',label:"康复时间",sortable:false,width:100,hidden:true},
		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{name:'updateDate', sortable:false, label:'数据更新时间',align:"center", width:140},
		{name:'interviewCount',index:'interviewCount',sortable:false, label:'走访次数',align:"center", width:80},
		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80}
	]
}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="MentalPatient" name="moduleName"/>
	<jsp:param value="严重精神障碍患者" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@MENTALPATIENT_KEY"/>'/>
		<input type="hidden" id="_supervisorName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@MENTALPATIENT_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@MENTALPATIENT_KEY)" escape="false"/>'/>
</div>