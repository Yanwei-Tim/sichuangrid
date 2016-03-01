<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="tabLevelChart" >
	<div id="importantPersonlPie" style="width:420px;height: 300px" class="SigmaReport" ></div>
</div>

<script type="text/javascript">
var d=new Date();
var year=d.getFullYear();
var month=d.getMonth()+1
var peopleType=currType;//populationGis
switch(currType){
		case "overseaStaff": peopleType="overseaStaff";break;
		case "householdStaff": peopleType="householdStaff";break;
		case "floatingPopulation": peopleType="floatingPopulation";break;
		case "unsettledPopulation": peopleType="unsettledPopulation";break;
		case "all_actual_population": peopleType="populationGis";break;
}
var url = "${path}/baseInfoStat/statisticsPopulation/getStatisticsPopulationPie.action?orgId="+pieOrgId+"&year="+year+"&month="+month+"&populationType="+peopleType;
if(peopleType!=null && peopleType!="undefined"){
	$("#importantPersonlPie").pieChart({
		url:url,
		moduleName:document.title,
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name);
			showInfo(900, 600,year,month);
		}
	});
}
</script>
