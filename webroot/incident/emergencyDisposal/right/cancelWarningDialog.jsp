<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
$("#cancelWarningForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
	    $(form).ajaxSubmit({
			 success : function(data) {
	    	      if(!data.id){
		    	    $.messageBox({message : data,level : "error"});
			        return;
		    	  }
	               $.messageBox({message:"解除预警成功!"});
	               var incidentId=$("#incidentId").val();
	               if($("#incident_"+incidentId).next().html()!=null){
	            	   $("#incident_"+incidentId).next().click();
	            	   $("#incident_"+incidentId).remove();
	            	   $("#handleWarningDailog").dialog("close");
		           }else{
		        	  $("#incident_"+incidentId).remove();
		        	  loadDataUrl();
		        	  emptyEarlyWarningCategoryId();
		        	  $("#handleWarningDailog").dialog("close");
			       }
			   },
			  error : function(XMLHttpRequest, textStatus, errorThrown) {
				  alert("提交错误");
			  }
		  });  
		},
		rules:{
		   "incidents.actualContent":{
			  required:true
		   }
		},
		messages:{
		  "incidents.actualContent":{
			required:"请输入解除原因" 
		   }
		}
	});
</script>
<form id="cancelWarningForm" method="post" action="/incident/emergencyDisposal/cancelWarning.action">
<input type="hidden" id="cInDeId" name="incidents.id" value="<s:property value="#parameters.incidentId"/>"/>
<input type="hidden" id="incdId" name="incidents.status" value="<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/>"/>
<div class="container container_24">
<div class="grid_5 lable-right">
	<em class="form-req">*</em><label>&nbsp;&nbsp;审&nbsp;核&nbsp;人:</label>
</div>
<div class="grid_19" >
	<input type="text" id="incidents_auditer" name="incidents.auditer" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().name"/>"  readonly="readonly" style="width: 99%"/>
</div>  
<div class='clearLine'>&nbsp;</div>                                                   
<div class="grid_5 lable-right">
      <em class="form-req">*</em>
      <label>解除原因:</label>
</div>
<div class="grid_19">
   <textarea  id="responsibility" name="incidents.actualContent" class="form-txt" style="height:99px;"></textarea>
</div>
</div>
</form>