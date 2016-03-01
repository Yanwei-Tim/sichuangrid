<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
.footerInfo{
	border: 1px solid #ccc;
	line-height: 1.6em;
	padding: 5px;
	margin: -3px 17px 0 5px;
	height: 44px;
	overflow-y: auto;
}
</style>
<div class="container_24">
	<div style="clear: both;"></div>
	<div id="gridbox" class="SigmaReport" style="overflow-x:hidden;overflow-y:auto !important;position:relative;height: 440px;width:99%"></div>
	<div class="footerInfo">
		<p>1.党政主导：县（市、区）党委、政府及时研究、协调解决工作中的重大问题，有落实省两办[2014]43号文件精神的相关文件、会议纪要或会议记录；</p>
		<p>2.经费保障情况：网格化服务管理工作经费纳入当地财政预算，及时兑现网格员补助；</p>
		<p>3.落实网格员情况：城市社区有专职网格员，农村有专兼职网格员；</p>
		<p>4.机制建设：建立健全了网格员管理、教育培训、考核、奖惩制度，并落实到日常管理中；</p>
		<p>5.工作创新：受到中央、省委领导肯定；召开省级现场会；全市（州）实现部门资源整合，做到信息共享及其他创新举措。</p>
	</div>
</div>
<div id="MainDialog"></div>
<div id="annualPrintDlg"></div>
<script type="text/javascript">
$(document).ready(function(){
		reloadReportDate();
});

$(".print").click(function(){
	printAnnual();
});


function reloadReportDate(){
	$.ajax({
		async: false,
		url:'${path}/baseInfoStat/annualAssessmentManage/getAnnualAssessmentReportForList.action',
		data:{
		},
		success:function(responseData){
			rebuildeGrid(responseData);
		}
	});
}

function rebuildeGrid(reportData){
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
	grid = new SigmaReport("gridbox",context,columns);
	
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("网格化服务管理工作年终考核表<a href='javascript:;' class='print' title='打印'></a>");
	
	grid.bindData(reportData);
}
function printAnnual(){
	$("#annualPrintDlg").createDialog({
			width:1020,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/evaluation/annualAssessmentPrint.ftl',
			buttons:{
			   "打印":function(){
				print();
		  	   },
			   "关闭":function(){
			        $("#annualPrintDlg").dialog("close");
			   }
			}
	});
}
function print(){
	$("#annualAssessmentReportPrint").printArea();
	$("#annualPrintDlg").dialog("close");
}
</script>