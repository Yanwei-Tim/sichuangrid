<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
	.todo{width:20px;height:22px;display:block;margin:0 auto;vertical-align:top;background:url(/resource/images/icon_tbmk.png) no-repeat;}
	.todo.yellow-todo{background-position:0 -27px;}
	.todo.red-todo{background-position:0 -54px;}
	.todo.blue-todo{background-position:0 0px;}
	.event-expedited .expedited{width:14px;height:22px;display:block;margin:0 auto;background:url(/resource/images/icon_tbmk.png) no-repeat -2px -90px;}
</style>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addFunctionOrgTimeLimitStandard">
			<a id="add" href="javascript:void(0)"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateFunctionOrgTimeLimitStandard">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteFunctionOrgTimeLimitStandard">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="standardList"> </table>
		<div id="standardListPager"></div>
	</div>
	<div id="issueDialog"></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="itemName" domainName="@com.tianque.domain.property.PropertyTypes@ITEM_NAME" />

function callback(){
    var dfop = {
    	userName:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>'
    }
    TQ.functionOrgEimeLimitStandardList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/issue/issueAccessConfig/functionOrgTimeLimitStandardList.js',
    callback: callback
})
</script>
