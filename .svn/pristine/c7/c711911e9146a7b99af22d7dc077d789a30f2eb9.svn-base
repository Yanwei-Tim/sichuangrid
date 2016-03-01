<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%
	request.setAttribute("rightContent",request.getParameter("rightContent"));
%>
<script type="text/javascript">
  $(function(){
		ifDegreed();
	  	cleanEarlyWarningDat();
	    $("#startContent").click(function(){
		    if(!$("#currentStepId").val()){
		    	$.messageBox({message:"请选择一个处置步骤！",level:"warn"});
		    	return ;
		    }
	    	     $("#actualContentDailog").createDialog({
	 	   			width:600,
	 	   			height:400,
	 	   			title:'启动预案',
	 	   			modal : true,
	 	   			url:'/incident/emergencyDisposal/findIncidentStep.action?mode=edit&incidentSteps.id='+$("#currentStepId").val(),
	 	   			buttons: {
	 	   			  "确定" : function(event){
	 			         $("#actualContentForm").submit();
	 				    },
	 	   			   "关闭" : function(){
	 	   			        $(this).dialog("close");
	 	   			   }
	 	   			}
	 	   		});
	     });

    $("#callbackContent").click(function(){
    	 if(!$("#currentStepId").val()){
		    	$.messageBox({message:"请选择一个处置步骤！",level:"warn"});
		    	return ;
		    }
	     $("#actualContentDailog").createDialog({
   			width:600,
   			height:400,
   			title:'增加反馈内容',
   			modal : true,
   			url:'/incident/emergencyDisposal/findIncidentStepFeedbacks.action?incidentStepFeedbacks.incidentSteps.id='+$("#currentStepId").val(),
   			buttons: {
   			  "确定" : function(event){
		         $("#callBackContentForm").submit();
			    },
   			   "关闭" : function(){
   			        $(this).dialog("close");
   			   }
   			}
   		});
	});
    
  });

  function cleanEarlyWarningDat(){
	  if('${incidents.id}'==null || '${incidents.id}' == "" || '${incidents.id}'=='undefined'){
			$("#warningContentJubjection").empty();
			$("#lookOver").buttonDisable();
		  }
	  }
  
  var  showHeight=function(){
	
  	function getHeight(){
		$(".main_warn_content .warnInform").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-$(".warnRemind").height()-33);
  	
  	}
    function processEvent(){
	  	var timer;
	  	getHeight();
	  	$(window).resize(function(){
	  		clearTimeout(timer);
	  		timer=setTimeout(function(){getHeight()},300);
  		});
  	}	
  	processEvent();
  }();
  
  function ifDegreed(){
	  if('${incidents.incidentType.name}' ==null || '${incidents.incidentType.name}' ==''){
		  return;
		 }
		var str="无分级"
			if('${incidents.degree.id}'==null ||'${incidents.degree.id}'==''){
				$("#warningContentDegreeId").html(str)
				}
		}

	$("#lookOver").click(function(){
		if('${incidents.id}'==null || '${incidents.id}' == "" || '${incidents.id}'=='undefined'){return;}
		$.createDialog({
			title : "查看周边",
			width : 700,
			height : 550,
			url:'${path}/incident/emergencyDisposal/incidentMap.jsp'
		});
	});
function emptyEarlyWarningCategoryId(){
		cleanEarlyWarningDat();
		$("#warningContentCategoryId").text("");
		$("#warningContentJubjection").text("");
		$("#warningContentDegreeId").text("");
		$("#warningContentSource").text("");
		$("#warningContentApplicate").text("");
		$("#warningContentApplyDate").text("");
		$("#warningContentOccurAddress").text("");
		$("#warningContentRange").text("");
		$("#warningContentOrActualContent").text("");
}
	
</script>
<div id="earlyWarningContent">
<div class="main_warn_content main_warn_content_${rightContent}">
	<div class="warnRemind clearfix">
	  	<input type="hidden" id="incidentId" name="incidents.id" value="${incidents.id}"/>
		<div class="warnPlace warnPlace_${rightContent}">
			<table class="tablelist">	
				<tr>
					<td class="title">事件大类 </td>
					<td class="text"><span id="warningContentCategoryId"><pop:PropertyDictViewTag defaultValue="${incidents.category.id}" name="@com.tianque.domain.property.PropertyTypes@INCIDENT_SUBJECTION"/></span></td>
					<td class="title">事件小类</td>
					<td class="text"><span id="warningContentJubjection">${incidents.incidentType.name}</span></td>
				</tr>
				<tr>
					<td class="title">事件等级</td>
					<td class="text"><span id="warningContentDegreeId"><pop:PropertyDictViewTag defaultValue="${incidents.degree.id}" name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE"/></span></td>
					<td class="title">预警来源</td>
					<td class="text"><span id="warningContentSource">${incidents.sourceType}</span></td>
				</tr>
				<tr>
					<td class="title">预警提请人 </td>
					<td class="text"><span id="warningContentApplicate">${incidents.applicant}</span></td>
					<td class="title">预警提出时间</td>
					<td class="text" id="applyDate"><span id="warningContentApplyDate"><s:date name="incidents.applyDate" format="yyyy-MM-dd"/></span></td>
				</tr>
				<tr>
					<td class="title">事发地点 </td>
					<td class="text"><span id="warningContentOccurAddress">${incidents.occurAddress}</span></td>
					<td class="title">影响范围</td>
					<td class="text"><span id="warningContentRange">${incidents.range}</span></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="clear"></div>
 	<div class="ui-corner-all check_around" id="nav">
		<%--<a id="lookOver" href="javascript:void(0)"><span>查看周边</span></a>
	    --%><a id="startContent" href="javascript:void(0)" style="display:none"><span>启动预案</span></a>
	    <a id="callbackContent" href="javascript:void(0)" style="display:none"><span>增加反馈内容</span></a>
	</div>
	<div class="warnInform clearfix">
		<h3 class="title" id="warningContentTitle">预案内容</h3>
		<div class="showText" id="warningContentOrActualContent"><pre class='processContent processCallbackContent'>${incidents.content}</pre></div>
		<div id="handleWarningDailog"></div>
	</div>
	<div id="actualContentDailog"></div>
</div>
</div>
