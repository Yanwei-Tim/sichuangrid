<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="<@s.property value='#parameters.type'/>Print" style="height:100%;width:100%">
<style type="text/css">
		#<@s.property value='#parameters.type'/>Gridbox{overflow:hidden !important;}
        .printTable_title{position:relative;margin:0 3px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 28px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:1168px !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:100%;
			border-collapse:collapse;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
			margin:0 3px;
			border-left:1px solid #ccc;
		}
		
		.SigmaReportPrint .body{
			margin:0 3px;
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
			line-height:28px;
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
<div id="<@s.property value='#parameters.type'/>Gridbox" class="SigmaReportPrint">
</div>
</div>
<script type="text/javascript">
var positiveInfoGrid = null;
var orgId=getCurrentOrgId();
function getList(){
	$.ajax({
		url:'${path }/plugin/taskListManage/common/getVisitListOfStatistics.action?orgId='+orgId+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val()+'&searchYear='+$("#yearVal").val(),
		success:function(data){
			positiveInfoGrid.bindData(data);
		}
	})
}

$(document).ready(function(){
	$.ajax({
		url:'${path}/sysadmin/orgManage/ajaxOrganization.action?organization.id=<@s.property value="#parameters.parentOrgId"/>',
		success:function(data){
			if(data == null){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			$("#title_<@s.property value='#parameters.type'/>Gridbox").children().remove();
			$("#title_<@s.property value='#parameters.type'/>Gridbox").html(data.orgName+document.title+"报表");
		}
	})
	var context = {};
	positiveInfoGrid = new SigmaReport("<@s.property value='#parameters.type'/>Gridbox",context,columns, "SigmaReportPrint","printTable_title");
	getList();
})
</script>


