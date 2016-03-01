<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-left">
	<div>
		<div class="ui-widget">
			<input id="org_autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<div class="ui-corner-all" id="nav">
			<!-- <input type="text" id="user_autocomplete"  class="show_search_btn" value="快速检索..." /> -->
			<select class="basic-input" id="isLock" name="user.isLock" style="display:none;">
 					<option value="false" selected="selected">活动的</option>
 					<option value="true">锁定的</option>
 					<option value="all">全部</option>
			</select>
			<pop:JugePermissionTag ename="searchUser">
				<a id="searchUser" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		 	<a id="sendInvite" href="javascript:void(0);"><span>发起邀请</span></a>
		  	<span>会议类型</span>
		  	<select id="meetingType">
				<option selected value=2>视频会议</option>
				<option value=1>语音会议</option>
			</select> 
			<div style="display:none;" >
				<input id=conventionersAcc class=req type=text value=""/>
				<input id=conventionersName class=req type=text value=""/>
				<input id=currentUserAcc class=req type=text value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>@hztianque.com">
				<input id=currentUserName class=req type=text value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>">
				<input id=ppMeetKey size=32 type=text value="<pop:GetGlobalSettingValueTag key="@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_PKI"/>"">
				<input id=ppMeetCode class=req size=100 type=text value="<pop:GetGlobalSettingValueTag key="@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_KEY"/>">
			</div>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 50%;float: left">
			<table id="userList"> </table>
			<div id="userListPager"></div>
		</div>
		<div style="float: left">
			已选择用户：
			<div>
				<ul id="selectedUser" >
					
				</ul>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>

<div id="userMaintanceDialog"></div>
<div id="noticeDialog"></div>
<script type="text/javascript">
$.showTxtValue()

var dialogWidth=580;
var dialogHeight=540;
var currentOrgId;
var tree;
var arr = new Array();

