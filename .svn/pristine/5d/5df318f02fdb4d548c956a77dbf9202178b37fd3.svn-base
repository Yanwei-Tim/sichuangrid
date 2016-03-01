<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="dialog-form" title="" class="container container_24">
	<div id="tabs" style="height:400px;">
	<div id="mentalPatientDiv" class="container container_24">
	<form id="mentalPatientForm" method="post" action="${path}/baseinfo/mentalPatientManage/addMentalPatient.action" >
	<pop:token />
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="cacheIdId" type="hidden" name="cacheId.id" value="${cacheId.id}" />
		<input type="hidden" name="population.id" value="${population.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="keyType" type="hidden" name="" value="${keyType}" />
		<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
		<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
		<input type="hidden" name="contextId"  value="${contextId}" />
		<jsp:include page="/baseinfo/commonPopulation/populationInfo/mentalPatient.jsp"></jsp:include>

	</form>
   </div>
 </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#mentalPatientForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(!data.id){
	       	 			$.messageBox({ 
							evel: "error"
			 			});
	        			return;
					}
	                if("add"==$("#mode").val()){
	                	 if($("#isLock").val()!='1'){
	    				 	$("#mentalPatientList").addRowData(data.id,data,"first");
		                	$("#mentalPatientList").setSelection(data.id);
		                 }
	                 	 $("#mentalPatientList").trigger("reloadGrid");
	                	 $.messageBox({message:"严重精神障碍患者新增成功！"});
	    		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#mentalPatientList").setRowData(data.id,data);
	                	 if(data.death){
	                			if($("#isLock").val()=='0') {
	                				$("#mentalPatientList").delRowData(data.id);
								}else {
									$("#mentalPatientList").setRowData(data.id,data);
									$("#"+data.id+"").css('color','#778899');
								}
						 }else{
							$("#mentalPatientList").setRowData(data.id,data);
						  }
						  $("#mentalPatientList").trigger("reloadGrid");
	                	 $.messageBox({message:"严重精神障碍患者修改成功！"});
	    				 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);	
	                }
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
</script>