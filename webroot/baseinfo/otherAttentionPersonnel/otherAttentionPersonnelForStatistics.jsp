<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>

<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="OtherAttentionPersonnel" name="moduleName"/>
</jsp:include>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER"  />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />



function operateFormatter(el, options, rowData){
	return "<pop:JugePermissionTag ename='viewDangerousGoodsPractitioner'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
}

function periodOfValidityFormatter(el, options, rowData){
	return rowData.periodOfValidityStart + "至" + rowData.periodOfValidityEnd;
}

function statusFormatter(el, options, rowData) {
	if(rowData.death){
		return "<font color='red'>"+rowData.name+"</font>";
	}
	if(rowData.isEmphasis==1){
		return "<font color='#778899'>"+rowData.name+"</font>";
	}
	return rowData.name;
}

function cardFormatter(el, options, rowData) {
	if(rowData.isEmphasis==1) {
		return "<font color='#778899'>"+rowData.idCardNo+"</font>";
	}
	return rowData.idCardNo;
}
var gridOption = {
		colNames:['id','encryptId','orgId','操作','关注程度','姓名','性别','身份证号码','常住地址','所属区域(网格)','户籍地',
		          '工作单位或就读学校','工作情况','有无服务成员','最新服务时间','数据来源','数据更新时间','从业资格证书','从业资格证书号','有效期','法人代表','法人联系手机','法人联系电话',
		          '曾用名','出生日期','户籍地详址','临时居所','联系手机','固定电话','职业',
	 	          '政治面貌','婚姻状况','身高','民族','宗教信仰','文化程度','血型',
	 	          '电子邮箱','是否关注','是否死亡'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	        {name:"organization.id", index:"organization.id", hidden:true,hidedlg:true},
	        {name:"operate",width:50,formatter:operateFormatter,frozen:true},
	        {name:"attentionExtent",index:"attentionExtent",width:100,formatter:attentionExtentColor,sortable:true,frozen:true},
		    {name:"name",index:'name',formatter:nameFont,width:60,sortable:true,frozen:true},
		    {name:'gender',sortable:true,formatter:genderFormatter,width:40},
		    {name:'idCardNo',index:'idCardNo', width:110,sortable:true,frozen:true},
		    {name:'currentAddress',sortable:false,hidden:true,width:100},
		    {name:'organization.orgName',sortable:false,hidden:true,width:260},
		    {name:'detailNativeAddress',sortable:false,hidden:true,width:150},	        
		    {name:'workUnit',sortable:false,hidden:true,width:150},
		    {name:'workCondition',sortable:true,width:100},
		    
		    {name:'hasServiceTeamMember',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
			{name:'sourcesState',index:'sourcesState',sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		    {name:'updateDate', sortable:false,width:140},
			{name:'workingCertificate',width:150,hidden:true,sortable:false},
		    {name:'workingCertificateNo',width:150,hidden:true,sortable:false},
		    {name:'periodOfValidity',formatter:periodOfValidityFormatter,width:150,hidden:true,sortable:false},
		    {name:'legalPerson',sortable:false,hidden:true,width:150},
		    {name:'legalPersonMobileNumber',width:150,hidden:true,sortable:false},
		    {name:'legalPersonTelephone',width:150,hidden:true,sortable:false},
	        {name:'usedName',width:70,hidden:true,sortable:false},
		    {name:'birthday',index:'birthday',width:70,hidden:true,sortable:true,width:120},
		    {name:'nativePlaceAddress',width:150,hidden:true,sortable:false},
		    {name:'otherAddress',width:150,hidden:true,sortable:false},
		    {name:'mobileNumber',width:50,hidden:true,sortable:false},
		    {name:'telephone',width:50,hidden:true,sortable:false},
		    {name:'career',width:100,sortable:false,hidden:true,formatter:careerFormatter},
		    {name:'politicalBackground',formatter:politicalBackgroundFormatter,width:70,hidden:true,sortable:false},
		    {name:'maritalState',formatter:maritalStateFormatter,width:70,hidden:true,sortable:false},
		    {name:'stature',width:70,hidden:true,sortable:false},
		    {name:'nation',width:70,formatter:nationFormatter,hidden:true,sortable:false},
		    {name:'faith',formatter:faithFormatter,width:70,hidden:true,sortable:false},
		    {name:'schooling',formatter:schoolingFormatter,width:70,hidden:true,sortable:false},
		    {name:'bloodType',formatter:bloodTypeFormatter,width:70,hidden:true,sortable:false},
		    {name:'email',width:70,hidden:true,sortable:false},
		    {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true},
		    {name:'death',sortable:false,hidden:true,hidedlg:true}
		]};
</script>



<div class="content">
<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="OtherAttentionPersonnel" name="moduleName"/>
	<jsp:param value="其他人员" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson"/>
	<jsp:param value="${searchType}" name="searchType"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@OTHERATTENTIONPERSONNEL_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@OTHERATTENTIONPERSONNEL_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@OTHERATTENTIONPERSONNEL_KEY)" escape="false"/>'/>
</div>