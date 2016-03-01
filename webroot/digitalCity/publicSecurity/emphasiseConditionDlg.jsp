<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
 <% String dailogName = request.getParameter("dailogName");
	request.setAttribute("dailogName", dailogName);

 	request.setAttribute("type", request.getParameter("publicSecurityType"));
	request.setAttribute("temp",request.getParameter("temp"));
	%>

<div id="emphasis-form" class="container container_24">
   	<form id="emphasisForm" method="post">
   	<pop:token />
   	    <input type="hidden" name="dailogName" id="dailogName" value="<%=request.getParameter("dailogName")%>" />
   		<input type="hidden" name="ids" id="locationIds" value="<%=request.getParameter("locationIds")%>" />
   		<input type="hidden" <s:if test='#attr.type.equals("skynet")'> name="skynet.isEmphasis"</s:if> 
   			 <s:elseif test='#attr.type.equals("bayonet")'> name="bayonet.isEmphasis" </s:elseif> 
   			 <s:else> name="snapshotSystem.isEmphasis" </s:else>
   			 id="isEmphasisValue" value="<%=request.getParameter("isEmphasis")%>" />
   		<input type="hidden" <s:if test='#attr.type.equals("skynet")'> name="skynet.logoutTime"</s:if>
   			 <s:elseif test='#attr.type.equals("bayonet")'> name="bayonet.logoutTime" </s:elseif> 
   			 <s:else> name="snapshotSystem.logoutTime" </s:else>
   			 id="logOutTimeValue" value="" />
     <div class="grid_7 lable-right">
         <label class="form-lbl">&nbsp;&nbsp;取消关注时间：</label>
     </div>
     <div class="grid_17">
         <input type="text" <s:if test='#attr.type.equals("skynet")'> name="skynet.logoutTime" </s:if> 
	   			<s:elseif test='#attr.type.equals("bayonet")'> name="bayonet.logoutTime" </s:elseif> 
	   			<s:else> name="snapshotSystem.logoutTime" </s:else> id="logOutTime"  disabled="disabled" class="dialogtip form-txt"
        maxlength="20" />
     </div>
     	<div class='clearLine'></div>
    <div class="grid_7 lable-right">
    		<em class="form-req">*</em>
         <label class="form-lbl">取消关注原因：</label>
     </div>

     <div class="grid_17">
			<input type="text" <s:if test='#attr.type.equals("skynet")'> name="skynet.logoutReason" </s:if> 
	   			<s:elseif test='#attr.type.equals("bayonet")'> name="bayonet.logoutReason" </s:elseif> 
	   			<s:else> name="snapshotSystem.logoutReason" </s:else> id="logOutReason" style="width:99%" 
	   			
	   			 <s:if test='#attr.type.equals("skynet")'> value="${skynet.logoutReason }" </s:if> 
	   			<s:elseif test='#attr.type.equals("bayonet")'> value="${bayonet.logoutReason }" </s:elseif> 
	   			<s:else> value="${snapshotSystem.logoutReason }" </s:else> maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入注销原因',maxlength:$.format('备注最多只能输入{0}个字符')}}"  />
     </div>
	</form>
</div>
<script type="text/javascript">


$(function(){
    $("#emphasisForm").attr("action","${path}/${dailogName}Manage/updateEmphasiseById.action" );
	var t=document.getElementById("logOutTime");
	d=new Date();
	t.value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();

	t.onfocus=function(){
	 var d=new Date();
	 t.value=d.getHours()+":"+d.getMinutes()
	}
	$("#logOutTimeValue").val($("#logOutTime").val());
	$("#emphasisForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data==null || !data){
	       	 			$.messageBox({
							message:"取消关注失败，请重新选择要取消关注的记录",
							level: "error"
			 			});
	        			return;
					}
					if(null=="${temp}" || ""=="${temp}"){
						 $.messageBox({message:document.title+"取消关注成功"});
						 notExecute="${temp}";
					}else{
						 $.messageBox({level:'warn',message:"除选中的红色数据外,其余"+document.title+"取消关注成功"});
						 notExecute="${temp}".split(",");
					}
					 $("#${dailogName}List").trigger("reloadGrid");
					 $("#${dailogName}Dialog").dialog("close");
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

