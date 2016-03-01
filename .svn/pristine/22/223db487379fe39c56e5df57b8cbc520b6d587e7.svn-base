<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>


<div class="content" >
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<div class="userState">
			<%-- <select class="basic-input" id="isSynchronize" name="mobileUser.isSynchronize">
	 			<option value="false" selected="selected">未同步</option>
	 			<option value="true">已同步</option>
	 			<option value="all">全部</option>
			</select> --%>
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input searchtxt" style="width:120px;" name="searchText" id="searchText" type="text" value="请输入用户名" maxlength="18" onblur="value=(this.value=='')?'请输入用户名':this.value;" onfocus="value=(this.value=='请输入用户名')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
			</div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchUser">
				<a id="searchMobileUser" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addMobileUser">
			<a id="addMobileUser" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateMobileUser">
			<a id="updateMobileUser"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteMobileUser">
			<a id="deleteMobileUser" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewMobileUser">
			<a id="viewMobileUser" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="importMobileUser,downMobileUser">
   		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
   		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
	   		<pop:JugePermissionTag ename="importMobileUser">
				<a id="importMobileUser" href="javascript:void(0)"><span>Excel导入</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="downMobileUser">
				<a id="exportMobileUser" href="javascript:void(0)"><span>导出Excel</span></a>
	   		</pop:JugePermissionTag>
	   	</div>
	   	<pop:JugePermissionTag ename="matchupOrganization">
			<a id="matchupOrganization" href="javascript:void(0)"><span>匹配网格</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="matchupRole">
			<a id="matchupRole" href="javascript:void(0)"><span>匹配岗位</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="recycleMobileUser">
			<a id="recycleMobileUser" href="javascript:void(0)"><span>账号回收</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="openGps,closeGps">
   		<a href="javascript:;" class="nav-dropdownBtn"><span>开启|关闭GPS</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
   		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
	   		<pop:JugePermissionTag ename="openGps">
				<a id="openGps" href="javascript:void(0)"><span>开启GPS</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="closeGps">
				<a id="closeGps" href="javascript:void(0)"><span>关闭GPS</span></a>
	   		</pop:JugePermissionTag>
	   	</div>
	   	<pop:JugePermissionTag ename="openFourTeams,closeFourTeams">
   		<a href="javascript:;" class="nav-dropdownBtn"><span>开启|关闭四支队伍</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
   		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
	   		<pop:JugePermissionTag ename="openFourTeams">
				<a id="openFourTeams" href="javascript:void(0)"><span>开启四支队伍</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="closeFourTeams">
				<a id="closeFourTeams" href="javascript:void(0)"><span>关闭四支队伍</span></a>
	   		</pop:JugePermissionTag>
	   	</div>
	   	<pop:JugePermissionTag ename="activationMobileUser">
				<a id="activationState" href="javascript:void(0)"><span>激活</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="disableMobileUser">
				<a id="disableState" href="javascript:void(0)"><span>停用</span></a>
			</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="clearMobileUserImsi">
			<a id="clearMobileUserImsi" href="javascript:void(0)"><span>清空手机用户IMSI号</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="accountMove">
			<a id="accountMove" href="javascript:void(0)"><span>账号转移</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="mobileUserList"> </table>
		<div id="mobileUserListPager"></div>
	</div>
	<div id="mobileUserMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="rolesDialog"></div>
</div>
<%-- <div style="display:none">
	<input type="text" id="key" value="<s:property value="@com.tianque.controller.WorkContactController@key"/>"/>
	<input type="text" id="code" value="<s:property value="@com.tianque.controller.WorkContactController@code"/>"/>
	<input type="text" id="uu" value=""/>
</div> --%>
<script type="text/javascript">

function isAdminFormatter(el,options,rowData){
	if(rowData.admin){
        return "是";
    }
    if(rowData["admin"]){
    	return "是";
    }
    return "否";
}
function  isValidatorImsiFormatter(el,options,rowData){
	if(rowData.validatorImsi){
        return "是";
    }
    return "否";
}

