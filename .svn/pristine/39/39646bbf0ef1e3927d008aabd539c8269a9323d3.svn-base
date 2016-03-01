<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
<s:if test='!"view".equals(mode)'>
	jQuery.validator.addMethod("roleNameInLevel", function(value, element){
		if(!$('#roleName').val()){
           return false;
		}else return true;
	});
	var validator = $("#maintainForm").formValidate({
		promptPosition: "topLeft",
		ignore:'input:checkbox',
		submitHandler: function(form) {
			var roleJSONStr = "";
			for(var _p in roleJsonStr){
				if(roleJsonStr[_p]!=null){
					roleJSONStr += _p +":"+roleJsonStr[_p]+", ";
				}
			}
			if(roleJSONStr != ''){
				roleJSONStr = roleJSONStr.substr(0, roleJSONStr.length - 2);
			}
			$("#roleJson").val(roleJSONStr);
	         $(form).ajaxSubmit({
	             success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"
						 });
                     	return;
                     }
        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode)'>
// 				    	$("#roleList").addRowData(data.id,data,"first");
				    	 $("#roleList").trigger("reloadGrid");
				    	$.messageBox({message:"创建岗位成功!"});
				    	$("#maintainForm").resetForm();
			     		$("#roleDialog").dialog("close");
				     </s:if>
				     <s:if test='"edit".equals(mode)'>
				        $("#roleList").trigger("reloadGrid");
					    $.messageBox({message:"修改岗位成功!"});
				     	$("#roleDialog").dialog("close");
				     </s:if>
				     <s:if test='"addLeaderView".equals(mode)'>
				        $("#roleList").trigger("reloadGrid");
					    $.messageBox({message:"领导视图岗位配置成功!"});
				     	$("#roleDialog").dialog("close");
				     </s:if>
				     
				     //$("#roleList").setSelection(data.id);"addLeaderView".equals(mode)
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
		rules: {
			"role.roleName" :{
			   required: true,
			   exculdeParticalChar:true
			   <s:if test='!"addLeaderView".equals(mode)'>
		       ,remote: {
	               data: {
	                    "role.roleName": function(){
	                        return $('#roleName').val();
	                    },
	                    "mode" : function(){
	                        return $('#mode').val();
	                    },
	                    "role.id" : function(){
	                        return $('#roleid').val();
	                    },
	                    "role.useInLevel.id":function(){
	                         return $('#useInl').val();
	                    }
	                },
	        	   url: "${path}/sysadmin/roleManage/validateRoleName.action",
	        	   type: "get"
	        }
		    </s:if>
			},
			"role.description":{
				maxlength:100
			},
			"role.useInLevel.id": {
				required: true
			},
			"role.workBenchType.id": {
				required: true
			}
		},
		messages: {
			"role.roleName" :{
			   required:  "岗位名称不能为空!",
			   roleNameInLevel : "岗位名称不能为空!",
			   exculdeParticalChar:"岗位名称只能输入字母，数字和中文字符"
			   <s:if test='!"addLeaderView".equals(mode)'>
				,remote     : "岗位名已经存在"
			   </s:if>
			},
			"role.description":{
				maxlength:$.format("备注最多输入{0}个字符")
			},
			"role.useInLevel.id": {
				required   : "请选择应用级别!"
			},
			"role.workBenchType.id": {
				required   : "请选择工作台类型!"
			}
		}
	});

</s:if>

  	$("#permissionTree").jqGrid({
  		<s:if test='!"addLeaderView".equals(mode)'>
	   	url: "${path}/sysadmin/roleManage/preparePermissionTree.action?role.id=" + $("#roleid").val(),
	   	</s:if>
	   	<s:if test='"addLeaderView".equals(mode)'>
	   	url:"${path}/sysadmin/roleManage/prepareLeaderViewPermissionTree.action?role.id=" + $("#roleid").val(),
	   	</s:if>
	    treeGrid: true,
		treeGridModel : 'adjacency',
		hidegrid: false,
		datatype: "json",
		height:220,
		mtype: "POST",
		editable: false,
		caption:"权限列表",
		width: 520,
	   	colModel:[
	   		{name:"permission.id", width:1,hidden:true,key:true,sortable:false},
	   		{name:"permission.cname",label:"模块",width:180,sortable:false},
	   		{name:"",label:"",width:20,sortable:false,formatter:choosePermissionModule},
	   		{name:"check",label:"权限",sortable:false,width: 310,editable:false,formatter:choosePermission}
	   	],
	   	rowNum:-1,
	   	treeReader : {
	   	   	level_field: "level",
			parent_id_field: "parentEname",
			leaf_field: "leaf",
			expanded_field: "expanded"
	   	},
	   	jsonReader: {
			repeatitems : false,
			id: "0"
		},
		loadComplete:function(){
			var treeList=$("#permissionTree").find(".tree-wrap-ltr");
			for(var i=0;i<treeList.length;i++){
				$(treeList[i]).prependTo($(treeList[i]).parent().parent().children("td")[1]);
			}
		}
   	});
});

