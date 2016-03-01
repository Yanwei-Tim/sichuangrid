<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/common/orgSelectedComponent.jsp"/>
<style type="text/css">
.theFirIe{width:19px!important;}
</style>
<input type="hidden" id="issueTypeDomainName" value="<@s.property value='statisticAnalysisVo.issueTypeDomainName'/>"/>
<div style="clear: both;"></div>
<div style="width: 100%">
	<table id="childIssueTypeStatistic"></table>
</div>
<div style="clear: both;"></div>
<script type="text/javascript">
$(document).ready(function(){
	if(document.all){ 
		var gridWin = 271;
	} else {
		var gridWin = 279;
	}
	$("#childIssueTypeStatistic").jqGridFunction({
		rowNum:1000,
		//width:1155,
		height:'360',
		dataType:"json",
		url:'${path}/statisticAnalysis/findStatisticAnalysisToIssueType.action',
		postData: {
			"statisticAnalysisVo.org.id":${statisticAnalysisVo.org.id!},
			"statisticAnalysisVo.issueTypeDomainName":$("#issueTypeDomainName").val(),
			"statisticAnalysisVo.startDate":$("#startDate").val(),
			"statisticAnalysisVo.endDate":$("#endDate").val()
		},
		colModel:[
			{name:"",index:'id',align:'center',width:25},
			{name:"statisticAnalysisDetailVo.issueTypeName",label:'事件类型',width:gridWin},
			{name:"statisticAnalysisDetailVo.acceptNum",label:'受理事件数',width:gridWin},
			{name:"statisticAnalysisDetailVo.completedNum",label:'已办结数',width:gridWin},
			{name:"statisticAnalysisDetailVo.unCompletedNum",label:'未办结数',width:gridWin}
		],
		gridComplete:function(){
			 $(this).closest('.ui-jqgrid-view').find('div.ui-jqgrid-hdiv').hide();
			 
			if(document.all){ 
			$("#childIssueTypeStatistic tr").each(function(){
			 	$(this).find("td").first().addClass("theFirIe");
			 })
			} 

		}
	});
	$(".ui-jqgrid-bdiv").css("overflow-x","hidden");//去除x轴的滚动条
	$("th.ui-state-default.ui-th-column-header.ui-th-ltr").css("text-align","center");
	  $(".ui-jqgrid-hbox").css("padding-right","0px");
	  $("#childIssueTypeStatistic > tbody tr").find("td").each(function(index ,value){ 
			$(this).width($(this).width()-1);
	  });
	
});

</script>