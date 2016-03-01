<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    
<script type="text/javascript">
			$("#searchHighA").click(function(){
				$("#unsettledPopulationMaintanceDialog").createDialog({
					width: 650,
					height: 330,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/unsettledPopulation/maintainUnsettledPopulationConditionDlg.jsp',
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
				if($("#searchByIdC").val()=="请输入姓名"){
					$.notice({
						level: 'alert',
					    okbtnName: "确定",
					    title:'提示',
					    message: "请输入姓名"
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
				url: '${path}/baseinfo/unsettledPopulation/unsettledPopulationStatistics.jsp?isSearch='+isSearch,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		
</script>
<s:include value="/baseinfo/leaderView/populationStatistics/commonStatistics.jsp">
	<s:param  name="populationType"><s:property value="@com.tianque.core.util.BaseInfoTables@UNSETTEDPOPULATION_KEY"/></s:param>
</s:include>
<div id="unsettledPopulationMaintanceDialog"></div>