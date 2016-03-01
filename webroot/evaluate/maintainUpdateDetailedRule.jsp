<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
	
	<c:if test='${"add"==mode }'>
   	<form id="routes-form" method="post" action="${path }/evaluate/evaluateManage/addDetailedRule.action" >
	<input id="parentRuleId" type="hidden" name="detailedRule.parentRule.id" value="" />
    </c:if>
	<c:if test='${"edit"==mode }'>
   	<form id="routes-form" method="post" action="${path }/evaluate/evaluateManage/updateDetailedRule.action" >
		<s:if test="detailedRule.parentRule.id">
			<input id="parentRuleId" type="hidden" name="detailedRule.parentRule.id" value="${detailedRule.parentRule.id }" />
		</s:if>
    </c:if>
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<c:if test='${"edit"==mode }'>
			<input id="detailedRule.id" type="hidden" name="detailedRule.id"  value="${detailedRule.id }"/>
		</c:if>
		<input id="detailedRule.evaluate.id" type="hidden" name="detailedRule.evaluate.id"  value="${detailedRule.evaluate.id }"/>
		<div style="clear:both"></div>
		
		<div class="grid_4 lable-right">
		<c:if test='${"view"!=mode }'>
				<em class="form-req">*</em>
		</c:if>
			<label>考核细则：</label>
		</div>
		<div class="grid_20" style="height: 100px">
			<textarea name="detailedRule.content" class="form-txt" style="overflow-y:scroll;height:90px;"
			 id="content" <s:if test='"view".equals(mode)'>disabled='true'</s:if>>${detailedRule.content}</textarea>
		</div>
		
		
		<div style="clear:both"></div>
		<c:if test='${parameters.standardScore[0]!="" }'>
			
			<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
			</s:if>
				<label>标准分：</label>
			</div>
			<div class="grid_20">
				<input type="text"  name="detailedRule.standardScore" class="form-txt" maxlength="60"
				 id="score" value="${detailedRule.standardScore}" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
			</div>
			
		</c:if>
		<div>
			
			<div class="grid_4 lable-right">
			<c:if test='${"view"!=mode }'>
					<em class="form-req">*</em>
			</c:if>
					<label class="form-lbl">标准类型:</label>
			</div>
			<div class="grid_20">
				<select name="detailedRule.ruleType.id" id="type" class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETAILED_RULE_TYPE" defaultValue="${detailedRule.ruleType.id}"/>
				</select>
			</div>
		</div>
		
	</form>
</div>
<c:if test='${"view"!=mode }'>
<script type="text/javascript">
$(document).ready(function(){
	var mode = $("#mode").val();
	var rowid = $("#itemsList").jqGrid('getGridParam','selrow');
	var nextId = $("#"+rowid).next().attr("id");
	var rowData = $("#itemsList").getRowData(nextId);
	if(rowData.treelevel == 2 && mode == "edit" && rowData.standardScore != ""){
		$("#score").attr("readonly","readonly")
	}else{
		if($(rowData.treelevel).text() == 2 && mode =="edit" && rowData.standardScore != ""){
			$("#score").attr("readonly","readonly")
		}
	}
	$("#routes-form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		<c:if test='${"add"==mode}'>
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			if(rowId){
				$("#parentRuleId").val(rowId);
			}
			if(Number(<s:property value="#parameters.level" />) == 4){
				$("#parentRuleId").val("");
			}
		</c:if>
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
	       	 			$.messageBox({
							message:data,
							level: "error"
			 			});
	        			return;
					}
					<c:if test='${"add"==mode}'>
					$.messageBox({
						message:"考核细则添加成功"
		 			});
		 			</c:if>
		 			<c:if test='${"edit"==mode}'>
					$.messageBox({
						message:"考核细则修改成功"
		 			});
		 			</c:if>
					reload();
					
					$("#rulesDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"detailedRule.content":{
				required:true,
				maxlength:100
			},
			"detailedRule.standardScore":{
				required:true,
				positiveInteger:true,
				min:1,
				max:1000
			},
			"detailedRule.ruleType.id":{
				required:true
			}
		},
		messages:{
			"detailedRule.content":{
				required: "请输入考核细则",
				maxlength: "输入的字符过长，必须小于100个"
			},
			"detailedRule.standardScore":{
				required:"请输入标准分",
				positiveInteger:"标准分必须是正整数",
				min:"标准分必须大于一",
				max:"标准分最大值为1000"
			},
			"detailedRule.ruleType.id":{
				required:"请输入标准类型"
			}
		}
	});
})
</script>
</c:if>