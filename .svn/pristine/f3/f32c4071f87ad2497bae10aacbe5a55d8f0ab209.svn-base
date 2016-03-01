<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="dialog-form" class="container container_24">
	<div id="tabs" style="height:400px;">
	   	<div id="positiveInfoDiv" class="container container_24">
	  		<form id="positiveInfoForm" method="post" action="${path}/baseinfo/positiveInfoManage/savePositiveInfo.action" >
	  		<pop:token />
			<input name="population.id" id="positiveInfo_id" value="${population.id}" type="hidden">
	   		<input id="cacheIdId" type="hidden" name="cacheId.id" value="${cacheId.id}" />
	   		<input  type="hidden" name="population.death" value="${population.death}" />
	   		<input id="nnn" type="hidden" name="population.idCardNo" value="${population.idCardNo}" />
			<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
			<input type="hidden" name="contextId"  value="${contextId}" />
			
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<jsp:include page="/baseinfo/commonPopulation/populationInfo/positiveInfo.jsp"></jsp:include>	
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
					
					if("add"==$("#mode").val()){
						if($("#isLock").val()!='1'){
	    				 	$("#positiveInfoList").addRowData(data.id,data,"first");
		                	$("#positiveInfoList").setSelection(data.id);
		                }
		                $("#positiveInfoList").trigger("reloadGrid");
		                <s:if test='"toPositiveInfoDialog".equals(#attr.dailogName)'>
		        			setRectificativePersonUnemphasis();
		        		</s:if>
	                	$.messageBox({message:"刑释人员新增成功！"});
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#positiveInfoList").setRowData(data.id,data);
	                	 if(data.death){
               				if($("#isLock").val()=='0') {
               					$("#positiveInfoList").delRowData(data.id);
							}else {
								 $("#positiveInfoList").setRowData(data.id,data);
								 $("#"+data.id+"").css('color','#778899');
							}
						}else{
							$("#positiveInfoList").setRowData(data.id,data);
						}
						 $("#positiveInfoList").trigger("reloadGrid");
	                	$.messageBox({message:"刑释人员修改成功！"});
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
<s:if test='"toPositiveInfoDialog".equals(#attr.dailogName)'>
function setRectificativePersonUnemphasis(){
	$.ajax({
		url:"${path}/baseinfo/rectificativePersonManage/updateEmphasiseByIdCardNoAndOrgId.action",
		data:{
			"population.idCardNo":"${population.idCardNo}",
			"population.organization.id":"${population.organization.id}",
			"population.logoutDetail.logoutReason":"已转换为刑释人员",
			"population.logoutDetail.logoutDate":getCurrentDate(),
			"population.logoutDetail.logout":1
		},
		success:function(){
			$("#rectificativePersonList").trigger("reloadGrid");
		}
	});
}
</s:if>
function getCurrentDate(){
	var date = new Date();
	var myDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	return myDate;
}
</script>