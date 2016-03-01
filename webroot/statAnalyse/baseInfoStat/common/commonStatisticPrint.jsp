<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>
<div id="${type}Print" style="height:100%;width:100%">
<style type="text/css">
		#${type}Gridbox{overflow:hidden !important;overflow-x:auto !important;}
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 28px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:640px !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:621px; 
			*width:623px;
			border-collapse:collapse;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
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
<div id="${type}Gridbox" class="SigmaReportPrint">
</div>
</div>
<script type="text/javascript">
var positiveInfoGrid = null;
var orgName;
function getPositiveInfo(){
	$.ajax({
		url:'/baseInfo/statisticManage/getBaseInfoStatisticList.action?orgId='+'<s:property value="#parameters.parentOrgId"/>'+'&year='+'<s:property value="#parameters.year"/>'+'&month='+'<s:property value="#parameters.month"/>'+'&type='+'<s:property value="#parameters.type"/>',
		success:function(data){
			positiveInfoGrid.bindData(data);
		}
	})
}

$(document).ready(function(){
	$.ajax({
		url:'${path}/sysadmin/orgManage/ajaxOrganization.action?organization.id=<s:property value="#parameters.parentOrgId"/>',
		success:function(data){
			if(data == null){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			$("#title_${type}Gridbox").children().remove();
			$("#title_${type}Gridbox").html(data.orgName+document.title+"报表");
		}
	})
	var context = {};
	positiveInfoGrid = new SigmaReport("${type}Gridbox",context,columns, "SigmaReportPrint","printTable_title");
	getPositiveInfo();
})
</script>


