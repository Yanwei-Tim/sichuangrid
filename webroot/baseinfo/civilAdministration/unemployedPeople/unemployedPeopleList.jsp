<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="UnemployedPeople" name="moduleName"/>
</jsp:include>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="technologyLevel" domainName="@com.tianque.domain.property.PropertyTypes@TECHNOLOGYLEVEL" />
<pop:formatterProperty name="unemploymentReason" domainName="@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON" />
<pop:formatterProperty name="employmentIntention" domainName="@com.tianque.domain.property.PropertyTypes@EMPLOYMENTINTENTION" />
<pop:formatterProperty name="trainingIntention" domainName="@com.tianque.domain.property.PropertyTypes@TRAININGINTENTION" />
<pop:formatterProperty name="unemployedPeopleType" domainName="@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

var gridOption = {
	colModel:[
		{name:"id", index:"id", sortable:false,hidden:true,frozen : true},
		{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		{name:"organization.id", index:"organization.id",sortable:false, hidden:true,hidedlg:true},
		{name:"operate", index:"operate",label:"操作",width:90,formatter:operateFormatter,sortable:false,frozen : true,align:"center"},
		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",width:100,sortable:false,formatter:attentionExtentColor,forzen:true},
		{name:"name", index:"name",label:"姓名",formatter:nameFont,sortable:false,width:100, frozen : true},
		{name:"gender", label:"性别",index:"gender",width:50,sortable:false, formatter:genderFormatter,align:"center"},
		{name:"idCardNo", index:"idCardNo", sortable:false,width:160, label:"身份证号码",align:"center", frozen : true},
		{name:"currentAddress",label:"常住地址",sortable:false, width:200},
		{name:"unemploymentReason", label:"失业原因",sortable:false,formatter:unemploymentReasonFormatter, width:300},
		{name:"unemploymentDate",label:"失业时间",sortable:false,align:"center", width:100},
		
		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{name:'updateDate', sortable:false, label:'数据更新时间',align:"center", width:160},
		{name:"usedName", index:'usedName', label:'曾用名',sortable:false, width:80 ,hidden:true},
		{name:"organization.orgName", index:"orgInternalCode", width:150,label:"所属网格",sortable:false,hidden:true},
		{name:"unemployedPeopleType", label:"人员类型",sortable:false,formatter:unemployedPeopleTypeFormatter, width:100,hidden:true},
		{name:"registerNumber", label:"失业/就业证号",sortable:false,hidden:true},
		{name:"upEnterWorkDate", label:"参加工作时间",sortable:false,align:"center", width:100,hidden:true},
		{name:"originalWorkUnit", label:"原工作单位名称",width:200,sortable:false,hidden:true},
		{name:"originalWorkUnitType", label:"原单位类型", width:90,sortable:false,hidden:true},
		{name:"title", label:"职称",sortable:false, width:100,hidden:true},
		{name:"technologyLevel", label:"技术等级",sortable:false,formatter:technologyLevelFormatter, width:100,hidden:true},


		{name:"workUnit",label:"工作单位或就读学校", width:200,sortable:false,hidden:true},
		{name:"province",label:"户籍地", width:200,sortable:false,formatter:householdRegisterFormatter, hidden:true},
		{name:"nativePlaceAddress",label:"户籍详址",sortable:false,width:200, hidden:true},
		{name:"stature",label:"身高(cm)",width:80,align:"center",sortable:false,hidden:true},
		{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:false,hidden:true},
		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:150,sortable:false,hidden:true},
		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true},
		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100,align:"center",sortable:false,hidden:true},
		{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
		{name:"otherAddress",label:"临时居所",width:200,sortable:false,hidden:true},
		{name:"telephone",label:"联系电话", width:120,align:"center",sortable:false,hidden:true},
		{name:"mobileNumber",label:"联系手机", width:100,align:"center",sortable:false,hidden:true},
		{name:"email",label:"电子邮件", width:150,sortable:false,hidden:true},
		{name:"birthday",label:"出生日期", width:100,sortable:false,align:"center",hidden:true},
		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=600;
</script>
<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="UnemployedPeople" name="moduleName"/>
	<jsp:param value="失业人员" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson" />
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@UNEMPLOYEDPEOPLE_KEY"/>'/>
		<input type="hidden" id="_supervisorName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@UNEMPLOYEDPEOPLE_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@UNEMPLOYEDPEOPLE_KEY)" escape="false"/>'/>
</div>