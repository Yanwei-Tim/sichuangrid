<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String keyType = request.getParameter("keyType");
request.setAttribute("keyType",keyType);
%>
<script type="text/javascript">
			$("#searchHighA").click(function(){
				$("#householdStaffPopulationDialog").createDialog({
					width: 650,
					height: 330,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/youths/searchYouthsDlg.jsp?keyType=${keyType}',
					buttons: {
						"查询" : function(event){
							$("#statisticsListDialog").createDialog(getDialogParam(1));
					   },
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			});

			$("#searchByIdcardA").click(function(){
				if($("#searchByIdC").val()=="请输入身份证号码"){
					$.notice({
						level: 'alert',
					    okbtnName: "确定",
					    title:'提示',
					    message: "请输入身份证号码"
					});
					return;
				};
				$("#statisticsListDialog").createDialog(getDialogParam(0));
			});

        function getDialogParam(isSearch){
			return {
				width: 900,
				height: 600,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/youths/youthsStatistics.jsp?isSearch='+isSearch,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		
</script>
<s:if test="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@IS_NEWANALYSE_JOB">
	<jsp:include page="/baseinfo/leaderView/populationStatistics/commonStatistics.jsp" >
		<jsp:param name="younthType" value="younth" />
		<jsp:param name="populationType" value="householdStaff" />
		<jsp:param name="areaDataTableUrl" value="${path}/baseinfo/leaderViewManageNew/statisticsYouthsCount.action?tableType=${keyType}" />
		<jsp:param name="dataMonthTableUrl" value="${path}/baseinfo/leaderViewManageNew/statisticsYouthsMonthSummary.action?tableType=${keyType}"/>
	</jsp:include>	
</s:if>
<s:else>
	<jsp:include page="/baseinfo/leaderView/populationStatistics/commonStatistics.jsp" >
		<jsp:param name="younthType" value="younth" />
		<jsp:param name="populationType" value="householdStaff" />
		<jsp:param name="areaDataTableUrl" value="${path}/baseinfo/leaderViewManage/statisticsYouthsCount.action?tableType=${keyType}" />
		<jsp:param name="dataMonthTableUrl" value="${path}/baseinfo/leaderViewManage/statisticsYouthsMonthSummary.action?tableType=${keyType}"/>
	</jsp:include>		
</s:else>

<input type="hidden" id="keyTypes" value="${keyType}" />
<div id="householdStaffPopulationDialog"></div>