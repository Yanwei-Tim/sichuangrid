<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="idleYouth" class="container container_24">
	<form action="${path}/baseinfo/idleYouthManage/addIdleYouth.action" method="post" id="maintainForm">
	<pop:token />
		<div id="perUuid"></div>
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="contextId"  value="${contextId}" />
		<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
		<input id="keyType" type="hidden" name="" value="${keyType}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
		<input type="hidden" name="cacheId.id" value="${cacheId.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<jsp:include page="/baseinfo/commonPopulation/populationInfo/idleYouth.jsp"></jsp:include>
	</form>
</div>
	
<script type="text/javascript">
	$(document).ready(function(){
		$("#maintainForm").formValidate({
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
		                	$("#idleYouthList").trigger("reloadGrid");
		                	 $.messageBox({message:"重点青少年新增成功！"});
		    				 $("#idleYouthList").addRowData(data.id,data,"first");
		    				 $("#idleYouthList").setSelection(data.id);
		    		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
		                }
		                if("edit"==$("#mode").val()){
		                	 $("#idleYouthList").setRowData(data.id,data);
		                	 if(data.death){
		                			if($("#isLock").val()=='0') {
		                				$("#idleYouthList").delRowData(data.id);
									}else {
										$("#idleYouthList").setRowData(data.id,data);
										 $("#"+data.id+"").css('color','#778899');
										 $("#idleYouthList").setSelection(data.id);
									}
							}
	                	 	$.messageBox({message:"重点青少年修改成功！"});
	                	 	 $("#idleYouthList").trigger("reloadGrid");
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
		$(".dialogtip").inputtip();
	});
</script>