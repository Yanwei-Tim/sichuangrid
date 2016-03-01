<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="optimalObject" class="container container_24">
	<form id="maintainForm" method="post"action="${path}/baseinfo/optimalObjectManage/addOptimalObject.action"  >
	<pop:token />
		<div id="perUuid"></div>
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
		<input type="hidden" name="population.id" value="${population.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input type="hidden" name="contextId"  value="${contextId}" />
		<jsp:include page="/baseinfo/commonPopulation/populationInfo/optimalObject.jsp"></jsp:include>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
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
							evel: "error",
							message:data
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                	 $.messageBox({message:"优抚对象新增成功！"});
	    				 $("#optimalObjectList").addRowData(data.id,data,"first");
	                	 $("#optimalObjectList").trigger("reloadGrid");
	    		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#optimalObjectList").setRowData(data.id,data);
	                	 if(data.death){
                			if($("#isLock").val()=='0') {
                				$("#optimalObjectList").delRowData(data.id);
							}else {
								$("td[aria-describedby='optimalObjectList_name']").css('color','red');
								$("td[aria-describedby='optimalObjectList_idCardNo']").css('color','#778899');
								$("#"+data.id+"").css('color','#778899');
							}
						 }
	                	 $("#optimalObjectList").trigger("reloadGrid");
	                	 $.messageBox({message:"优抚对象修改成功！"});
// 	    				 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
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