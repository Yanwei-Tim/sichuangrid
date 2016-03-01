<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="unemployedPeople" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/unemployedPeopleManage/addUnemployedPeople.action"  >
		<pop:token />
			<div id="perUuid"></div>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
			<input type="hidden" name="contextId"  value="${contextId}" />
			<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
			<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
			<input type="hidden" name="population.id" value="${population.id}" />
	    	<input type="hidden" readonly="readonly" name="population.birthday" value="<fmt:formatDate value="${population.birthday}" type="date" pattern="yyyy-MM-dd" />"   class="form-txt" /> 
			<jsp:include page="/baseinfo/commonPopulation/populationInfo/unemployedPeople.jsp"></jsp:include>
			<input name="isSubmit" id="isSubmit" type="hidden" value="">
		</form>
  	</div>
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
           	 			message : data,
						level : "error"
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                 if($("#isLock").val()!='1'){
	    				 $("#unemployedPeopleList").addRowData(data.id,data,"first");
		                 $("#unemployedPeopleList").setSelection(data.id);
		               }
		                $("#unemployedPeopleList").trigger("reloadGrid");
		                 $.messageBox({message:"失业人员新增成功！"});
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#unemployedPeopleList").setRowData(data.id,data);
	                	 if(data.death){
                			if($("#isLock").val()=='0') {
                				$("#unemployedPeopleList").delRowData(data.id);
							}else {
								$("td[aria-describedby='unemployedPeopleList_name']").css('color','red');
								$("td[aria-describedby='unemployedPeopleList_idCardNo']").css('color','#778899');
								$("#"+data.id+"").css('color','#778899');
							}
						}
						 $("#unemployedPeopleList").trigger("reloadGrid");
	                	 $.messageBox({message:"失业人员修改成功！"});
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