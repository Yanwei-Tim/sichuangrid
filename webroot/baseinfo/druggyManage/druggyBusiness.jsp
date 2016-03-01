<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="druggy" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/druggyManage/updateDruggyBusiness.action"  >
			<pop:token />
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="population.id" value="${population.id}" />
			<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
			<jsp:include page="/baseinfo/commonPopulation/populationInfo/druggy.jsp"></jsp:include>
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
							evel: "error"
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                	if($("#isLock").val()!='1'){
	    				 	$("#druggyList").addRowData(data.id,data,"first");
		                	$("#druggyList").setSelection(data.id);
		                }
		                 $("#druggyList").trigger("reloadGrid");
	                	 $.messageBox({message:"吸毒人员新增成功！"});
	                	 $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#druggyList").setRowData(data.id,data);
	                	 if(data.death){
                			if($("#isLock").val()=='0') {
                				$("#druggyList").delRowData(data.id);
							}else {
							    $("#druggyList").setRowData(data.id,data);
								$("#"+data.id+"").css('color','#778899');
							}
						}
						 $("#druggyList").trigger("reloadGrid");
	                	 $.messageBox({message:"吸毒人员修改成功！"});
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