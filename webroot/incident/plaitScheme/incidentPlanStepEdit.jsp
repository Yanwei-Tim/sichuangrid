<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
  $(function(){
     
	  var  mode =$("#planStepmode").val(); //防止异步dailog 关闭后  $("#planStepmode").val() 不存在 所以提前放到新的内存空间中去
	  $("#planStepForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
		    $(form).ajaxSubmit({
				 success : function(data) {
		    	    if(!data.id){
	   		    	    $.messageBox({message : data,level : "error"});
					    return;
	   		    	 }
	   		    	  if("edit"== mode){
	   		    		 $("#incidentPlanStepList").setRowData(data.id,data);
	   		    		 $.messageBox({message:"处理小组修改成功!"});  
		   		       }else if("add"== mode){
		   		    	 $("#incidentPlanStepList").addRowData(data.id,data,"first");
		   			     $.messageBox({message:"处理小组添加成功!"}); 
			   		   }
	   		    	 $("#editincidentPlanStep").dialog("close");
				  },
				  error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				  }
			  });  
			},
			rules:{
				"incidentPlanStep.content":{
				  required:true,
				  maxlength:500
				}
			},
			messages:{
				"incidentPlanStep.content":{
				  required:"请输入处置内容",
				  maxlength:$.format("处置内容最多输入500个字符")
				}
			}
		});
   
  });

</script>
<form id="planStepForm" method="post" action="${path}/incident/incidentDealPlans/incidentPlanStepEidt.action">
  <input type="hidden" id="planStepId" name="incidentPlanStep.id" value="${incidentPlanStep.id}"/>
  <input type="hidden" id="hPlanStepId" name="incidentPlanStep.incidentDealPlan.id" value="${incidentPlanStep.incidentDealPlan.id}"/>
  <input type="hidden" id="planStepmode" name="mode" value="${mode}"/>
  <input type="hidden" id="seqIndex" name="incidentPlanStep.seqIndex" value="${incidentPlanStep.seqIndex}"/>
  
  <div class="container container_24">
    <div class="grid_5 lable-right">
      <em class="form-req">*</em>
      <label>处置内容:</label>
    </div>
    <div class="grid_19" style="height:90px;">
    	<textarea  id="issueContent" name="incidentPlanStep.content" class="form-txt" style="height:150px;" >${incidentPlanStep.content}</textarea>
    </div>
  </div>
 </form>
