<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.ui-layout-center{overflow:auto;}
</style>

<div style="height:265px;" >
	<div id="gridboxLedgerPeopleCollect" class="SigmaReport"></div>
</div>
<div style="height:265px;" >
	<div id="gridboxLedgerPeopleDone" class="SigmaReport"></div>
</div>
<script type="text/javascript">
var ledgerPeoplePrimaryOrgStatGridCollect = null;
function initLedgerPeopleGridCollect(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"县乡村",width:50,align:'center',mode:"string"},
		        {name:"orgLevel",caption:"类别",width:50,align:'center',mode:"string"},
				{name:"sums",caption:"合计",width:40,align:'center',mode:"string"},	
   				{name:"water",caption:"水利",width:40,align:'center',mode:"string"},	
   				{name:"traffic",caption:"交通",width:40,align:'center',mode:"string"},	
   				{name:"energy",caption:"能源",width:40,align:'center',mode:"string"},	
   				{name:"education",caption:"教育",width:50,align:'center',mode:"string"},	
   				{name:"science",caption:"科技文体",width:40,align:'center',mode:"string"},	
   				{name:"medical",caption:"医疗卫生",width:40,align:'center',mode:"string"},	
   				{name:"labor",caption:"劳动和社会保障",width:40,align:'center',mode:"string"},	
   				{name:"environment",caption:"环境保护",width:40,align:'center',mode:"string"},	
   				{name:"town",caption:"城乡规划建议管理",width:40,align:'center',mode:"string"},	
   				{name:"agriculture",caption:"农业",width:40,align:'center',mode:"string"},	
   				{name:"other",caption:"其他",width:40,align:'center',mode:"string"}
	   		   ];	
	ledgerPeoplePrimaryOrgStatGridCollect = new SigmaReport("gridboxLedgerPeopleCollect",context,columns, null,null,tableTitle,printBtn);
	return ledgerPeoplePrimaryOrgStatGridCollect;
}

var ledgerPeoplePrimaryOrgStatGridDone = null;
function initLedgerPeopleGridDone(tableTitle,printBtn){
	var context = {};
	var columns = [		
		        {name:"columnName",caption:"县乡村",width:50,align:'center',mode:"string"},
		        {name:"orgLevel",caption:"类别",width:50,align:'center',mode:"string"},
				{name:"sums",caption:"合计",width:40,align:'center',mode:"string"},	
   				{name:"water",caption:"水利",width:40,align:'center',mode:"string"},	
   				{name:"traffic",caption:"交通",width:40,align:'center',mode:"string"},	
   				{name:"energy",caption:"能源",width:40,align:'center',mode:"string"},	
   				{name:"education",caption:"教育",width:50,align:'center',mode:"string"},	
   				{name:"science",caption:"科技文体",width:40,align:'center',mode:"string"},	
   				{name:"medical",caption:"医疗卫生",width:40,align:'center',mode:"string"},	
   				{name:"labor",caption:"劳动和社会保障",width:40,align:'center',mode:"string"},	
   				{name:"environment",caption:"环境保护",width:40,align:'center',mode:"string"},	
   				{name:"town",caption:"城乡规划建议管理",width:40,align:'center',mode:"string"},	
   				{name:"agriculture",caption:"农业",width:40,align:'center',mode:"string"},	
   				{name:"other",caption:"其他",width:40,align:'center',mode:"string"}
	   		   ];	
	ledgerPeoplePrimaryOrgStatGridDone = new SigmaReport("gridboxLedgerPeopleDone",context,columns, null,null,tableTitle,printBtn);
	return ledgerPeoplePrimaryOrgStatGridDone;
}
</script>


