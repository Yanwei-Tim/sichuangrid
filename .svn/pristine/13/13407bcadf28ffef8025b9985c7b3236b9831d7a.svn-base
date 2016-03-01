<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	

<script type="text/javascript">

$("#callBackContentForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
	    $(form).ajaxSubmit({
			 success : function(data) {
	    	    if(!data.id){
		    	    $.messageBox({message : data,level : "error"});
				    return;
	    	      }
	    	      var str='';
	    	      
	    	      str+="<h3 class='processTitle processCallbackTitle'>"+"<span class='processTitle_msg'>"+data.feedBackDate+"</span>";
                  str+="<span class='processTitle_msg'>"+data.feedBackUser+"</span>"+"<span class='processTitle_msg'>"+"反馈如下内容 :"+"</span>"+"</h3>";
                  str+="<pre class='processContent processCallbackContent'>"+data.content+"</pre>";
                  
				  $("#warningContentOrActualContent").append(str);
								
		    	  $.messageBox({message:"反馈内容添加成功"});  
		    	  $("#actualContentDailog").dialog("close");
			   },
			  error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			  }
		  });  
		},
		rules:{
			"incidentStepFeedbacks.content":{
			  required:true,
			  maxlength:500
			}
		  
		},
		messages:{
			"incidentStepFeedbacks.content":{
			  required:"请输入反馈内容",
			  maxlength:$.format("反馈内容最多输入500个字符")
		    }
		}
	});
</script>

<form id="callBackContentForm" method="post" action="${path}/incident/emergencyDisposal/editIncidentStepFeedbacks.action">
   <input type="hidden" name="incidentStepFeedbacks.incidentSteps.id" value="${incidentStepFeedbacks.incidentSteps.id}"/>
   <input type="hidden" id="incidentStepFeedbacksFeedBackDate" name="incidentStepFeedbacks.feedBackDate" value="<s:date name="incidentStepFeedbacks.feedBackDate" format="yyyy-MM-dd HH:mm:ss"/>"/>
   <div class="container container_24">
   
      <div class="grid_5 lable-right"><em class="form-req">*</em><label>反馈时间:</label></div>
      <div class="grid_19">
        <s:date name="incidentStepFeedbacks.feedBackDate" format="yyyy-MM-dd HH:mm:ss"/>
      </div> 
      <div class="grid_5 lable-right"><em class="form-req">*</em><label>反馈内容:</label></div>
      <div class="grid_19">
        <textarea id="ncidentStepFeedbacksContent"  name="incidentStepFeedbacks.content" class="form-txt"
	    style="height: 220px;"></textarea>
	  </div>
 </div>
</from>
	  