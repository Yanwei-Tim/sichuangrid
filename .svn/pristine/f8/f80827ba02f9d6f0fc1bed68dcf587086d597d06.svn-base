<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
ul#selectzones {margin: 0; padding: 0;}
ul#selectzone {margin: 0; padding: 0;}
ul#selectzones li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
ul#selectzones span.ui-icon {float: left; margin: 0 4px;}
</style>
<div id="dialog-form" title="用户维护" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
			<input id="mode"	type="hidden" name="mode" value="${mode}" />
			<input id="organizationId"	type="hidden" name="user.organization.id" value="${organizationId}" />
			<input id="userId"	type="hidden" name="user.id" value="${user.id}" />
			<input id="isSerchType"	type="hidden" name="user.isSerchType" value="true" />
<%--             <input id="isLock"	type="hidden" name="user.lock" value="${user.lock}" /> --%>
<%--             <input type="hidden" name="searchLockStatus" id="searchLockStatus" value="${searchLockStatus}"/> --%>
			<div  class="grid_4 lable-right" >
				<label class="form-lbl">用户名： </label>
			</div>
			<div class="grid_4" id="userDiv">
			    	<input type="text" name="user.userName" id="userName" maxlength="32" value=""
	  				class="form-txt" title="用户名：用户登录系统的帐号！"/>
	  		</div>
	  		<div  class="grid_3 lable-right">
	  			<label class="form-lbl">姓名：</label>
	  		</div>
			<div class="grid_4">
			    <input type="text" name="user.name" id="name" maxlength="20" title="姓名：用户的真实姓名！"
	  				value=""
	  				class="form-txt" />
    		</div>
    		<div class="grid_4 lable-right">
			<label class="form-lbl">是否锁定：</label>
			</div>
			<div class="grid_2">
			    <select name="user.searchLockVal" id="ignoreIsShutDownOrNot">
			       <option value="0"></option>
			       <option value="1">否</option>
			       <option value="2">是</option>
			    </select>
			</div>
			<div class='clearLine'></div>
			<div class="grid_6 lable-right">
				<label class="form-lbl">是否开启GPS定位：</label>
			</div>
			<div class="grid_5">
			    <select name="user.gpsOrNot" id="usergpsOrNot">
			       <option value="0"></option>
			       <option value="1">否</option>
			       <option value="2">是</option>
			    </select>
			</div><div class="grid_6 lable-right">
				<label class="form-lbl">是否开启四支队伍：</label>
			</div>
			<div class="grid_5">
			    <select name="user.fourTeamsOrNot" id="userfourTeamsOrNot">
			       <option value="0"></option>
			       <option value="1">否</option>
			       <option value="2">是</option>
			    </select>
			</div>
		<div class='clearLine'></div>
			<div class="grid_6 lable-right">
				<label class="form-lbl">账号类型：</label>
			</div>
			<div class="grid_5">
			    <select name="user.accountType" id="useraccountType">
			       <option value="0"></option>
			       <option value="1">PC</option>
			       <option value="2">手机</option>
			    </select>
			</div>
			<div class="grid_6 lable-right">
				<label class="form-lbl">在线状态：</label>
			</div>
			<div class="grid_5">
			    <select name="user.onLineState" id="useronLineState">
			       <option value="0"></option>
			       <option value="1">不在线</option>
			       <option value="2">在线</option>
			    </select>
			</div>
			<div class="grid_4 lable-right">
			<label class="form-lbl">账号状态：</label>
			</div>
			<div class="grid_4">
			    <select name="user.state" id="userstate">
			       <option value="0"></option>
			       <option value="1">待激活</option>
			       <option value="2">正常</option>
			        <option value="3">已停用</option>
			    </select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">最后登录日期：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="user.lastLoginTime" id="lastLoginDate" class="form-txt"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">查询日期：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="user.timeforQuery" id="timeforQuery" class="form-txt"/>
			</div>
			<div class='clearLine'></div>
			<div id="tabs">
				<ul>
					<li><a href="#roleList">选择岗位</a></li>
				</ul>
				<div id="roleList" style="height:170px;width:99%;">
				   <div>
				   	 <table id="roleTable" ></table>
				   </div>
				</div>
					<select id="roleIds" name="roleIds" multiple="multiple" style="display:none"></select>
		    </div>
	   </form>
