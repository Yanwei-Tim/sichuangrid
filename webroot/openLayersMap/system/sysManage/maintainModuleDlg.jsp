<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
	<form  method="post" id="maintainForm">
		<pop:token />
		<input type="hidden" name="gisModuleVo.id" value="${gisModuleVo.id }"/>
		<input type="hidden" name="gisModuleVo.isSystemAttribute" value="${gisModuleVo.isSystemAttribute }" />
		<input type="hidden" name="gisModuleVo.isBusinessLayer" value="${gisModuleVo.isBusinessLayer }" />
		<input type="hidden" name="gisModuleVo.isPopulation" value="${gisModuleVo.isPopulation }" />
		<s:if test='"add".equals(mode)'>
			<input type="hidden" name="gisModuleVo.modeType" value="commonMap" />
		</s:if>
		<s:if test='"edit".equals(mode)'>
			<input type="hidden" name="gisModuleVo.isHasSonClass" value="${gisModuleVo.isHasSonClass}" />
		</s:if>
		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">模块名称：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="moduleName" name="gisModuleVo.moduleName" class="form-txt" value="${gisModuleVo.moduleName}" maxlength="20" />
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">表名/类型：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="gisModuleVo.tableName" id="gisModuleVo-tableName" maxlength="20"  value="${gisModuleVo.tableName}" class="dialogtip form-txt" 
				<s:if test='"edit".equals(mode)'>readonly</s:if> />
		</div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">所属网格字段：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="orgFiled" name="gisModuleVo.orgFiled" class="dialogtip form-txt" value="${gisModuleVo.orgFiled}"  maxlength="30" title="用来获取该模块表中的组织机构的id。" />
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">权限内置码：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="permissionName" name="gisModuleVo.permissionName" class="dialogtip form-txt" value="${gisModuleVo.permissionName}"  maxlength="30" />
		</div>
		<div class='clearLine'></div>
		
		<div class="grid_20 lable-right">
			<input id="gisModuleVo-isHasSonClass" type="checkbox"  name="gisModuleVo.isHasSonClass" value="true" 
			<s:if test="gisModuleVo.isHasSonClass" >checked="checked"</s:if> <s:if test='"edit".equals(mode)'>disabled</s:if> />
			<label class="form-check-text">是否允许创建子模块 </label>
		</div>
		<div class='clearLine'></div>
		<div class="functionIncludeDiv">
			<jsp:include page="${path }/openLayersMap/system/sysManage/maintainFunctionDlg.jsp"></jsp:include>
		</div>
	</form>
	<input type="hidden" value="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@gisNeedFields'/>" id="gisNeedFields"/>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var mode="<s:property value='#parameters.mode'/>";
	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/gis/twoDimensionMapModuleManage/addModule.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#maintainForm").attr("action","${path}/gis/twoDimensionMapModuleManage/updateModule.action");
	</s:if>
	<s:if test='"view".equals(mode)'>
		$("#maintainForm").find("input,textarea").attr("disabled",true);
		$("#maintainForm").find("input:button").hide();
	</s:if>
	
	$(".columnSelect").click(function(){
		var tableName=$("#gisModuleVo-tableName").val();
		if(tableName==null || tableName=="" ||tableName==undefined){
			$.messageBox({message:"请输入表名/类型!"});
			return;
		}
		var result = false;
		var isSystemAttribute = $("input[name='gisModuleVo.isSystemAttribute']").val();
		if(isSystemAttribute==1
				&& (tableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PERSON'/>" 
						|| tableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>" 
						|| tableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PERSON'/>") ){
				result="true";
		}else{
			var action = "${path}/gis/tableOperateManage/existTableFindByTableName.action";
			var parameters = { "tableName":tableName };
			result = ajaxSubmit(action,parameters,result);
		}
		if(result=="true"){
			var select=$(this).parent().parent().find("input:text");
			var	selectA=select.eq(0).attr("id");
			var	selectB=select.eq(1).attr("id");
			if($("input[name='gisModuleVo.isSystemAttribute']").val()==1){//判断是否为系统内置
				$("#columnSelectDialog").createDialog({
					width:400,
					height:500,
					title:"字段选择",
					url:"${path}/gis/gisTypeManage/getBeanColumn.action?tableName="+tableName+"&selectB="+selectB,
					buttons: {
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			}else{
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
			}
		}else{
			$.messageBox({level:"error",message:result});
		}
	});
	
	function updateModuleMenu(data){//修改菜单项
		$("#propertiesDomainYear li").each(function(){
   			if($(this).hasClass("select")){
   				$(this).find("a").text(data.moduleName);
   				$("#gisModuleName").val(data.moduleName);
   				$("#gisTableName").val(data.tableName);
   				$("#gisModuleVo-orgFiled").val(data.orgFiled);
   			}
   		})
	}
	
	function addModuleMenu(data){//添加菜单项
		$("#propertiesDomainYear").prepend('<li><a href="javascript:void(0)" class="daily" name="'+data.id+'" >'+data.moduleName.substring(0,5)+'</a><span></span></li>');
		$("#propertiesDomainYear li a").unbind("click").on("click", function(){
			$("#propertiesDomainYear li").removeClass("select");
			getGisModuleVoById($(this).attr("name"));
			$(this).parent().addClass("select");
		});
		$("#propertiesDomainYear li a:first").click();
	}
	
	
	$("#maintainForm").formValidate({
		showErrors:showErrorsForTab,
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			var result = submitValidate();
			if(result!=true && result!="true"){
				$.messageBox({ message:result, evel: "error" });
       			return;
			}
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({evel: "error",message:data});
            			return;
					}
	                if("add"==mode){
                		$.messageBox({message:"模块新增成功!"});
                		addModuleMenu(data);
	                }else if("edit"==mode){
                		$.messageBox({message:"模块修改成功!"});
                		updateModuleMenu(data);
	                }
	               	$("#moduleDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"gisModuleVo.moduleName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"gisModuleVo.tableName":{
				required:true,
				exsistedTableName:true
			},
			"function.searchFieldAName":{
				//required:true
			},
			"function.searchFieldA":{
				//required:true
			}
		},
		messages:{
			"gisModuleVo.moduleName":{
				required:"请输入模块名字",
				exculdeParticalChar:"姓名只能输入字母，数字和中文字符",
				minlength:$.format("姓名至少需要输入{0}个字符")
			},
			"gisModuleVo.tableName":{
				required:"请输入表名",
				exsistedTableName:"表名已存在"
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
	jQuery.validator.addMethod("exsistedTableName", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		
		if("edit"==mode){
			return true;
		}
		var isOk=true;
		$.ajax({
			async:false,
			url:"${path}/gis/twoDimensionMapModuleManage/isExistTableName.action",
			data:{
				"gisModuleVo.tableName":value
			},
			success:function(responseData){
				if(!responseData){
					isOk=false;
	       		}
			}
		});
		return isOk;
	});

	function submitValidate(){
		var checked = $("#gisModuleVo-isHasSonClass").attr("checked");
		var tableName = $("#gisModuleVo-tableName").val();
		var isSystemAttribute = $("input[name='gisModuleVo.isSystemAttribute']").val()
		if(checked=="checked" || isSystemAttribute>0){
			return true;
		}
		var result;
		var action = "${path}/gis/tableOperateManage/existTableFindByTableName.action";
		var parameters = { "tableName":tableName };
		result = ajaxSubmit(action,parameters,result);
		if(result==true  || result=="true"){
			action = "${path}/gis/tableOperateManage/existTableFieldsFindByTableNameAndFields.action";
			parameters = { "tableName":tableName, "fields":constructFields }
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
})
	
</script>

