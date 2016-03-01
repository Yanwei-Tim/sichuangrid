<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div class="content" >
	<div class="ui-corner-all" id="nav" style="position:relative;" ></div>
	<form id="matchupMobileUserRoleForm" action="${path}/sysadmin/userManage/matchupMobileUserRole.action" method="post">
		<pop:token/>
		<input type="hidden" name="organizationId" id="organizationId" value='<s:property value="#parameters.organizationId"/>' /> 
		<input type="hidden" id="userIds" name="userIds" value='<s:property value="#parameters.userIds"/>' />
		<input type="hidden" id="selectedRoleIds" name="selectedRoleIds" value='' />
	</form>
	<div style="width: 100%">
		<table id="mobileUserRoleList"> </table>
		<!-- <div id="mobileUserRoleListPager"></div> -->
	</div>
	<div id="mobileUserRoleMaintanceDialog"></div>
	
</div>
<script type="text/javascript">
function isRoleNull(){
	var selectedIds = $("#mobileUserRoleList").jqGrid("getGridParam", "selarrrow");
	 if(null!=selectedIds && ""!=selectedIds &&  typeof(selectedIds)!="undefined" ){
		$("#selectedRoleIds").attr("value",selectedIds);
		return true;
	}
	return false;
	
}

jQuery.validator.addMethod("checkRoleIds", function(value, element){
	var selectedIds = $("#mobileUserRoleList").jqGrid("getGridParam", "selarrrow");
	 if(null!=selectedIds && ""!=selectedIds &&  typeof(selectedIds)!="undefined" ){
		$("#selectedRoleIds").attr("value",selectedIds);
		return true;
	}
	return false;
});
$(document).ready(function(){
	 $("#mobileUserRoleList").jqGridFunction({
		  url: "${path}/sysadmin/userManage/matchupMobileUserRoleList.action",
		  datatype: "json",
		  postData: {
		        "organizationId":  function(){
		        	if($('#organizationId').val()!=''){
			        	return $('#organizationId').val();
			        }else{
				        return 0;
				        }
		        },
		        "userIds" : function(){
			        if($('#userIds').val()!=''){
			        	return $('#userIds').val();
			        }else{
				        return 0;
				        }
		        }
	        },
		 colModel:[
		          {name:"id",index:"id",hidden:true},
		   	      {name:"roleName",index:'roleName',label:'岗位名称',sortable:false},
		   	      {name:"description",index:"description",label:'岗位描述',sortable:false}
				],
		multiselect:true,
		loadComplete:selectRoles,
		sortname:'roleName',
		sortorder:'desc',
		rowNum : 500,
		viewrecords:true,
		width:540,
		height: 290,
		jsonReader:{
		repeatitems:false,
		id:"0"
		}
	});
});

$("#matchupMobileUserRoleForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form){
		if(!isRoleNull()){
			$.messageBox({
				message:"岗位不能为空",
				level: "warn"
			 });
			return;
		}
		
		$(form).ajaxSubmit({
			success:function(data){
				if(!data){
       	 			$.messageBox({
						evel: "error",
						message:data
		 			});
        			return;
				}
				$.messageBox({message:"手机账号匹配岗位成功！"});
                $("#mobileUserList").trigger("reloadGrid");
                $("#mobileUserMaintanceDialog").dialog("close");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
	      			alert("提交数据时发生错误");
   		    }
		});
	},
	rules:{
		
	},
	messages:{
		
	}
});

function selectRoles(){

}
</script>