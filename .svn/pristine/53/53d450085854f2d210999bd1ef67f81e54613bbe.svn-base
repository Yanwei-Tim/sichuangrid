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
					{name:"growthRate",caption:"环比增长率",width:80,mode:"string"},
   					{name:"growthSpeeding",caption:"环比增长速度",width:80,mode:"string"}
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
function formatterHistorySumColumn() {
	if(isNaN($("#cmonth").val())){
		if ($("#cmonth").val()=='年') {
			return ($("#cyear").val()-1)+"年";
		} else if($("#cmonth").val()=='第1季度'){
			return ($("#cyear").val()-1)+"年第4季度";
		} else if($("#cmonth").val()=='第2季度'){
			return $("#cyear").val()+"年第1季度";
		} else if($("#cmonth").val()=='第3季度'){
			return $("#cyear").val()+"年第2季度";
		} else if($("#cmonth").val()=='第4季度'){
			return $("#cyear").val()+"年第3季度";
		}
	} else {
		if($("#cmonth").val()=='1') {
			return ($("#cyear").val()-1)+"年12月";
		} else {
			return $("#cyear").val()+"年"+($("#cmonth").val()-1)+"月";
		}
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
		if ($("#cmonth").val()=='年') {
			document.title=$("#cyear").val()+"年环比情况";
		} else {
			document.title=$("#cyear").val()+"年"+$("#cmonth").val()+"环比情况";
		}
	} else {
		document.title=$("#cyear").val()+"年"+$("#cmonth").val()+"月环比情况";
	}
}
</script>