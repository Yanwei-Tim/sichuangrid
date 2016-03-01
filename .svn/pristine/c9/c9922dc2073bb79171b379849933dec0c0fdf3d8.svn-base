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
var peopleType=currType;
var title="";
switch(currType){
		case "overseaStaff": peopleType="overseaStaff";title="境外人员";break;
		case "householdStaff": peopleType="householdStaff";title="户籍人口";break;
		case "floatingPopulation": peopleType="floatingPopulation";title="流动人口";break;
		case "unsettledPopulation": peopleType="unsettledPopulation";title="未落户人口";break;
		case "actualHouse": peopleType="actualHouse";title="房屋"; break;
		case "rentalHouse": peopleType="rentalHouse";title="房屋";break;
}
var url = "${path}/baseInfo/statisticManage/getStatisticPie.action?orgId="+pieOrgId+"&year="+year+"&month="+month+"&type="+peopleType;
if(peopleType!=null && peopleType!="undefined"){
	$("#importantPersonlPie").pieChart({
		url:url,
		moduleName:title,
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name);
			showInfo(900, 600,year,month);
		}
	});
}
</script>
