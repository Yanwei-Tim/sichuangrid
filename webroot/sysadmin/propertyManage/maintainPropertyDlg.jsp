<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<c:if test='${mode=="edit"}'>
	<form action="${path}/sysadmin/propertyManage/updatePropertyDict.action" method="post" id="propertyDict-create-form">
</c:if> 
<c:if test='${mode=="add" || mode=="copy"}'>
	<form action="${path}/sysadmin/propertyManage/ajaxAddPropertyDict.action" method="post" id="propertyDict-create-form">
</c:if>
<s:property value=""/>
	<input type="hidden" name="propertyDict.id" value="${propertyDict.id}" id="propertyDictId" /> 
	<input type="hidden" name="propertyDomainId" value="<s:property value="#parameters.propertyDomainId"/>" />
	<input type="hidden" name="propertyDict.propertyDomain.id" value="<s:property value="#parameters.propertyDomainId"/>" id="propertyDomainId" /> 
    <div class="grid_8 lable-right">
    <em class="form-req">*</em>
       <label class="form-lbl">显示名称：</label>
    </div>
    <div class="grid_15">
        <input type="text" name="propertyDict.displayName" id="displayName" maxlength="20"
	  	<c:if test='${mode=="view"}'>readonly</c:if> value="${propertyDict.displayName}" class="form-txt" />
    </div>
    <div class="grid_1">   	
    </div>
	<div class='clearLine'>&nbsp;</div>
    
	<c:if test="${gridInternalProperty!=null }">
	<div class="grid_8 lable-right">
        <label class="form-lbl"> 内置属性：</label>
	</div>
    <div class="grid_16">
        <select name="propertyDict.internalId" <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt" id="gridInternalProperty">
	       <c:forEach items="${gridInternalProperty }" var="gp">
	             <option value="${gp.identify}">${gp.displayName}</option>
	       </c:forEach>
		</select>
	</div>
	</c:if>
</form>
</div>
<script>
$(document).ready(function(){
	$("#gridInternalProperty").val("${propertyDict.internalId}");
	<c:if test='${mode!="view"}'>
	$.validator.setDefaults({
		highlight: function(input) {
			$(input).addClass("ui-state-highlight"); 
		},
		unhighlight: function(input) {
			$(input).removeClass("ui-state-highlight");
		}
	});

	$("#propertyDict-create-form").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
             	success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"							
						 });
                     	return;
                     }
        	   		<c:if test='${mode=="add"|| mode=="copy"}'>
					    $("#propertyDataGrid").addRowData(data.id,data,"first");
					    $.messageBox({message:"系统属性添加成功!"});
					    $("#propertyDict-create-form").resetForm();
				     	$("#property-dailog").dialog("close");
				    </c:if>
				    <c:if test='${mode=="edit"}'>
					    $.messageBox({message:"系统属性修改成功!"});
					    $("#propertyDataGrid").trigger("reloadGrid");
				     	$("#property-dailog").dialog("close");
				     </c:if>
				    $("#property-dataGrid").setSelection(data.id);
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
		rules: {
			"propertyDict.displayName": {
				required: true,
				minlength: 1,
				maxlength: 100,
				remote: {
	                   data: {
	                        "propertyDict.displayName": function(){
	                            return $('#displayName').val();
	                        },
	                        "propertyDict.id": function(){
	                            return $('#propertyDictId').val();
	                        },
	                        "propertyDomainId" : function(){
	                            return $('#propertyDomainId').val();
	                        }                      
	                    },
	            	   url: "${path}/sysadmin/propertyManage/validateDisplayName.action",
	            	   type: "get"
	            }
			}
		},
		messages: {
			"propertyDict.displayName": {
				required: "请输入显示名称!",
				minlength  : $.format("至少需要{0}个字符"),
				maxlength  : $.format("不能大于{0}个字符"),
				remote:"显示名已经存在!"
			}
		}
	});
	</c:if>
});
</script>
