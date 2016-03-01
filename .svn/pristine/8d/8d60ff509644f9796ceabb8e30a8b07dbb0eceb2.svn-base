<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<form id="maintainFormA" method="post">
			<pop:token />
			<input type="hidden" id="gisTypeManage-id" name="gisTypeManage.id" value="${gisTypeManage.id }">
			<input type="hidden" id ="gisTypeManage-innerKey" name="gisTypeManage.innerKey" value="${gisTypeManage.innerKey }">
			<input type="hidden"  name="gisTypeManage.checked" value="${gisTypeManage.checked }">
			
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">类型名称：</label>
			</div>
			<div class="grid_6">	
				<input type="text" id="gisTypeManage-name" name="gisTypeManage.name" value="${gisTypeManage.name}" 
				maxlength="20" <s:if test='"view".equals(mode)'>disabled</s:if> class="dialogrow form-txt"/>
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">表名：</label>
			</div>
			<div class="grid_6">	
				<input type="text" id="gisTypeManage-tableName" name="gisTypeManage.tableName" value="${gisTypeManage.tableName}" maxlength="30" 
					<s:if test='"edit".equals(mode)'>readonly</s:if> class="dialogrow form-txt"/>
			</div>
			<div class="clearLine"></div>
			
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">类型Key：</label>
			</div>
			<div class="grid_6">	
				<input type="text" id="gisTypeManage-key" name="gisTypeManage.key" value="${gisTypeManage.key}" maxlength="60"  class="dialogrow form-txt"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">所属网格字段：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="orgFiled" name="gisTypeManage.orgFiled" class="form-txt dialogtip" value="${gisTypeManage.orgFiled}" maxlength="30" title="用来获取该模块表中的组织机构的id。" />
			</div>
			<div class='clearLine'></div>
			
		   	<div class="functionIncludeDiv">
				<jsp:include page="${path }/openLayersMap/system/sysManage/maintainFunctionDlg.jsp"></jsp:include>
			</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
		$("#maintainFormA").attr("action","${path}/gis/gisTypeManage/addGisTypeManage.action");
	</s:if>
	<s:else>
		$("#maintainFormA").attr("action","${path}/gis/gisTypeManage/updateGisTypeManage.action");
	</s:else>
	<s:if test='"view".equals(mode)'>
		$("#maintainFormA").find("input,textarea").attr("disabled",true);
		$("#maintainFormA").find("input:button").hide();
	</s:if>
	
	$(".columnSelect").click(function(){
		var tableName=$("#gisTypeManage-tableName").val();
		if(tableName==null || tableName=="" ||tableName==undefined){
			$.messageBox({message:"请输入表名/类型!"});
			return;
		}
		var innerKey = $("input[name='gisTypeManage.innerKey']").val();
		var action = "${path}/gis/tableOperateManage/existTableFindByTableName.action";
		var parameters = { "tableName":tableName };
		var select=$(this).parent().parent().find("input:text");
		var	selectA=select.eq(0).attr("id");
		var	selectB=select.eq(1).attr("id");
		if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PERSON'/>" 
				|| innerKey=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>" 
				|| innerKey=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PERSON'/>" ){
			$("#columnSelectDialog").createDialog({
				width:400,
				height:500,
				title:"字段选择",
				url:"${path}/gis/gisTypeManage/getBeanColumn.action?innerKey="+innerKey+"&selectB="+selectB,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			var result = ajaxSubmit(action,parameters,result);
			if(result=="true"){
				$("#columnSelectDialog").createDialog({
					width:600,
					height:600,
					title:"字段选择",
					url:"${path}/gis/gisTypeManage/getCodeByTableName.action?tableName="+tableName+"&selectA="+selectA+"&selectB="+selectB,
					buttons: {
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			}else{
				$.messageBox({level:"error",message:result});
			}
		}
	});
	
	$("#maintainFormA").formValidate({
		showErrors:showErrorsForTab,
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			var result = submitValidate();
			if(result!=true && result!="true"){
				$.messageBox({evel: "error", message:result});
				return;
			}
			$(form).ajaxSubmit({
				success:function(data){
					var innerType = $("#gisTypeManage-innerKey").val();
			    	<s:if test='"add".equals(mode)'>
			    		$("#class-typeManageList").addRowData(data.id,data,"first");
	    				$.messageBox({message:"类型新增成功!"});
					</s:if>
					<s:else>
						$("#class-typeManageList").setRowData(data.id,data);
	    				$.messageBox({message:"类型修改成功!"});
					</s:else>
			    	$("#editGisTypeManageDiv").dialog("close");
				} 
			})
		},
		rules:{
			"gisTypeManage.name":{
				required:true,
				maxlength:30,
				exsistedGisTypeManage:true
			},
			"gisTypeManage.key":{
				required:true,
				exsistedGisTypeManage:true
			},
			"gisTypeManage.tableName":{
				required:true
			},
			"function.searchFieldAName":{
				//required:true
			},
			"function.searchFieldA":{
				//required:true
			}
		},
		messages:{
			"gisTypeManage.name":{
				required:"请输入类型名称 ",
				maxlength:$.format("类型名称最多只能输入{0}个字符"),
				exsistedGisTypeManage:"此类型名称已经被使用，请重新输入"
			},
			"gisTypeManage.key":{
				required:"请选择类型key",
				exsistedGisTypeManage:"此类型key已经被使用，请重新输入"
			},
			"gisTypeManage.tableName":{
				required:"请输入表名"
			},
			"function.searchFieldAName":{
				required:"请输入搜索条件A的中文名称"
			},
			"function.searchFieldA":{
				required:"请输入搜索条件A的字段"
			}
		}
	});
	$(".dialogtip").inputtip();
	jQuery.validator.addMethod("exsistedGisTypeManage", function(value, element){
		var isOk=true;
		$.ajax({
			async:false,
			url:"${path}/gis/gisTypeManage/hasDuplicateGisTypeManage.action",
			data:{
				"gisTypeManage.id":$("#gisTypeManage-id").val(),
				"gisTypeManage.name":$("#gisTypeManage-name").val(),
				"gisTypeManage.key":$('#gisTypeManage-key').val()
			},
			success:function(responseData){
				if(responseData) isOk  = false;
			}
		});
		return isOk;
	});

	function submitValidate(){
		if($("#gisIsSystemAttribute").val()=="<s:property value='@com.tianque.openLayersMap.util.SystemProperty@systemProperty'/>"){
			return true;
		}
		var tableName = $("#gisTypeManage-tableName").val();
		var result;
		var action = "${path}/gis/tableOperateManage/existTableFindByTableName.action";
		var parameters = { "tableName":tableName };
		result = ajaxSubmit(action,parameters,result);
		if(result==true ||result=="true"){
			action = "${path}/gis/tableOperateManage/existTableFieldsFindByTableNameAndFields.action";
			parameters = {
				"tableName":tableName,
				"fields":constructFields
			}
			result = ajaxSubmit(action,parameters,result);
		}
		return result;
	}

	function ajaxSubmit(action,parameters,result){
		$.ajax({
			async:false,
			url:action,
			data:parameters,
			success:function(responseData){
				result = responseData;
			}
		});
		return result;
	}
});
</script>		