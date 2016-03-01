<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="zongkuangPrint" style="overflow: auto;height:100%;width:100%">
<style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 23px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:595px;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:595px;  
			border-collapse:collapse;
			overflow-y: hidden;
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
var primaryOrgStatGrid = null;
var orgName;
function getPrimaryOrgStat(){
	$.ajax({
		url:'${path}/baseInfoStat/searchPrimaryOrganizationDataColumn/getDataColumn.action?parentOrgId=<@s.property value="#parameters.parentOrgId"/>',
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			primaryOrgStatGrid.bindData(data);
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
			$("#title_gridbox12").children().remove();
			$("#title_gridbox12").html(data.orgName+"基层综治组织报表");
		}
	})
	var context = {};
	var columns = [
			{name:"org.orgName",caption:"区域",width:70,mode:"string"},
			{name:"general",caption:"基层综治组织",children:[
			     {name:"compositeTeamCount",caption:"队伍数量",width:40,mode:"string",align:"left"},
			     {name:"compositeTeamMemberCount",caption:"人数",width:40,mode:"string",align:"left"}
			 ]},
			  {name:"general",caption:"群防群治队伍",children:[
			      {name:"massesTeamCount",caption:"队伍数量",width:40,mode:"string",align:"left"},
			      {name:"massesTeamMemberCount",caption:"人数",width:40,mode:"string",align:"left"}
			  ]},
			  {name:"general",caption:"社会志愿者队伍",children:[
			      {name:"postulantTeamCount",caption:"队伍数量",width:40,mode:"string",align:"left"},
			      {name:"postulantTeamMemberCount",caption:"人数",width:40,mode:"string",align:"left"}
			   ]}
		];
	
	primaryOrgStatGrid = new SigmaReport("gridbox12",context,columns, "SigmaReportPrint","printTable_title");
	getPrimaryOrgStat();
})
</script>


