<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
ul#selectzones {margin: 0; padding: 0;}
ul#selectzone {margin: 0; padding: 0;}
ul#selectzones li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
ul#selectzones span.ui-icon {float: left; margin: 0 4px;}
.defaultButton{background:url(${resource_path}/resource/images/button.jpg) no-repeat;border:0;width:54px;height:24px;line-height:24px;text-align:center;color:#2C6EA2;}
</style>
<div id="dialog-form" title="账号配置" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
<%-- 	 	<input id="orgLevel" name="user.organization.orgLevel.id" value="${user.organization.orgLevel.id}"/> --%>
		<div class="grid_3 lable-right">
			<label class="form-lbl">层级选择：</label>
		</div>
		<div class="grid_7">
	    	<input id="userOrganization" type="text" name="user.organization.orgName" class="form-txt" />
	    	<input id="userOrganizationId" type="hidden" name="orgId" class="form-txt" />
		</div>
		<div class="grid_2 lable-right">
			<label class="form-lbl">用户名：</label>
		</div>
		<div class="grid_6">
			<input id="userName" type="text" name="user.userName" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">账号类型：</label>
		</div>
		<div class="grid_6">
	    	<select name="user.accountType" class="form-select" id="accountType">
	    		<option value='' >全部</option>
		   		<option value='1' >PC</option>
		   		<option value='2' >手机</option>
			</select>
		</div>
<!-- 		<div class="grid_3 lable-right"> -->
<!-- 			<label class="form-lbl">应用范围：</label> -->
<!-- 		</div> -->
<!-- 		<div class="grid_6"> -->
<%-- 	    	<select name="addRoleType" class="form-select" id="addRoleType"> --%>
<!-- 		   		<option value='1' >本页选中应用</option> -->
<!-- 		   		<option value='2' >全部应用</option> -->
<%-- 			</select> --%>
<!-- 		</div> -->
   		<div class="grid_3 lable-right" >
  			<input type="button" value="查 询" id="search_users" style="width: 50px;height: 25px;"/>
  		</div>
	</form>
	<div class='clearLine'></div>
	<div style="width:100%;">
	   	 <table id="roleTable" ></table>
	   	 <div id="roleTablePager"></div>
	</div>
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
	    inputName:"orgId",
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
	var tree ;
	$("#"+selfId+"-tree").height(defaultOption.maxHeight).width(defaultOption.listWidth);
	$.ajax({
		url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+'<s:property value="#parameters.orgId"/>',
		success:function(data){
			tree = $("#"+selfId+"-tree").initAdministrativeTree({shouldJugeMultizones:true,allOrg:defaultOption.allOrg,url:url});
			tree.on("click",function(node,e) {
		        comboWithTooltip.setRawValue(node.text);
		        comboWithTooltip.collapse();
		        $("input[name='"+defaultOption.inputName+"']").val(node.id);
			});
			$.searchChild(tree,data);
		}
	});

	$("#roleTable").jqGridFunction({
		 datatype: "local",
		 height:290,
		 width:670,
// 		 colModel:[
// 		          {name:"id",index:"id",hidden:true},
// 		          {name:"userName",index:'userName',label:'用户名'},
// 		      	  {name:"name",index:"name",label:'用户姓名'},
// 		      	  {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格"},
// 			      {name:"roles",index:"roles",label:'所在岗位',sortable:false,formatter:getRoleNames,align:'center'}
// 				],
		colModel:[
				{name:"id",index:"id",hidden:true,sortable:false},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"userName",index:'userName',sortable:true,label:'用户名',width:100},
				{name:"name",index:"name",sortable:true,label:'用户姓名',width:100},
				{name:'workPhone',label:'工作电话',sortable:true,align:'center',width:140,hidden:true},
				{name:'mobile',label:'手机',sortable:true,align:'center',width:100,hidden:true},
				{name:'isLogin',label:'在线状态',sortable:false,formatter:isLoginFormatter,align:'center',width:100},
				{name:'lastLoginTime',label:'最后登录时间',width:140,sortable:true},
				{name:'admin',label:'是否超级管理员',sortable:false,formatter:isAdminFormatter,align:'center',width:100,hidden:true},
				{name:'fourthAccount',label:'是否四级体系建设账号',sortable:false,formatter:isFourthAccountFormatter,align:'center',width:100,hidden:true},
				{name:'gps',label:'是否开启GPS定位',sortable:false,formatter:isGPSFormatter,align:'center',width:100,hidden:true},
				{name:'fourTeams',label:'是否开启四支队伍',sortable:false,formatter:isFourTeamsFormatter,align:'center',width:100,hidden:true},
				{name:"roles_name",index:"roles",label:'所在岗位',sortable:false,formatter:getRoleNames,width:90},
				{name:"organization.orgLevel.displayName",sortable:true,index:"orgLevel",label:'所在层级',width:100},
				{name:"shutDown",index:"shutDown",label:'是否启用',hidden:true,sortable:false,formatter:isShutDown,align:'center',width:70},
				{name:"lock-formatter",index:"lock",label:'是否锁定',sortable:false,formatter:isLockFormatter,align:'center',width:70,hidden:true},
				{name:"lock",index:"lock",label:'是否锁定',sortable:true,hidden:true,hidedlg:true,width:90},
				{name:"createDate",index:"createDate",label:'新增时间',sortable:true,hidden:true,width:90},
				{name:"activationTime",index:"activationTime",label:'激活时间',sortable:true,width:120},
				{name:"state",index:"state",label:'账号状态',sortable:true,width:90,formatter:stateFormatte},
				{name:'workCompany',label:'工作单位或就读学校',sortable:true,width:180,hidden:true}
		],
		multiselect:true,
		onSelectRow:selectRow
	});
	$("#search_users").click(function(){
		$("#roleTable").setGridParam({
			url:"${path}/sysadmin/userManage/userList.action",
// 			url:"${path}/sysadmin/roleManage/findUsersList.action",
			datatype:'json',
			page:1
		});
		var selectedId = $("#roleList").getGridParam("selrow");
		var role =  $("#roleList").getRowData(selectedId);
		$("#roleTable").setPostData({
			'organizationId':$("#userOrganizationId").val(),
			"user.userName":$("#userName").val(),
			"user.accountType":$("#accountType").val(),
			"searchLockStatus":"all",
			"searchChildOrg":true,
			"user.organization.orgLevel.id":role['useInLevel.id'] 
		});
		
		$("#roleTable").trigger("reloadGrid");
		
	});
});

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

function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("roleTable");
	var count = $("#roleTable").jqGrid("getGridParam","reccount");
	if(selectedCounts == count&&selectedCounts!=0){
		jqGridMultiSelectState("roleTable", true);
	}else{
		jqGridMultiSelectState("roleTable", false);
	}
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
</script>