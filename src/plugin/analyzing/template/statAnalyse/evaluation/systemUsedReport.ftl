<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/common/orgSelectedComponent.jsp"/>   

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
.btnbanner{
float: left;
vertical-align: middle;
margin-right: 10px;
}
#nav{
	padding-top:0;
}
<#--
.footerInfo{
	border: 1px solid #ccc;
	line-height: 1.6em;
	padding: 5px;
	margin: -3px 17px 0 5px;
	height: 42px;
	overflow-y: auto;
}
-->
</style>
<div class="container_24">
	<div style="clear: both;"></div>
	<div id="gridbox" class="SigmaReport" style="overflow-x:hidden;overflow-y:auto !important;position:relative;height: 220px;width:99%;"></div>
	<#--
	<div class="footerInfo">
		<p>备注：</p>
			 <p>日活跃度：昨天进入信息系统并开展工作的用户/帐号总数</p>
		     <p>周活跃度：上周至少5天，每天至少1次进入信息系统开展工作的用户/帐号总数</p>
			 <p>月活跃度：上月至少20天，每天至少1次进入信息系统开展工作用户/账号总数</p>
			 <p>特殊人群日走访服务量：昨天特殊人群的走访总量</p>
			 <p>特殊人群周走访服务量：上周特殊人群的走访总量</p>
			 <p>特殊人群月走访服务量：上月特殊人群的走访总量</p>
		 
	</div>-->
</div>
<div id="MainDialog"></div>
<div id="systemUsedPrintDialog"></div>
<script type="text/javascript">


$(document).ready(function(){
	reloadReportDate();
});

$(".print").click(function(){
	printSystemUsed();
});

function reloadReportDate(){
	$.ajax({
		async: false,
		url:'${path}/baseInfoStat/systemUsedReportManage/getSystemUsedReportForList.action',
		data:{
			"orgId":getCurrentOrgId()
		},
		success:function(responseData){
			rebuildeGrid(responseData);
		}
	});
}

function rebuildeGrid(reportData){
   	var context = {};
   	var columns = [		
				{name:"org.orgName",caption:"区域",width:60,mode:"string"}, 
				{name:"general",caption:"手持终端和PC端使用情况",width:60,mode:"string",children:[
					{name:"dayActiveRate",caption:"昨天活跃度",width:60,mode:"string"}, 
					{name:"weekActiveRate",caption:"上周活跃度",width:60,mode:"string"}, 
					{name:"monthActiveRate",caption:"上月活跃度",width:60,mode:"string"}
				]}, 
				{name:"general",caption:"特殊人群服务走访情况",width:60,mode:"string",children:[
					{name:"daySpecialCrowdCount",caption:"昨天走访服务量",width:60,mode:"string"}, 
					{name:"weekSpecialCrowdCount",caption:"上周走访服务量",width:60,mode:"string"}, 
					{name:"monthSpecialCrowdCount",caption:"上月走访服务量",width:60,mode:"string"}
				]}
   		];
	grid = new SigmaReport("gridbox",context,columns);
	
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("信息系统使用情况表<a href='javascript:;' class='print' title='打印'></a>");
	
	grid.bindData(reportData);
}

function printSystemUsed(){
	$("#systemUsedPrintDialog").createDialog({
			width:980,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/evaluation/systemUsedReportPrint.ftl',
			buttons:{
			   "打印":function(){
				print();
		  	   },
			   "关闭":function(){
			        $("#systemUsedPrintDialog").dialog("close");
			   }
			}
	});
}
function print(){
	$("#systemUsedReportPrint").printArea();
	$("#systemUsedPrintDialog").dialog("close");
}


</script>