$(document).ready(function(){
	$("#sendInvite").click(metting);
	var centerHeight=$("div.ui-layout-center").outerHeight();
	$(".orgTree").height(centerHeight-70);
	
	currentOrgId="";
	$("#org_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":request.term},
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
			$("#user_autocomplete").removeAttr("userId");
			$("#user_autocomplete").val("");
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	var clearUserUserAutocomplete=true;

	var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}
	$.addClick(tree,afterChangNode);

	$("#isLock").change(function(event){
		lockUserButtonText($(event.srcElement).val());
		$("#user_autocomplete").empty();
		findUserByOrgId();
		setUserOperateButton();
	});

	$("#searchUser").click(function(event){
		$("#userMaintanceDialog").createDialog({
			width: 700,
			height: 300,
			title:'查询用户',
			url:'${path}/sysadmin/userManage/searchUsersDlg.jsp?orgId='+currentOrgId+'&isLock='+$('#isLock').val(),
			buttons: {
		   		"查询" : function(event){
		   			$("#maintainForm").submit();
		   			$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#user_autocomplete").userAutocomplete({
		postData: {
			searchChildOrg : false,
			searchLockStatus : function(){if(isActivedUsers()=="unlocked"){return "unlocked";}else if(isActivedUsers()=="locked"){return "locked";}return "all";},
			organizationId : function(){return currentOrgId;}
		},
		select : function(event, ui){
			$("#user_autocomplete").attr("userId",ui.item.id);
			clearUserUserAutocomplete=false;
			$("#userList").trigger("reloadGrid");
		}
	});

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	});

	$('#refreshOrgTree li').hover(
			function() { $(this).addClass('ui-state-hover'); },
			function() { $(this).removeClass('ui-state-hover'); }
	);

	$("#refresh").click(function(){
		$("#user_autocomplete").removeAttr("userId");
		refresh();
	});
	
	$("#userList").jqGridFunction({
		url:'${path}/sysadmin/userManage/userList.action',
		datatype: "local",
		postData: {
	        "searchLockStatus":  function(){
	            return isActivedUsers();
	        },
	        "organizationId":function(){
			    return currentOrgId;
	        },
	        "user.id" : function(){
		        if($("#user_autocomplete").attr("userId")){
		        	return $("#user_autocomplete").attr("userId");
		        }else{
			        return -1;
		        }
	        },
	        searchChildOrg : false
        },
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:"userName",index:'userName',sortable:false,label:'用户名',width:100},
      		{name:"name",index:"name",sortable:false,label:'用户姓名',width:100},
	        {name:'workCompany',label:'工作单位或就读学校',sortable:false,width:180,hidden:true},
	        {name:'workPhone',label:'工作电话',sortable:false,align:'center',width:140,hidden:true},
	        {name:'mobile',label:'手机',sortable:false,align:'center',width:100},
	        {name:'lastLoginTime',label:'最后登录时间',width:140,sortable:false,hidden:true},
	        {name:'admin',label:'是否超级管理员',sortable:false,formatter:isAdminFormatter,align:'center',width:100,hidden:true},
	        {name:"roles_name",index:"roles",label:'所在岗位',sortable:false,formatter:getRoleNames,width:100,hidden:true},
	        {name:"roles_id",index:"roles",label:'所在岗位',sortable:false,hidden:true,formatter:getRoleIds},
	        {name:"roles",index:"roles",label:'所在岗位',sortable:false,hidden:true,align:'center',hidden:true},
	        {name:"shutDown",index:"shutDown",label:'是否启用',sortable:false,formatter:isShutDown,align:'center',width:90,hidden:true},
	        {name:"lock",index:"lock",label:'是否锁定',sortable:false,hidden:true,hidedlg:true,width:90}
	        <s:if test="@com.tianque.component.ThreadVariable@getUser().isAdmin()">
	        ,
	        {name:'',label:'同步用户',formatter: asyncoUserFormmater,align:'center'}
	        </s:if>
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){loopMaintainArr(aRowids,status);},
		showColModelButton:false,
		loadComplete: afterLoad,
		onSelectRow:function(rowId,status){maintainArr(rowId,status);}
	});
	function show(){
		$.each(arr, function(key, val) {
		});
	}
	function removeUser(id){
		var existFlag = false;
		var index = -1;
		$.each(arr, function(key, val) {
			if(val.id == id){
				existFlag = true;
				index = key;
				return false;
			}
		});
		if(existFlag){
			arr.splice(index,1);
		}
		var str = "";
		var strName = "";
		$("#selectedUser").empty();
		for(var i=0;i<arr.length;i++){
			var rowData = arr[i];
			str += rowData.userName;
			str += '@hztianque.com';
			str +=',';
			strName += rowData.name;
			strName +=',';
			$("#selectedUser").append("<li>"+rowData.name+"<img src='${resource_path}/resource/system/images/directorySettingManagement/delete.png' id="+rowData.id+"></li>");
			
		}
		str = str.substring(0,str.length-1);
		strName = strName.substring(0,strName.length-1);
		$("#conventionersAcc").val(str);
		$("#conventionersName").val(strName);
		//$("#selectedUser").append(strName);
		$("li img").click(function(){removeUser(this.id);});
		$("#userList").setSelection(id, false);
	}
	function loopMaintainArr(aRowids,status){
		for(var i=0;i<aRowids.length;i++){
			maintainArr(aRowids[i],status);
		}
	}
	function maintainArr(rowId,status){
		var user = $("#userList").getRowData(rowId);
		//如果选上，如果不存在，则添加；如果取消，如果存在则删除
		if(status){
			var existFlag = false;
			$.each(arr, function(key, val) {
				if(val.id == rowId){
					existFlag = true;
					return false;
				}
			});
			if(!existFlag){
				arr.push(user);
			}
		}else{
			var existFlag = false;
			var index = -1;
			$.each(arr, function(key, val) {
				if(val.id == rowId){
					existFlag = true;
					index = key;
					return false;
				}
			});
			if(existFlag){
				arr.splice(index,1);
			}
			//show();
		}
		/*
		var selectedCounts = getActualjqGridMultiSelectCount("userList");
		var count = $("#userList").jqGrid("getGridParam","records");
		var user =  $("#userList").getRowData($("#userList").getSelectedRowId());
		var arrCount = arr. push(user);
		var rowIds = getActualjqGridMultiSelectIds("userList");
		*/
		var str = "";
		var strName = "";
		$("#selectedUser").empty();
		for(var i=0;i<arr.length;i++){
			var rowData = arr[i];
			str += rowData.userName;
			str += '@hztianque.com';
			str +=',';
			strName += rowData.name;
			strName +=',';
			$("#selectedUser").append("<li>"+rowData.name+"<img src='${resource_path}/resource/system/images/directorySettingManagement/delete.png' id="+rowData.id+"></li>");
			
		}
		str = str.substring(0,str.length-1);
		strName = strName.substring(0,strName.length-1);
		$("#conventionersAcc").val(str);
		$("#conventionersName").val(strName);
		//$("#selectedUser").append(strName);
		$("li img").click(function(){removeUser(this.id);});
	}
	function getSelectedIds(){
		var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function isAdminFormatter(el,options,rowData){
		if(rowData.admin){
	        return "是";
	    }
	    if(rowData["admin"]){
	    	return "是";
	    }
	    return "否";
    }
    function isShutDown(el,options,rowData){
        if(rowData.shutDown){
            return "是";
        }
        return "否";
    }
	function refresh(){
		$("#userList").setGridParam({
			url:"${path}/sysadmin/userManage/userList.action",
			datatype: "json",
			page:1
		});
		$("#userList").setPostData({
			"searchLockStatus":  function(){
                return isActivedUsers();
	        },
	        "organizationId":function(){
			    return currentOrgId;
	        },
	        "user.id" : function(){
		        if($("#user_autocomplete").attr("userId")){
		        	return $("#user_autocomplete").attr("userId");
		        }else{
			        return -1;
		        }
	        },
	        searchChildOrg : false
	   	});
		$("#userList").trigger("reloadGrid");
	}
	function afterChangNode(node) {
		var id = node.attributes.id;
		currentOrgId=id;
		clearUserUserAutocomplete=true;
        $("#org_autocomplete").val(node.attributes.text);
        if(clearUserUserAutocomplete){
            $("#user_autocomplete").removeAttr("userId");
           // $("#user_autocomplete").val("");
        }
        findUserByOrgId(id);
        setUserOperateButton();
    //	setUserOperateButton();
	}
});
<s:if test="@com.tianque.component.ThreadVariable@getUser().isAdmin()">
function asyncoUserFormmater(el,options,rowData){
    return "<button onclick='asyncoUser("+rowData["id"]+")'>同步</button>";
}
function asyncoUser(userid){
	$.ajax({
		url: '${path}/sysadmin/userManage/asyncoUser.action?user.id='+userid,
		success:function(data){
			if(data==true){
				$.messageBox({
				    message: "用户同步成功"
				});
			}
		}
	});
}
</s:if>
function isActivedUsers(){
   if($("#isLock").val() == "false"){return "unlocked";}
   if($("#isLock").val() == "true"){return "locked";}
   return "all";
}

