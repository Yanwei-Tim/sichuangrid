<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div id="chartsTabs">

<div id="gridbox" class="SigmaReport">
</div>
</div>
<div id="pieDialogNew"></div>




<script type="text/javascript">

function dealTeamPie(rowData){
	window._rowData = rowData;
	$("#pieDialog").createDialog({
		width:600,
		height:550,
		title:"基层组织队伍统计图表",
		url:"/statAnalyse/primaryOrganizationStat/teamCountPie.jsp"
	});

}

function dealMemberCount(rowData){
	window._rowData = rowData;
	$("#pieDialog").createDialog({
		width:600,
		height:550,
		title:"基层组织人员统计图表",
		url:"/statAnalyse/primaryOrganizationStat/memberCountPie.jsp"
	});

}
function delay(){
}
var grid = null;
function onOrgChanged(){
	$.ajax({
		url:'${path}/baseInfoStat/searchPrimaryOrganizationDataColumn/getDataColumn.action?parentOrgId='+getCurrentOrgId(),
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
}


$(document).ready(function(){
	setTimeout(delay,800);
	var context = {};
	var columns = [
			{name:"org.orgName",caption:"区域",width:120,mode:"string"},
			{name:"general",caption:"综治组织",children:[
   			    {name:"compositeTeamCount",caption:"队伍数量",width:100,mode:"string"},
   			    {name:"compositeTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
   			]},
   			{name:"general",caption:"基层党组织",children:[
   			    {name:"partyTeamCount",caption:"队伍数量",width:100,mode:"string"},
   			    {name:"partyTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
			]},
   			{name:"general",caption:"基层自治组织",children:[
   			    {name:"autonomyTeamCount",caption:"队伍数量",width:100,mode:"string"},
   			    {name:"autonmoyTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
   			]},
   			{name:"general",caption:"群防群治队伍",children:[
   			    {name:"massesTeamCount",caption:"队伍数量",width:100,mode:"string"},
   			    {name:"massesTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
   			]},
   			{name:"general",caption:"社会志愿者队伍",children:[
   			    {name:"postulantTeamCount",caption:"队伍数量",width:100,mode:"string"},
   			    {name:"postulantTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
   			]},
   			{name:"general",caption:"专项工作领导小组",children:[
			    {name:"leaderGroupTeamCount",caption:"队伍数量",width:100,mode:"string"},
			    {name:"leaderGroupTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
      		]}
		];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();

	// 列表信息 和 柱图 饼图   外层容器计算高度
	$(".SigmaReport").height(
		$(".ui-layout-center").outerHeight() - $("").outerHeight() - $(".submenu").outerHeight() - $("ui-tabs-nav").outerHeight() - 10
	);
	$(".SigmaReport").width(
			$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -25
	);
	//列表信息
	$(".ui-tabs-panel").height(
		$(".ui-layout-center").outerHeight() - $("").outerHeight() - $(".submenu").outerHeight() - $("ui-tabs-nav").outerHeight() - 10
	);

	$(".ui-tabs-panel").width(
		$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -25
	);
	//柱图 饼图
	$(".print").click(function(){
		$("#pieDialogNew").createDialog({
			width:650,
			height:400,
			title:"打印基层组织",
			url:'${path}/statAnalyse/primaryOrganizationStat/print.jsp?parentOrgId='+getCurrentOrgId(),
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#pieDialogNew").dialog("close");
			   }
			}
		});
	});

})
function print(){
	$("#zongkuangPrint").printArea();
	$("#pieDialogNew").dialog("close");
}
</script>


