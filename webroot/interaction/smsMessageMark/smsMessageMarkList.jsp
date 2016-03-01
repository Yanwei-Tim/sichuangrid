<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<div style="height:100%;overflow:auto;overflow-x:hidden;position:relative;">
	<div class="content" >
		<div class="ui-corner-all" id="nav">
			<%-- <pop:JugePermissionTag ename="searchMyContact">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag> 
			<span class="lineBetween "></span>
			--%>
			<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
			<pop:JugePermissionTag ename="addSmsMessageMark">
				<a id="add" href="javascript:;"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateSmsMessageMark">
				<a id="update"  href="javascript:void(0)"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteSmsMessageMark">
				<a id="delete"  href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
			</s:if>
			<pop:JugePermissionTag ename="viewSmsMessageMark">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
			<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="width:100%;" id="grid_content" >
			<table id="smsMessageMarkList"></table>
			<div id="smsMessageMarkListPager"></div>
		</div>
		<div id="searchDialog"></div>
		<div id="smsMessageMarkMaintanceDialog"></div>

	</div>
</div>

<script type="text/javascript">

function callback(){
    var dfop = {
    		viewSmsMessageMark:'<pop:JugePermissionTag ename="viewSmsMessageMark">true</pop:JugePermissionTag>'
    }
    TQ.smsMessageMarkList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/smsMessageMark/smsMessageMarkList.js',
    callback: callback
}) 
</script>