function userLockOperate(){
	var selectedId = $("#userList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	var user =  $("#userList").getRowData(selectedId);
	var activedUsers = isActivedUsers();
	$.ajax({
		url: '${path}/sysadmin/userManage/lockOperate.action',
		data: {
	        "user.id": function(){
	            return user.id;
	        },
	        "searchLockStatus" : function(){
	            return user.lock;
	        }
        },
		success:function(data){
			if(data == true){
				$("#userList").delRowData(user.id);
				$.messageBox({
				    message: ((user.lock=="false") ? "成功锁定用户:"+ user.userName +"!" :  "成功解锁用户:"+ user.userName +"!")
				 });
				$("#refresh").click();
				return;
			}
          		$.messageBox({
				message:data,
				level: "error"
			});
    	}
	});
}


function findUserByOrgId(){
	//$("#userList").setGridParam({datatype:'json'});
	//$("#userList").trigger("reloadGrid");
	$("#refresh").click();
}

function setUserautocompleteLockstatus(lock){
	if (lock == "true"){
		$("#user_autocomplete").option( "searchLockStatus", "locked");
	}else{
		$("#user_autocomplete").option( "searchLockStatus", "unlocked");
	}
}

function lockUserButtonText(lock){
	if (lock=="true"){
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-chattr'/>解锁</span>");
	}else{
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-lock'/>锁定</span>");
	}
}

function afterLoad(){
	//setUserOperateButton();
	//$("#userList").resetSelection(); 
	//$("#userList").setSelection("1", true); 
	//$("#userList").jqGrid("setSelection",3); 
	$.each(arr, function(key, val) {
		$("#userList").setSelection(val.id, true);
	});
}

function setUserOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("userList");
	var count = $("#userList").jqGrid("getGridParam","records");
	var user =  $("#userList").getRowData($("#userList").getSelectedRowId());
	var selectedIds = getActualjqGridMultiSelectIds("userList");
	if(selectedCounts == count && selectedCounts!=0){
		jqGridMultiSelectState("userList", true);
	}else{
		jqGridMultiSelectState("userList", false);
	}
	useredOrStopUsedButtom(selectedIds);

}
function lockButtom(){
	$("#add").buttonDisable();
	$("#update").buttonDisable();
	$("#resetPwd").buttonDisable();
	$("#lockUser").buttonEnable();
	$("#lookUser").buttonEnable();
	$("#deleteUser").buttonDisable();
	if($("#userList").getSelectedRowId()){
		$("#lockUser").buttonEnable();
		$("#lookUser").buttonEnable();
		$("#deleteUser").buttonEnable();
	}else{
		$("#deleteUser").buttonDisable();
		$("#lockUser").buttonDisable();
		$("#lookUser").buttonDisable();
	}
}

function unlockButtom(){
	$("#add").buttonEnable();
	if($("#userList").getSelectedRowId()!=null && $("#userList").getSelectedRowId()!="undefined"){
		$("#update").buttonEnable();
		$("#resetPwd").buttonEnable();
		$("#lockUser").buttonEnable();
		$("#lookUser").buttonEnable();
		$("#deleteUser").buttonEnable();
	}else{
		$("#deleteUser").buttonDisable();
		$("#update").buttonDisable();
		$("#resetPwd").buttonDisable();
		$("#lockUser").buttonDisable();
		$("#lookUser").buttonDisable();
	}
}

