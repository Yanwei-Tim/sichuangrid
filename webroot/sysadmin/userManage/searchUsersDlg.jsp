<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<form id="maintainForm" action="" method="post">
		<input type="hidden" name="user.lock" value='<s:property value="#parameters.isLock"/>'/>
		<input type="hidden" name="searchLockStatus" value='<s:property value="#parameters.isLock"/>'/>
		<div class="grid_4 lable-right">
			<label class="form-lbl">用户名：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="user.userName" id="userUsernameId" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">用户姓名：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.name" id="userNameId" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">所属部门：</label>
		</div>
		<div class="grid_7">
	    	<input id="userOrganization" type="text" name="user.organization.orgName" class="form-txt" />
	    	<input id="userOrganizationId" type="hidden" name="user.organization.id" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否管理员：</label>
		</div>
		<div class="grid_7">
		    <select name="user.ignoreIsAdminOrNot" id="userIgnoreIsAdminOrNot">
		       <option value="0"></option>
		       <option value="1">否</option>
		       <option value="2">是</option>
		    </select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">住宅电话：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.homePhone" id="userHomePhone" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">工作电话：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.workPhone" id="userWorkPhone" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">手机号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.mobile" id="userMobile" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">最后登录日期：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.lastLoginTime" readonly id="lastLoginDate" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">是否四级体系建设账号：</label>
		</div>
		<div class="grid_3">
		    <select name="user.ignoreIsFourthAccountOrNot" id="userIgnoreIsFourthAccountOrNot">
		       <option value="0"></option>
		       <option value="1">否</option>
		       <option value="2">是</option>
		    </select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">账号类型：</label>
		</div>
		<div class="grid_4">
		    <select name="user.accountType" id="userAccountType">
		       <option value="0"></option>
		       <option value="1">PC</option>
		       <option value="2">手机</option>
		    </select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">在线状态：</label>
		</div>
		<div class="grid_4">
		    <select name="user.onLineState" id="userOnLineState">
		       <option value="0"></option>
		       <option value="1">不在线</option>
		       <option value="2">在线</option>
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">是否开启GPS定位：</label>
		</div>
		<div class="grid_6">
		    <select name="user.gpsOrNot" id="userGpsOrNot">
		       <option value="0"></option>
		       <option value="1">否</option>
		       <option value="2">是</option>
		    </select>
		</div><div class="grid_5 lable-right">
			<label class="form-lbl">是否开启四支队伍：</label>
		</div>
		<div class="grid_7">
		    <select name="user.fourTeamsOrNot" id="userFourTeamsOrNot">
		       <option value="0"></option>
		       <option value="1">否</option>
		       <option value="2">是</option>
		    </select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">账号状态：</label>
		</div>
		<div class="grid_4">
		    <select name="user.state" id="userState">
		       <option value="0"></option>
		       <option value="1">待激活</option>
		       <option value="2">正常</option>
		        <option value="3">已停用</option>
		    </select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">查询日期：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.timeforQuery" readonly id="timeforQuery" class="form-txt"/>
		</div>
		<div class="grid_7">
	    	<input type="text" style="border: 0px;color: red;width: 200px" value="用于查询此日期之后未登录的用户"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<!--
		<div class="grid_4 lable-right">
			<label class="form-lbl">所在岗位：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="" class="form-txt" />
		</div>
		 -->
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){

	var self=$("#userOrganization");
	var selfId=self.attr("id");
	var defaultOption={
		store:new Ext.data.SimpleStore({fields:[],data:[[]]}),
	    editable:false, //禁止手写及联想功能
	    mode: 'local',
	    triggerAction:'all',
	    name:'org',
	    fieldLabel : '组织机构',
	    maxHeight: 250,
	    tpl: "<div id="+selfId+"-tree"+" style='width:100%;overflow:auto;'>"+"</div>", //html代码
	    selectedClass:'',
	    onSelect:Ext.emptyFn,
	    //emptyText:'请选择...',
	    listWidth:180,
	    inputName:"user.organization.id",
	    allOrg:false,
	    url:false
	};
	function style(){
		$("#"+selfId+"-tree").parent().parent().remove();
		self.width(self.width()-25);
		$("#"+selfId+"tree").bgiframe();
	};
	style();
	var comboWithTooltip = new Ext.form.ComboBox(defaultOption);
	comboWithTooltip.applyTo(selfId);
	var url;
	if(defaultOption.url){
		url = defaultOption.url;
	}else{
		url = "/sysadmin/orgManage/firstLoadExtTreeData.action";
	}
	var searchTree ;
	$("#"+selfId+"-tree").height(defaultOption.maxHeight).width(defaultOption.listWidth);
	$.ajax({
		url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+	<s:property value="#parameters.orgId"/>,
		success:function(data){
			searchTree = $("#"+selfId+"-tree").initAdministrativeTree({shouldJugeMultizones:true,allOrg:defaultOption.allOrg,url:url});
			searchTree.on("click",function(node,e) {
		        comboWithTooltip.setRawValue(node.text);
		        comboWithTooltip.collapse();
		        $("input[name='"+defaultOption.inputName+"']").val(node.id);
			});
			$.searchChild(searchTree,data);
		}
	});

	//$("#userOrganization").treeSelect({inputName:"user.organization.id"});
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
			$.setTreeValue($("#userOrganizationId").val(),tree);
			var userName  = transformVal($('#userUsernameId').val());
			var name = transformVal($('#userNameId').val());
			var ignoreIsAdminOrNot  = transformVal($('#userIgnoreIsAdminOrNot').val());
			var homePhone = transformVal($('#userHomePhone').val());
			var workPhone = transformVal($('#userWorkPhone').val());
			var mobile = transformVal($('#userMobile').val());
			var lastLoginTime = transformVal($('#lastLoginDate').val());
			var ignoreIsFourthAccountOrNot = transformVal($('#userIgnoreIsFourthAccountOrNot').val());
			var accountType = transformVal($('#userAccountType').val());
			var onLineState = transformVal($('#userOnLineState').val());
			var state = transformVal($('#userstate').val());
			var gpsOrNot = transformVal($('#userGpsOrNot').val());
			var fourTeamsOrNot = transformVal($('#userFourTeamsOrNot').val());
			var timeforQuery = transformVal($('#timeforQuery').val());
			jQuery("#userList").setPostData({});
			var url = jQuery("#userList").getGridParam("url");
			jQuery("#userList").setGridParam({"url":"${path}/sysadmin/userManage/searchUsers.action?"+params+"","page":1});
			$("#userList").trigger("reloadGrid");
			jQuery("#userList").setGridParam({
				"url":url,
				page:1
			});
			//jQuery("#userList").setGridParam({"url":url});
			jQuery("#userList").setPostData({
				"searchLockStatus":  function(){
		            return !isActivedUsers();
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
		        "user.userName":function(){return userName;},
		        "user.name":function(){return name;},
		        "user.ignoreIsAdminOrNot":function(){return ignoreIsAdminOrNot;},
		        "user.homePhone":function(){return homePhone;},
		        "user.workPhone":function(){return workPhone;},
		        "user.mobile":function(){return mobile;},
		        "user.lastLoginTime":function(){return lastLoginTime;},
		        "user.ignoreIsFourthAccountOrNot":function(){return ignoreIsFourthAccountOrNot;},
		        "user.accountType":function(){return accountType;},
		        "user.onLineState":function(){return onLineState;},
		        "user.state":function(){return state;},
		        "user.gpsOrNot":function(){return gpsOrNot;},
		        "user.fourTeamsOrNot":function(){return fourTeamsOrNot;},
		        "user.timeforQuery":function(){return timeforQuery;},
		        searchChildOrg : false
	       	});
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#userOrganizationId").val(),
				success:function(data){
					if(data.indexOf("/")==0){
						data=data.substring(1);
					}
					data="/"+searchTree.id+"-root/"+data;
					searchTree.selectPath(data,null,function(bSuccess, oSelNode){
						searchTree.getSelectionModel().select(oSelNode);
					});
				}
			});
		},
		rules:{},
		messages:{}
	});
	
	function transformVal(dest){
		if('null' == dest) return '';
		return typeof dest == 'undefined' ? '' : dest;
	}
});
</script>
