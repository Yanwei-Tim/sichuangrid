<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.ui-layout-center{overflow:auto;}
</style>
<div style="height:265px;" >
	<div id="gridbox1LedgerSteadyWorkCollect" class="SigmaReport"></div>
</div>
<div style="height:265px;" >
	<div id="gridbox1LedgerSteadyWorkDone" class="SigmaReport"></div>
</div>
<script type="text/javascript">
var ledgerSteadyWorkPrimaryOrgStatGridCollect = null;
function initLedgerSteadyWorkGridCollect(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"县乡村",width:50,align:'center',mode:"string"},
		        {name:"orgLevel",caption:"县乡村",width:60,align:'center',mode:"string"},
				{name:"sums",caption:"合计",width:40,align:'center',mode:"string"},	
   				{name:"visitsCount",caption:"涉法涉诉",width:40,align:'center',mode:"string"},	
   				{name:"forestSoilCount",caption:"林水土",width:40,align:'center',mode:"string"},	
   				{name:"favorablePoliciesCount",caption:"惠农政策及村务",width:40,align:'center',mode:"string"},	
   				{name:"civilAdministrationIssuesCount",caption:"民政信息",width:50,align:'center',mode:"string"},	
   				{name:"populationMedicalCount",caption:"人口医疗卫生",width:40,align:'center',mode:"string"},	
   				{name:"laborSocialSecurityCount",caption:"劳动保障",width:40,align:'center',mode:"string"},	
   				{name:"transportationCount",caption:"交通运输",width:40,align:'center',mode:"string"},	
   				{name:"urbanConstructionAndCLECount",caption:"城建综合执法",width:40,align:'center',mode:"string"},	
   				{name:"cpcPartyDisciplinesCount",caption:"党纪政纪",width:40,align:'center',mode:"string"},	
   				{name:"steadyRecordWorkEducationCount",caption:"教育",width:40,align:'center',mode:"string"},	
   				{name:"enterpriseRestructuringCount",caption:"企业改制",width:40,align:'center',mode:"string"},
   				{name:"steadyEnvironmental",caption:"环境保护",width:40,align:'center',mode:"string"},
   				{name:"organizationPersonnelCount",caption:"组织人事",width:40,align:'center',mode:"string"},
   				{name:"steadyRecordWorkOtherCount",caption:"其他类",width:40,align:'center',mode:"string"},
   				{name:"keyPersonnelCount",caption:"重点人员",width:40,align:'center',mode:"string"},
   				{name:"steadyWorkOther",caption:"其他",width:40,align:'center',mode:"string"}
	   		   ];	
	ledgerSteadyWorkPrimaryOrgStatGridCollect = new SigmaReport("gridbox1LedgerSteadyWorkCollect",context,columns, null,null,tableTitle,printBtn);
	return ledgerSteadyWorkPrimaryOrgStatGridCollect;
}

var ledgerSteadyWorkPrimaryOrgStatGridDone = null;
function initLedgerSteadyWorkGridDone(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"县乡村",width:50,align:'center',mode:"string"},
		        {name:"orgLevel",caption:"县乡村",width:60,align:'center',mode:"string"},
				{name:"sums",caption:"合计",width:40,align:'center',mode:"string"},	
   				{name:"visitsCount",caption:"涉法涉诉",width:40,align:'center',mode:"string"},	
   				{name:"forestSoilCount",caption:"林水土",width:40,align:'center',mode:"string"},	
   				{name:"favorablePoliciesCount",caption:"惠农政策及村务",width:40,align:'center',mode:"string"},	
   				{name:"civilAdministrationIssuesCount",caption:"民政信息",width:50,align:'center',mode:"string"},	
   				{name:"populationMedicalCount",caption:"人口医疗卫生",width:40,align:'center',mode:"string"},	
   				{name:"laborSocialSecurityCount",caption:"劳动保障",width:40,align:'center',mode:"string"},	
   				{name:"transportationCount",caption:"交通运输",width:40,align:'center',mode:"string"},	
   				{name:"urbanConstructionAndCLECount",caption:"城建综合执法",width:40,align:'center',mode:"string"},	
   				{name:"cpcPartyDisciplinesCount",caption:"党纪政纪",width:40,align:'center',mode:"string"},	
   				{name:"steadyRecordWorkEducationCount",caption:"教育",width:40,align:'center',mode:"string"},	
   				{name:"enterpriseRestructuringCount",caption:"企业改制",width:40,align:'center',mode:"string"},
   				{name:"steadyEnvironmental",caption:"环境保护",width:40,align:'center',mode:"string"},
   				{name:"organizationPersonnelCount",caption:"组织人事",width:40,align:'center',mode:"string"},
   				{name:"steadyRecordWorkOtherCount",caption:"其他类",width:40,align:'center',mode:"string"},
   				{name:"keyPersonnelCount",caption:"重点人员",width:40,align:'center',mode:"string"},
   				{name:"steadyWorkOther",caption:"其他",width:40,align:'center',mode:"string"}
	   		   ];	
	ledgerSteadyWorkPrimaryOrgStatGridDone = new SigmaReport("gridbox1LedgerSteadyWorkDone",context,columns, null,null,tableTitle,printBtn);
	return ledgerSteadyWorkPrimaryOrgStatGridDone;
}
</script>


