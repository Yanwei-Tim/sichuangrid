<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div id="nav" class="newChartsStyle cf" style="margin-left:5px;">
     <div class="btnbanner btnbannerData">
			<@s.include value="/common/orgSelectedComponent.jsp" />
     </div>
     时间： <select id="searchType">
	    		<option value="0" selected>按月统计</option>
	    		<option value="1">按周统计</option>
	    		<option value="2">按年统计</option>
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
			<span id="searchByYear">
				<select id="yearVal"></select> 
				年 
			</span>
	        <a id="search" href="javascript:void(0)"><span>查询</span></a>
     <#-- 导出按钮功能 -->
      &nbsp;&nbsp;<a id="export" href="javascript:void(0)"><span>导出</span></a>
</div>

<div>
	<div id="gridbox" class="SigmaReport"></div>
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
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				$("#yearVal").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
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
		  $("#searchByYear").hide();
	}else if(val ==1 ){
		  $("#searchByMonth").hide();
		  $("#searchByWeek").show();
		  $("#searchByYear").hide();
	}else if(val==2){
		  $("#searchByMonth").hide();
		  $("#searchByWeek").hide();
		  $("#searchByYear").show();
	}
}
	var fitColumns=true;
		var columns = [
			{name:"orgname",caption:"区域",mode:"string"},
			    {name:"general",caption:"宣传核查",children:[
				{name:"general",caption:"",children:[
					{name:"policeSum",caption:"已发送",width:57,mode:"string"},
					{name:"policeVisit",caption:"已签收",width:57,mode:"string"}
				]}]},
				{name:"general",caption:"民警带领开展工作",children:[
				{name:"general",caption:"",children:[
				    {name:"publicSum",caption:"已发送",width:60,mode:"string"},
					{name:"publicVisit",caption:"已签收",width:60,mode:"string"}
					
				]}]},
				{name:"general",caption:"异常情况报告",children:[
				{name:"general",caption:"大量聚集",children:[
				    {name:"gatherSum",caption:"已发送",width:57,mode:"string"},
					{name:"gatherVisit",caption:"已签收",width:57,mode:"string"},
					{name:"gatherReply",caption:"已回复",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"异常气味",children:[
				    {name:"smellSum",caption:"已发送",width:57,mode:"string"},
					{name:"smellVisit",caption:"已签收",width:57,mode:"string"},
					{name:"smellReply",caption:"已回复",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"异常声音",children:[
				    {name:"noiseSum",caption:"已发送",width:57,mode:"string"},
					{name:"noiseVisit",caption:"已签收",width:57,mode:"string"},
					{name:"noiseReply",caption:"已回复",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"无身份证",children:[
				    {name:"noIdcardSum",caption:"已发送",width:57,mode:"string"},
					{name:"noIdcardVisit",caption:"已签收",width:57,mode:"string"},
					{name:"noIdCardReply",caption:"已回复",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"群租房人员来往复杂",children:[
				    {name:"groupLiveSum",caption:"已发送",width:57,mode:"string"},
					{name:"groupLiveVisit",caption:"已签收",width:57,mode:"string"},
					{name:"groupLiveReply",caption:"已回复",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"已搬走",children:[
				    {name:"liveSum",caption:"已发送",width:57,mode:"string"},
					{name:"liveVisit",caption:"已签收",width:57,mode:"string"},
					{name:"liveReply",caption:"已回复",width:57,mode:"string"}
					
				]}]},
				{name:"general",caption:"总数",children:[
				{name:"general",caption:"",children:[
				    {name:"floatingPopulationSum",caption:"已发送",width:57,mode:"string"},
					{name:"floatingPopulationVisit",caption:"已签收",width:57,mode:"string"},
					{name:"floatingPolulationReply",caption:"已回复",width:57,mode:"string"}
				]}]}
			
		];


var grid = null;

function changeTable(){
   $("table td[caption='宣传核查']").attr("rowspan","2");
   $("table td[caption='民警带领开展工作']").attr("rowspan","2");
   $("table td[caption='大量聚集']").prev().remove();
   $("table td[caption='大量聚集']").prev().remove();
   $("table td[caption='总数']").attr("rowspan","2");
   $("table td[caption='已搬走']").next().remove();
   
   $("table td[caption='宣传核查']").attr("style","text-align:center;padding-top:9px;");
   $("table td[caption='民警带领开展工作']").attr("style","text-align:center;padding:9px;");
   $("table td[caption='总数']").attr("style","text-align:center;padding:9px;");
   $("table td[caption='区域']").attr("style","text-align:center;padding:20px;");
}

function onOrgChanged(orgId){
    var orgId=getCurrentOrgId();
	$.ajax({
		dataType:"json",
		url:'${path }/plugin/taskListManage/common/getFloatingPopulationVisitList.action?orgId='+orgId+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val()+'&searchYear='+$("#yearVal").val(),
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,"",null,null);
	$("#title_gridbox").html("流动人口统计表<a href='javascript:;' class='print' title='打印'></a>");
	//setTimeout('onOrgChanged()',350);
	getYear();
	typeChange();
	onOrgChanged();
	changeTable();
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$(".print").click(function(){
		
		var url = '${path}/task/reportForm/floatingPopulationTaskPrint.ftl?parentOrgId='+getCurrentOrgId()+"&moduleName="+document.title;
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
		});
	});
	
	function print(){
		$("#Print").printArea();
		$("#PrintDlg").dialog("close");
	}
	
	$("#export").click(function(){
		var url = '${path }/plugin/taskListManage/common/downloadFloating.action?orgId='+getCurrentOrgId()+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val()+'&searchYear='+$("#yearVal").val();
		downloadFile(url);
	});
	$("#search").click(function(){
		onOrgChanged();
	});
	
	$("#searchType").change(function(){
		 typeChange();
	});	
	function downloadFile(url){  
	    var elemIF = document.createElement("iframe");  
	    elemIF.src = url;  
	    elemIF.style.display = "none";  
	    document.body.appendChild(elemIF);  
	}
})
	
	


</script>

