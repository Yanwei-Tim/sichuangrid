<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-left">
	<div class="ui-widget">
		<input id="org_autocomplete" type="text" class="ui-autocomplete-input" value=""/>
		<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div id="nav">
		<div class="userState">
			<!-- <input type="text" id="user_autocomplete"  class="show_search_btn" value="快速检索..." /> -->
			<select class="basic-input" id="isLock" name="user.isLock">
 					<option value="false" selected="selected">活动的</option>
 					<option value="true">锁定的</option>
 					<option value="withActivation">待激活</option>
 					<option value="all">全部</option>
			</select>
			
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input searchtxt" style="width:100px;" name="searchText" id="searchText" type="text" value="请输入用户名" maxlength="18" onblur="value=(this.value=='')?'请输入用户名':this.value;" onfocus="value=(this.value=='请输入用户名')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
			</div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
				<pop:JugePermissionTag ename="searchUserListData">
			<a id="searchUser" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="userListUpdate">
			<a id="update"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListResetPassword">
			<a id="resetPwd" href="javascript:void(0)"><span>重置密码</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListLockUsers">
			<a id="lockUser" href="javascript:void(0)"><span>锁定</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListView">
			<a id="lookUser" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="usersListReUsed">
		    <a id="used" style="display: none;" href="javascript:void(0)"><span>启用</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListStopUsed">
		    <a id="stopUsed" style="display: none;" href="javascript:void(0)"><span>禁用</span></a>
	    </pop:JugePermissionTag>
	    <pop:JugePermissionTag ename="usersListActivation">
				<a id="activationState" href="javascript:void(0)"><span>激活</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="usersListDisable">
				<a id="disableState" href="javascript:void(0)"><span>停用</span></a>
			</pop:JugePermissionTag>
    	<pop:JugePermissionTag ename="generatedQrcodeByUserList">
			<a id="generatedQrcodeByUserList" href="javascript:void(0)"><span>生成二维码</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="userList"> </table>
		<div id="userListPager"></div>
	</div>
