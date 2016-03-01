<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script>
  $(function(){
     var buttonArray=  $(".ui-dialog-buttonset button");
     var prevButton;
     var saveButton;
     var nextButton;
	 for(var i=0;i<buttonArray.length;i++){
		   if($(buttonArray[i]).find("span").html()=="保存"){
			   saveButton=buttonArray[i];
			   $(buttonArray[i]).hide();
			}else if($(buttonArray[i]).find("span").html()=="下一步"){
				nextButton=buttonArray[i];
			 }else if($(buttonArray[i]).find("span").html()=="上一步"){
				 $(buttonArray[i]).hide();
				 prevButton=buttonArray[i];
			}
		}
	 $(nextButton).click(function(){
			 $("#mainForm").submit();
	 });

	 $(prevButton).click(function(){
		
		 $("#nextStep").hide();
         $(nextButton).show();
         $(prevButton).hide();
         $(saveButton).hide();
         $("#incidentDealDialog").dialog("option" ,{height:300,position:'center'});
         $("#incidentDPEndRule").attr("disabled",false);
      
	 });

	 
		
	  initData();
	  $("#incidentTabs").tabs(); 
	  $("#leadTeam").click(function(){
	          if($(this).attr("checked")=='checked'){	
	        	  $("#leadTab-1").show();
	        	  $('#incidentTabs').tabs( "select" ,0);
	           }else{
	        	      $("#leadTab-1").hide();
	        	  
	        	      if($("#complyTeam").attr("checked")=='checked'){	
	        	            $('#incidentTabs').tabs( "select" ,1);
	        	      }else{
	        	    	   $('#incidentTabs').tabs( "select" ,2);
		        	  }
		        	  
	           }
	  });

	   $("#complyTeam").click(function(){
		      if($(this).attr("checked")=='checked'){	
	        	  $("#leadTab-2").show();
	        	  $('#incidentTabs').tabs( "select" ,1);
	           }else{
	        	   $("#leadTab-2").hide();
	        	   if($("#leadTeam").attr("checked")=='checked'){	
       	              $('#incidentTabs').tabs( "select" ,0);
       	           }else{
       	    	       $('#incidentTabs').tabs( "select" ,2);
	        	   }
	           }
		  });
	 
    
    

      $("#mainForm").formValidate({
   		promptPosition: "bottomLeft",
   		submitHandler: function(form){
   	    $(form).ajaxSubmit({
   			 success : function(data) {
   	    	    if(!data.id){
     		    	  $.messageBox({message : data,level : "error"});
   				      return;
   	    	    }
   	    	   $("#incidentDealId").val(data.id);
   	    	   $("#hiddenIncidentDealPlanId").val(data.id);
   	    	   $("#incidentDPEndRule").attr("disabled",true);
   	    	   $.messageBox({message:"上一步编辑成功!"});
   	            $("#nextStep").show();
   			    $(saveButton).show();
   			    $(nextButton).hide();
   			    $(prevButton).show();
   			    $("#incidentDealDialog").dialog("option" ,{height:dialogHeight,position:'top'});
   			   },
   			  error : function(XMLHttpRequest, textStatus, errorThrown) {
   				alert("提交错误");
   			  }
   		  });  
   		},
   		rules:{
   	   		"incidentDealPlan.endRule":{
   		     required:true,
   		     maxlength:500  
   	   		 }
   		},
   		messages:{
   			"incidentDealPlan.endRule":{
   		       required:"请输入终止条件",
   		       maxlength:$.format("终止条件最多输入500个字符")
	   		 }
   		}
   	 });
    	 
  

      $("#mainNextForm").formValidate({
   		promptPosition: "bottomLeft",
   		submitHandler: function(form){
   	    $(form).ajaxSubmit({
   			 success : function(data) {
  			 
   	    	    if(!data.id){
     		    	  $.messageBox({message : data,level : "error"});
   				      return;
     		     }
    	
     		     $.messageBox({message:"编辑成功!"});
     		     $("#incidentDealDialog").dialog("close"); 
     		     ajaxInitPageData();
   			   },
   			  error : function(XMLHttpRequest, textStatus, errorThrown) {
   				alert("提交错误");
   			  }
   		  });  
   		},
   		rules:{
   	   	
   	   		"incidentDealTeam.composition":{
   		
   		     required:true,
   		     maxlength:200
   	   		},
   	     	"incidentDealTeam.responsibility":{
   	   		 
   	   		 required:true,
   	         maxlength:500
		   	}
   		},
   		messages:{
   			"incidentDealTeam.composition":{
   		      required:"请输入组成方式",
   		      maxlength:$.format("组成方式最多输入200个字符")
	   		},
	   		"incidentDealTeam.responsibility":{
	   		 required:"请输入职责",
	   	     maxlength:$.format("职责最多输入500个字符")
		   	}
	   		
   		}
   	});

   	
 });

  function initData(){
	  $("#editIncidentTypeDescription").html($("#incidentTypeDescription").html());
	  $("#editIncidentTypeDegreeRuleListRule").html($("#incidentTypeDegreeRuleListRule").html());
	 }
