<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
 <% String dailogName = request.getParameter("dailogName");
	request.setAttribute("dailogName", dailogName);

 	request.setAttribute("type", request.getParameter("type"));
	request.setAttribute("temp",request.getParameter("temp"));
	%>

<div id="emphasis-form" class="container container_24">
   	<form id="emphasisForm" method="post">
   	<pop:token />
   	    <input type="hidden" name="dailogName" id="dailogName" value="<%=request.getParameter("dailogName")%>" />
   		<input type="hidden" name="locationIds" id="locationIds" value="<%=request.getParameter("locationIds")%>" />
   		<input type="hidden" name="location.isEmphasis" id="isEmphasisValue" value="<%=request.getParameter("isEmphasis")%>" />
   		<input type="hidden" name="location.isEmphasisDate" id="isEmphasisDateValue" value="" />
   		<input type="hidden" name="modulKey" id="modulKey" value="${modulKey}" />
	<s:if test="#attr.type.equals('attention')">
	<div class='clearLine'></div>
     <div class="grid_7 lable-right">
         <label class="form-lbl">取消关注时间：</label>
     </div>
     <div class="grid_17">
         <input type="text"  id="isEmphasisDate"  disabled="disabled" class="dialogtip form-txt"
        maxlength="20" />
     </div>
     	<div class='clearLine'></div>
    <div class="grid_7 lable-right">
    		<em class="form-req">*</em>
         <label class="form-lbl">取消关注原因：</label>
     </div>

     <div class="grid_17">
			<input type="text" name="location.isEmphasisReason" id="isEmphasisReason"     maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入取消关注原因',maxlength:$.format('取消关注原因最多只能输入{0}个字符')}}"/>
     </div>
	</s:if>
    <s:elseif test="#attr.type.equals('companyPlace')">
     <div class="grid_7 lable-right">
         <label class="form-lbl">&nbsp;&nbsp;取消关注时间：</label>
     </div>
     <div class="grid_17">
         <input type="text" name="isEmphasisDate" id="isEmphasisDate"  disabled="disabled" class="dialogtip form-txt"
        maxlength="20" />
     </div>
     	<div class='clearLine'></div>
    <div class="grid_7 lable-right">
    		<em class="form-req">*</em>
         <label class="form-lbl">取消关注原因：</label>
     </div>

     <div class="grid_17">
			<input type="text" name="location.isEmphasisReason" id="isEmphasisReason" style="width:99%" value="${location.isEmphasisReason}" maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入取消关注原因',maxlength:$.format('取消关注原因最多只能输入{0}个字符')}}"  />
     </div>
     </s:elseif>
	</form>
</div>
<script type="text/javascript">


$(function(){
    $("#emphasisForm").attr("action","${path}/baseinfo/searchCompanyPlace/updateEmphasiseById.action" );
	var t=document.getElementById("isEmphasisDate");
	d=new Date();
	t.value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();

	t.onfocus=function(){
	 var d=new Date();
	 t.value=d.getHours()+":"+d.getMinutes()
	}
	$("#isEmphasisDateValue").val($("#isEmphasisDate").val());
	$("#emphasisForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data){
	       	 			$.messageBox({
							message:data,
							evel: "error"
			 			});
	        			return;
					}
					if(null=="${temp}" || ""=="${temp}"){
						document.title = modulTitle;
						<s:if test="#attr.type.equals('companyPlace')">
							$("#companyPlaceList").trigger("reloadGrid");
							$("#companyPlaceMaintanceDialog").dialog("close");
						</s:if>
						$.messageBox({message:document.title+"取消关注成功"});
					}else{
						 notExecute="${temp}".split(",");
						 $.messageBox({level:'warn',message:"除选中的红色数据外,其余"+document.title+"取消关注成功"});
					}
					
					doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
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

