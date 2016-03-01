<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="systemUsedReportPrint"  style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 24px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:900px; !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:900px;  
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
		<#--
		.footerInfo{
			border: 1px solid #ccc;
			line-height: 1.6em;
			padding: 5px;
			margin: -3px 17px 0 8px;
			height: 50px;
			overflow-y: auto;
		}-->
	</style>
   <div id="systemUsedGridbox" class="SigmaReportPrint" style="overflow: auto;width:100%"></div>
   <#--
   <div class="footerInfoPrint">
		<p>备注：</p>
			 <p>日活跃度：昨天进入信息系统并开展工作的用户/帐号总数</p>
		     <p>周活跃度：上周至少5天，每天至少1次进入信息系统开展工作的用户/帐号总数</p>
			 <p>月活跃度：上月至少20天，每天至少1次进入信息系统开展工作用户/账号总数</p>
			 <p>特殊人群日走访服务量：昨天特殊人群的走访总量</p>
			 <p>特殊人群周走访服务量：上周特殊人群的走访总量</p>
			 <p>特殊人群月走访服务量：上月特殊人群的走访总量</p>
		 
	</div>
	-->
</div>
<script>
var systemUsedGrid = null;
$(document).ready(function(){
	
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
		systemUsedGrid = new SigmaReport("systemUsedGridbox",context,columns, "SigmaReportPrint","printTable_title");
		
		$("#title_systemUsedGridbox").children().remove();
		$("#title_systemUsedGridbox").html("信息系统使用情况表");
		
		systemUsedGridboxPrint();
		$("#systemUsedGridbox").css({"overflow-y": "auto"});
	});
function systemUsedGridboxPrint(){
	$.ajax({
		url:'${path}/baseInfoStat/systemUsedReportManage/getSystemUsedReportForList.action',
		data:{
			"orgId":getCurrentOrgId()
		},
		success:function(data){
			systemUsedGrid.bindData(data);
		}
	})
}
</script>