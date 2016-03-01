<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
 <%
	request.setAttribute("lowerCaseModuleName", request.getParameter("lowerCaseModuleName"));
 	String actionUrl;
 	if(null != request.getParameter("actionUrl")){
 		actionUrl = request.getParameter("actionUrl");
 	}else{
 		actionUrl = request.getParameter("lowerCaseModuleName")+"Manage/updateEmphasiseById.action";
 	}

 	request.setAttribute("upperCaseModuleName",request.getParameter("lowerCaseModuleName").substring(0,1).toUpperCase()+request.getParameter("lowerCaseModuleName").substring(1));
 	request.setAttribute("actionUrl",actionUrl);

 	request.setAttribute("type", request.getParameter("type"));
 	request.setAttribute("temp",request.getParameter("temp"));
 %>
<div id="emphasis-form" class="container container_24">
   	<form id="populationEmphasisForm" method="post">
   	<pop:token />
   		<input type="hidden" name="populationIds" id="populationIds" value="<%=request.getParameter("populationIds")%>" />
   		<input type="hidden" name="population.logoutDetail.logout" id="isEmphasisValue" value="<%=request.getParameter("isEmphasis")%>" />
   		<input type="hidden" name="population.logoutDetail.logoutDate" id="logOutTimeValue"/>
   		<input type="hidden" name="orgId" id="orgId" value="<%=request.getParameter("orgId")%>" />
	<s:if test="#attr.type.equals('actualPopulation')">
     <div class="grid_5 lable-right">
         <label class="form-lbl">注销时间：</label>
     </div>
     <div class="grid_19">
         <input type="text"  id="logOutTime"  disabled="disabled" class="dialogtip form-txt"
        maxlength="20" />
     </div>
     	<div class='clearLine'></div>
    <div class="grid_5 lable-right">
    		<em class="form-req">*</em>
         <label class="form-lbl">注销原因：</label>
     </div>

     <div class="grid_19">
			<input type="text" name="population.logoutDetail.logoutReason" id="logOutReason"    maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入注销原因',maxlength:$.format('注销原因最多只能输入{0}个字符')}}"  />
     </div>
     </s:if>
     <s:else>
	<div class='clearLine'></div>
     <div class="grid_7 lable-right">
         <label class="form-lbl">取消关注时间：</label>
     </div>
     <div class="grid_17">
         <input type="text"  id="logOutTime"  disabled="disabled" class="dialogtip form-txt"
        maxlength="20" />
     </div>
     	<div class='clearLine'></div>
    <div class="grid_7 lable-right">
    		<em class="form-req">*</em>
         <label class="form-lbl">取消关注原因：</label>
     </div>

     <div class="grid_17">
			<input type="text" name="population.logoutDetail.logoutReason" id="logOutReason"     maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入取消关注原因',maxlength:$.format('取消关注原因最多只能输入{0}个字符')}}"  />
     </div>
     </s:else>
	</form>
</div>
<script type="text/javascript">
	$("#logOutTimeValue").val(new Date());
	var d=new Date();
	$("#logOutTime").val(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
$(function(){

	$("#populationEmphasisForm").attr("action","${path}/baseinfo/"+"${actionUrl}");
	$("#populationEmphasisForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data==null || !data){
	       	 			$.messageBox({
							message:data,
							level: "error"
			 			});
	        			return;
					}
					 if(null=="${temp}"||""=="${temp}"){
						 if("${type}"=="actualPopulation"){
							 $.messageBox({message:document.title+"注销成功"});
						 }else{
							 $.messageBox({message:document.title+"取消关注成功"});
						 }
					 }else{
						 if("${type}"=="actualPopulation"){
							 $.messageBox({level:'warn',message:"除选中的红色数据外,其余"+document.title+"注销成功"});
						 }else{
							 $.messageBox({level:'warn',message:"除选中的红色数据外,其余"+document.title+"取消关注成功"});
						 }
					 }
					 $("#${lowerCaseModuleName}List").trigger("reloadGrid");
					 $("#${lowerCaseModuleName}Dialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
		      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{},
		messages:{}
	});

})



</script>

