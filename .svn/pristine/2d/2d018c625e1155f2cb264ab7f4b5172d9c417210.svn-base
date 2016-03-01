<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" class="container container_24">
	<div id="tabs">
		<ul>
			<li id="li_tabAInfo"><a id="a_tabA" href="#tabAInfo">发送对象信息</a></li>
			<li id="li_queryInfo" <c:if test='${mode=="add"}'>style="display:none"</c:if> ><a id="a_query" href="${path}/interaction/newSMS/smsqueryconditionManage/smsqueryconditionList.jsp?objectId=${smsSendObject.id}">条件管理信息</a></li>
			<li id="li_tabBInfo" <c:if test='${mode=="add"}'>style="display:none"</c:if> ><a id="b_tabB" href="#tabBInfo">编辑模板</a></li>
<%-- 			<li id="li_templateInformation" <s:if test='"add".equals(mode)'>style="display:none"</s:if> ><a id="templateForm" href="${path}/smsSendObjectManage/dispatchOperate.action?mode=editTemplate&id=${smsSendObject.id}">编辑模板信息</a></li> --%>
		</ul>
		
		<form id="maintainForm" method="post" action="javascript:void(0);">
			<div id="tabAInfo" class="clearfix">
				<div style="height:380px;overflow: auto;" >
					<input type="hidden" name="smsSendObject.id" value="${smsSendObject.id}">
					<div class="grid_4 lable-right" >
						<em class="form-req">*</em>
						<label>名称：</label>
					</div>
					<div class="grid_20">
					     <input type="text" name="smsSendObject.name" value="${smsSendObject.name}" class="form-txt" />
					</div>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_4 lable-right" >
						<em class="form-req">*</em>
						<label>描述：</label>
					</div>
					<div class="grid_20">
						<textarea rows="4" class="form-txt" id="smsContent" name="smsSendObject.description" style="height:100px;" >${smsSendObject.description}</textarea>
					</div>
				</div>
			</div>
			
			<div id="tabBInfo" class="clearfix">
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
						<label>查询参考信息：</label>
					</div>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_1" ></div>
					<div class="grid_21" style="height:430px;overflow: auto;">
						<s:iterator value="smsSendObjectVo.sqcList" var="sqc" status="st">
								<label><s:property value="#st.index + 1"/>、<s:property value="#sqc.description"/>(<s:property value="#sqc.key"/>)</label><br/>
						</s:iterator>
					</div>
				</div>
			</div>
		</form>
		
	</div>
</div>
<script type="text/javascript">

$(document).ready(function(){
	$("li").click(function(){
		$("button").show();
	})
		
	$("#tabs").tabFunction({ cache: true },"tabB");
	
<c:if test='${mode!="view"}'>
	$("#maintainForm").formValidate({
		showErrors:showErrorsForTab,
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
				     if($("#${dialogName}")!=null){
				    	 $("#${dialogName}").dialog("close");
				     }
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"smsSendObject.name":{
				required:true
			},
			"smsSendObject.tableType":{
				required:true
			},
			"smsSendObject.description":{
				required:true,
				minlength:2,
				maxlength:210
			}
		},
		messages:{
			"smsSendObject.name":{
				required:"请输入名称！"
			},
			"smsSendObject.tableType":{
				required:"请选择对象类型！"
			},
			"smsSendObject.description":{
				required:"描述不能为空！",
				minlength:$.format("短信内容至少需要输入{0}个字符"),
				maxlength:$.format("短信内容最多只能输入{0}个字符")
			}
		}
	});
	<c:if test='${mode=="add"}'>
    	$("#maintainForm").attr("action","${path}/smsSendObjectManage/addSmsSendObject.action");
	</c:if>
	<c:if test='${mode=="update"}'>
   		$("#maintainForm").attr("action","${path}/smsSendObjectManage/updateSmsSendObject.action");
	</c:if>
</c:if>

});

</script>