function useredOrStopUsedButtom(strs){
	var used = false;
	var stopused = false;
	if(isActivedUsers()){
		if(strs.length==0){
			jqGridMultiSelectState("userList", false);
		}
	}
}
function viewUserInfo(rowid){
	if(rowid==null){
 		return;
	}
	var user =  $("#userList").getRowData(rowid);
	$("#userMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看用户信息',
		modal : true,
		url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=view&user.id='+user.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
function getSelectedId(){
    var selectedIdLast = null;
    var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");

    for(var i=0;i<selectedIds.length;i++){
		selectedIdLast = selectedIds[i];
   }
   return selectedIdLast;
}

function getRoleNames(el,options,rowData){
    var j=0;
    var roleNames="";
    if(rowData.roles!=null && rowData.roles.length >0){
    	var n = rowData.roles.length;
        for(var m=0;m<n;m++){
        	j++;
            if(j!=n){
                roleNames+=rowData.roles[m].roleName+",";
            }else{
            	roleNames+=rowData.roles[m].roleName;
            }
        }
    }else{
    	roleNames="系统管理员"
    }
    return roleNames;
}
function getRoleIds(el,options,rowData){
    var j=0;
    var roleIds="";
    if(rowData.roles!=null && rowData.roles.length >0){
    	var n = rowData.roles.length;
        for(var m=0;m<n;m++){
        	j++;
            if(j!=n){
                roleIds+=rowData.roles[m].id+",";
            }else{
            	roleIds+=rowData.roles[m].id;
            }
        }
    }else{
    	roleIds="1"
    }
    return roleIds;
}

function reSetPatelConfig(rowData){
        	$.ajax({
        		async: false ,
        		url:"${path }/sysadmin/userManage/reSetPatelConfigByUserId.action",
        	   	data:{
        		"userId":rowData.id
        		}
        	});
}
//1验证2注册 3邀请 4发信息 
function metting(){
	if(arr.length == 0){
		$.messageBox({"message":"请先选中联系人再开启视频！","level":'warn'});
		return;
	}
	//包括自己，提示，return
	regAll();
	inviteMeet();
}
function sendMessage(){
	var ids = "";
	for(var i=0;i<arr.length;i++){
		ids += arr[i].id;
		ids += ",";
	}
	ids = ids.substring(0,ids.length-1);
	$.ajax({
		url:'${path}/interactive/platformMessageOutboxManage/sendPlatformMessage.action?userReceivers='+ids+'&platformMessage.title=会议邀请&platformMessage.content=<a href="javascript:void(0);" onclick="toJoinMeet()">点此参加会议<a>',
		success:function(data){
			if(data){
				//$.messageBox({ message:""});
			}else{
				//$.messageBox({ message:"！",level: "warn"});
			}
        }
	});
}

function regAll(){
	regMeetAcc($("#currentUserAcc").val(),$("#currentUserName").val());
	var regAccList = $("#conventionersAcc").val().split(',');
	var regNameList = $("#conventionersName").val().split(',');
	for(var i=0;i<regAccList.length;i++){
		regMeetAcc(regAccList[i],regNameList[i]);
	}
}
function regMeetAcc(uname,name){
    var data = {};
    var url = 'http:\//115.236.101.203:8898/?r=/api/register&callback=?';
    data.u = uname;
    data.n = name;
	data.code = $('#ppMeetCode').val();
	data.key = encodeURIComponent($('#ppMeetKey').val());
    $.getJSON(url, data,function(json){});
}

function inviteMeet() {
	$("#userMaintanceDialog").createDialog({
		width: document.body.clientWidth,
		height: document.body.clientHeight,
		draggable:false,
		title:'远程会议',
		url:'${path}/interaction/netConference/inviteMettingDlg.jsp',
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		},
		close: function() {
			meetShutDown();
			meetAccLogOut();
		}
	});
}
function meetShutDown(){
	var data = {};
	data.u = $("#currentUserAcc").val();
	data.code = $('#ppMeetCode').val();
	data.key = encodeURIComponent($('#ppMeetKey').val());
	var url = 'http:\//115.236.101.203:8898/?r=/api/endmeet&callback=?';
	$.getJSON(url, data,function(json){});
}
function meetAccLogOut(){
	var data = {};
	data.u = $("#currentUserAcc").val();
	data.code = $('#ppMeetCode').val();
	data.key = encodeURIComponent($('#ppMeetKey').val());
	var url = 'http:\//115.236.101.203:8898/?r=/api/logout&callback=?';
	$.getJSON(url, data,function(json){});
}

</script>