function isMatchupOrgFormatter(el,options,rowData){
	if(rowData.orgInternalCode=="."){
        return "否";
    }
    return "是";
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

function isFourthAccountFormatter(el,options,rowData){
	if(rowData.fourthAccount){
        return "是";
    }
    return "否";
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
function stateFormatte(el,options,rowData){
	if(rowData.state==1){
        return "待激活";
    }else if(rowData.state==2){
    	return "正常";
    }else if(rowData.state==3){
    	return "已停用";
    }
}
function isLoginFormatter(el,options,rowData){
	if(rowData.login){
        return "在线";
    }
    return "不在线";
}
function activationUserOperate(){
	var selectedIds = getSelectedIds();
	var ids=deleteOperatorByEncrypt('mobileUser',selectedIds,'encryptId');
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
	var selectedId = $("#mobileUserList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	var user =  $("#mobileUserList").getRowData(selectedId);
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
				//$("#mobileUserList").delRowData(user.id);
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
function refresh(postData){
	$("#mobileUserList").setGridParam({
		url:"${path}/sysadmin/userManage/mobileUserList.action",
		datatype: "json",
		page:1
	});
	$("#mobileUserList").setPostData({
		"userVo.mobileusable":true
       
   	});
	
	$("#mobileUserList").trigger("reloadGrid");
}

function search(postData){
	$("#mobileUserList").setGridParam({
		url:"${path}/sysadmin/userManage/mobileUserList.action",
		datatype: "json",
		page:1
	});
	$("#mobileUserList").setPostData($.extend(postData,{type:"chlid"}));
	$("#mobileUserList").trigger("reloadGrid");
}

$(document).ready(function(){
	
	$("#mobileUserList").jqGridFunction({
		
		mtype:'post',
		datatype: "local",
		colModel:[
	    	{name:"id",index:"id",hidden:true,sortable:false},
	    	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	    	{name:"organization.id",index:"organization.id", hidden:true,sortable:false,frozen:true,hidedlg:true},
	    	{name:"orgInternalCode",index:"orgInternalCode",hidden:true,sortable:false,frozen:true,hidedlg:true},
	    	{name:"organization.orgLevel.id",index:"organization.orgLevel.id",hidden:true,sortable:false,frozen:true,hidedlg:true},
	    	{name:"organization.orgLevel.displayName",index:"organization.orgLevel.displayName",hidden:true,sortable:false,frozen:true,hidedlg:true},
	    	{name:"userName",index:'userName',sortable:true,label:'用户名',width:100},
      		{name:"name",index:"name",sortable:true,label:'用户姓名',width:100},
      		{name:'isLogin',label:'在线状态',sortable:false,formatter:isLoginFormatter,align:'center',width:100},
      		{name:'lastLoginTime',label:'最后登录时间',sortable:true,width:140},
      		{name:'workPhone',index:"workPhone",label:'工作电话',sortable:true,align:'center',width:140,hidden:true},
	        {name:'mobile',index:"mobile",label:'手机',sortable:true,align:'center',width:100,hidden:true},
	    	{name:"isMatchupOrg",index:"orgInternalCode",label:'是否已匹配网格',formatter:isMatchupOrgFormatter,sortable:true,align:'center',width:100,hidden:true},
	    	{name:'fourthAccount',label:'是否四级体系建设账号',sortable:false,formatter:isFourthAccountFormatter,align:'center',width:100,hidden:true},	
	    	{name:'gps',label:'是否开启GPS定位',sortable:false,formatter:isGPSFormatter,align:'center',width:100,hidden:true},
	        {name:'fourTeams',label:'是否开启四支队伍',sortable:false,formatter:isFourTeamsFormatter,align:'center',width:100,hidden:true},
		    {name:'admin',label:'是否超级管理员',sortable:false,formatter:isAdminFormatter,align:'center',width:100,hidden:true},
	        {name:'validatorImsi',label:'是否验证IMSI号',sortable:false,formatter:isValidatorImsiFormatter,align:'center',width:100,hidden:true},
	        {name:"roles",index:"name",label:'所在岗位',sortable:true,formatter:getRoleNames,width:90,hidden:false},
	        {name:"organization.fullOrgName",sortable:false,index:"fullOrgName",label:'所在层级',width:100},
	        {name:'imsi',label:'imsi码',sortable:false,align:'center',width:120,hidden:true},
	        {name:'mobileVersion',label:'版本号',sortable:true,align:'center',width:120,hidden:true},
	        {name:'mobileInnerVersion',label:'内部版本号',sortable:true,align:'center',width:120,hidden:true},
	        {name:'workCompany',label:'工作单位或就读学校',sortable:true,width:180,hidden:true},
	        {name:"createDate",index:"createDate",label:'新增时间',sortable:true,hidden:true,width:90},
	        {name:"activationTime",index:"activationTime",label:'激活时间',sortable:false,width:90},
	        {name:"state",index:"state",label:'账号状态',sortable:true,width:90,formatter:stateFormatte},
	        {name:'imei',label:'imei号',sortable:false,align:'center',width:120,hidden:true},
	        {name:'mobileType',label:'手机型号',index:"mobileType",sortable:true,align:'center',width:90,hidden:true},
	        {name:'newVersionFirstLoginTime',label:'新版本首次登录时间',sortable:true,width:140},
	        {name:'mobileSystemVersion',label:'手机操作系统版本号',sortable:true,align:'center',width:120,hidden:true}
	  	],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		loadComplete: afterLoad,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){setUserOperateButton();}
	});
	jQuery("#mobileUserList").jqGrid('setFrozenColumns');
	
	$("#addMobileUser").click(function (){
		$("#mobileUserMaintanceDialog").createDialog({
			width: 600,
			height: 500,
			title:'新增手机账号用户',
			url:'${path}/sysadmin/userManage/dispatchOperate.action?mode=add',
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
	$("#updateMobileUser").click(function (){
		var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"只能选择一条记录修改！"});
			 return;
		}
		var selectedId = getMobileUserSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			 return;
		}
		var rowData=  $("#mobileUserList").getRowData(selectedIds);
		$("#mobileUserMaintanceDialog").createDialog({
			width: 600,
			height: 500,
			title:'修改手机账号用户',
			url:'${path}/sysadmin/userManage/dispatchOperate.action?mode=edit&user.id='+rowData.encryptId,
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

	//删除手机账号
	$("#deleteMobileUser").click(function (){
			var selectedRowId1=getSelectedIds();
			if(null==selectedRowId1 || ""==selectedRowId1){
				$.messageBox({level:"warn",message:"请选择一条数据或多条再进行操作！"});
		 		return;
			}
			if(isCanDeleteMobileUser()){
				$.messageBox({level:"warn",message:"账号存在正在使用中，不允许删除，请选择一条或多条没有使用中的！"});
				return;
				
			}
			var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');

			$.confirm({
				title:"确认删除",
				message:"该删除后就不能还原，确认删除？",
				width: 300,
				okFunc: function(){
					$.ajax({
						url:"${path}/sysadmin/userManage/deleteUserById.action",
						type:'post',
						data:{
							"userIds":allValue
							},	
						success:function(data){
							if(data!=true && data!="true" ){
								$.messageBox({message:data,level:"error"});
								return ;
							}
							$("#mobileUserList").trigger("reloadGrid");
							/* for(var i=0;i<selectedRowId1.length;i++){
								$("#uu").val($("#mobileUserList").getRowData(selectedRowId1[i]).userName+'@hztianque.com');
							} */
							$.messageBox({message:"用户删除成功！"});
						}
					});
				}
			});
	});
	//匹配网格
	$("#matchupOrganization").click(function (){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条或多条数据再进行操作！"});
	 		return;
		}
		if(isCanDeleteMobileUser()){
			$.messageBox({level:"warn",message:"所选记录中有已匹配记录！请选择一条或多条未匹配网格的记录，再进行匹配！"});
			return;
		}
		var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');
		$("#mobileUserMaintanceDialog").createDialog({
			width: 300,
			height: 300,
			title:"匹配网格",
			url:'${path}/sysadmin/mobileUserManage/matchupOrgSelectDlg.jsp?selectedIds='+allValue,
			buttons: {
		   		"确定" : function(event){
		   			$("#maintainMatchupForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
		
	});
	//匹配岗位
	$("#matchupRole").click(function (){
		var selectedRowId1=getSelectedIds();
		var sameOrgId=getSameOrg();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条或多条数据再进行操作！"});
	 		return;
		}
		if(isCanMatchupMobileUser()){
			$.messageBox({level:"warn",message:"所选记录中有未匹配网格的记录！请先匹配网格，再进行匹配！"});
			return;
		}
		if(isSameOrg()){
			$.messageBox({level:"warn",message:"所选记录不属于同一层级，请选择同一层级的记录 "});
			return;	
		}
		var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');
		$.confirm({
			title:"确认匹配岗位",
			message:"匹配岗位后会删除原有岗位确认匹配岗位？",
			width: 300,
			okFunc: function(){
				$("#mobileUserMaintanceDialog").createDialog({
					width: 500,
					height: 450,
					title:"匹配岗位",
					url:'${path}/sysadmin/mobileUserManage/mobileUserRoleList.jsp?userIds='+allValue+'&organizationId='+sameOrgId,
					buttons: {
				   		"确定" : function(event){
				   			$("#matchupMobileUserRoleForm").submit();
				   		},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			}
		});
	});
	//账号回收
	$("#recycleMobileUser").click(function (){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条或多条数据再进行操作！"});
	 		return;
		}
		if(isCanMatchupMobileUser()){
			$.messageBox({level:"warn",message:"所选记录中有未匹配网格的记录！不需要回收！"});
			return;
		}
		var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');
		$.confirm({
			title:"确认回收账号",
			message:"回收账号后会删除原有岗位和匹配的网格，确认回收？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/sysadmin/userManage/recycleMobileUser.action",
					type:'post',
					data:{
						"userIds":allValue
						},
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#mobileUserList").trigger("reloadGrid");
						$.messageBox({message:"回收账号成功！"});
					}
				});
			}
		});
	});
	
	$("#viewMobileUser").click(function(event){
		var selectedIds=$("#mobileUserList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		var selectedId=$("#mobileUserList").jqGrid("getGridParam", "selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewMobileUserInfo(selectedId);
	});
	 //高级搜索
	$("#searchMobileUser").click(function (){
		$("#mobileUserMaintanceDialog").createDialog({
			width: 600,
			height: 500,
			title:'用户查询-请输入查询条件',
			url:'${path}/sysadmin/userManage/dispatchOperate.action?mode=search',
			buttons: {
			   	"查询" : function(event){
			   		searchMobileUser();
			   		$(this).dialog("close");
			   	},
			   	"关闭" : function(){
			        $(this).dialog("close");
			   	}
			}
		});
	});	 

	$("#importMobileUser").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:"${path}/common/import.jsp?dataType=mobileUser&dialog=noticeDialog&isNew=1&startRow=6&templates=MOBILEUSER&listName=mobileUserList",
			buttons:{
				"导入" : function(event){
					$("#mForm").submit();
				},
			   	"关闭" : function(){
			    	$(this).dialog("close");
			   	}
			},
			shouldEmptyHtml:false
		});
	});
	$("#exportMobileUser").click(function(event){
		if ($("#mobileUserList").getGridParam("records")>0){
			$("#noticeDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出手机列表信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'mobileUserList',
					downloadUrl:'${path}/sysadmin/userManage/downloadMobileUsers.action'
					},
				buttons: {
		   			"导出" : function(event){
						exceldownloadSubmitForm();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:"warn",message:"列表中没有数据，不能导出！"});
		}
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
			var userTemp =  $("#mobileUserList").getRowData(selectedIds[i]);
			if(userTemp.state=='正常'){
				$.messageBox({level:"warn",message:"所选账号中含非‘待激活’状态的账号,激活失败！"});
				return;
			}
	   }
		$.confirm({
				title:'确认激活',
				message:'激活后用户将可以登录系统，您确定激活吗?',
				width: 300,
				okFunc: activationUserOperate
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
		var user =  $("#mobileUserList").getRowData(selectedIds[0]);
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
				okFunc: disableUserOperate
		});
	});
	
	$("#refresh").click(function (){
		refresh();
	}).click();
	
	//快速搜索
	$("#refreshSearchKey").click(function(event){
		$("#searchText").attr("value","请输入用户名");
	});	

	$('#fastSearchButton').click(function(){
		var text = $('#searchText').val();
		if(text=="请输入用户名"){
			return;
		}
		var	initParam = {
				 "userVo.userName":text,
				 "userVo.mobileusable":true
		}
		$("#mobileUserList").setGridParam({
			url:"${path}/sysadmin/userManage/mobileUserList.action",
			datatype:"json",
			page:1
		});
		$("#mobileUserList").setPostData(initParam);
		$("#mobileUserList").trigger("reloadGrid");
	});
	
	//开启关闭GPS
	$("#openGps").click(function (){
		openOrCloseGps("开启","open");
	});
	$("#closeGps").click(function (){
		openOrCloseGps("关闭","close");
	});
	//开启关闭四支队伍
	$("#openFourTeams").click(function (){
		openOrCloseFourTeams("开启","open");
	});
	$("#closeFourTeams").click(function (){
		openOrCloseFourTeams("关闭","close");
	});
	function openOrCloseGps(type,mode){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条数据或多条再进行操作！"});
	 		return;
		}
		var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');
		$.confirm({
			title:"确认"+type+"GPS",
			message:"是否确认"+type+"GPS定位，确认"+type+"？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/sysadmin/userManage/openOrCloseGpsByUserId.action",
					type:'post',
					data:{
						"userIds":allValue,
						"mode":mode
						},	
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#mobileUserList").trigger("reloadGrid");
						$.messageBox({message:"操作成功！"});
					}
				});
			}
		});
	}
	function openOrCloseFourTeams(type,mode){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条数据或多条再进行操作！"});
	 		return;
		}
		var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');
		$.confirm({
			title:"确认"+type+"四支队伍",
			message:"是否确认"+type+"四支队伍，确认"+type+"？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/sysadmin/userManage/openOrCloseFourTeamsByUserId.action",
					type:'post',
					data:{
						"userIds":allValue,
						"mode":mode
						},	
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#mobileUserList").trigger("reloadGrid");
						$.messageBox({message:"操作成功！"});
					}
				});
			}
		});
	}
	//账号转移
	$("#accountMove").click(function (){
		accountMove();
	});
});