</div>
<script type="text/javascript">
var scoreResult=0;
$(document).ready(function(){
	$(".dialogtip").inputtip();
	$(function() {
		$( "#tabs" ).tabs();
	});
  $("#roleTable").jqGrid({
   	 url: "${path}/sysadmin/userManage/prepareRoleTableForUserList.action?mode=${mode}",
	 datatype: "json",
	 postData: {
	        "organizationId":  function(){
	            return "${organizationId}";
	        },
	        "user.id" : function(){
		        if($("#userList").getGridParam("selrow")==null)
			        return 0;
	            return $("#userList").getGridParam("selrow");
	        }
        },
	 colModel:[
	          {name:"id",index:"id",hidden:true},
	   	      {name:"roleName",index:'roleName',label:'岗位名称',sortable:false},
	   	      {name:"useInLevel.displayName",index:"displayName",label:'所属层级',sortable:false}
			],
	multiselect:true,
	loadComplete:selectRoles,
	sortname:'roleName',
	sortorder:'desc',
	rowNum:-1,
	viewrecords:true,
	width: 500,
	height: 140,
	jsonReader:{
		repeatitems:false,
		id:"0"
	}
   });
  $('#timeforQuery').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
      maxDate:'+0d'}
			);
	$('#lastLoginDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
      maxDate:'+0d'});
	$("#maintainForm").formValidate({
		promptPosition: "bottomRight",
		submitHandler: function(form){
			var params = $(form).serialize();
			var roleIds = transformVal($('#roleIds').val());
			var name = transformVal($('#name').val());
			var userName = transformVal($('#userName').val());
// 			var searchLockStatus = transformVal($('#searchLockStatus').val());
// 			var ignoreIsShutDownOrNot = transformVal($('#ignoreIsShutDownOrNot option:selected').val());
			var lastLoginTime = transformVal($('#lastLoginDate').val());
			var timeforQuery = transformVal($('#timeforQuery').val());
			var usergpsOrNot = transformVal($('#usergpsOrNot').val());
			var userfourTeamsOrNot = transformVal($('#userfourTeamsOrNot').val());
			var useraccountType = transformVal($('#useraccountType').val());
			var useronLineState = transformVal($('#useronLineState').val());
			var userstate = transformVal($('#userstate').val());
			jQuery("#userList").setPostData({});
			var url = jQuery("#userList").getGridParam("url");
			jQuery("#userList").setGridParam({"url":"${path}/sysadmin/searchUserListData/searchUserList.action?"+params+"&organizationId="+currentOrgId});
			$("#userList").trigger("reloadGrid");
			jQuery("#userList").setGridParam({
				"url":url,
				"page":1
				});
			jQuery("#userList").setPostData({
		        "user.id" : function(){
			        if($("#user_autocomplete").attr("userId")){
			        	return $("#user_autocomplete").attr("userId");
			        }else{
				        return -1;
			        }
		        },
				"user.lock":  function(){
		            return !isActivedUsers();
		        },
		        //'searchLockStatus':function(){return searchLockStatus;},
		        'user.userName':function(){return userName;},
		        'user.name':function(){return name;},
		        'user.ignoreIsShutDownOrNot':function(){
		        	return typeof ignoreIsShutDownOrNot == 'undefined' ? 0 : ignoreIsShutDownOrNot ;
		        },
		        'user.lastLoginTime':function(){return lastLoginTime;},
		        'user.timeforQuery':function(){return timeforQuery;},
		        "organizationId":function(){return currentOrgId; },
		        "roleIdsStr":function(){return  roleIds; },
		        "user.gpsOrNot":function(){return  usergpsOrNot; },
		        "user.fourTeamsOrNot":function(){return  userfourTeamsOrNot; },
		        "user.accountType":function(){return  useraccountType; },
		        "user.onLineState":function(){return  useronLineState; },
		        "user.state":function(){return  userstate; },
		        searchChildOrg : true
	       	});
		},
		rules:{},
		messages:{}
	});

});

function transformVal(dest){
	if('null' == dest) return '';
	return typeof dest == 'undefined' ? '' : dest;
}


function addHiddenRoleHtml(){
	 var rootRoleSelect=$("#roleIds");
	 rootRoleSelect.empty();
   	 var selectRoleIds=$("#roleTable").getGridParam('selarrrow');
   	 selectRoleIds=getCorrectSelectedRoleIds(selectRoleIds);
   	 for (var index=0;index<selectRoleIds.length;index++){
   	   	 var rowid=selectRoleIds[index];
   		$("<option>").attr("id","role_"+rowid).val(rowid).attr("selected",true).appendTo(rootRoleSelect);
   	 }
}

function getCorrectSelectedRoleIds(gridSelectedRowIds){
	var result=new Array();
  	 for (var index=0;index<gridSelectedRowIds.length;index++){
   	   	 var rowid=gridSelectedRowIds[index];
   	   	 if (rowid!=null && typeof(rowid)!="undefined" && rowid!="" && !containedRole(result,rowid)){
   	   	   result[result.length]=rowid;
   	   	 }
   	 }
   	 return result;
}

function containedRole(selectedRoles,role){
	for (var index=0;index<selectedRoles.length;index++){
  	   	 var rowid=selectedRoles[index];
  	   	 if (rowid==role) return true;
	}
	return false;
}

function selectRole(rowId){
     addHiddenRoleHtml();
}

function doNothing(){}


function selectRoles(){
	<s:iterator id="userRole" value="user.roles" status="sta">
	$("#roleTable").setSelection(${userRole.id},true);
	</s:iterator>
	addHiddenRoleHtml();
	$("#roleTable").setGridParam({onSelectRow:selectRole});
	$("#roleTable").setGridParam({onSelectAll:selectRole});
}

</script>