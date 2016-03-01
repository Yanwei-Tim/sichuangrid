<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="OptimalObject" name="moduleName"/>
</jsp:include>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<script type="text/javascript">
	var dialogWidth = 800;
	var dialogHeight = 600;

	//字典的自定义标签
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<pop:formatterProperty name="optimalCardType" domainName="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" />
	<pop:formatterProperty name="abilityLiving" domainName="@com.tianque.domain.property.PropertyTypes@VIABILITY" />
	<pop:formatterProperty name="supportSituation" domainName="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS" />
	<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
	<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
	<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
	<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
	<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
	<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
	<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

	function isTreat(el, options, rowData) {
		if (true == rowData.treat) {
			return "是";
		} else {
			return "否";
		}
	}

	function operateFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='viewOptimalObject'> <a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
	}
	var gridOption = {
		colModel : [
	{name:"id", index:"id", hidden:true,frozen : true},
	{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	{name:"organization.id", index:"organization.id", hidden:true,hidedlg:true},
	{name:"operate", index:"operate",label:"操作",width:50,formatter:operateFormatter,frozen : true},
	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",width:100,formatter:attentionExtentColor,sortable:true, forzen:true},
	{name:"name", index:"name",label:"姓名",formatter:nameFont,width:100, frozen : true},
	{name:"gender", index:"gender", label:"性别", width:40, formatter:genderFormatter,align:"center"},
	{name:"idCardNo", index:"idCardNo", label:"身份证号码", width:150, frozen : true},
	{name:"currentAddress", index:"currentAddress", label:"常住地址", width:150, frozen : true},
	{name:"optimalCardType", index:'optimalCardType', label:'优抚类型', width:100, formatter:optimalCardTypeFormatter, frozen : true},
	{name:"abilityLiving", index:'abilityLiving', label:'生活能力', width:80, formatter:abilityLivingFormatter, frozen : true},
	
	{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
	{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
	{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
	{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170, frozen : true,hidden:true},
	{name:"province", index:"province", label:"户籍地", width:60,sortable:false, frozen : true,hidden:true},
	{name:"optimalCardNo", index:'optimalCardNo', label:'优待证号', width:100, frozen : true,hidden:true},
	{name:"supportSituation", index:'supportSituation', label:'供养情况', width:100, formatter:supportSituationFormatter, frozen : true,hidden:true},
	{name:"usedName", index:'usedName', label:'曾用名', width:80 ,hidden:true},
	{name:"workUnit",label:"工作单位或就读学校", width:200,sortable:false,hidden:true},
	{name:"nativePlaceAddress",label:"户籍详址",width:200, hidden:true},
	{name:"stature",label:"身高(cm)",width:100,sortable:false,hidden:true},
	{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:false,hidden:true},
	{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:100,sortable:false,hidden:true},
	{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true},
	{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
	{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100,sortable:false,hidden:true},
	{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
	{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
	{name:"otherAddress",label:"临时居所",width:100,sortable:false,hidden:true},
	{name:"telephone",label:"联系电话", width:100,sortable:false,hidden:true},
	{name:"mobileNumber",label:"联系手机", width:100,sortable:false,hidden:true},
	{name:"email",label:"电子邮件", width:100,sortable:false,hidden:true},
	{name:"birthday",label:"出生日期", width:100,hidden:true},
	{name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
	{name:'isEmphasis',label:"是否注销",hidedlg:true,hidden:true,width:80}
		            ]
	}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="OptimalObject" name="moduleName"/>
	<jsp:param value="优抚对象" name="moduleCName"/>
	<jsp:param value="${searchType}" name="searchType"/>
	<jsp:param value="服务人员" name="supervisorPerson" />
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@OPTIMALOBJECT_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@OPTIMALOBJECT_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@OPTIMALOBJECT_KEY)" escape="false"/>'/>
</div>