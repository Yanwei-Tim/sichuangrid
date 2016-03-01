<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.ui-layout-center{overflow:auto;}
</style>

<div style="height:240px;" >
	<div id="gridboxLedgerPoorPeopleCollect" class="SigmaReport"></div>
</div>
<div style="height:240px;" >
	<div id="gridboxLedgerPoorPeopleDone" class="SigmaReport"></div>
</div>
<script type="text/javascript">
var ledgerPoorPeoplePrimaryOrgStatGridCollect = null;
function initLedgerPoorPeopleGridCollect(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"县乡村",width:50,align:'center',mode:"string"},
		        {name:"orgLevel",caption:"类别",width:50,align:'center',mode:"string"},
				{name:"sums",caption:"合计",width:60,align:'center',mode:"string"},	
   				{name:"housingCount",caption:"住房",width:60,align:'center',mode:"string"},	
   				{name:"lifeCount",caption:"生活",width:60,align:'center',mode:"string"},	
   				{name:"medicalCount",caption:"医疗",width:60,align:'center',mode:"string"},	
   				{name:"employmentCount",caption:"就业",width:60,align:'center',mode:"string"},	
   				{name:"goSchoolCount",caption:"就学",width:60,align:'center',mode:"string"},	
   				{name:"ledgerPoorPeopleOtherCount",caption:"其他",width:60,align:'center',mode:"string"}
	   		   ];	
	ledgerPoorPeoplePrimaryOrgStatGridCollect = new SigmaReport("gridboxLedgerPoorPeopleCollect",context,columns, null,null,tableTitle,printBtn);
	return ledgerPoorPeoplePrimaryOrgStatGridCollect;
}

var ledgerPoorPeoplePrimaryOrgStatGridDone = null;
function initLedgerPoorPeopleGridDone(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"县乡村",width:50,align:'center',mode:"string"},
		        {name:"orgLevel",caption:"类别",width:50,align:'center',mode:"string"},
				{name:"sums",caption:"合计",width:60,align:'center',mode:"string"},	
   				{name:"housingCount",caption:"住房",width:60,align:'center',mode:"string"},	
   				{name:"lifeCount",caption:"生活",width:60,align:'center',mode:"string"},	
   				{name:"medicalCount",caption:"医疗",width:60,align:'center',mode:"string"},	
   				{name:"employmentCount",caption:"就业",width:60,align:'center',mode:"string"},	
   				{name:"goSchoolCount",caption:"就学",width:60,align:'center',mode:"string"},	
   				{name:"ledgerPoorPeopleOtherCount",caption:"其他",width:60,align:'center',mode:"string"}
	   		   ];	
	ledgerPoorPeoplePrimaryOrgStatGridDone = new SigmaReport("gridboxLedgerPoorPeopleDone",context,columns, null,null,tableTitle,printBtn);
	return ledgerPoorPeoplePrimaryOrgStatGridDone;
}
</script>


