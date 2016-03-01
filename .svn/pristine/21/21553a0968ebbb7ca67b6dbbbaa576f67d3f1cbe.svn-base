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
	formatterTitle();
	columns = [
					{name:"organization.orgName",caption:"区域",width:120,mode:"string"},
					{name:"historySum",caption:formatterHistorySumColumn(),width:80,mode:"number",format:"#"},
					{name:"nowSum",caption:formatterNowSumColumn(),width:80,mode:"number",format:"#"},
					{name:"growthRate",caption:"同比增长率",width:80,mode:"string"},
   					{name:"growthSpeeding",caption:"同比增长速度",width:80,mode:"string"}
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
function formatterHistorySumColumn() {
	if(isNaN($("#cmonth").val())){
		if ($("#cmonth").val()=='年') {
			return ($("#cyear").val()-1)+"年";
		} else {
			return ($("#cyear").val()-1)+"年"+$("#cmonth").val();
		}
	} else {
		return ($("#cyear").val()-1)+"年"+$("#cmonth").val()+"月";
	}
}

function formatterNowSumColumn() {
	if(isNaN($("#cmonth").val())){
		if ($("#cmonth").val()=='年') {
			return $("#cyear").val()+"年";
		} else {
			return $("#cyear").val()+"年"+$("#cmonth").val();
		}
	} else {
		return $("#cyear").val()+"年"+$("#cmonth").val()+"月";
	}
}

function formatterTitle() {
	if(isNaN($("#cmonth").val())){
		document.title=$("#cmonth").val()+"同比情况（"+($("#cyear").val()-1)+"-"+$("#cyear").val()+"）年";
	} else {
		document.title=$("#cmonth").val()+"月同比情况（"+($("#cyear").val()-1)+"-"+$("#cyear").val()+"）年";
	}
}
</script>