</div>
<div id="userMaintanceDialog"></div>
<div id="noticeDialog"></div>
<div id="qrcodeDialog"></div>
<script type="text/javascript">
$.showTxtValue()
var dialogWidth=580;
var dialogHeight=420;
var currentOrgId;
var tree;
$(document).ready(function(){
	var loginUser = '<s:property value="#loginAction.user.userName"/>';
	if(loginUser=="admin"){
		$("#used").attr("style","display");
		$("#stopUsed").attr("style","display");
	}else{
		$("#used").attr("style","display: none;");
		$("#stopUsed").attr("style","display: none;");
	}

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
    	//setUserOperateButton();
	}

	var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}
	$.addClick(tree,afterChangNode);

	$("#refreshSearchKey").click(function(event){
		$("#searchText").attr("value","请输入用户名");
	});	

	$('#fastSearchButton').click(function(){
		var text = $('#searchText').val();
		if(text=="请输入用户名"){
			return;
		}
		var	initParam = {
				 "user.userName":text,
				 "searchLockStatus":$('#isLock').val()
		}
		$("#userList").setGridParam({
			url:"${path}/sysadmin/searchUserListData/searchUserList.action?organizationId="+currentOrgId,
			datatype:"json",
			page:1
		});
		$("#userList").setPostData(initParam);
		$("#userList").trigger("reloadGrid");
	});

	$("#isLock").change(function(event){
		lockUserButtonText($(event.srcElement).val());
		$("#user_autocomplete").empty();
		findUserByOrgId();
		setUserOperateButton();
		if( $("#isLock").val() == "true"){
		}
	});
	$("#user_autocomplete").userAutocomplete({
		postData: {
			searchChildOrg : true,
			searchLockStatus : function(){if(isActivedUsers()=="unlocked"){return "unlocked";}else if(isActivedUsers()=="locked"){return "locked";}else if(isActivedUsers()=="withActivation"){return "withActivation";}return "all";},
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

	$("#update").click(function(event){
		var selectedIds=$("#userList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		var selectedId=$("#userList").jqGrid("getGridParam", "selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedId);
		if(user.userName=="admin"){
			<s:if test='!"admin".equals(@com.tianque.core.util.ThreadVariable@getUser().getUserName())'>
			$.messageBox({level:'warn',message:'不能修改admin！'});
			return ;
			</s:if>
		}
		if(user.lock == "true"){
			$.messageBox({level:'warn',message:'不能修改锁定状态的用户！'});
			return ;
		}
		$("#userMaintanceDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'修改用户',
			url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=edit&user.id='+user.encryptId+"&organizationId="+currentOrgId,
			buttons: {
		   		"保存" : function(event){
				var isAdmin = $("#userIsAdmin").val();
				var roles=$("#roleIds").find("option")
		   		if(roles.size()==0 && isAdmin=="false"){
		   			$.messageBox({
						message:"岗位不能为空",
						level:"warn"
                     });
		   			return;
			   	}else{
			   		$("#maintainForm").submit();
				}
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#resetPwd").click(function(event){
		var selectedIds=$("#userList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		var selectedId=$("#userList").jqGrid("getGridParam", "selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedId);
		var currentLoginUserId='<s:property value="#loginAction.user.id"/>';

		
		
		if(user.userName=="admin"){
			<s:if test='!"admin".equals(@com.tianque.core.util.ThreadVariable@getUser().getUserName())'>
			$.messageBox({level:'warn',message:'不能重置admin的密码！'});
			return ;
			</s:if>
		}
		$("#userMaintanceDialog").createDialog({
			width: 350,
			height: 300,
			title:'重置用户登录密码',
			url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=resetPwd&user.id='
				+user.encryptId,
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#lockUser").click(function(){
		var title;
		var message;
		var selectedId = getSelectedIds();
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedId);
		if(user.userName=="admin"){
			<s:if test='!"admin".equals(@com.tianque.core.util.ThreadVariable@getUser().getUserName())'>
			$.messageBox({level:'warn',message:'不能锁定admin！'});
			return ;
			</s:if>
		}
	    if(user.admin=='是'){
        	$.notice({
				level:'warn',
				message:'系统管理员无法锁定'
			});
			return ;
	    }

	    var currentLoginUserId='<s:property value="#loginAction.user.id"/>';
	    if(user.id==currentLoginUserId){
	    	$.notice({
				level:'warn',
				message:'当前用户不允许进行锁定'
			});
			return ;
	    }
	    if(user.lock == "false"){
	        title = "确认锁定";
	        message = "锁定后，该用户将无法登录系统，您确定锁定该用户吗？";
	    }else if(user.lock == "true"){
	        title = "确认解锁";
	        message = "解锁后用户将可以登录系统，您确定解锁吗?";
	    }else{
		    return ;
		}
		$.confirm({
				title:title,
				message:message,
				width: 400,
				okFunc: function(){
					userLockOperate();
				}
		});
	});
	$("#lookUser").click(function(event){
		var selectedIds=$("#userList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		var selectedId=$("#userList").jqGrid("getGridParam", "selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewUserInfo(selectedId);
	});
	$("#activationState").click(function(){
		var title;
		var message;
		var selectedIds = getSelectedIds();
		if(null==selectedIds || ""==selectedIds){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		for(var i=0;i<selectedIds.length;i++){
			var userTemp =  $("#userList").getRowData(selectedIds[i]);
			if(userTemp.state=='正常'){
				$.messageBox({level:"warn",message:"所选账号中含非‘待激活’状态的账号,激活失败！"});
				return;
			}
	   }
		$.confirm({
				title:'确认激活',
				message:'激活后用户将可以登录系统，您确定激活吗?',
				width: 300,
				okFunc: function(){
					activationUserOperate();
				}
		});
	});
	$("#disableState").click(function(){
		var title;
		var message;
		var selectedIds = getSelectedIds();
		if(selectedIds==null||""==selectedIds||(selectedIds!=null&&selectedIds.length>1)){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedIds[0]);
		var state=user.state;
		if(state=='已停用'){
			$.messageBox({level:"warn",message:"该用户已是停用状态,不可再次停用！"});
			return ;
	    }
		if(state=='待激活'){
			$.messageBox({level:"warn",message:"该用户是待激活状态,不可停用！"});
			return ;
	    }
		if(user.isLogin=='在线'){
			$.messageBox({level:"warn",message:"该用户在线,不可停用！"});
			return ;
	    }
		$.confirm({
				title:'确认停用',
				message:'停用后用户不可登录系统，您确定停用吗?',
				width: 300,
				okFunc: function(){
					disableUserOperate();
				}
		});
	});
	
	$("#refresh").click(function(){
		$("#user_autocomplete").removeAttr("userId");
		refresh();
	});
	$("#used").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:"earn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.confirm({
				title:"启用该帐号",
				message:"是否启用该帐号",
				okFunc: function() {
					$.ajax({
						url:"${path}/sysadmin/userManage/reUsed.action?userIds="+allValue,
						success:function(data){
							if(data){
								$.messageBox({message:"已经成功启用该帐号"});
							}else{
								$.messageBox({message:"该帐号启用失败"});
							}
							$("#userList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	$("#stopUsed").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.confirm({
				title:"禁用该账号",
				message:"您是否确定禁用该账号",
				okFunc: function() {
					$.ajax({
						url:"${path}/sysadmin/userManage/stopUsed.action?userIds="+allValue,
						success:function(data){
							if(data){
								$.messageBox({message:"已经成功禁用该帐号"});
							}else{
								$.messageBox({level:'error',message:"该帐号禁用失败"});
							}
							$("#userList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	$("#searchUser").click(function(event){
		$("#userMaintanceDialog").createDialog({
			width: 570,
			height: 350,
			title:'用户查询-请输入查询条件',
			url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=search&organizationId='+currentOrgId+'&user.lock='+$('#isLock').val()+'&searchLockStatus='+$('#isLock').val(),
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

	function searchUserListData(){
		var roleIds=$("#roleIds").val();
		var userName=$("#userName").val();
		var name =$("#name").val();

		$("#userList").setGridParam({
			url:"${path}/sysadmin/searchUserListData/searchUserList.action",
			datatype: "json",
			page:1
		});

		var searchCondition = {
			"roleIds":roleIds,
			"user.userName":userName,
			"user.name":$("#name").val()
		}
		$("#userList").setPostData({
			"aaaa":roleIds
			//"user.userName":"qw"
			});
		$("#userList").trigger("reloadGrid");
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
	
	function isLoginFormatter(el,options,rowData){
		if(rowData.login){
	        return "在线";
	    }
	    return "不在线";
    }
	
	function isGPSFormatter(el,options,rowData){
		if(rowData.gps){
	        return "是";
	    }
	    return "否";
    }
	function isFourTeamsFormatter(el,options,rowData){
		if(rowData.fourTeams){
	        return "是";
	    }
	    return "否";
    }
	//是否锁定 
	function isLockFormatter(el,options,rowData){
		if(rowData.lock){
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
    function stateFormatte(el,options,rowData){
		if(rowData.state==1){
	        return "待激活";
	    }else if(rowData.state==2){
	    	return "正常";
	    }else if(rowData.state==3){
	    	return "已停用";
	    }
    }

    function activationUserOperate(){
    	var selectedIds = getSelectedIds();
    	var ids=deleteOperatorByEncrypt('user',selectedIds,'encryptId');
    	$.ajax({
    		url: '${path}/sysadmin/userManage/activationAndDisableUserOperate.action',
    		data: {
    			"userIds":ids,
    	        "state":2
            },
    		success:function(data){
    			if(data == true){
    				$.messageBox({
    				    message: "成功激活用户!"
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
    function disableUserOperate(){
    	var selectedId = $("#userList").getGridParam("selrow");
    	if(selectedId==null){
     		return;
    	}
    	var user =  $("#userList").getRowData(selectedId);
    	$.ajax({
    		url: '${path}/sysadmin/userManage/activationAndDisableUserOperate.action',
    		data: {
    	        "userIds": function(){
    	            return user.id;
    	        },
    	        "state" :3
            },
    		success:function(data){
    			if(data == true){
    				//$("#userList").delRowData(user.id);
    				$.messageBox({
    				    message: "成功停用用户:"+ user.userName +"!"
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


	function refresh(){
		$("#userList").setGridParam({
			url:"${path}/sysadmin/userManage/getUserList.action",
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
	        searchChildOrg : true
	   	});
		$("#userList").trigger("reloadGrid");
	}
	$("#userList").jqGridFunction({
		url:'${path}/sysadmin/userManage/getUserList.action?1=1',
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
	        searchChildOrg : true
        },
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:"userName",index:'userName',sortable:true,label:'用户名',width:100},
      		{name:"name",index:"name",sortable:true,label:'用户姓名',width:100},
	        {name:'workPhone',label:'工作电话',sortable:true,align:'center',width:140,hidden:true},
	        {name:'mobile',label:'手机',sortable:true,align:'center',width:100,hidden:true},
	        {name:'lastLoginTime',label:'最后登录时间',sortable:true,width:140},
	        {name:'isLogin',label:'在线状态',sortable:false,formatter:isLoginFormatter,align:'center',width:100},
	        {name:'admin',label:'是否超级管理员',sortable:false,formatter:isAdminFormatter,align:'center',width:100,hidden:true},
	        {name:'gps',label:'是否开启GPS定位',sortable:false,formatter:isGPSFormatter,align:'center',width:100,hidden:true},
	        {name:'fourTeams',label:'是否开启四支队伍',sortable:false,formatter:isFourTeamsFormatter,align:'center',width:100,hidden:true},
	        {name:"shutDown",index:"name",label:'是否启用',hidden:true,sortable:false,formatter:isShutDown,align:'center',width:70},
	        {name:"roles",index:"name",label:'所在岗位',sortable:false,formatter:getRoleNames,width:90,hidden:false},
	        {name:"organization.orgLevel.displayName",sortable:true,index:"orgLevel",label:'所在层级',width:100},
	        {name:"lock-formatter",index:"islock",label:'是否锁定',sortable:true,formatter:isLockFormatter,hidedlg:true,width:70,hidden:true},
	        {name:"lock",index:"lock",label:'是否锁定',sortable:true,hidden:true,hidedlg:true,width:90,hidden:true},
	        {name:"createDate",index:"createDate",label:'新增时间',sortable:true,hidden:true,width:90},
	        {name:"activationTime",index:"activationTime",label:'激活时间',sortable:true,width:130,hidden:false},
	        {name:"state",index:"state",label:'账号状态',sortable:true,hidden:false,width:90,formatter:stateFormatte},
	        {name:'workCompany',label:'工作单位或就读学校',sortable:true,width:200,hidden:true}
	        <s:if test="@com.tianque.component.ThreadVariable@getUser().isAdmin()">
	        ,
	        {name:'',label:'同步用户',sortable:true,formatter: asyncoUserFormmater,align:'center'}
	        </s:if>
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		loadComplete: afterLoad,
		<pop:JugePermissionTag ename="usersListView">
		ondblClickRow: doubleClickTable,
		</pop:JugePermissionTag>
		onSelectRow:function(){setUserOperateButton();}
	});

	$("#generatedQrcodeByUserList").click(function(){
		var selectedRowIds=getSelectedIds();
		if(selectedRowIds.length ==0 ){
			$.messageBox({level:"warn",message:"请选择一条或者多条数据再进行操作！"});
	 		return;
		}
		var allValue=deleteOperatorByEncrypt('user',selectedRowIds,'encryptId');
		$("#qrcodeDialog").createDialog({
			width: 700,
			height: 600,
			title:"二维码列表",
			url:"${path}/twoDimensionCodeManage/generateQrcode.action?qrcodeType=userQrcodeType&ids="+allValue,
			buttons:{
				"打印":function(){
					printQrcode();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
			
		});
		
	});
	
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
	if($("#isLock").val() == "withActivation"){return "withActivation";}
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
	            return user.encryptId;
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
				$("#userList").trigger("reloadGrid");
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
	}else if (lock=="all"){
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-chattr'/>锁定/解锁</span>"); 
	}else{ 
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-lock'/>锁定</span>");
	}
}

function afterLoad(){
	setUserOperateButton();
}

function setUserOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("userList");
	var count = $("#userList").jqGrid("getGridParam","records");
	var user =  $("#userList").getRowData($("#userList").getSelectedRowId());
	var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");
	if(selectedCounts == count && selectedCounts!=0){
		jqGridMultiSelectState("userList", true);
	}else{
		jqGridMultiSelectState("userList", false);
	}
	useredOrStopUsedButtom(selectedIds);

}

function lockButtom(){
	$("#update").buttonDisable();
	$("#resetPwd").buttonDisable();
	$("#lockUser").buttonEnable();
	$("#lookUser").buttonEnable();
	if($("#userList").getSelectedRowId()){
		$("#lockUser").buttonEnable();
		$("#lookUser").buttonEnable();
	}else{
		$("#lockUser").buttonDisable();
		$("#lookUser").buttonDisable();
	}
}

function unlockButtom(){
	if($("#userList").getSelectedRowId()){
		$("#update").buttonEnable();
		$("#resetPwd").buttonEnable();
		$("#lockUser").buttonEnable();
		$("#lookUser").buttonEnable();
	}else{
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
		for(var i=0; i<strs.length;i++){
			var user = $("#userList").getRowData(strs[i]);
			if(user["shutDown"]=="是"){
				used = true;
			}
			if(user["shutDown"]=="否"){
				stopused = true;
			}
		}
	}
}
function viewUserInfo(rowid){
	if(rowid==null){
 		return;
	}
	var user =  $("#userList").getRowData(rowid);
	$("#userMaintanceDialog").createDialog({
		width: 580,
		height: 530,
		title:'查看用户信息',
		modal : true,
		url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=view&user.id='+user.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function doubleClickTable(rowid){
	var userInfo =  $("#userList").getRowData(rowid);
	if(userInfo==null || userInfo.id==null){
 		return;
	}
	$("#userMaintanceDialog").createDialog({
		width: 580,
		height: 530,
		title:'查看用户信息',
		modal : true,
		url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=view&user.id='+userInfo.encryptId,
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
	var selectIdLast;
	var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectIdLast = selectedIds[i];
	}
	return selectIdLast;
}

function printQrcode(){
	$.dialogLoading("open");
	$("#qrcodeDialog").printArea();
	$("#qrcodeDialog").dialog("close");
	$.dialogLoading("close");
}
</script>
