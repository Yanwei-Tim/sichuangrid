<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="DangerousGoodsPractitioner" name="moduleName"/>
</jsp:include>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER"  />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="dangerousWorkingType" domainName="@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

function periodOfValidityFormatter(el, options, rowData){
	var result= (rowData.periodOfValidityStart==null)?"":rowData.periodOfValidityStart;
	if(rowData.periodOfValidityEnd!=null){
		result += " 至 " + rowData.periodOfValidityEnd;
	}
	return result;
}

function domicileFormatter(e,options,rowData){
	return rowData.province + rowData.city + rowData.district;
}

var gridOption = {
		colNames:['id','encryptId','orgId','操作','关注程度','姓名','性别','身份证号码','常住地址','所属区域(网格)','户籍地',
		          '工作单位或就读学校','危险从业类别','数据来源','数据更新时间','有无服务成员','最新服务时间','从业资格证书','从业资格证书号','有效期','法人代表','法人联系手机','法人联系电话',
		          '曾用名','出生日期','户籍地详址','户籍派出所','临时居所','联系手机','固定电话','职业',
	 	          '政治面貌','婚姻状况','身高','民族','宗教信仰','文化程度','血型',
	 	          '电子邮箱','是否关注','是否死亡'],
	 	          //,'有无服务成员','最新服务时间'
	   	colModel:[
	        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
	        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	        {name:"organization.id", index:"organization.id", sortable:false,hidden:true,hidedlg:true},
	        {name:"operate",width:90,formatter:operateFormatter,frozen:true,sortable:false,align:"center"},
	        {name:"attentionExtent",index:"attentionExtent",width:100,formatter:attentionExtentColor,sortable:false,frozen:true},
		    {name:"name",index:'name',formatter:nameFont,width:60,sortable:false,frozen:true},
		    {name:'gender',sortable:false,formatter:genderFormatter,width:40,align:"center"},
		    {name:'idCardNo',index:'idCardNo',width:160,align:"center",sortable:false,frozen:true},
		    {name:'currentAddress',sortable:false,hidden:true,width:200},
		    {name:'organization.orgName',sortable:false,hidden:true,width:150},
		    {name:'province',formatter:domicileFormatter,sortable:false,hidden:true,width:150},
		    {name:'workUnit',sortable:false,hidden:true,width:180},
		    
		    {name:'dangerousWorkingType',formatter:dangerousWorkingTypeFormatter,sortable:false,align:"center",width:80},
		    
		   // {name:'hasServiceTeamMember',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
			//{name:'lastRecordTime',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
			{name:'sourcesState',index:'sourcesState',sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		    {name:'updateDate', sortable:false,width:160,align:"center"},
		    
		    {name:'hasServiceTeamMember',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime',index:'lastRecordTime',sortable:false,align:'center',width:100},
			
			{name:'workingCertificate',width:150,hidden:true,sortable:false},
		    {name:'workingCertificateNo',width:150,hidden:true,sortable:false},
		    {name:'periodOfValidity',formatter:periodOfValidityFormatter,align:"center",width:180,hidden:true,sortable:false},
		    
		    {name:'legalPerson',sortable:false,hidden:true,width:150},
		    {name:'legalPersonMobileNumber',width:180,align:"center",hidden:true,sortable:false},
		    {name:'legalPersonTelephone',width:100,align:"center",hidden:true,sortable:false},
	        {name:'usedName',width:70,hidden:true,sortable:false},
		    {name:'birthday',index:'birthday',width:70,align:"center",hidden:true,sortable:false,width:120},
		    
		    {name:'nativePlaceAddress',width:150,hidden:true,sortable:false},
		    {name:'nativePoliceStation',sortable:false,index:'nativePoliceStation',width:120,hidden:true},
		    {name:'otherAddress',width:150,hidden:true,sortable:false},
		    {name:'mobileNumber',width:150,align:"center",hidden:true,sortable:false},
		    {name:'telephone',width:100,align:"center",hidden:true,sortable:false},
		    {name:'career',width:100,sortable:false,hidden:true,formatter:careerFormatter},
		    
		    {name:'politicalBackground',formatter:politicalBackgroundFormatter,width:150,hidden:true,sortable:false},
		    {name:'maritalState',formatter:maritalStateFormatter,width:75,align:"center",hidden:true,sortable:false},
		    {name:'stature',width:70,hidden:true,align:"center",sortable:false},
		    {name:'nation',width:100,formatter:nationFormatter,hidden:true,sortable:false},
		    {name:'faith',formatter:faithFormatter,width:80,hidden:true,sortable:false},
		    {name:'schooling',formatter:schoolingFormatter,width:100,hidden:true,sortable:false},
		    {name:'bloodType',formatter:bloodTypeFormatter,width:90,hidden:true,sortable:false},
		    {name:'email',width:150,hidden:true,sortable:false},
		    {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true},
		    {name:'death',sortable:false,hidden:true,hidedlg:true}
		]};
</script>
<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="DangerousGoodsPractitioner" name="moduleName" />
	<jsp:param value="危险品从业人员" name="moduleCName" />
	<jsp:param value="服务人员" name="supervisorPerson" />
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@DANGEROUSGOODSPRACTITIONER_KEY"/>'/>
		<input type="hidden" id="_supervisorName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@DANGEROUSGOODSPRACTITIONER_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@DANGEROUSGOODSPRACTITIONER_KEY)" escape="false"/>'/>
</div>