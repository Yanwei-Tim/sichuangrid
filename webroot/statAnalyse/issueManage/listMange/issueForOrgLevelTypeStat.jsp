<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="chartsTabs">
	<div id="gridbox" class="SigmaReport">
	</div>
</div>

<script type="text/javascript">
function onOrgChanged(){
	$.ajax({
		url:"${path}/statAnalyse/issueReport/getDataColumnByLevel.action",
		data:{
			"year":$("#cyear").val(),
			"month":getMonthValue(),
			"parentOrgId":getCurrentOrgId(),
			"queryType":$("#type").val(),
			"reportDateType.id":$("#reoprtDateType").val()
		},
		success:function(data){
			if(data == null){
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

function autoFillYearAndMonth() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
}

$(document).ready(function(){
	document.title=$("#type").find("option:selected").text();
	columns = [
				{name:"general",caption:"部门",children:[
		   			{name:"orgType.displayName",caption:"部门类型",width:80,mode:"string"},
	   			    {name:"issueLevelStats[index].orgLevelOrFunOrgType.displayName",caption:"部门层级",width:80,mode:"string"}
		   		]},
				{name:"general",caption:"事件类型",children:[
	   			    {name:"issueLevelStats[index].issueAreaStat.contradictionSum",caption:"民生服务",width:80,mode:"number",format:"#"},
	   			    {name:"issueLevelStats[index].issueAreaStat.resolveTheContradictionSum",caption:"矛盾劝解",width:80,mode:"number",format:"#"},
	   			    {name:"issueLevelStats[index].issueAreaStat.securityPrecautionSum",caption:"参与治安防控",width:80,mode:"number",format:"#"},
	   			    {name:"issueLevelStats[index].issueAreaStat.specialPopulationSum",caption:"参与特殊人群服务管理",width:80,mode:"number",format:"#"},
	   			    {name:"issueLevelStats[index].issueAreaStat.socialConditionSum",caption:"社情民意收集",width:80,mode:"number",format:"#"},
	   			    {name:"issueLevelStats[index].issueAreaStat.policiesAndLawSum",caption:"法律法规宣传",width:80,mode:"number",format:"#"},
				    {name:"issueLevelStats[index].issueAreaStat.emergencieSum",caption:"突发事件报告",width:80,mode:"number",format:"#"},
				    {name:"issueLevelStats[index].issueAreaStat.otherManageSum",caption:"其它",width:80,mode:"number",format:"#"}
	   			]}
			];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();
	//柱图 饼图
	$(".print").click(function(){
		$("#issuePrintDlg").createDialog({
			width:680,
			height:450,
			title:"打印",
			url:'${path}/statAnalyse/issueManage/listMange/print.jsp',
			buttons: {
			   "打印" : function(){
					print();
		  	   },
			   "关闭" : function(){
				   $("#issuePrintDlg").dialog("close");
			   }
			}
		});
	});
});
</script>