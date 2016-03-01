<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="RectificativePerson" name="moduleName"/>
</jsp:include>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<%request.setAttribute("orgIdForStat",request.getParameter("orgIdForStat"));%>


<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
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
var dialogHeight=650;
function operateFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewRectificativePerson'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
}
var gridOption = {
	colModel:[
   		{name:"id", index:"id", hidden:true,frozen : true},
   		{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
   		{name:"organization.id", index:"organization.id", hidden:true,hidedlg:true},
   		{name:"operate", index:"operate",label:"操作",width:50,formatter:operateFormatter,frozen : true},
   		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",width:100,formatter:attentionExtentColor,sortable:true,frozen:true},
  		{name:"name", label:"姓名",index:"name",width:100,formatter:nameFont,frozen : true},
  		{name:"gender", label:"性别",index:"gender",width:40, formatter:genderFormatter},
  		{name:"idCardNo-formatter", label:"身份证号码", index:"idCardNo", width:160,hidedlg:true,hidden:true},
  		{name:"idCardNo", label:"身份证号码", index:"idCardNo", width:160,hidden : true},
  		{name:'currentAddress',label:"常住地址",sortable:false,width:180},
  		{name:'executeType',label:"刑法执行类别",index:"executeType",width:100, formatter:executeTypeFormatter},
  		{name:"organization.orgName",label:"所属网格", index:"organization.orgName", width:200,hidden:true},
  		{name:"usedName",label:"曾用名",hidden:true, width:100},
  		{name:"stature",label:"身高(cm)",hidden:true, width:100},
  		{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:false,hidden:true},
  		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:100,sortable:false,hidden:true},
  		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true},
  		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
  		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100,sortable:false,hidden:true},
  		{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
  		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
  		{name:"workUnit",label:"工作单位或就读学校", width:100,hidden:true},
  		{name:"email",label:"电子邮件", width:100,sortable:false,hidden:true},
  		{name:"birthday",label:"出生日期", width:100,hidden:true},
  		{name:"rectifyStartDate",label:"社区矫正开始时间",width:120},
  		{name:"rectifyEndDate",label:"社区矫正结束时间",width:120},
  		
  		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
  		{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
  		{name:'telephone',label:"联系电话",sortable:false,width:100,hidden:true},
  		{name:'mobileNumber',label:"联系手机",sortable:false,width:100, hidden:true},
  		{name:'penaltyTerm',label:"原判刑期",sortable:false,width:100, hidden:true},
  		{name:"province",label:"户籍地", width:200,sortable:false,formatter:householdRegisterFormatter, hidden:true},
  		{name:"nativePlaceAddress",label:"户籍详址",width:200, hidden:true},
  		{name:'recentSituation',label:"近况描述 ",sortable:false,width:200, hidden:true},
  		{name:'otherAddress',label:"临时居所 ",sortable:false,width:200, hidden:true},
  		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
  		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
  	]
}
</script>

<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="RectificativePerson" name="moduleName"/>
	<jsp:param value="社区矫正人员" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson"/>
	<jsp:param value="${orgIdForStat}" name="orgIdForStat"/>
	<jsp:param value="${searchType}" name="searchType"/>
</jsp:include>

<div style="display:none;">
<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_KEY"/>'/>	
	<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_KEY)" escape="false"/>'/>
</div>