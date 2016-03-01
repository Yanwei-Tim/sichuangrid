<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="IdleYouth" name="moduleName"/>
</jsp:include>

<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<script type="text/javascript">
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER"  />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="staffType" domainName="@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE" />
var dialogWidth=800;
var dialogHeight=600;

	function householdRegisterFormatter(el, options, rowData){
		var str = "";
		if(rowData.province != null)
			str += rowData.province;
		if(rowData.city != null)
			str += rowData.city
		if(rowData.district != null)
			str += rowData.district;
		return str;
	}
	
	function operateFormatter(el, options, rowData){
	    return "<pop:JugePermissionTag ename='viewIdleYouth'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
     }

	

	var gridOption = {
	   	colModel:[
   			{name:"id",index:"id",hidden:true,frozen:true},
   			{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
   			{name:"organization.id", index:"organization.id", hidden:true,hidedlg:true},
   			{name:"operate", index:"operate",label:"操作",width:50,formatter:operateFormatter,frozen : true},
   			{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
   			{name:"name", index:"name",label:"姓名",formatter:nameFont,width:100, frozen : true},
			{name:"gender",label:'性别',sortable:true,width:50,align:'center',formatter : genderFormatter },
			{name:"idCardNo", index:"idCardNo", width:160, label:"身份证号码", frozen : true},
			{name:'currentAddress',label:'常住地址',sortable:false,width:120},
			
			{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
			{name:'usedName',label:'曾用名',sortable:false,width:100,hidden:true},
			{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:140,sortable:true,hidden:true},
			{name:'workUnit',label:'工作单位或就读学校',sortable:false, width:100,hidden:true},
			{name:"province",label:"户籍地", width:200,sortable:false,formatter:householdRegisterFormatter, hidden:true},
			{name:"birthday",label:"出生日期", width:200,sortable:false,hidden:true},
			{name:'bloodType',label:'血型',formatter:bloodTypeFormatter,sortable:false,width:100,hidden:true},
			{name:'politicalBackground',label:'政治面貌',formatter:politicalBackgroundFormatter,sortable:false,width:100,hidden:true},
			{name:'nativePlaceAddress',label:'户籍地详址',sortable:false,width:100,hidden:true},
			{name:'otherAddress',label:'临时居所',sortable:false,width:100,hidden:true},
			{name:'mobileNumber',label:'联系手机',sortable:false,width:100,hidden:true},
			{name:'telephone',label:'固定电话',sortable:false,width:100,hidden:true},
			{name:'career',label:'职业',formatter:careerFormatter,sortable:false,width:100,hidden:true},
			{name:'maritalState',label:'婚姻状况',formatter:maritalStateFormatter,sortable:false,width:100,hidden:true},
			{name:'stature',label:'身高(cm)',sortable:false,width:100,hidden:true},
			{name:'nation',label:'民族',formatter:nationFormatter,sortable:false,width:100,hidden:true},
			{name:'faith',label:'宗教信仰',formatter:faithFormatter,sortable:false,width:100,hidden:true},
			{name:'schooling',label:'文化程度',formatter:schoolingFormatter,sortable:false,width:100,hidden:true},
			{name:'email',label:'电子邮箱',sortable:false,width:100,hidden:true},
			{name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
	        {name:'isEmphasis',sortable:false,label:'是否关注',hidden:true,hidedlg:true,width:100,hidden:true}
		]};
		

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#idleYouthList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#idleYouthList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}


</script>
<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="IdleYouth" name="moduleName"/>
	<jsp:param value="重点青少年" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson" />
	<jsp:param value="${searchType}" name="searchType"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_KEY)" escape="false"/>'/>
</div>