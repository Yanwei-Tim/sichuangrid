<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="FPersonnel" name="moduleName"/>
</jsp:include>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
function rendIsRepeat(el, options, rowData){
	if(1==rowData.isRepeat){
		return "是";
	}else{
		return "否";
	}
}
function provinceFormatter(el, options, rowData){
	var str = "";
	if(rowData.province != null)
		str += rowData.province;
	if(rowData.city != null)
		str += rowData.city
	if(rowData.district != null)
		str += rowData.district;
	return str;
}

var dialogWidth=800;
var dialogHeight=650;
var gridOption = {
		colModel:[
			        {name:"id",index:"id",hidden:true},
			        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
			        {name:"operate",width:90,label:'操作',formatter:operateFormatter,frozen:true,sortable:false,align:"center"},
			        {name:"attentionExtent",label:'关注程度',index:"attentionExtent",width:100,formatter:attentionExtentColor,sortable:false},
			        {name:"name", index:"name",label:"姓名",formatter:nameFont,width:80, sortable:false,frozen:true},
			        {name:'idCardNo',label:'身份证号码',sortable:false,width:160},
			        {name:"organization.id", index:"organization.id",sortable:false,hidden:true,hidedlg:true},
			        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		      		{name:"gender",label:'性别',sortable:false,width:50,align:'center',formatter:genderFormatter },
		      		{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:200,hidden:true},
		      		{name:'birthday', sortable:false, label:'出生日期', width:90},
		      		{name:'schooling',label:'文化程度',sortable:false,formatter : schoolingFormatter,width:80,hidden:true},
			   		{name:'telephone',label:'联系电话',sortable:false, width:120,hidden:true},
			   		{name:'mobileNumber',label:'联系手机',sortable:false, width:100,hidden:true},
			        {name:'currentAddress',label:'常住地址',sortable:false,width:100},
			        {name:'nativePoliceStation',label:'户籍派出所',sortable:false,width:100,hidden:true},
			        {name:'nativePlaceAddress',label:'户籍详址',sortable:false,width:100},
			        {name:"province", index:"province", label:"户籍地", width:160,sortable:false,hidden:true,formatter:provinceFormatter},
			        {name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100},
			        {name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
					{name:'lastRecordTime',label:'最新服务时间',index:'lastRecordTime',sortable:false,align:'center',width:100},
			        {name:'sourcesState',label:'数据来源',sortable:false,hidden:true,formatter:sourceStateFormatter,width:80},
			        {name:'updateDate', sortable:false, label:'数据更新时间',align:"center", width:160}
			       
				]
}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="FPersonnel" name="moduleName"/>
	<jsp:param value="F" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@FPERSONNEL_KEY"/>'/>
		<input type="hidden" id="_supervisorName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@FPERSONNEL_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_"
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@FPERSONNEL_KEY)" escape="false"/>'/>
</div>