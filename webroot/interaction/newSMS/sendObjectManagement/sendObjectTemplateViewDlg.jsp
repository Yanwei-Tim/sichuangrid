<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" class="container container_24" >
	<form id="smsTemplateForm" method="post" action="javascript:void(0);">
		<div class="grid_18" style="border: 1px solid #ccc;height:480px;overflow: auto;">
			<div class="grid_4" >
				<label>查询模板：</label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_1" ></div>
			<div class="grid_22" style="height:430px;overflow: auto;">
				<textarea rows="4" class="form-txt" id="template" name="smsSendObject.template" style="height:400px;" >${smsSendObject.template}</textarea>
			</div>
		</div>
		<div class="grid_1" ></div>
		<div class="grid_5" style="width:21.7%;border: 1px solid #ccc;height:480px;overflow: auto;">
			<div class="grid_23" >
				<label>查询参考字段：</label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_1" ></div>
			<div class="grid_21" style="height:430px;overflow: auto;">
				<s:iterator value="smsSendObject.sqcList" var="sqc" status="st">
						<label><s:property value="#st.index + 1"/>、<s:property value="#sqc.key"/>(<s:property value="#sqc.description"/>)</label><br/>
				</s:iterator>
			</div>
		</div>
		
	</form>
</div>
<script type="text/javascript">
<c:if test='${mode!="view"}'>

	$(document).ready(function(){
		
		$("#smsTemplateForm").formValidate({
			promptPosition: "bottomLeft", 
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								message:data,
								level: "error"							
				 			});
	            			return;
						}
						<c:if test='${mode=="add"}'>
							$.messageBox({message:"成功新增发送对象!"});
						</c:if>
						<c:if test='${mode=="update"}'>
							$.messageBox({message:"成功修改发送对象!"});
						</c:if>
						$("#reload").click();
					     $("#sendObjectDialog").dialog("close");
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
				"smsSendObject.template":{
					required:true,
					minlength:2
				}
			},
			messages:{
				"smsSendObject.description":{
					required:"请输入短信内容",
					minlength:$.format("短信内容至少需要输入{0}个字符"),
					maxlength:$.format("短信内容最多只能输入{0}个字符")
				}
			}
		});
   		$("#smsTemplateForm").attr("action","${path}/smsSendObjectManage/updateSmsSendObjectTemplate.action");
	});

</c:if>
</script>