<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	

<script type="text/javascript">

$("#actualContentForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
	    $(form).ajaxSubmit({
			 success : function(data) {
	    	    if(!data.id){
		    	    $.messageBox({message : data,level : "error"});
				    return;
		    	 }
	    	     if($("#mode").val()=='edit'){
	    	   	    $.messageBox({message:"处理内容修改成功"});  
		    	  }else if($("#mode").val()=='add'){
		    		  $.messageBox({message:"处理内容添加成功"});  
		    		  $("#add_leadTeam").css("display","none");
			       }
	    	      $("#warningContentOrActualContent").html(data.actualContent);
	    	      ajaxGetStepList(leadteam_steptype,handteam_steptype,planstep_steptype);
		    	  $("#actualContentDailog").dialog("close");
			   },
			  error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			  }
		  });  
		},
		rules:{
			"incidentSteps.actualContent":{
			  required:true,
			  maxlength:800
			},
			"incidentSteps.stepName":{
				 required:true,
				 maxlength:50
		    }
		  
		},
		messages:{
			"incidentSteps.actualContent":{
			  required:"请输入处理内容",
			  maxlength:$.format("处理内容最多输入800个字符")
		    },
		    "incidentSteps.stepName":{
		       required:"请输入小组名称",
		       maxlength:$.format("小组名称最多输入50个字符")
		    }
		}
	});
</script>

<form id="actualContentForm" method="post" action="${path}/incident/emergencyDisposal/editIncidentstep.action">
   <input type="hidden" id="inStepsId" name="incidentSteps.id" value="${incidentSteps.id}"/>
   <input type="hidden" id="inStepsId" name="incidentSteps.executed" value="true"/>
   <input type="hidden" id="incidentStepSeqIndex" name="incidentSteps.seqIndex" value="${incidentSteps.seqIndex}"/>
   <input type="hidden" id="incidentStepStepType" name="incidentSteps.stepType" value="${incidentSteps.stepType}"/>
   <input type="hidden" id="incidentStepsIncidentsId" name="incidentSteps.incidents.id" value="${incidentSteps.incidents.id}"/>
   <input type="hidden" id="mode" name="mode" value="${mode}"/>
   <div class="container container_24">
      <div class="grid_5 lable-right"><em class="form-req">*&nbsp;</em><label>处理时间:</label></div>
      <div class="grid_19">
      	<input type="text" name="incidentSteps.executeDate" value="<s:date name="incidentSteps.executeDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" style="width: 99%"/>
      </div> 
     
      
        <s:if test="mode.equals('add') && incidentSteps.stepType==@com.tianque.incident.vo.IncidentConstants@HANDTEAM_STEPTYPE">
	      <div class="grid_5 lable-right"><em class="form-req">*&nbsp;</em><label>小组名称:</label></div>
	      <div class="grid_19">
	         <input id="incidentStepStepName"  type="text" class="form-txt"  name="incidentSteps.stepName" value="${incidentSteps.stepName}"/>
	      </div>
	    </s:if>
	   
	      <s:elseif test="mode.equals('add') && incidentSteps.stepType!=@com.tianque.incident.vo.IncidentConstants@HANDTEAM_STEPTYPE">
	      	 <input  type="hidden" class="form-txt"  name="incidentSteps.stepName" value="${incidentSteps.stepName}" readonly="readonly"/>
	      </s:elseif>
	      
	     <div class="grid_5 lable-right"><em class="form-req">*&nbsp;</em><label>处理内容:</label></div>
	     <div class="grid_19">
	       <textarea id="incidentStepsActualContent"  name="incidentSteps.actualContent" class="form-txt"
	    style="height: 220px;"><s:if test="incidentSteps.actualContent!=null">${incidentSteps.actualContent}</s:if><s:else>${incidentSteps.planContent}</s:else></textarea>
  </div>
 </div>
</from>
	  