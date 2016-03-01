<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div id="elderlyPeople" class="container container_24">

	<form action="${path}/baseinfo/elderlyPeopleManage/addElderlyPeople.action" method="post" id="maintainForm">
	<pop:token />
		<div id="perUuid"></div>
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input type="hidden" name="contextId"  value="${contextId}" />
			<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
   		<div class='clearLine'>&nbsp;</div>
   		<jsp:include page="/baseinfo/commonPopulation/populationInfo/elderlyPeople.jsp"></jsp:include>
   		
	  </form>
   </div>

<script type="text/javascript">

<c:if test='${mode=="add"}'>
$.ajax({
	async: false,
	url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
	data:{
		"organization.id" : $("#currentOrgId").val()
	},
	success:function(responseData){
		$("#commonPopulationOrgName").val(responseData);
	}
});
</c:if>
$("#maintainForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form){
	$("#_imgUrl").val($("#imgUrl").val());
	 $(form).ajaxSubmit({
		 async:false,
		 success : function(data) {
			if (!data.id) {
				$.messageBox({
					message : data,
					level : "error"
				});
				return;
			}
			 if("add"==$("#mode").val()){
				 if($("#isEmphasis").val()!="1"){
					 $("#elderlyPeopleList").setRowData(data.id,data);
					 $("#elderlyPeopleList").setSelection(data.id);
				 }
				 $("#elderlyPeopleList").trigger("reloadGrid");
				 $.messageBox({message:"老年人信息新增成功！"});
				// disableButtons();
		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
			 }
			 if("edit"==$("#mode").val()){
				 if(data.death == true || data.death == "true"){
					 if($("#isEmphasis").val()=="0"){
						 $("#elderlyPeopleList").delRowData(data.id+"");
					 }else{
						 $("#elderlyPeopleList").setRowData(data.id,data);
						 $("#"+data.id+"").css('color','#778899');
						 $("#elderlyPeopleList").setSelection(data.id);
					 }
				 }else{
					
						 $("#elderlyPeopleList").setRowData(data.id,data);
					 
				 }
				 $("#elderlyPeopleList").trigger("reloadGrid");
				 $.messageBox({message:"老年人信息修改成功！"});
				// disableButtons();
				 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
			 }
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("提交错误");
		}
		});
	},
	rules:{
	},
	messages:{
	}
});
</script>