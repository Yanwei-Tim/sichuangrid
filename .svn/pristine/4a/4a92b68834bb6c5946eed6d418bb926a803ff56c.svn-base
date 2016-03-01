<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<div style="height:100%;overflow:auto;overflow-x:hidden;position:relative;">
	<div class="content" >
		<div class="ui-corner-all" id="nav">
			<pop:JugePermissionTag ename="searchMyContact">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
			<span class="lineBetween "></span>
			
			<pop:JugePermissionTag ename="addMyContact">
				<a id="add" href="javascript:;"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateMyContact">
				<a id="update"  href="javascript:void(0)"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewMyContact">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteMyContact">
				<a id="delete"  href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
				<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
			<pop:JugePermissionTag ename="ctdMyContact">
				<a id="ctd" href="javascript:void(0)"><span><strong class="ui-ico-tel"></strong>电话</span></a>
			</pop:JugePermissionTag>
		</div>
		<div style="width:100%;" id="grid_content" >
			<table id="myContactList"></table>
			<div id="myContactListPager"></div>
		</div>
		<div id="searchDialog"></div>
		<div id="myContactMaintanceDialog"></div>

	</div>
</div>

<script type="text/javascript">

function callback(){
    var dfop = {
    		viewMyContact:'<pop:JugePermissionTag ename="viewMyContact">true</pop:JugePermissionTag>'
    }
    TQ.myContactList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/contact/myContact/myContactList.js',
    callback: callback
}) 
</script>
