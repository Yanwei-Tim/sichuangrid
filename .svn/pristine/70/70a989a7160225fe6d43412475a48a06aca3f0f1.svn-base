<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
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
     
            <a id="viewStatisticsRole" href="javascript:void(0)"><span>统计规则</span></a>
</div>
<div>
	<div id="gridbox" class="SigmaReport dd"></div>
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
			{name:"organization.orgName",caption:"区域",mode:"string",width:100},
			{name:"general",caption:"吸毒人员",children:[
				{name:"druggyPopulationNum",caption:"人数",width:60,mode:"string"},
				{name:"visitsDruggyNumber",caption:"走访人数",width:80,mode:"string"},
				{name:"visitsNumberOfDruggy",caption:"走访次数",width:80,mode:"string"},
				{name:"visitsProportionOfDruggy",caption:"走访比例",width:80,mode:"string"},
				{name:"exceptionOfDruggy",caption:"异常",width:60,mode:"string"},
				{name:"signOfDruggy",caption:"签收",width:60,mode:"string"},
				{name:"replyOfDruggy",caption:"回复",width:60,mode:"string"}
			]},
			{name:"general",caption:"严重精神障碍患者",children:[
				{name:"mentalPatientPopulationNum",caption:"人数",width:60,mode:"string"},
				{name:"visitsMentalPatientNumber",caption:"走访人数",width:80,mode:"string"},
				{name:"visitsNumberOfMentalPatient",caption:"走访次数",width:80,mode:"string"},
				{name:"visitsProportionOfMentalPatient",caption:"走访比例",width:80,mode:"string"},
				{name:"exceptionOfMentalPatient",caption:"异常",width:60,mode:"string"},
				{name:"signOfMentalPatient",caption:"派出所签收",width:80,mode:"string"},
				{name:"signOfMentalJustice",caption:"卫生所签收",width:80,mode:"string"},
				{name:"replyOfMentalPatient",caption:"回复",width:60,mode:"string"}
			]},
			{name:"general",caption:"社区服刑人员",children:[
				{name:"rectificativePopulationNum",caption:"人数",width:60,mode:"string"},
				{name:"visitsRectificativeNumber",caption:"走访人数",width:80,mode:"string"},
				{name:"visitsNumberOfRectificative",caption:"走访次数",width:80,mode:"string"},
				{name:"visitsProportionOfRectificative",caption:"走访比例",width:80,mode:"string"},
				{name:"exceptionOfRectificative",caption:"异常",width:60,mode:"string"},
				{name:"signOfRectificative",caption:"签收",width:60,mode:"string"},
				{name:"replyOfRectificative",caption:"回复",width:60,mode:"string"}
			]},
			{name:"general",caption:"刑释人员",children:[
				{name:"positiveInfoPopulationNum",caption:"人数",width:60,mode:"string"},
				{name:"visitsPositiveInfoNumber",caption:"走访人数",width:80,mode:"string"},
				{name:"visitsNumberOfPositiveInfo",caption:"走访次数",width:80,mode:"string"},
				{name:"visitsProportionOfPositiveInfo",caption:"走访比例",width:80,mode:"string"},
				{name:"exceptionOfPositiveInfo",caption:"异常",width:60,mode:"string"},
				{name:"signOfPositiveInfo",caption:"签收",width:60,mode:"string"},
				{name:"replyOfPositiveInfo",caption:"回复",width:60,mode:"string"}
			]}
		];


var grid = null;

function onOrgChanged(orgId){
    var orgId=getCurrentOrgId();
	$.ajax({
		dataType:"json",
		url:'${path }/plugin/taskListManage/common/getVisitListOfStatistics.action?orgId='+orgId+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val()+'&searchYear='+$("#yearVal").val(),
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){
$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,"任务清单（新）",null,null);
	getYear();
	typeChange();
	onOrgChanged();
	$(".print").click(function(){
		var url = '${path}/task/reportForm/taskListStatisticsPrint.ftl?parentOrgId='+getCurrentOrgId()+"&moduleName="+document.title;
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
		var url = '${path}/plugin/taskListManage/common/downloadTask.action?orgId='+getCurrentOrgId()+'&searchType='+$("#searchType").val()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&week='+$("#week").val()+'&searchYear='+$("#yearVal").val();
		downloadFile(url);
	});
	function downloadFile(url){  
	    var elemIF = document.createElement("iframe");  
	    elemIF.src = url;  
	    elemIF.style.display = "none";  
	    document.body.appendChild(elemIF);  
	}
	
	$("#viewStatisticsRole").click(function(){
		$("#issueStatisticsRole").createDialog({
			width: 550,
			height: 400,
			title:"任务清单统计表（新）规则",
			url:"${path}/task/reportForm/taskListStatisticsRole.ftl",
			buttons:{
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
			
		});
	});
	
	$("#search").click(function(){
		 onOrgChanged();
	});
	
	$("#searchType").change(function(){
		 typeChange();
	});	
	
})
	

</script>

