<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="RectificativePerson" name="moduleName"/>
</jsp:include>
<script type="text/javascript">
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="executeType" domainName="@com.tianque.domain.property.PropertyTypes@EXECUTE_TYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />




var dialogWidth=800;
var dialogHeight=640;
var gridOption = {
	colModel:[
   		{name:"id", index:"id", hidden:true,sortable:false,frozen : true},
   		{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
   		{name:"organization.id", index:"organization.id",sortable:false, hidden:true,hidedlg:true},
   		{name:"operate", index:"operate",label:"操作",width:90,formatter:operateFormatter,frozen : true,sortable:false,align:"center"},
   		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",width:100,formatter:attentionExtentColor,sortable:false,frozen:true},
		{name:"name", label:"姓名",index:"name",width:100,formatter:nameFont,sortable:false,frozen : true},
		{name:"gender", label:"性别",index:"gender",width:40,align:"center",formatter:genderFormatter,sortable:false},
		{name:"idCardNo", label:"身份证号码", index:"idCardNo", align:"center",width:160,frozen : true,sortable:false},
		{name:"organization.orgName",label:"所属网格", index:"organization.orgName", sortable:false,width:150,hidden:true},
		{name:"usedName",label:"曾用名",hidden:true, sortable:false,width:100},
		{name:'mobileNumber',label:"联系手机",sortable:false,width:100,align:"center", hidden:true},
		{name:'telephone',label:"联系电话",sortable:false,width:120,align:"center",hidden:true},
		{name:"birthday",label:"出生日期", width:100,align:"center",sortable:false,hidden:true},
		{name:"workUnit",label:"工作单位或就读学校", sortable:false,width:100,hidden:true},
		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:150,sortable:false,hidden:true},
		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true},
		{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:false,hidden:true},
		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:80,align:"center",sortable:false,hidden:true},
		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
		{name:"stature",label:"身高(cm)",hidden:true,align:"center", sortable:false,width:80},
		{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
		{name:"email",label:"电子邮件", width:150,sortable:false,hidden:true},
		{name:"province",label:"户籍地", width:150,sortable:false,formatter:householdRegisterFormatter, hidden:true},
		{name:"nativePlaceAddress",label:"户籍详址",sortable:false,width:200, hidden:true},
		{name:'nativePoliceStation',label:'户籍派出所',sortable:false,index:'nativePoliceStation',width:120,hidden:true},
		{name:'currentAddress',label:"常住地址",sortable:false,width:200},
		{name:'otherAddress',label:"临时居所 ",sortable:false,width:200, hidden:true},
		{name:'accusation',label:"罪名 ",sortable:false,width:200, hidden:true},
		{name:'executeType',label:"刑法执行类别",index:"executeType",width:100, sortable:false,formatter:executeTypeFormatter},
		{name:'penaltyTerm',label:"原判刑期",sortable:false,width:100, hidden:true},
		{name:"rectifyStartDate",label:"社区矫正开始时间",align:"center",sortable:false,width:120},
		{name:"rectifyEndDate",label:"社区矫正结束时间",align:"center",sortable:false,width:120},
		{name:'recentSituation',label:"近况描述 ",sortable:false,width:200, hidden:true},
		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{name:'updateDate', sortable:false, label:'数据更新时间',align:"center", width:160},
		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		{name:'interviewCount',index:'interviewCount',sortable:false, label:'走访次数',align:"center", width:80},
		{name:'isRemind',sortable:false,hidden:true,hidedlg:true,width:80}
	]
}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="RectificativePerson" name="moduleName"/>
	<jsp:param value="社区矫正人员" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.attentionPopulationType"
	value='<s:property value="@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_KEY"/>'/>
	<input type="hidden" id="_supervisorName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_KEY)" escape="false"/>'/>
</div>