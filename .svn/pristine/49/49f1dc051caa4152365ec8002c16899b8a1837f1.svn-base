<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="tabs" style="float: left; width: 62%;">
	<ul>
	<li><a href="#publicNoticeDivSend">按层级发送</a></li>
	<li><a href="#publicNoticeRoleSend">按岗位发送</a></li>
	</ul>
	<div id="publicNoticeRoleSend">
   		<div class="ui-corner-all" id="nav">
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入岗位名称" id="searchNoticeRoleText" maxlength="18" class="searchtxt" style="width: 180px;"
						onblur="value=(this.value=='')?'请输入岗位名称':this.value;" onfocus="value=(this.value=='请输入岗位名称')?'':this.value;" />
					<input type="button" id="refreshsearchNoticeRoleTextKey" class="ui-icon ui-icon-refresh searchbtnico">
				</div>
			</div>
			<a href="javascript:;" id="fastsearchNoticeRoleTextButton"><span>搜索</span></a>
			<a href="javascript:;" id="refreshRoleTextButton"><span>刷新</span></a>
		</div>
   		<table id="publicNoticeRoleTable"></table>
   		<div id="publicNoticeRoleTablePager"></div>
    </div>
	<div id="publicNoticeDivSend" style="height: 370px;">
		<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
		<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
			<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
		</s:action>
		<div class="center-left" style="margin-top: 32px;">
			<div id="refreshOrgTreeDiv">
				<div class="ui-widget">
					<input id="org-tree-autocomplete" type="text" value=""/>
					<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
				</div>
			</div>
			<div class="orgTree">
				<div id="orgTree" style="height: 340px; overflow: auto;"></div>
			</div>
		</div>
		<div class="center-right">
			<div id="tabs_div_org">
				<ul><li><a href="#publicNoticeORG_XZ">行政部门</a></li><li><a href="#publicNoticeORG_ZN">职能部门</a></li></ul>
				<div id="publicNoticeORG_XZ" style="height: 330px; overflow: hidden;">
					<table id="publicNoticeORG_XZTable"></table>
				</div>
				<div id="publicNoticeORG_ZN" style="height: 330px; overflow: hidden;">
					<table id="publicNoticeORG_ZNTable"></table>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="hasSelected_tabs" style="width: 36%; float: right;">
	<ul><li><a href="#publicNoticeAlreadySelect">已选接收对象</a></li></ul>
	<div id="publicNoticeAlreadySelect" style="height: 370px; overflow-y: auto;"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="useInLevel" domainName="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" />
var tree;
function doSear_(p1){
	$("#publicNoticeRoleTable").setGridParam({
 		url:'${path}/sysadmin/searchRoleManage/searchRoles.action',
		datatype: "json",
		page:1
	});
	var	postData = {"role.roleName": p1 === true ? "" : $("#searchNoticeRoleText").val(), "role.useInLevel.id" : ""}
	$("#publicNoticeRoleTable").setPostData(postData);
	$("#publicNoticeRoleTable").trigger("reloadGrid");
}
function addSelectObject(key, value, type, pStr){
	var _id = "SELECT_DIV_"+type+"_"+key, pStr = pStr == null ? '' : pStr;
	var _hiddenName = 'notice_role_hidden';
	_hiddenName = type == 'Z' ? 'notice_ZN_hidden' : (type == 'X' ? 'notice_XZ_hidden' : _hiddenName);
	if(!document.getElementById(_id)){
		var str = "<div id='"+_id+"'><input type='hidden' name='"+_hiddenName+"' title='"+ pStr + value +"' value='"+key+"'/><div name='publicNotice_div_content_sendObject' style='float: left; width: 200px; margin-left: 3px; margin-bottom: 5px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;'>" + pStr + value + "</div><div style='float: right; margin-right: 5px;'><a href='###' onclick='delSelectObject(\""+key+"\", \""+type+"\")'>删除</a></div></div>";
		$("#publicNoticeAlreadySelect").append(str);
	}
}
function delSelectObject(key, type, isNeed){
	if(!isNeed && type == 'R'){
		$("#publicNoticeRoleTable").setSelection(key, false);
	}
	if(!isNeed && type == 'Z'){
		$("#publicNoticeORG_ZNTable").setSelection(key, false);
	}
	if(!isNeed && type == 'X'){
		$("#publicNoticeORG_XZTable").setSelection(key, false);
	}
	$("#SELECT_DIV_"+type+"_"+key+"").remove();
}

