<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getEvaluateAllYears" var="getEvaluateAllYears" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<s:bean name="java.util.Date" id="date"></s:bean>
<s:date var="formateDate" nice="false" name="#date" format="yyyy" />
<div class="ui-corner-all" id="nav">
	<div class="btnbanner btnbannerData">
		<input id="checkValue" type="hidden" value="" />
		<select name="evaluate.year" id="getEvaluateYear">
			<s:iterator value="#getEvaluateAllYears.evaluateYears">
 				 <option value='<s:property />'><s:property /></option>
			</s:iterator>
			<option value="">历年所有</option>
		</select>
		<pop:JugePermissionTag ename="searchEvaluate">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
	</pop:JugePermissionTag>
       </div>
	<span class="lineBetween "></span>
	<pop:JugePermissionTag ename="addEvaluateRules">
		<a id="add" href="javascript:;"><span>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="updateEvaluateRules">
		<a id="update" href="javascript:void(0)"><span>修改</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteEvaluateRules">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="viewEvaluate">
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="activeEvaluate">
		<a id="active" href="javascript:void(0)"><span>启用</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="unactiveEvaluate">
		<a id="unactive" href="javascript:void(0)"><span>停用</span></a>
	</pop:JugePermissionTag>
	<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	
</div>
<div style="clear: both;"></div>
<form method="post" action="${path}/evaluate/evaluateManage/deleteMultiEvaluateByIds.action" id="deleteForm">
	<div style="width: 100%;">
		<table id="evaluateList"> </table>
		<div id="evaluateListPager"></div>
	</div>
</form>
<div id="evaluateDialog"></div>
<div id="rulesDialog"></div>
<script type="text/javascript">
function updateFun(){
	var rowId = $("#evaluateList").jqGrid('getGridParam','selrow');
	if(rowId == null){
		$.messageBox({level: 'warn',message: "请选择一条记录"});
		return;
	}
	var rowData = $("#evaluateList").getRowData(rowId);
	if(rowId==undefined || rowData.state=='已启用'){
		$.messageBox({level:'warn',message:'已启用标准不能修改！'});
		return;
	}
	$("#evaluateDialog").createDialog({
		width: 900,
		height: 600,
		title:"修改考核标准",
		url:PATH+"/evaluate/updateStandardEvaluateDlg.jsp?evaluateId="+rowId+"&title="+encodeURIComponent(rowData.title),
		buttons: {
			"保存" : function(event){
				showValidateMessage = true;
				$("#standForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function callback(){
    var dfop = {
    	organizationId:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>',
    	evaluateState:'<s:property value="@com.tianque.service.state.EvaluateState@ACTIVE"/>',
    	viewEvaluate:'<pop:JugePermissionTag ename="viewEvaluate">true</pop:JugePermissionTag>'
    }
    TQ.standardEvaluateList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/standardEvaluateList.js',
    callback: callback
})
</script>