function getSelectedIds(){
	var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function doubleClickTable(rowid){
	var userInfo =  $("#mobileUserList").getRowData(rowid);
	if(userInfo==null || userInfo.id==null){
 		return;
	}
	$("#userMaintanceDialog").createDialog({
		width: 600,
		height: 500,
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

function viewMobileUserInfo(rowid){
	if(rowid==null){
 		return;
	}
	var user =  $("#mobileUserList").getRowData(rowid);
	$("#mobileUserMaintanceDialog").createDialog({
		width: 600,
		height: 500,
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

//验证是否可以删除手机账号（已经匹配组织机构的不可以删除）
function isCanDeleteMobileUser(){
	var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
	if(null==selectedIds || ""==selectedIds || selectedIds.length > 0 ){
		for(var i=0;i<selectedIds.length;i++){
			var rowDateInfo=$("#mobileUserList").jqGrid("getRowData",selectedIds[i]);
			if(rowDateInfo.orgInternalCode!="." ){
				return true;
			}
		}
	}
	return false;
}
//验证是否已经匹配网格 
function isCanMatchupMobileUser(){
	var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
	if(null==selectedIds || ""==selectedIds || selectedIds.length > 0 ){
		for(var i=0;i<selectedIds.length;i++){
			var rowDateInfo=$("#mobileUserList").jqGrid("getRowData",selectedIds[i]);
			if(rowDateInfo.orgInternalCode=="." ){
				return true;
			}
		}
	}
	return false;
}

//验证是否是同一级的组织区域
function isSameOrg(){
	var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
	var temp;
	if(null==selectedIds || ""==selectedIds || selectedIds.length > 0 ){
		for(var i=0;i<selectedIds.length;i++){
			var rowDateInfo=$("#mobileUserList").jqGrid("getRowData",selectedIds[i]);
			if(i==0)
				temp=rowDateInfo.orgInternalCode;
			if(temp!=rowDateInfo.orgInternalCode ){
				return true;
			}
		}
	}
	return false;
}

//获取是同一组织机构的手机账号的组织机构
function getSameOrg(){
	var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
	var temp;
	var tempId;
	if(null==selectedIds || ""==selectedIds || selectedIds.length > 0 ){
		for(var i=0;i<selectedIds.length;i++){
			var rowDateInfo=$("#mobileUserList").jqGrid("getRowData",selectedIds[i]);
			if(i==0)
				tempId=rowDateInfo['organization.id'];
				temp=rowDateInfo.orgInternalCode;
			if(temp!=rowDateInfo.orgInternalCode ){
				return "";
			}
		}
	}
	return tempId;
}
function searchMobileUser(){
	var formdata = $("#searchMobileUserForm").serialize();
	if (formdata != '') {
		formdata = formdata.replace(/=/g, '":"');
		formdata = formdata.replace(/&/g, '","');
		formdata += '"';
	}
	formdata = decodeURIComponent('{"' + formdata + '}');
	search(parseObj(formdata));
}

function parseObj(strData) {
	return (new Function("return " + strData))();
}

function setUserOperateButton(){
	
}

function afterLoad(){
	setUserOperateButton();
}


function getMobileUserSelectedIdLast() {
	var selectedId;
	var selectedIds = $("#mobileUserList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
} 

$("#clearMobileUserImsi").click(function (){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条数据或多条再进行操作！"});
	 		return;
		}
		var allValue=deleteOperatorByEncrypt('mobileUser',selectedRowId1,'encryptId');
		$.confirm({
			title:"确认清空",
			message:"该清空后就不能还原，确认清空？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/sysadmin/userManage/clearMobileUserImsi.action",
					type:'post',
					data:{
						"userIds":allValue
						},	
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#mobileUserList").trigger("reloadGrid");
						$.messageBox({message:"清空成功！"});
					}
				});
			}
		});
});

function accountMove(){
		var selectedId = getSelectedIds();
		if(selectedId==null||selectedId==""){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var str="";
		var selectAccountsLevel=$("#mobileUserList").getRowData(selectedId[0])["organization.orgLevel.id"];
		for(var i=0;i<selectedId.length;i++){
			var row=$("#mobileUserList").getRowData(selectedId[i]);
			if(row["organization.orgLevel.id"]!=selectAccountsLevel){
				$.messageBox({level:"warn",message:"请选择同一层级的账号再进行操作！"});
		 		return;
			}
			str+=selectedId[i]+",";
		}
		str=str.substring(0,str.length-1);
		$("#mobileUserMaintanceDialog").createDialog({
			width: 400,
			height: 250,
			title:'账号转移',
			url:'${path}/sysadmin/userManage/dispatchOperate.action?mode=accountMoveSelect&dict.id='+selectAccountsLevel,
			buttons: {
		   		"保存" : function(event){
		   			if($("#roleIds").val()==null||$("#roleIds").val()==""){
						$.messageBox({level:"error",message:"请选择岗位！"});
				 		return;
					}
		   			var roleIds=$("#roleIds").val();
		   			var roleIdsStr="";
		   			for(var i=0;i<roleIds.length;i++){
		   				roleIdsStr+=roleIds[i]+",";
		   			}
		   			roleIdsStr=roleIdsStr.substring(0,roleIdsStr.length-1);
					$.ajax({
						async:false,
						url: "${path}/sysadmin/userManage/mobileUserMove.action",
						type:'post',
						data:{
							"userIds":str,
							"roleIdsStr":roleIdsStr,
							"user.organization.id":$("#userOrganizationId").val(),
							"user.organization.orgInternalCode":$("#orgInternalCode").val()
						},	
						success:function(responseData){
							$.messageBox({message:"账号迁移成功"});
							$("#refresh").click();
						}
					});
					$("#mobileUserMaintanceDialog").dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
}
</script>
