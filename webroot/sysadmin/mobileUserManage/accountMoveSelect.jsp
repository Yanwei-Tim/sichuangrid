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
<div id="dialog-form" title="账号转移" class="container container_24">
<!-- 		<div class="grid_9 lable-right"> -->
<!-- 			<label class="form-lbl">所选账号层级：</label> -->
<!-- 		</div> -->
<!-- 		<div class="grid_13"> -->
<%-- 	    	<input id="selectAccountsLevel" value="${dict.displayName }" type="text" class="form-txt" readonly="readonly"/> --%>
<%-- 	    	<input id="selectInternalId" type="hidden" value="${dict.internalId }" class="form-txt" readonly="readonly"/> --%>
<!-- 		</div> -->
		<div class="grid_4 lable-right">
			<label class="form-lbl">转移到：</label>
		</div>
		<div class="grid_17">
	    	<input id="userOrganization" type="text" name="user.organization.orgName" class="form-txt" />
	    	<input id="userOrganizationId" type="hidden" name="orgId" class="form-txt" />
	    	<input id="orgInternalCode" type="hidden" name="orgInternalCode" class="form-txt" />
	    	<input id="internalId" type="hidden" name="internalId" class="form-txt" />
		</div>
		<div class='clearLine' style="padding-top: 8px;"></div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">岗位：&nbsp;&nbsp;&nbsp;&nbsp;</label>
		</div>
		<div class="grid_15" style="height: 50px;">
			<textarea rows="3"  cols="2" style="width: 100% ; background-color: white;" readonly="readonly"  id="roles_panel" ></textarea>
			<select id="roleIds" name="roleIds" multiple="multiple"  style="display:none"></select>
			<br/>
			<span style="color:red;">注：选择的岗位将会替换所选用户原有岗位</span>
		</div>
		<div class="grid_2" style="padding-left: 24px;">
			<input id="selectRoles" type="button" value="选择岗位" class="form-button">
		</div>
<!-- 		<div class='clearLine' style="padding-top: 8px;"></div> -->
<!-- 		<div class="grid_18 lable-right"> -->
<%-- 			<span style="color:red;">注：选择的岗位将会替换所选用户原有岗位</span> --%>
<!-- 		</div> -->
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
		        $("#internalId").val(node.attributes.orgLevelInternalId);
		        $("#orgInternalCode").val(node.attributes.orgInternalCode);
			});
			$.searchChild(tree,data);
		}
	});
	$("#selectRoles").click(function(event){
		$("#rolesDialog").createDialog({
			width: 500,
			height: 500,
			title:'请选择岗位',
			url:'${path}/sysadmin/mobileUserManage/searchSelectRoleDlg.jsp',
			buttons: {
			   	"确定" : function(){
			   		fillSelectRolesInputer("roles_panel","roleIds");
					$("#rolesDialog").dialog("close");
			   	},
			   	"取消":function(){
					$("#rolesDialog").dialog("close");
			   	}
			}
		});
	});
});
</script>