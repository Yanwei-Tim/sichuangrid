<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

 <div id="aidNeedPopulation" class="container container_24">

		<form action="${path}/baseinfo/aidNeedPopulationManage/addAidNeedPopulation.action" method="post" id="maintainForm">
		<pop:token />
		<div id="perUuid"></div>
		<input type="hidden" name="ownerOrg.id" id="organizationId" value="<s:property value='#parameters.orgId'/>" />
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input id="keyType" type="hidden" name="" value="${keyType}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
		<input type="hidden" name="cacheId.id" value="${cacheId.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
		<input type="hidden" name="contextId"  value="${contextId}" />
		<jsp:include page="/baseinfo/commonPopulation/populationInfo/aidNeedPopulation.jsp"></jsp:include>
   </form>
   </div>
  <script type="text/javascript">

$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		$("#_imgUrl").val($("#imgUrl").val());
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
	                 	if($("#isEmphasis").val()!="1"){
	                 		$("#aidNeedPopulationList").addRowData(data.id,data,"first");
	    					$("#aidNeedPopulationList").setSelection(data.id);
	    				}
	                	 $("#aidNeedPopulationList").trigger("reloadGrid");
	                	 $.messageBox({message:"需救助人员新增成功！"});
	                	
	    		        doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#aidNeedPopulationList").setRowData(data.id,data);
	                	 if(data.death){
	                			if($("#isEmphasis").val()=="0") {
	                				$("#aidNeedPopulationList").delRowData(data.id);
								}else {
									 $("#"+data.id+"").css('color','#778899');
								}
							}
						 $("#aidNeedPopulationList").trigger("reloadGrid");
	                	 $.messageBox({message:"需救助人员修改成功！"});
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