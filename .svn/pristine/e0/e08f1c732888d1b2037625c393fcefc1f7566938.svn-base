<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
	table {   
   		border:solid black 2px; 
    	padding:0;    
   		margin:0 auto;   
      	border-collapse: collapse ;   
  	}   
  	td {  
      	border:solid black 1px;
       	font-size:12px text-align:center;
    	padding: 3px 3px 3px 8px;   
      	color: #000000;   
      	line-height:20px;
  	} 
</style>
<div class="center-right">
	<div class="content">
		<div class="ui-corner-all" id="nav">
			<!-- <span>
				<input id="givenYear" type="radio" name="calendar" style="float:left;margin-top:3px;"/><label style="float:left;padding:0 10px;line-height:25px;">年报</label>
				<input id="month" type="radio" name="calendar" style="float:left;margin-top:3px;"  checked="checked"/><label style="float:left;padding:0 10px;line-height:25px;">月报</label>
			</span> -->
			
			<label style="float:left;padding:0 10px;line-height:25px;">报表类型：</label> 
			<select id="reportTypes" name="" style="float:left;margin-top:3px;">
				<option value="1">年报</option>
				<option value="2">季报</option>
				<option value="3" selected="selected">月报</option>					
			</select>
				
			<label style="float:left;padding:0 10px;line-height:25px;">年份：</label> 
			<select id="yearDate" name="issueInvestigationMediate.year" style="float:left;margin-top:3px;">
				<option value="0"></option>
				<s:iterator value="years" var="year">
					<option value="${year}">${year}</option>
				</s:iterator>
			</select>
				
			<div id="monthSelect" style="display: none;">
				<label style="float:left;padding:0 10px;line-height:25px;">月份：</label>
				<select id="monthDate" name="issueInvestigationMediate.month" style="float:left;margin-top:3px;">
					<option value="0"></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
			</div>
			
			<div id="quarterSelect" style="display: none;">
				<label style="float:left;padding:0 10px;line-height:25px;">季度：</label>
				<select id="quarterDate" name="issueInvestigationMediate.quarter" style="float:left;margin-top:3px;">
					<option value="0"></option>
					<option value="1">第一季度</option>
					<option value="2">第二季度</option>	
					<option value="3">第三季度</option>	
					<option value="4">第四季度</option>	
				</select>
			</div>
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
			<pop:JugePermissionTag ename="generatedDataInvestigation">
				<a id="replace" href="javascript:void(0)"><span>生成数据</span></a>
			</pop:JugePermissionTag>
			<!-- a id="export" href="javascript:void(0)"><span>导出</span></a -->
		</div>
		<div style="clear: both;"></div>
		<div style="overflow:hidden;overflow-y:auto;position:relative;height: 100%;width:100%" id="reportRenderer">
			<table id="issueInvestigationMediateList"></table>
		</div>
		<div id="issueInvestigationMediateDialog"></div>
		<div id="noticeDialog"></div>
		<div id="promptDialog"></div>
	</div>
