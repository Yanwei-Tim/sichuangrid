<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
    
<script type="text/javascript">
			$("#searchHighA").click(function(){
			
				$("#floatingPopulationDialog").createDialog({
					width: 650,
					height: 330,
					title: title+'查询-请输入查询条件',
				
					url: '${(path)!}/baseinfo/floatingPopulation/searchFloatingPopulationDlg.jsp',
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
				url: '${(path)!}/baseinfo/floatingPopulation/floatingPopulationStatistics.jsp?isSearch='+isSearch,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		
</script>
<@s.include value="/leaderView/populationStatistics/commonStatistics.ftl">
	<@s.param  name="populationType"><@s.property value="@com.tianque.core.util.BaseInfoTables@FLOATINGPOPULATION_KEY"/></@s.param>
</@s.include>
<div id="floatingPopulationDialog"></div>