<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="ElderlyPeople" name="moduleName"/>
</jsp:include>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
	<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
	<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
	<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
	<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
	<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
	<pop:formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />
	<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
	<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
	<pop:formatterProperty name="peopleType" domainName="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" />
	<pop:formatterProperty name="currentStatus" domainName="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" />
	<pop:formatterProperty name="liveStatus" domainName="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" />
	<pop:formatterProperty name="spouseStatus" domainName="@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS" />
	<pop:formatterProperty name="incomeSource" domainName="@com.tianque.domain.property.PropertyTypes@INCOME_SOURCE" />
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	function nativePlaceFormatter(el, options, rowData){
		var str = "";
		if(rowData.province != null)
			str += rowData.province;
		if(rowData.city != null)
			str += rowData.city;
		if(rowData.district != null)
			str += rowData.district;
		return str;
	}


var gridOption = {
	   	colModel:[
	        {name:"id", index:"id", sortable:false,hidden:true,frozen:true},
	        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	        {name:"organization.id",sortable:false, index:"organization.id", hidden:true,hidedlg:true},
	        {name:"operate", index:"operate",label:"操作",width:90,formatter:operateFormatter,frozen : true,sortable:false,align:"center"},
	        {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:false,width:100,formatter:attentionExtentColor,frozen:true},
	        {name:"name", index:'name', label:'姓名',formatter:nameFont,width:80,sortable:false,frozen:true },
	        {name:'gender',formatter:genderFormatter, label:'性别', align:'center',sortable:false, width:50},
	        {name:'idCardNo',index:'idCardNo', label:'身份证号码', align:'center',sortable:false,width:160,frozen:true},
	        {name:'organization.orgName',sortable:false,hidden:true,index:'organization.orgName',label:'所属网格',width:150},
	        {name:'province',index:'province',sortable:false,label:'户籍地',hidden:true,formatter:nativePlaceFormatter,width:150},
	        {name:'nativePlaceAddress', sortable:false, label:'户籍地详址', width:150,hidden:true},
	        {name:'currentAddress', sortable:false, label:'常住地址', width:200},
	        
	        {name:"birthday", index:'birthday', sortable:false,label:'出生日期',hidden:true, align:'center', width:80 },
	        {name:'elderPeopleId', sortable:false, label:'老年人证号',hidden:true, width:100},
	        {name:'peopleType',formatter:peopleTypeFormatter, sortable:false, label:'人员类型', align:'center', width:100},
	      
	        {name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			{name:'updateDate', sortable:false, label:'数据更新时间', align:'center', width:160},
	        {name:'currentStatus',formatter:currentStatusFormatter, sortable:false, label:'目前状况', width:100,hidden:true},
	        
	        {name:'liveStatus', formatter:liveStatusFormatter, sortable:false, label:'居住情况', width:100,hidden:true},
	        {name:'spouseStatus',formatter:spouseStatusFormatter,  sortable:false, label:'配偶情况', width:80,hidden:true},
	        {name:'incomeSource',formatter:incomeSourceFormatter,  sortable:false, label:'经济来源',width:80,hidden:true},
	        {name:'enterWorkDate', sortable:false, label:'参加工作日期', align:'center', width:100,hidden:true},
	        {name:'retireUnitAndDuty', sortable:false, label:'离退休单位', width:100,hidden:true},
	        
	        {name:'retireDate', sortable:false, label:'离退休日期', align:'center', hidden:true, width:100},
	        {name:'zhiwu', sortable:false, label:'单位职务', width:100,hidden:true},
	        {name:'pension', sortable:false, label:'退休金(元)',hidden:true, width:100},
	        {name:"usedName", index:'usedName', sortable:false,label:'曾用名/别名', width:95 ,hidden:true},
	        {name:'mobileNumber', sortable:false, label:'联系手机', align:'center', width:100,hidden:true},
	        
	        {name:'telephone', sortable:false, label:'固定电话', align:'center', width:120,hidden:true},
	        {name:"nation",sortable:false,label:"民族",formatter:nationFormatter,width:80, hidden:true },
	        {name:"politicalBackground",sortable:false,label:"政治面貌",formatter:politicalBackgroundFormatter,width:150,hidden:true},
	        {name:"schooling",sortable:false,label:"文化程度",formatter:schoolingFormatter,width:80, hidden:true },
	        {name:'career', sortable:false,formatter:careerFormatter,label:'职业', width:80,hidden:true},
	        
	        {name:'workUnit', sortable:false, label:'工作单位或就读学校', width:100,hidden:true},
	        {name:"maritalState",sortable:false,label:"婚姻状况",formatter:maritalStateFormatter, align:'center', width:80, hidden:true },
	        {name:"stature",sortable:false,label:"身高(cm)",width:80, align:'center', hidden:true },
	        {name:"bloodType",sortable:false,label:"血型",formatter:bloodTypeFormatter,width:80, hidden:true },
	        {name:"faith",sortable:false,label:"宗教信仰",formatter:faithFormatter,width:80, hidden:true },
	        {name:"email",sortable:false,label:"电子邮箱",width:150, hidden:true },
	        {name:'otherAddress', sortable:false, label:'临时居所', width:200,hidden:true},
	        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
	        {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true,width:80}
		]};

</script>

<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="ElderlyPeople" name="moduleName"/>
	<jsp:param value="老年人" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson" />
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@ELDERLYPEOPLE_KEY"/>'/>
		<input type="hidden" id="_supervisorName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@ELDERLYPEOPLE_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@ELDERLYPEOPLE_KEY)" escape="false"/>'/>
</div>