</script>

<form id="mainForm" method="post" action="${path}/incident/incidentDealPlans/editIncidentDealPlan.action">
<div class="project_explain clearfix">
<div class="define clearfix"><strong class="title">事件定义：</strong>
<p class="text" id="editIncidentTypeDescription"></p>
</div>
<div class="define clearfix"><strong class="title">分级依据：</strong>
<pre class="text" id="editIncidentTypeDegreeRuleListRule"></pre>
</div>
</div>

    <input type="hidden" value="${incidentDealPlan.id}" name="incidentDealPlan.id" id="hiddenIncidentDealPlanId" /> 
	<input type="hidden" value="${incidentDealPlan.incidentType.id}" name="incidentDealPlan.incidentType.id" id="incidentTypeId" />
	<input type="hidden" value="${incidentDealPlan.degree.id}" name="incidentDealPlan.degree.id" id="degreeId" />
	<input type="hidden" id="incidentDealPlanmode" name="mode" value="${mode}" />
<div class="container container_24" >
   <div class="lead_Content">
     <div class="grid_3 lable-right leadContent">
      <strong class="title"><span class="form-req mark">*</span> 终止条件：</strong>
     </div>
     <div class="heightAuto edit_condition">
     <textarea rows="3" cols="" class="form-txt heightAuto" id="incidentDPEndRule" name="incidentDealPlan.endRule">${incidentDealPlan.endRule}</textarea>
	</div>
</div>
</div>
</form>
<form id="mainNextForm" method="post" action="${path}/incident/incidentDealPlans/editIncidentDealPlanTow.action">
    <input type="hidden" value="${incidentDealPlan.id}" name="incidentDealPlan.id" id="incidentDealId"/> 
    <input type="hidden" id="leadTeamId" name="incidentDealTeam.id" value="${incidentDealTeam.id}" /> 
	<input type="hidden" id="leadTeamName" name="incidentDealTeam.teamName" value="领导小组" /> 
	<input type="hidden" id="leadDealPlanId" name="incidentDealTeam.incidentDealPlan.id" value="${incidentDealTeam.incidentDealPlan.id}" />
    <input type="hidden" id="seqIndexListStrId" name="seqIndexIdListStr"  value="" />
    <input type="hidden"  name="incidentDealTeam.teamType"  value=" <s:property value="@com.tianque.incident.vo.IncidentConstants@LEAD_TEAM_TYPE"/>" />
<div id="nextStep" style="display:none;">
<div class="container container_24" >

   <div class="grid_3 lable-right">
   <input type="checkbox"  id="leadTeam" name="incidentDealPlan.leadTeam" value="true"
	<s:if test="%{incidentDealPlan.leadTeam}">checked="checked"</s:if> />
   </div>
   <div class="grid_2">&nbsp;领导小组</div>


<div class="grid_3 lable-right">
<input type="checkbox" id="complyTeam" name="incidentDealPlan.complyTeam" value="true"
	<s:if test="%{incidentDealPlan.complyTeam}">checked="checked"</s:if> />
</div>
<div class="grid_9">&nbsp;处置小组</div>

<div class='clearLine'>&nbsp;</div>
<div id="incidentTabs" class="heightAuto">
<ul>
	<li id="leadTab-1"><a href="#leadTabs">* 领导小组</a></li>
	<li id="leadTab-2"><a
		href="/incident/plaitScheme/incidentDealTeamList.jsp">* 处置小组</a></li>
	<li id="leadTab-3"><a
		href="/incident/plaitScheme/incidentPlanStepList.jsp">* 处置步骤</a></li>
</ul>
 <div style="padding-left:10px;padding-right:10px;" id="leadTabs" >
       <div class="grid_4 lable-right">组成方式:</div>
       <div class="heightAuto">
        <textarea id="incidentDealTeamComposition"  name="incidentDealTeam.composition" class="form-txt"
	    style="height: 85px;padding-right:10px">${incidentDealTeam.composition}</textarea>
	   </div>

        <div class='clearLine'>&nbsp;</div>
       <div class="grid_4 lable-right">职 责:</div>
       <div class="heightAuto">
         <textarea id="incidentDealTeamResponsibility" name="incidentDealTeam.responsibility" class="form-txt"
	    style="height: 85px;padding-right:10px">${incidentDealTeam.responsibility}</textarea>
	   </div>
  </div>
</div>
</div>
</div>
</form>
