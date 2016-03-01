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
		        {name:"columnName",caption:"单位",width:80,align:'center',mode:"string"},
		        {name:"sums",caption:"合计",width:50,align:'center',mode:"string"},
		        {name:"jan",caption:"1月建账数",width:50,align:'center',mode:"string"},
				{name:"feb",caption:"2月建账数",width:40,align:'center',mode:"string"},	
   				{name:"mar",caption:"3月建账数",width:40,align:'center',mode:"string"},	
   				{name:"apr",caption:"4月建账数",width:40,align:'center',mode:"string"},	
   				{name:"may",caption:"5月建账数",width:40,align:'center',mode:"string"},	
   				{name:"jun",caption:"6月建账数",width:50,align:'center',mode:"string"},	
   				{name:"jul",caption:"7月建账数",width:40,align:'center',mode:"string"},	
   				{name:"aug",caption:"8月建账数",width:40,align:'center',mode:"string"},	
   				{name:"sep",caption:"9月建账数",width:40,align:'center',mode:"string"},	
   				{name:"oct",caption:"10月建账数",width:40,align:'center',mode:"string"},	
   				{name:"nov",caption:"11月建账数",width:40,align:'center',mode:"string"},	
   				{name:"dec",caption:"12月建账数",width:40,align:'center',mode:"string"}	
	   		   ];	
	primaryStatGridCollect = new SigmaReport("gridboxCollect",context,columns, null,null,tableTitle,printBtn);
	return primaryStatGridCollect;
}
</script>


