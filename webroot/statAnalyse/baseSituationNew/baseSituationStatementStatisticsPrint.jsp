<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="zongkuangPrint" style="overflow: auto;height:100%;width:100%">
<style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 23px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:1100px !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:1100px;  
			border-collapse:collapse;
			overflow: hidden;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
		    width:595px;
			margin:0 5px;
			border-left:1px solid #ccc;
		}
		
		.SigmaReportPrint .body{
			margin:0 5px;
			border-left:1px solid #ccc;
		}
		
		.SigmaReportPrint .body tr.selected{
			background-color:#CCE4F9;
		}
		.SigmaReportPrint .body tr.disabled{
			background:#F0EDED;
			color:#CECECE;
		}
		
		.SigmaReportPrint .body tr.hover{
			background-color:rgb(255,255,151);
		}
		
		.SigmaReportPrint .scroll{
		}
		
		.SigmaReportPrint .body td{
			border-right:1px solid #ccc;
			border-bottom:1px solid #ccc;
			font-size:12px;
			height:20px;
			padding:0px;
			text-align:center;
			color:#333;
		}
		
		.SigmaReportPrint .body input{
			font-size:12px;
			border:0px solid red;
		}
		.SigmaReportPrint  input{
			font-size:12px;
			border:0px solid red;
		}
		
		.SigmaReportPrint .body div.focused{
			background-color:rgb(255,250,255);
		}
		
		.SigmaReportPrint .body div{
			white-space:nowrap;
			padding:3px;
			display:block;
			text-align:center;
		}
		.SigmaReportPrint .body div.checked{
			width:16px;
			height:16px;
			border:1px solid red;
			background-image:url(right.gif);
			background-repeat:no-repeat;
		}
		
		.SigmaReportPrint .head td{
			background:#e7edf5;
			border-right:1px solid #ccc;
			border-bottom:1px solid #ccc;
			font-size:12px;
			height:28px;
			line-height:28px;(
			overflow:hidden;
			padding-top:2px;
		}
		
		
		.SigmaReportPrint .head div.title{
			padding-top:2px;
			float:left;
			height:18px;
			overflow:hidden;
			white-space:nowrap;
		}
	</style>
<div id="gridbox12" class="SigmaReportPrint">
</div>
</div>
<script type="text/javascript">
var baseSituationStatement = null;
var orgName;
function getBaseSituationStatement(){
	$.ajax({
		url:"${path}/baseInfo/baseSituationStatementNew/basesituationStatementSort.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&statisticsType="+$("#type").val()+"&sortName="+sortName+"&sort="+sortColumn,
		success:function(data){
			if(data == null ){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			baseSituationStatement.bindData(data);
		}
	})
}


$(document).ready(function(){
	var context = {};
	var columns = [		
				{name:"organization.orgName",caption:"区域",width:100,mode:"string"}, 
				{name:"togetherOrganizeCount",caption:"综治组织",width:100,mode:"string"},
				{name:"preventionCount",caption:"群防群治组织人数",width:110,mode:"string"},
				{name:"floatingPersionCount",caption:"流动人口",width:60,mode:"string"},
				{name:"rentalHouseCount",caption:"出租房",width:50,mode:"string"},
				{name:"orgName",caption:"特殊人群",width:100,mode:"string",children:[
					{name:"positivePersionCount",caption:"刑释人员",width:100,mode:"string"},
					{name:"rectificativePersonCount",caption:"矫正人员",width:100,mode:"string"},
					{name:"mentalPatientPersionCount",caption:"严重精神障碍患者",width:100,mode:"string"},
					{name:"druggyPersionCount",caption:"吸毒人员",width:100,mode:"string"},
					{name:"idleYouthCount",caption:"重点青少年",width:100,mode:"string"},
				]},
				{name:"newSecurityCount",caption:"治安重点单位场所",width:100,mode:"string"},
				{name:"doneIssueCount",caption:"事件处理统计",width:100,mode:"string"} 
		   		];
	
	baseSituationStatement = new SigmaReport("gridbox12",context,columns, "SigmaReportPrint","printTable_title");
	getBaseSituationStatement();
})
</script>


