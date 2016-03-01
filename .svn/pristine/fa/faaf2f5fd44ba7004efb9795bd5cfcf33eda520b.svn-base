<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
#tableWrap{
	overflow-x:auto;
	border-top: 1px solid #ccc;
}
#tableWrap .head{
	border-top: 0 none;
}

</style>
<div id="nav" class="newChartsStyle cf" style="margin-left:5px;">
     <div class="btnbanner btnbannerData">
			<s:include value="/common/orgSelectedComponent.jsp" />
     </div>
	    时间： <select id="searchType">
	    		<option value="0" selected>按月统计</option>
	    		<option value="1">按周统计</option>
	    	</select>
	    	<span id="searchByMonth">
				<select id="year"></select> 
				年 
				<select id="month"></select>
			</span>
			<span id="searchByWeek">
				<select id="week">
					<option value="0">本周</option>
					<option value="1">上周</option>
				</select>
			</span>
	        <a id="search" href="javascript:void(0)"><span>查询</span></a>

            <a id="export" href="javascript:void(0)"><span>导出</span></a>
</div>
<div>
	<div id="gridbox" class="SigmaReport dd">
	
	</div>
</div>
<div id="PrintDlg"></div>
<script type="text/javascript">
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"   月</option>");
			}
		}
	});
}
function getYear(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
}
function typeChange(){
	var val = $("#searchType").val();
	if(val==0){
		  $("#searchByMonth").show();
		  $("#searchByWeek").hide();
	}else if(val ==1 ){
		  $("#searchByMonth").hide();
		  $("#searchByWeek").show();
	}
}
	var fitColumns=true;
		var columns = [
			{name:"orgname",caption:"区域",mode:"string"},
			{name:"totalSum",caption:"发送量",mode:"string"},
			{name:"signSum",caption:"签收量",mode:"string"},
			{name:"signPercent",caption:"签收量百分比",mode:"string"}
			<pop:JugePermissionTag ename="foodAndDrugsManagement">
			,
				{name:"general",caption:"食品药品工商管理",children:[
				<pop:JugePermissionTag ename="policyPropaganda">
					{name:"general",caption:"政策法规宣传",children:[
                     	{name:"policyPropagandaSum",caption:"网格员发送",width:80,mode:"string"},
                     	{name:"policyPropagandaVisit",caption:"已签收",width:60,mode:"string"},
                     	{name:"policyPropagandaSignPercent",caption:"百分比",width:60,mode:"string"}
                    ]} 
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="foodSafty">
					,
					{name:"general",caption:"食品安全协管",children:[
                 		{name:"foodSaftySum",caption:"网格员发送",width:80,mode:"string"},
                 		{name:"foodSaftyVisit",caption:"已签收",width:60,mode:"string"},
                 		{name:"foodSaftySignPercent",caption:"百分比",width:60,mode:"string"}
                	]} 
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="drugysSafty">
					,
					{name:"general",caption:"药品安全协管",children:[
	             		{name:"drugsSaftySum",caption:"网格员发送",width:80,mode:"string"},
	             		{name:"drugsSaftyVisit",caption:"已签收",width:60,mode:"string"},
	             		{name:"drugsSaftySignPercent",caption:"百分比",width:60,mode:"string"}
	            	]} 
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="businessManage">
					,
					{name:"general",caption:"工商行政管理协管",children:[
             			{name:"businessManageSum",caption:"网格员发送",width:80,mode:"string"},
             			{name:"businessManageVisit",caption:"已签收",width:60,mode:"string"},
             			{name:"businessManageSignPercent",caption:"百分比",width:60,mode:"string"}
            		]} 
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="pyramidSalesManage">
					,
					{name:"general",caption:"打击非法传销协管",children:[
		     			{name:"pyramidSalesManageSum",caption:"网格员发送",width:80,mode:"string"},
		     			{name:"pyramidSalesManageVisit",caption:"已签收",width:60,mode:"string"},
		     			{name:"pyramidSalesManageSignPercent",caption:"百分比",width:60,mode:"string"}
		    		]} 
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="unlicensedManage">
					,
					{name:"general",caption:"查处取缔无证无照经营协管",children:[
		     			{name:"unlicensedManageSum",caption:"网格员发送",width:80,mode:"string"},
		     			{name:"unlicensedManageVisit",caption:"已签收",width:60,mode:"string"},
		     			{name:"unlicensedManageSignPercent",caption:"百分比",width:60,mode:"string"}
		    		]} 
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="otherSituationManage">
					,
					{name:"general",caption:"其它情况",children:[
		     			{name:"otherSituationManageSum",caption:"网格员发送",width:80,mode:"string"},
		     			{name:"otherSituationManageVisit",caption:"已签收",width:60,mode:"string"},
		     			{name:"otherSituationManageSignPercent",caption:"百分比",width:60,mode:"string"}
		    		]} 
				</pop:JugePermissionTag>
				]}
			</pop:JugePermissionTag>
		];


var grid = null;

function changeTable(){
   $("table td[caption='区域']").attr("style","text-align:center;padding:20px;");
   $("table td[caption='发送量']").attr("style","text-align:center;padding:20px;");
   $("table td[caption='签收量']").attr("style","text-align:center;padding:20px;");
   $("table td[caption='签收量百分比']").attr("style","text-align:center;padding:20px;");
}

function onOrgChanged(orgId){
    var orgId=getCurrentOrgId();
	$.ajax({
		dataType:"json",
		url:'${path }/serviceList/serviceListReportManage/getReportList.action?orgId='+orgId+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val(),
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){

	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,"食药工商",null,null);
	//setTimeout('onOrgChanged()',350);
	getYear();
	typeChange();
	onOrgChanged();
	changeTable();
	$(".print").click(function(){
		var url = '${path}/serviceList/servicListStatistics/serviceListPrint.jsp?parentOrgId='+getCurrentOrgId()+"&moduleName="+document.title;
		$("#PrintDlg").createDialog({
			width: 1200,
			height:490,
			title:document.title,
			url:url,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#PrintDlg").dialog("close");
			   }
			}
		})
	});
	
	function print(){
		$("#Print").printArea();
		$("#PrintDlg").dialog("close");
	}
	
	$("#export").click(function(){
		var url = '${path}/serviceList/serviceListReportManage/downloadServiceListReport.action?orgId='+getCurrentOrgId()+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val();
		downloadFile(url);
	});
	
	function downloadFile(url){  
	    var elemIF = document.createElement("iframe");  
	    elemIF.src = url;  
	    elemIF.style.display = "none";  
	    document.body.appendChild(elemIF);  
	}
	
	$("#search").click(function(){
		onOrgChanged();
	});
	
	$("#searchType").change(function(){
		 typeChange();
	});	
	
})
	

</script>