$(function (){
	$("#tabs").tabs();
	$("#hasSelected_tabs").tabs();
	$("#tabs_div_org").tabs();
	$("#refreshsearchNoticeRoleTextKey").click(function(){
		$("#searchNoticeRoleText").val("请输入岗位名称");
	});
	$("#fastsearchNoticeRoleTextButton").click(function(){
		if($("#searchNoticeRoleText").val() == '请输入岗位名称'){
			$("#searchNoticeRoleText").focus();
			return;
		}
		if($("#searchNoticeRoleText").val() != ''){
			doSear_();
		}
	});
	$("#refreshRoleTextButton").click(function(){
		$("#searchNoticeRoleText").val("请输入岗位名称");
		doSear_(true);
	});
	$("#publicNoticeRoleTable").jqGrid({
	   	url:'${path}/sysadmin/roleManage/roleList.action',
		datatype: "json",
		colNames:['id','岗位名称','应用层级','岗位描述'],
		height: 276,
		jsonReader:{
			repeatitems:false,
			id:"0"
		},
	    multiselect: true,
	    rowNum: 10,
		viewrecords:true,
	    pager: "#publicNoticeRoleTablePager",
	    loadComplete: function (){
	    	$("input[name='notice_role_hidden']").each(function (){
	    		$("#publicNoticeRoleTable").setSelection($(this).val(), true);
	    	});
	    },
	    onSelectRow: function (rowId, status){
	    	var role = $("#publicNoticeRoleTable").getRowData(rowId);
	    	if(status == true || status == "true"){
	    		addSelectObject(rowId, role.roleName, 'R', '[岗位] ');
	    	}else{
	    		delSelectObject(rowId, 'R', true);
	    	}
	    },
	    onSelectAll: function(aRowids,status){
	    	if(aRowids == null || aRowids == ''){
	    		return;
	    	}
	    	var rowIds = aRowids.toString().split(',');
	    	if(status == true || status == "true"){
	    		for(var i=0; i< rowIds.length; i++){
	    			addSelectObject(rowIds[i], $("#publicNoticeRoleTable").getRowData(rowIds[i]).roleName, 'R', '[岗位] ');
	    		}
	    	}else{
	    		for(var i=0; i< rowIds.length; i++){
	    			delSelectObject(rowIds[i], 'R', true);
	    		}
	    	}
	    },
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true},
	   		{name:'roleName',width: 160,sortable:false,index:'roleName'},
	   		{name:'useInLevel',width: 90,index:'useInLevel',sortable:false,formatter: useInLevelFormatter},
	   		{name:'description',width: 158,sortable:false}
	   	]
	});
	//职能部门、行政部门
	$("#publicNoticeORG_ZNTable").jqGrid({
 		url:'${path}/sysadmin/orgManage/getOrgZN.action',
		height: 300,
		rowNum: 1000,
		jsonReader:{
			repeatitems:false,
			id:"0"
		},
	    multiselect: true,
	    rowNum: 1000,
	    loadComplete: function (){
	    	$("input[name='notice_ZN_hidden']").each(function (){
	    		$("#publicNoticeORG_ZNTable").setSelection($(this).val(), true);
	    	});
	    },
	    onSelectRow: function (rowId, status){
	    	var role = $("#publicNoticeORG_ZNTable").getRowData(rowId);
	    	if(status == true || status == "true"){
	    		addSelectObject(rowId, role.orgName, 'Z', $.getSelectedNode(tree).text + ' > ');
	    	}else{
	    		delSelectObject(rowId, 'Z', true);
	    	}
	    },
	    onSelectAll: function(aRowids,status){
	    	if(aRowids == null || aRowids == ''){
	    		return;
	    	}
	    	var rowIds = aRowids.toString().split(',');
	    	if(status == true || status == "true"){
	    		for(var i=0; i< rowIds.length; i++){
	    			addSelectObject(rowIds[i], $("#publicNoticeORG_ZNTable").getRowData(rowIds[i]).orgName, 'Z', $.getSelectedNode(tree).text + ' > ');
	    		}
	    	}else{
	    		for(var i=0; i< rowIds.length; i++){
	    			delSelectObject(rowIds[i], 'Z', true);
	    		}
	    	}
	    },
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true},
	   		{name:'orgName', label: '名称',width: 252,sortable:false}
	   	]
	});
	var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree({url: '/sysadmin/orgManage/firstLoadExtTreeData.action?orgType=0', nodeUrl : '/sysadmin/orgManage/ajaxOrgsForExtTree.action?orgType=0'});
	}
	
	function reloadZNGrid(key){
		$("#publicNoticeORG_ZNTable").setGridParam({
	 		url:'${path}/sysadmin/orgManage/getOrgZN.action',
			datatype: "json",
			page:1
		});
		$("#publicNoticeORG_ZNTable").setPostData({'organization.id': key});
		$("#publicNoticeORG_ZNTable").trigger("reloadGrid");
	}
	
	function reloadXZGrid(key){
		$("#publicNoticeORG_XZTable").setGridParam({
	 		url:'${path}/sysadmin/orgManage/getOrgXZ.action',
			datatype: "json",
			page:1
		});
		$("#publicNoticeORG_XZTable").setPostData({'organization.id': key});
		$("#publicNoticeORG_XZTable").trigger("reloadGrid");
	}
	
	$.addClick(tree, function (node){
		var nodeId = node.attributes.id;
		$.post(PATH+"/sysadmin/orgManage/ajaxOrganization.action", {"organization.id": nodeId},function(data){
			$("#org-tree-autocomplete").val(data.orgName);
			reloadZNGrid(data.id);
			reloadXZGrid(data.id);
		});
	});
	
	$("#publicNoticeORG_XZTable").jqGrid({
	   	url: '${path}/sysadmin/orgManage/getOrgXZ.action',
		datatype: "json",
		jsonReader:{
			repeatitems:false,
			id:"0"
		},
		height: 310,
		rowNum: 1000,
	    multiselect: true,
	    loadComplete: function (){
	    	$("input[name='notice_XZ_hidden']").each(function (){
	    		$("#publicNoticeORG_XZTable").setSelection($(this).val(), true);
	    	});
	    },
	    onSelectRow: function (rowId, status){
	    	var role = $("#publicNoticeORG_XZTable").getRowData(rowId);
	    	if(status == true || status == "true"){
	    		addSelectObject(role.simplePinyin, role.displayName, 'X', $.getSelectedNode(tree).text + '下所有 ');
	    	}else{
	    		delSelectObject(role.simplePinyin, 'X', true);
	    	}
	    },
	    onSelectAll: function(aRowids,status){
	    	if(aRowids == null || aRowids == ''){
	    		return;
	    	}
	    	var rowIds = aRowids.toString().split(',');
	    	if(status == true || status == "true"){
	    		for(var i=0; i< rowIds.length; i++){
	    			var _role = $("#publicNoticeORG_XZTable").getRowData(rowIds[i]);
	    			addSelectObject(_role.simplePinyin, _role.displayName, 'X', $.getSelectedNode(tree).text + '下所有 ');
	    		}
	    	}else{
	    		for(var i=0; i< rowIds.length; i++){
	    			var _role = $("#publicNoticeORG_XZTable").getRowData(rowIds[i]);
	    			delSelectObject(_role.simplePinyin, 'X', true);
	    		}
	    	}
	    },
		sortname:'id',
		sortorder:'asc',
	   	colModel:[
	   		{name:'displayName', index: 'displayName',width: 252, label: '名称'},
	   		{name:'simplePinyin', index: 'id',key: true, width: 0, hidden:true}
	   	]
	});
	//组织机构代码
	function stringFormatter(str){
		if(str==undefined){
			return "";
		}
		return str;
	}
	$("#org-tree-autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":function(){return request.term;}},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+stringFormatter(item.contactWay),
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	
	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	    $("#org-tree-autocomplete").val("");
	});
	
	//加载数据
	var _data = $('#publicNoticeRole').val().split(',');
	var _dataContent = $('#publicNoticeRole').attr('title').split(';');
	for(var i=0; i < _data.length - 1; i++){
		addSelectObject(_data[i], _dataContent[i], 'R');
	}
	_data = $('#publicNoticeOrgZN').val().split(',');
	_dataContent = $('#publicNoticeOrgZN').attr('title').split(';');
	for(var i=0; i < _data.length - 1; i++){
		addSelectObject(_data[i], _dataContent[i], 'Z');
	}
	_data = $('#publicNoticeOrgXZ').val().split(',');
	_dataContent = $('#publicNoticeOrgXZ').attr('title').split(';');
	for(var i=0; i < _data.length - 1; i++){
		addSelectObject(_data[i], _dataContent[i], 'X');
	}
});
</script>