</div>
<script type="text/javascript">
var now = new Date();
var curYear=now.getFullYear();
var curMonth = now.getMonth();
$(document).ready(function(){
	$("#export").buttonDisable();
	$("#monthSelect").show();
	$("#yearDate").val(curYear);
	$("#monthDate").val(curMonth);

	$("#reportTypes").change(function(){
		if($("#reportTypes").val()==1){
			$("#monthSelect").hide();
			$("#quarterSelect").hide();
			$("#monthDate").val(0);
			$("#quarterDate").val(0);
		}
		if($("#reportTypes").val()==2){
			$("#monthSelect").hide();
			$("#quarterSelect").show();
			$("#monthDate").val(0);
			if(1<=(curMonth+1)<=3){
				$("#quarterDate").val(1);
			}else if(4<=(curMonth+1)<=6){
				$("#quarterDate").val(2);
			}else if(7<=(curMonth+1)<=9){
				$("#quarterDate").val(3);
			}else if(10<=(curMonth+1)<=12){
				$("#quarterDate").val(4);
			}
		}
		if($("#reportTypes").val()==3){
			$("#monthSelect").show();
			$("#quarterSelect").hide();
			$("#monthDate").val(curMonth);
			$("#quarterDate").val(0);
		}
	});

	//function clickMonth(){
	//	$("#monthSelect").show();
	//}
	//$("#month").click(clickMonth);
	
	//function clickGivenYear(){
	//	$("#monthSelect").hide();
	//	$('#monthDate option').remove();
	//	$("<option value='0'></option>").appendTo("#monthDate");
	//	$("<option value='1'>1</option>").appendTo("#monthDate");
	//	$("<option value='2'>2</option>").appendTo("#monthDate");
	//	$("<option value='3'>3</option>").appendTo("#monthDate");
	//	$("<option value='4'>4</option>").appendTo("#monthDate");
	//	$("<option value='5'>5</option>").appendTo("#monthDate");
	//	$("<option value='6'>6</option>").appendTo("#monthDate");
	//	$("<option value='7'>7</option>").appendTo("#monthDate");
	//	$("<option value='8'>8</option>").appendTo("#monthDate");
	//	$("<option value='9'>9</option>").appendTo("#monthDate");
	//	$("<option value='10'>10</option>").appendTo("#monthDate");
	//	$("<option value='11'>11</option>").appendTo("#monthDate");
	//	$("<option value='12'>12</option>").appendTo("#monthDate");
	//}
	//$("#givenYear").click(clickGivenYear);
	
    if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
        onOrgChanged(getCurrentOrgId(),isGrid());
    }

	$("#search").click(function(event){
		if($("#yearDate").val() >0){
			$("#export").buttonEnable();
		}else{
			$("#export").buttonDisable();
		}
		reloadReportDate();
	});

	$("#replace").click(function(event){
		$.confirm({
			title:"系统提示",
			message:"确定重新生成矛盾纠纷排查调处数据?",
			width:400,
			okFunc:replaceIssueInvestigationMediate
		});
	});
	
	//var year = $("#yearDate").val();
	//var month = $("#monthDate").val();
	//var actionUrl="${path}/issueInvestigationMediate/issueInvestigationMediateManage/downloadIssueInvestigationMediate.action?issueInvestigationMediate.year="+year+"&issueInvestigationMediate.month="+month+"&issueInvestigationMediate.week="+0;
	//$("#export").attr("href",actionUrl);
	$.loadingComp("close");
});

function reloadReportDate(){
	$.ajax({
		async: false,
		url: '${path}/issueInvestigationMediate/issueInvestigationMediateManage/findIssueInvestigationMediateList.action',
		data:{
			"issueInvestigationMediate.year":$("#yearDate").val(),
			"issueInvestigationMediate.month":$("#monthDate").val(),
			"issueInvestigationMediate.quarter":$("#quarterDate").val(),
			"issueInvestigationMediate.week":0,
			"orgId":function (){ return getCurrentOrgId()}
		},
		success:function(responseData){
			rebuildeGrid(responseData);
			$("#reportRenderer").height(380);
		}
	});
}

