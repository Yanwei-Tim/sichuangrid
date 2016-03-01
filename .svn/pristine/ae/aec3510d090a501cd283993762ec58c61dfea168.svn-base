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
		url:'${path}/statAnalyse/issueReport/getDataColumnByLevelNew.action',
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

$(document).ready(function(){
	document.title=$("#type").find("option:selected").text();
	columns = [
				{name:"orgLevelOrFunOrgType.displayName",caption:"部门层级",width:100,mode:"string"},
				{name:"issueAreaStat.addIssueSum",caption:"新增事件",width:75,mode:"number",format:"#"},
				{name:"issueAreaStat.doneIssueSum",caption:"办结事件",width:75,mode:"number",format:"#"},
				{name:"issueAreaStat.submitIssueSum",caption:"上报事件",width:75,mode:"number",format:"#"}
				];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();
	
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