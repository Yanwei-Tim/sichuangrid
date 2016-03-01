<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="annualAssessmentReportPrint"  style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 24px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:1000px; !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:1000px;  
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
		.footerInfoPrint{
			border: 1px solid #ccc;
			line-height: 1.6em;
			padding: 5px;
			margin: -3px 17px 0 8px;
			height: 40px;
			overflow-y: auto;
		}
	</style>
   <div id="annualAssessmentGridbox" class="SigmaReportPrint" style="overflow: auto;width:100%"></div>
   <div class="footerInfoPrint">
		<p>1.党政主导：县（市、区）党委、政府及时研究、协调解决工作中的重大问题，有落实省两办[2014]43号文件精神的相关文件、会议纪要或会议记录；</p>
		<p>2.经费保障情况：网格化服务管理工作经费纳入当地财政预算，及时兑现网格员补助；</p>
		<p>3.落实网格员情况：城市社区有专职网格员，农村有专兼职网格员；</p>
		<p>4.机制建设：建立健全了网格员管理、教育培训、考核、奖惩制度，并落实到日常管理中；</p>
		<p>5.工作创新：受到中央、省委领导肯定；召开省级现场会；全市（州）实现部门资源整合，做到信息共享及其他创新举措。</p>
	</div>
</div>
<script>
var annualAssessmentGrid = null;
$(document).ready(function(){
	
		var context = {};
		var columns = [
				{name:"organization.orgName",caption:"市州",width:60,mode:"string"}, 
				{name:"general",caption:"党政主导(0.1分)",width:60,mode:"string"}, 
				{name:"general",caption:"经费保障情况(0.15分)",width:60,mode:"string"}, 
				{name:"general",caption:"落实网格员情况(0.15分)",width:60,mode:"string"}, 
				{name:"general",caption:"机制建设(0.05分)",width:60,mode:"string"}, 
				{name:"general",caption:"网格地图(0.1分)",width:60,mode:"string"}, 
				{name:"general",caption:"工作创新(0.3分)",width:60,mode:"string"}
		];
		annualAssessmentGrid = new SigmaReport("annualAssessmentGridbox",context,columns, "SigmaReportPrint","printTable_title");
		
		$("#title_annualAssessmentGridbox").children().remove();
		$("#title_annualAssessmentGridbox").html("网格化服务管理工作年终考核表");
		
		annualAssessmentGridboxPrint();
		$("#annualAssessmentGridbox").css({"overflow-y": "auto"});
	});
function annualAssessmentGridboxPrint(){
	$.ajax({
		url:'${path}/baseInfoStat/annualAssessmentManage/getAnnualAssessmentReportForList.action',
		success:function(data){
			annualAssessmentGrid.bindData(data);
		}
	})
}
</script>