function rebuildeGrid(reportData){
	var tableHeadTxt="<table border='1' width='100%'><tr align='center'><td><font size='3'>内容</font></td><td colspan='0'><font size='3'>小类</font></td><td><font size='3'>市</font></td><td><font size='3'>县市区</font></td><td><font size='3'>乡镇街道</font></td><td><font size='3'>村居/片格</font></td><td><font size='3'>小计</font></td>"
		+"<td><font size='3'>年度累计</font></td></tr>";
	var tableContent="";
	var tableFootTxt="</table>";
	if (reportData!=null && reportData!="" && reportData.length>0){
		var typename="";
		for (var i=0;i<reportData.length;i++){
			var rowData=reportData[i];
					if(reportData[i].typeName!=typename){
						typename=reportData[i].typeName;
						if(reportData[i].typeName=="50人以下"){
						tableContent+="<tr><td>&nbsp;&nbsp;"+rowData.typeName+"</td><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td>"
												+"<td>&nbsp;&nbsp;"+rowData.cityCount+"</td><td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td>"
												+"<td>&nbsp;&nbsp;"+rowData.villageCount+"</td><td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td>";
												continue;
					}
					if(reportData[i].typeName=="500人以上"){
						tableContent+="<tr><td>&nbsp;&nbsp;"+rowData.typeName+"</td><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td>"
												+"<td>&nbsp;&nbsp;"+rowData.cityCount+"</td><td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td>"
												+"<td>&nbsp;&nbsp;"+rowData.villageCount+"</td><td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td>";
												continue;
					}
					if(reportData[i].typeName=="50至100人"){
					tableContent+="<tr><td>&nbsp;&nbsp;"+rowData.typeName+"</td><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td>"
											+"<td>&nbsp;&nbsp;"+rowData.cityCount+"</td><td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td>"
											+"<td>&nbsp;&nbsp;"+rowData.villageCount+"</td><td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td>";
											continue;
					}
					if(reportData[i].typeName=="100至500人"){
					tableContent+="<tr><td>&nbsp;&nbsp;"+rowData.typeName+"</td><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td>"
										+"<td>&nbsp;&nbsp;"+rowData.cityCount+"</td><td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td>"
										+"<td>&nbsp;&nbsp;"+rowData.villageCount+"</td><td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td>";
											continue;
					}
					if(reportData[i].typeName=="重大矛盾纠纷"){
						tableContent+="<tr><td rowspan='7' >&nbsp;&nbsp;"+rowData.typeName+"</td><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td>"
												+"<td>&nbsp;&nbsp;"+rowData.cityCount+"</td><td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td>"
												+"<td>&nbsp;&nbsp;"+rowData.villageCount+"</td><td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td>";
					}else{
						tableContent+="<tr><td rowspan='2'>&nbsp;&nbsp;"+rowData.typeName+"</td><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td>"
											+"<td>&nbsp;&nbsp;"+rowData.cityCount+"</td><td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td>"
											+"<td>&nbsp;&nbsp;"+rowData.villageCount+"</td><td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td>";
					}
				
			}else{
				tableContent+="<tr><td>&nbsp;&nbsp;"+rowData.typeSubsetName+"</td><td>&nbsp;&nbsp;"+rowData.cityCount+"</td>"+
						"<td>&nbsp;&nbsp;"+rowData.districtCount+"</td><td>&nbsp;&nbsp;"+rowData.townCount+"</td><td>&nbsp;&nbsp;"+rowData.villageCount+"</td>"+
						"<td>&nbsp;&nbsp;"+rowData.subtotal+"</td><td>&nbsp;&nbsp;"+rowData.yearCumulative+"</td><tr>";
			}
		}
		
	}
	$("#reportRenderer").html(tableHeadTxt+tableContent+tableFootTxt);
}

function onOrgChanged(orgId,isgrid){
	reloadReportDate();
}

function replaceIssueInvestigationMediate(){
	$("#replace").buttonDisable();
	$.ajax({
		url:'${path}/issueInvestigationMediate/issueInvestigationMediateManage/replaceIssueInvestigationMediate.action',
		data:{
			"issueInvestigationMediate.year":$("#yearDate").val(),
			"issueInvestigationMediate.month":$("#monthDate").val(),
			"issueInvestigationMediate.quarter":$("#quarterDate").val(),
			"issueInvestigationMediate.week":0
		},
		success:function(data){
		if (data>0){			
			$.messageBox({message:"矛盾纠纷排查调处数据已重新生成!"});
			$("#replace").buttonEnable();
		}else{
			$.messageBox({message:data});
			$("#replace").buttonEnable();
		}
		}
	});
}
</script>