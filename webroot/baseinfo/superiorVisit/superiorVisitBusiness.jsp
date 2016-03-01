<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="superiorVisit" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/superiorVisitManage/addSuperiorVisit.action"  >
		<pop:token />
			<div id="perUuid"></div>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
			<input type="hidden" name="contextId"  value="${contextId}" />
			<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
			<input type="hidden" name="population.id" value="${population.id}" />
			<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
			<input id="keyType" type="hidden" name="" value="${keyType}" />
			<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
	    	<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
			<jsp:include page="/baseinfo/commonPopulation/populationInfo/superiorVisit.jsp"></jsp:include>
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
							evel: "error"
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                	if($("#isLock").val()!='1'){
	    				 	$("#superiorVisitList").addRowData(data.id,data,"first");
		                	$("#superiorVisitList").setSelection(data.id);
		                }
		                 $("#superiorVisitList").trigger("reloadGrid");
	                	 $.messageBox({message:"重点上访人员新增成功！"});
	    		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#superiorVisitList").setRowData(data.id,data);
	                	 if(data.death){
                			if($("#isLock").val()=='0') {
                				$("#superiorVisitList").delRowData(data.id);
							}else {
							    $("#superiorVisitList").setRowData(data.id,data);
								$("#"+data.id+"").css('color','#778899');
							}
						}
						 $("#superiorVisitList").trigger("reloadGrid");
	                	 $.messageBox({message:"重点上访人员修改成功！"});
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