var roleJsonStr = {};
function checkRole(dom){
	var thirdTd=$(dom).parents('tr').find("td:eq(3)").text();
	if($(dom).attr("checked")){
		if($(dom).attr("isnull")!=='true'){
			checkParentNodes(dom);
			roleJsonStr[$(dom).val()] = null;
		}else{
			roleJsonStr[$(dom).val()] = true;
			checkParentNodes(dom);
		}
		if(thirdTd!=""){
			$("input[type='checkbox'][id="+$(dom).val()+"]").each(function (){
				$(this).attr("checked",true);
				checkRole($(this)[0]);
			});
		}
	}else{
		if($(dom).attr("isnull")==='true'){
			roleJsonStr[$(dom).val()] = null;
		}else{
			roleJsonStr[$(dom).val()] = false;
		}
		if(thirdTd!=""){
			$("input[type='checkbox'][id="+$(dom).val()+"]").each(function (){
				$(this).attr("checked",false);
				checkRole($(this)[0]);
			});
		}
	}
}
function checkParentNodes(dom){
	var nodeValue=$(dom).val();
	if(nodeValue==""||nodeValue==null||nodeValue<-1){
		return;
	}
	var nodeId=$(dom).attr("id");
	if(nodeId!=""&&nodeId!=null){
		if($(dom).attr("isnull")==="true")
			roleJsonStr[nodeValue] = true;
		else
			roleJsonStr[nodeValue] = null;
	}
	$(dom).attr("checked",true);
	if(nodeValue==nodeId){
		return;
	}
	var domTemp =$("input[type='checkbox'][value="+nodeId+"]");
	if(domTemp!=null){
		if($(domTemp).attr("checked")==true||$(domTemp).attr("checked")=="checked"){
			return;
		}else{
			checkParentNodes(domTemp);
		}
		
	}
};

function choosePermissionModule(el,options,rowData){
	var bchk =  rowData.check ?  " checked='checked' " : " isnull='true' ";
	var submitName = " value=\""+ rowData.permission.id + "\"";
	var submitId = " id=\""+ rowData.permission.id + "\"";
	if(rowData.permission&&rowData.permission.parent){
		submitId = "id=\""+ rowData.permission.parent.id + "\" ";
	}
	if (rowData.permission.permissionType==0) return;
   <s:if test='!"view".equals(mode)'>
   		<s:if test='"addLeaderView".equals(mode) '>
   		return "<input type=\"checkbox\"  disabled " + submitName + bchk  +submitId + " onclick='checkRole(this)'/>";
   		</s:if><s:else>
   		return "<input type=\"checkbox\" " + submitName + bchk  +submitId + " onclick='checkRole(this)'/>";
   		</s:else>
      
   </s:if><s:else>
      return "<input type=\"checkbox\" disabled " + bchk  +  "/>";
   </s:else>
};
function choosePermission(el,options,rowData){
	if (rowData.leaf){
		var result="";
		var index;
		var moduleName=rowData.permission.ename;
		for (index = 0;index<rowData.children.length;++index){
			var buttonPermission=rowData.children[index];
			var bchk =  buttonPermission.check ?  "checked='checked'" : " isnull='true' ";
			var submitName = " value=\""+ buttonPermission.permission.id + "\"";
			var submitId = "id=\""+ buttonPermission.permission.parent.id + "\" ";
			<s:if test='!"view".equals(mode)'>
		      result=result+"<div style='float:left'><label style='cursor:pointer;'>"+buttonPermission.permission.cname+"<input type=\"checkbox\" " + submitName + bchk + submitId + " onclick='checkRole(this)'/>"+"</label></div>";
			</s:if>
			<s:else>
		      result=result+"<div style='float:left'><label style='cursor:pointer;'>"+buttonPermission.permission.cname+"<input type=\"checkbox\" disabled " + submitName + bchk + submitId + "/>"+"</label></div>";
		   </s:else>
			   if((index+1)%3==0){
				   result=result+"<br/>";
			   }
		}
		return result;
	}else{
		return "";
	}
};

