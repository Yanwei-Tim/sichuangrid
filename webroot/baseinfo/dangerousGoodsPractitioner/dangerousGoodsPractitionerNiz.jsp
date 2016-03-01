<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<form id="maintainForm" method="post" action="/baseinfo/dangerousGoodsPractitionerManage/addDangerousGoodsPractitioner.action" >
<pop:token />
  <input name="mode" id="mode" value="${mode}" type="hidden">
  	<input type="hidden" name="contextId"  value="${contextId}" />
	<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
  <input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
  <input type="hidden" name="population.id" value="${population.id}" />
  <input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
  <div class="container container_24">  
    <jsp:include page="/baseinfo/commonPopulation/populationInfo/dangerousGoodsPractitioner.jsp"></jsp:include>
  </div>
</form>

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
							message:data,
							level: "error"
						 });
	                	 return;
	                }
	                if("add"==$("#mode").val()) {
						if($("#isLock").val()!='1'){
							 $("#dangerousGoodsPractitionerList").addRowData(data.id,data,"first");
							 $("#dangerousGoodsPractitionerList").setSelection(data.id);
						 }
						 $("#dangerousGoodsPractitionerList").trigger("reloadGrid");
						  $.messageBox({message:"危险品从业人员新增成功！"});
	                }else if("edit"==$("#mode").val()){   
						$("#dangerousGoodsPractitionerList").setRowData(data.id,data); 
						if(data.death){
							if($("#isLock").val()=='0') {
								$("#dangerousGoodsPractitionerList").delRowData(data.id);
							}else {
								$("td[aria-describedby='dangerousGoodsPractitionerList_name']").css('color','red');
								$("td[aria-describedby='dangerousGoodsPractitionerList_idCardNo']").css('color','#778899');
								$("#"+data.id+"").css('color','#778899');
							}
						}
						$("#dangerousGoodsPractitionerList").trigger("reloadGrid");
	                	$.messageBox({message:"危险品从业人员修改成功！"});
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
})
</script>

