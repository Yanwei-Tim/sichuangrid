<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="dialog-form" title="新增" class="container container_24">
	<div id="tabs" style="height:400px;">
	   	<div id="positiveInfoDiv" class="container container_24">
	  		<form id="positiveInfoForm" method="post" action="${path}/baseinfo/otherAttentionPersonnelManage/addOtherAttentionPersonnel.action" >
	  		<pop:token />
			<input name="population.id" id="positiveInfo_id" value="${id}" type="hidden">
	   		<input id="cacheIdId" type="hidden" name="cacheId.id" value="${cacheId.id}" />
	   		<input  type="hidden" name="population.death" value="${population.death}" />
	   		<input id="nnn" type="hidden" name="population.idCardNo" value="${population.idCardNo}" />
			<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
			<input type="hidden" name="contextId"  value="${contextId}" />
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<jsp:include page="/baseinfo/commonPopulation/populationInfo/otherAttentionPersonnel.jsp"></jsp:include>	
			</form>
	  	</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#positiveInfoForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(!data.id){
	     		  	 	$.messageBox({
							message:data,
							level: "error"
						 });
	                	 return;
	                }
					if("add"=="<s:property value='#parameters.mode[0]'/>"){
						if($("#isLock").val()!='1'){
	    				 	$("#otherAttentionPersonnelList").addRowData(data.id,data,"first");
		                	$("#otherAttentionPersonnelList").setSelection(data.id);
		                }
		                $("#positiveInfoList").trigger("reloadGrid");
	                	$.messageBox({message:"其他人员新增成功！"});
	                }
	                if("edit"=="<s:property value='#parameters.mode[0]'/>"){
	                	 $("#positiveInfoList").setRowData(data.id,data);
	                	 if(data.death){
               				if($("#isLock").val()=='0') {
               					$("#otherAttentionPersonnelList").delRowData(data.id);
							}else {
								 $("#otherAttentionPersonnelList").setRowData(data.id,data);
								 $("#"+data.id+"").css('color','#778899');
							}
						}else{
							$("#otherAttentionPersonnelList").setRowData(data.id,data);
						}
						 $("#otherAttentionPersonnelList").trigger("reloadGrid");
	                	$.messageBox({message:"其他人员修改成功！"});
	                }
	                doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交数据时发生错误");
  	   		   	}
			});
			},
		rules:{
		},
		messages:{
		}
	
	});
});

function getCurrentDate(){
	var date = new Date();
	var myDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	return myDate;
}
</script>