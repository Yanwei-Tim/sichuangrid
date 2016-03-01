<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/common/orgSelectedComponent.jsp"/>     
<style>
.SigmaReport{
  overflow: auto !important;
}
</style>

<div class="gridTeamStatistics" style="overflow:auto;height:480px;" >
	<div id="gridbox12" class="SigmaReport"></div>
</div>

<div id="${type}PrintDlg"></div>

<script type="text/javascript">
	$(document).ready(function(){
		var tableTitleName = "网格员信息统计表";
		var primaryOrgStatGrid = initReportGrid(tableTitleName,false);
		onOrgChanged(primaryOrgStatGrid);
		/* $(".print").click(function(){
			$("#zongkuangPrint").createDialog({
				width:800,
				height:600,
				title:document.title,
				url:'${path}/baseinfo/gridTeam/gridTeamStatisticsPrint.jsp',
				buttons: {
				   "打印" : function(){
					print();
			  	   },
				   "关闭" : function(){
				        $("#zongkuangPrint").dialog("close");
				   }
				}
			});
		}); */
	});
	
	function onOrgChanged(primaryOrgStatGrid){
		$.ajax({
			url:'${path}/baseinfo/gridTeamManage/gridTeamStatistics.action',
			data:{"organization.id":getCurrentOrgId()},
			success:function(data){
				if(data == null){
					$.messageBox({
						message: "网格员信息统计失败",
						level: "error"
					});
					return;
				}
				var newdata = $.parseJSON(data);
				if(newdata!=null && newdata.expLevel=="error"){
					$.messageBox({
						message: newdata.message,
						level: "error"
					});
					return;
				}
				primaryOrgStatGrid.bindData(data);
			}
		})
	}
	
	var primaryOrgStatGrid = null;
	function initReportGrid(tableTitle,printBtn){
		var context = {};
		var columns = [		
						{name:"org.orgName",caption:"区域",width:80,mode:"string"}, 
						{name:"subGrids",caption:"网格总数",width:120,mode:"string"},
						{name:"gridTeamCount",caption:"网格员总数",width:120,mode:"string"},
						{name:"fullTimeGridTeamCount",caption:"专职网格员数",width:120,mode:"string"},
						{name:"partTimeGridTeamCount",caption:"兼职网格员数",width:120,mode:"string"},
						{name:"phoneGridTeamCount",caption:"手持终端配备数",width:120,mode:"string"}
			   		];
		primaryOrgStatGrid = new SigmaReport("gridbox12",context,columns, null,null,tableTitle,printBtn);
		return primaryOrgStatGrid;
	}
	
</script>