</script>
<div id="dialog-form" title="岗位维护" class="container container_24">
<s:if test='"edit".equals(mode)  || "addLeaderView".equals(mode)'>
	<form id="maintainForm"  method="post" action="${path}/sysadmin/roleManage/updateRole.action">
	<pop:token />
</s:if>
<s:if test='"add".equals(mode)'>
	<form id="maintainForm"  method="post" action="${path}/sysadmin/roleManage/addRole.action">
	<pop:token />
</s:if>
<s:if test='"copy".equals(mode) '>
	<form id="maintainForm"  method="post" action="${path}/sysadmin/roleManage/copyRole.action">
	<pop:token />
</s:if>
  <input id="mode"	type="hidden" name="mode" value="${mode}" />
  <input id="roleJson" type="hidden" name="roleJson" />
  <input id="roleid" type="hidden" name="role.id" value="${role.id}" />
  <input id="createUser" type="hidden" name="role.createUser"	value="${role.createUser}" />
  <input id="createDate" type="hidden" name="role.createDate"
      value='<s:date name="role.createDate" format="yyyy-MM-dd HH:mm:ss"/>' />

<div class="grid_5 lable-right">
<em class="form-req">*</em>
	<label class="form-lbl">应用层级：</label>
	
</div>
<div class="grid_7">
<s:if test='"view".equals(mode)  || "addLeaderView".equals(mode)'>
         <select name="role.useInLevel.id" class="form-select" id="useInl"
		    <s:if test='"view".equals(mode) ||"addLeaderView".equals(mode) '>disabled='true'</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" defaultValue="${role.useInLevel.id}" />
	</select>
</s:if>	
			
<s:if test='"edit".equals(mode) || "add".equals(mode) || "copy".equals(mode) '>
	<select name="role.useInLevel.id" class="form-select"   id="useInl">
		<s:if test='"add".equals(mode)'>
			<option></option>
		</s:if>
		<s:iterator value="orgLevelDict">
			<option value='<s:property value="id"/>' 
				<s:if test="role.useInLevel.id == id">
					selected="selected"
				</s:if>
			> 
				<s:property value="displayName"/>
			</option>
		</s:iterator>	
	</select>	
</s:if>		
</div>

<div class="grid_5 lable-right">
<em class="form-req">*</em>
     <label class="form-lbl">岗位名称：</label>
  </div>
  <div class="grid_7">
    <input type="text" name="role.roleName" id="roleName" maxlength="20"
	  <s:if test='"view".equals(mode) ||"addLeaderView".equals(mode) '>readonly</s:if> value="${role.roleName}"  class="form-txt" />
  </div>
  
<div class='clearLine'>&nbsp;</div>
<!--  -->
<div class="grid_5 lable-right">
<em class="form-req">*</em>
	<label class="form-lbl">工作台类型：</label>
	
</div>
<div class="grid_7">
     <select name="role.workBenchType.id" class="form-select" id="useInl"
		 <s:if test='"view".equals(mode) ||"addLeaderView".equals(mode) '>disabled='true'</s:if>>
		 <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKBENCH_TYPE" defaultValue="${role.workBenchType.id}" />
	</select>
</div>

<div class='clearLine'>&nbsp;</div>
  <div class="grid_5 lable-right">
  	<label class="form-lbl">岗位描述： </label>
  </div>
  <div class="grid_18">
    <textarea name="role.description" id="description"
	  class="form-txt" style="height: 3em;"
	  <s:if test='"view".equals(mode) ||"addLeaderView".equals(mode) '>readonly</s:if>>${role.description}</textarea>
  </div>
  <div class='clearLine' style="height: 30px">&nbsp;</div>
  <div style="line-height:normal;!important;">
     <table id="permissionTree" style="*border-bottom:1px solid #a6c9e2;line-height:40px;width:500px  height:205px;overflow-x:auto"></table>
  </div>
<s:if test='!"view".equals(mode)'>

</form>
</s:if>
</div>