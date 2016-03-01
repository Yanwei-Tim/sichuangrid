<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.ui-layout-center{overflow:auto;}
</style>

<div style="height:400px;" >
	<div id="gridboxCollect" class="SigmaReport"></div>
</div>
<script type="text/javascript">
var primaryStatGridCollect = null;
function initCurrentYearCollectGridCollect(tableTitle,printBtn){
	var context = {};
	var columns = [		
			        {name:"columnName",caption:"层级",width:100,align:'center',mode:"string"},
			        {name:"collectCount",caption:"累计建账数",width:50,align:'center',mode:"string"},
			        {name:"doneCount",caption:"累计办结数",width:50,align:'center',mode:"string"},
					{name:"rate",caption:"办结率",width:50,align:'center',mode:"string"}
		   		   ];	
	primaryStatGridCollect = new SigmaReport("gridboxCollect",context,columns, null,null,tableTitle,printBtn);
	return primaryStatGridCollect;
}
</script>


