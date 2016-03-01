<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/common/orgSelectedComponent.jsp"/>

<div class="container container_24" id="nav">
	<div class="grid_2 lable-right">
		<label>开始日期：</label>
	</div>
	<div class="grid_3 form-left">
		<input type="text" name="" class="form-txt" readonly id="startDate" value="<@s.date name="" format="yyyy-MM-dd"/>" />
	</div>
	<div class="grid_2 lable-right">
		<label>截止日期：</label>
	</div>
	<div class="grid_3 form-left">
		<input type="text" name="" class="form-txt" readonly id="endDate" value="<@s.date name="" format="yyyy-MM-dd"/>" />
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;  
	<a id="search" href="javascript:void(0)" style="margin-top:4px;"><span><strong class="ui-ico-cx"></strong>查询</span></a>
	<@pop.JugePermissionTag ename="export">
		<a id="export"  href="javascript:void(0)" style="margin-top:4px;"><span>导出</span></a>
	</@pop.JugePermissionTag>
	
</div>
<div style="clear: both;"></div>
<div style="width: 100%">
	<table id="issueTypeStatistic"></table>
</div>
<div style="clear: both;"></div>
<script type="text/javascript">
$(document).ready(function(){
	var currentDate = new Date();
	$('#startDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endDate").datepicker("option", "minDate",date);
			}
		}
	});

	$("#startDate").datepicker("setDate",currentDate);

	$('#endDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
	    maxDate:'+0d',
	    onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#startDate").datepicker("option", "maxDate",date);
			}
		}
	});
	$("#endDate").datepicker("setDate",currentDate);
	
	$("#issueTypeStatistic").jqSubGrid({
		rowNum:1000,
		height:'360',
		dataType:"json",
		url:'${path}/statisticAnalysis/findStatisticAnalysisToIssueTypeDomain.action',
		postData: {
			"statisticAnalysisVo.org.id":getCurrentOrgId(),
			"statisticAnalysisVo.startDate":$("#startDate").val(),
			"statisticAnalysisVo.endDate":$("#endDate").val()
		},
		subGridUrl:'${path}/statisticAnalysis/findStatisticAnalysisToIssueTypePage.action',
		subGridPostData:function(data){return {
			"statisticAnalysisVo.issueTypeDomainName":data["statisticAnalysisDetailVo.issueTypeDomainName"],
			"statisticAnalysisVo.org.id":getCurrentOrgId(),
			"statisticAnalysisVo.startDate":$("#startDate").val(),
			"statisticAnalysisVo.endDate":$("#endDate").val()
			}},
		colModel:[
			{name:"statisticAnalysisDetailVo.issueTypeDomainId",hidden:true,width:24},
			{name:"statisticAnalysisDetailVo.issueTypeDomainName",label:'事件类型',width:100},
			{name:"statisticAnalysisDetailVo.acceptNum",label:'受理事件数',width:100},
			{name:"statisticAnalysisDetailVo.completedNum",label:'已办结数',width:100},
			{name:"statisticAnalysisDetailVo.unCompletedNum",label:'未办结数',width:100}
		],
		gridComplete:function(){
			//$("#issueTypeStatistic").find("tr:first").next().children().children().remove();
			//$("#issueTypeStatistic").find("tr:last").find("td:first").children().remove();
			//$('#issueTypeStatistic').setGridWidth(776);
		},
		subGridBeforeExpand:function(subgridId, rowId){
		
		}
	});
	$(".ui-jqgrid-bdiv").css("overflow-x","hidden");//去除x轴的滚动条
	$("#search").click(function(){
    	var postData = $("#issueTypeStatistic").getPostData();
    	
    	postData["statisticAnalysisVo.org.id"]= getCurrentOrgId();
	 	postData["statisticAnalysisVo.startDate"]=$("#startDate").val();
	 	postData["statisticAnalysisVo.endDate"]=$("#endDate").val();
	 	
	 	$("#issueTypeStatistic").trigger("reloadGrid");
    });
    
	$("#export").click(function(){
		var url="${path}/statisticAnalysis/downIssueTypeStatisticAnalysis.action?statisticAnalysisVo.org.id="+getCurrentOrgId()+"&statisticAnalysisVo.startDate="+$("#startDate").val()+"&statisticAnalysisVo.endDate="+$("#endDate").val();
		downloadFile(url);
	});
	
	function downloadFile(url){  
	    var elemIF = document.createElement("iframe");  
	    elemIF.src = url;  
	    elemIF.style.display = "none";  
	    document.body.appendChild(elemIF);  
	}
});

</script>