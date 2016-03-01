<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.ui-layout-center{overflow:auto;}
</style>

<div class="accountType" style="overflow:auto;height:480px;" >
	<div id="gridbox12" class="SigmaReport" style="width:2500px;overflow-x:auto;"></div>
</div>
<script type="text/javascript">
var primaryOrgStatGrid = null;
function initReportGrid(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"统计",width:50,align:'center',mode:"string"},
				{name:"total",caption:"合计",width:40,align:'center',mode:"string"},	
	   			{name:"peopleAsWork",caption:"民生工作",width:130,align:'center',children:[
					{name:"peopleAsWorkTotal",caption:"小计",width:40,align:'center',mode:"string"},	
	   				{name:"waterResourceCount",caption:"水利",width:40,align:'center',mode:"string"},	
	   				{name:"trafficCount",caption:"交通",width:40,align:'center',mode:"string"},	
	   				{name:"educationCount",caption:"教育",width:50,align:'center',mode:"string"},	
	   				{name:"healthMedicalCount",caption:"医疗卫生",width:40,align:'center',mode:"string"},	
	   				{name:"resourcesAgriculturalCount",caption:"农业资源",width:40,align:'center',mode:"string"},	
	   				{name:"energyCount",caption:"能源",width:40,align:'center',mode:"string"},	
	   				{name:"socialLaborCount",caption:"劳动和社会保障",width:40,align:'center',mode:"string"},	
	   				{name:"environmentalProtectionCount",caption:"环境保护",width:40,align:'center',mode:"string"},	
	   				{name:"planningAdviceManagementCount",caption:"城乡规划建议管理",width:40,align:'center',mode:"string"},	
	   				{name:"scienceTechnologyCount",caption:"科技文体",width:40,align:'center',mode:"string"},	
	   				{name:"otherResourcesCount",caption:"其他资源",width:40,align:'center',mode:"string"}
	   			 ]},
	   			{name:"ledgerPoorPeople",caption:"困难群众",width:130,align:'center',children:[
					{name:"ledgerPoorPeopleTotal",caption:"小计",width:40,align:'center',mode:"string"},	
					{name:"lifeCount",caption:"生活",width:40,align:'center',mode:"string"},	
					{name:"medicalCount",caption:"医疗",width:40,align:'center',mode:"string"},	
					{name:"housingCount",caption:"住房",width:40,align:'center',mode:"string"},	
					{name:"goSchoolCount",caption:"就学",width:40,align:'center',mode:"string"},	
					{name:"employmentCount",caption:"就业",width:40,align:'center',mode:"string"},	
					{name:"ledgerPoorPeopleOtherCount",caption:"其他",width:40,align:'center',mode:"string"},	
				]},
				{name:"steadyRecordWork",caption:"稳定工作",width:130,align:'center',children:[
				     {name:"steadyRecordWorkTotal",caption:"小计",width:40,align:'center',mode:"string"},	
				     {name:"visitsCount",caption:"涉法涉诉",width:40,align:'center',mode:"string"},	
				     {name:"forestSoilCount",caption:"林水土",width:40,align:'center',mode:"string"},	
				     {name:"favorablePoliciesCount",caption:"惠农政策及村（社区）政务、财务",width:50,align:'center',mode:"string"},	
				     {name:"civilAdministrationIssuesCount",caption:"民政问题",width:40,align:'center',mode:"string"},	
				     {name:"populationMedicalCount",caption:"人口与医疗卫生",width:40,align:'center',mode:"string"},	
				     {name:"laborSocialSecurityCount",caption:"劳动保障",width:40,align:'center',mode:"string"},	
				     {name:"transportationCount",caption:"交通运输",width:40,align:'center',mode:"string"},	
				     {name:"urbanConstructionAndCLECount",caption:"城建及综合执法",width:40,align:'center',mode:"string"},	
				     {name:"cpcPartyDisciplinesCount",caption:"党纪政纪",width:40,align:'center',mode:"string"},	
				     {name:"steadyRecordWorkEducationCount",caption:"教育",width:40,align:'center',mode:"string"},	
				     {name:"enterpriseRestructuringCount",caption:"企业改制",width:40,align:'center',mode:"string"},	
				     {name:"environmentalConservationCount",caption:"环境保护",width:40,align:'center',mode:"string"},	
				     {name:"organizationPersonnelCount",caption:"组织人事",width:40,align:'center',mode:"string"},	
				     {name:"steadyRecordWorkOtherCount",caption:"其它",width:40,align:'center',mode:"string"},	
				     {name:"keyPersonnelCount",caption:"重点人员",width:40,align:'center',mode:"string"}
				]}
	   		   ];	
	primaryOrgStatGrid = new SigmaReport("gridbox12",context,columns, null,null,tableTitle,printBtn);
	return primaryOrgStatGrid;
}
</script>


