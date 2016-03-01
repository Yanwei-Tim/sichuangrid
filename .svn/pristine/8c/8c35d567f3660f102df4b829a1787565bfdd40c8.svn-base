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
		url:'${path}/statAnalyse/issueReport/getDataColumnByArea.action',
		data:{
			"year":$("#cyear").val(),
			"month":getMonthValue(),
			"parentOrgId":getCurrentOrgId(),
			"queryType":$("#type").val(),
			"reportDateType.id":$("#reoprtDateType").val()
		},
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].organization == null)){
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
					{name:"organization.orgName",caption:"区域",width:120,mode:"string"},
					{name:"general",caption:"矛盾劝解",children:[
		   			    {name:"resolveGeneralSum",caption:"普通流程",width:80,mode:"number",format:"#"},
		   			    {name:"resolveSkipSum",caption:"越级流程",width:80,mode:"number",format:"#"}
		   			]},
		   			{name:"general",caption:"参与治安防控",children:[
		   			    {name:"securityGeneralSum",caption:"普通流程",width:80,mode:"number",format:"#"},
		   			    {name:"securitySkipSum",caption:"越级流程",width:80,mode:"number",format:"#"}
					]},
					{name:"general",caption:"突发事件报告",children:[
   		   			    {name:"emergencieGeneralSum",caption:"普通流程",width:80,mode:"number",format:"#"},
   		   			    {name:"emergencieSkipSum",caption:"越级流程",width:80,mode:"number",format:"#"}
   					]},
   					{name:"general",caption:"其他",children:[
   		   			    {name:"otherManageGeneralSum",caption:"普通流程",width:80,mode:"number",format:"#"},
   		   			    {name:"otherManageSkipSum",caption:"越级流程",width:80,mode:"number",format:"#"}
   					]}
				];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();
	$(".print").click(function(){
		$("#issuePrintDlg").createDialog({
			width:750,
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