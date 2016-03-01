<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

	<div class="content">
		<div class="ui-corner-all" id="nav">
			<div class="userState">
				<!-- <input type="text" id="user_autocomplete"  class="show_search_btn" value="快速检索..." /> -->
				<select class="basic-input" id="isLock" name="user.isLock">
	 					<option value="false" selected="selected">活动的</option>
	 					<option value="true">锁定的</option>
	 					<option value="all">全部</option>
				</select>
				
				
			</div>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="userIntroduceList"> </table>
			<div id="userIntroduceListPager"></div>
		</div>
	</div>
<div id="userMaintanceDialog"></div>
<div id="noticeDialog"></div>
<div style="display:none">
	<input type="text" id="key" value="<s:property value="@com.tianque.controller.WorkContactController@key"/>"/>
	<input type="text" id="code" value="<s:property value="@com.tianque.controller.WorkContactController@code"/>"/>
	<input type="text" id="uu" value=""/>
</div>
<script type="text/javascript">
var dialogWidth=580;
var dialogHeight=530;
var currentOrgId = "<s:property value='#parameters.currentOrgId'/>";
function refresh(){
	$("#userIntroduceList").setGridParam({
		url:"${path}/sysadmin/userManage/userList.action",
		datatype: "json",
		page:1
	});
	$("#userIntroduceList").setPostData({
		"searchLockStatus":  function(){
            return isActivedUsers();
        },
        "organizationId":currentOrgId,
        "user.id" : function(){
	        if($("#user_autocomplete").attr("userId")){
	        	return $("#user_autocomplete").attr("userId");
	        }else{
		        return -1;
	        }
        },
        searchChildOrg : false
   	});
	$("#userIntroduceList").trigger("reloadGrid");
}
$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").outerHeight();

	var clearUserUserAutocomplete=true;




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
			searchChildOrg : false,
			searchLockStatus : function(){if(isActivedUsers()=="unlocked"){return "unlocked";}else if(isActivedUsers()=="locked"){return "locked";}return "all";},
			organizationId : function(){return currentOrgId;}
		},
		select : function(event, ui){
			$("#user_autocomplete").attr("userId",ui.item.id);
			clearUserUserAutocomplete=false;
			$("#userIntroduceList").trigger("reloadGrid");
		}
	});


	
	function getSelectedIds(){
		var selectedIds = $("#userIntroduceList").jqGrid("getGridParam", "selarrrow");
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
	//是否锁定 
// 	function isLockFormatter(el,options,rowData){
// 		if(rowData.lock){
// 	        return "是";
// 	    }
// 	    return "否";
//     }
//     function isShutDown(el,options,rowData){
//         if(rowData.shutDown){
//             return "是";
//         }
//         return "否";
//     }
	$("#userIntroduceList").jqGridFunction({
		url:'${path}/sysadmin/userManage/userList.action',
		datatype: "json",
		postData: {
	        "searchLockStatus":  function(){
	            return isActivedUsers();
	        },
	        "organizationId":currentOrgId,
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
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"userName",index:'userName',sortable:true,label:'用户名',width:100},
      		{name:"name",index:"name",sortable:true,label:'用户姓名',width:100},
	        {name:'workPhone',label:'工作电话',sortable:true,align:'center',width:140},
	        {name:'mobile',label:'手机',sortable:true,align:'center',width:100},
	        {name:'lastLoginTime',label:'最后登录时间',width:140,sortable:true},
	        {name:'admin',label:'是否超级管理员',sortable:false,formatter:isAdminFormatter,align:'center',width:100},
	        {name:"roles_name",index:"roles",label:'所在岗位',sortable:false,formatter:getRoleNames,width:90}
// 	        {name:"roles_id",index:"roles",label:'所在岗位',sortable:false,hidden:true,formatter:getRoleIds},
// 	        {name:"roles",index:"roles",label:'所在岗位',sortable:false,hidden:true,align:'center'},
	       // {name:"shutDown",index:"shutDown",label:'是否启用',hidden:true,sortable:false,formatter:isShutDown,align:'center',width:70},
	       // {name:"lock-formatter",index:"lock",label:'是否锁定',sortable:false,formatter:isLockFormatter,align:'center',width:70},
	     //   {name:"lock",index:"lock",label:'是否锁定',sortable:true,hidden:true,hidedlg:true,width:90},
// 	        {name:'workCompany',label:'工作单位或就读学校',sortable:true,width:180}
// 	        <s:if test="@com.tianque.component.ThreadVariable@getUser().isAdmin()">
// 	        ,
// 	        {name:'',label:'同步用户',formatter: asyncoUserFormmater,align:'center',sortable:false}
// 	        </s:if>
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:false,
		loadComplete: afterLoad,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){setUserOperateButton();}
	});
	
	$("#userIntroduceList").closest(".ui-jqgrid-bdiv").css({ "height" : "362" });

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


function findUserByOrgId(){
	//$("#userList").setGridParam({datatype:'json'});
	//$("#userList").trigger("reloadGrid");
	$("#user_autocomplete").removeAttr("userId");
	refresh();
}


function lockUserButtonText(lock){

	if (lock=="true"){
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-chattr'/>解锁</span>");
	}else if(lock=="all"){
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-lock'/>锁定/解锁</span>"); 
	}else{
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-lock'/>锁定</span>");
	}
}

function afterLoad(){
	setUserOperateButton();
}

function setUserOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("userIntroduceList");
	var count = $("#userIntroduceList").jqGrid("getGridParam","records");
	var user =  $("#userIntroduceList").getRowData($("#userIntroduceList").getSelectedRowId());
	var selectedIds = getActualjqGridMultiSelectIds("userIntroduceList");
	if(selectedCounts == count && selectedCounts!=0){
		jqGridMultiSelectState("userIntroduceList", true);
	}else{
		jqGridMultiSelectState("userIntroduceList", false);
	}
	useredOrStopUsedButtom(selectedIds);

}


function useredOrStopUsedButtom(strs){
	var used = false;
	var stopused = false;
	if(isActivedUsers()){
		if(strs.length==0){
			jqGridMultiSelectState("userIntroduceList", false);
		}
	}
}

function doubleClickTable(rowid){
	var userInfo =  $("#userIntroduceList").getRowData(rowid);
	if(userInfo==null || userInfo.id==null){
 		return;
	}
	$("#userMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看用户信息',
		modal : true,
		url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=view&user.id='+userInfo.id,
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
    var selectedIds = $("#userIntroduceList").jqGrid("getGridParam", "selarrrow");

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


</script>
