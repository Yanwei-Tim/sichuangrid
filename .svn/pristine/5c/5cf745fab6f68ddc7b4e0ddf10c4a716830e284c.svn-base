<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<script type="text/javascript">
  $(function(){
	 // controlButtonState();
		 
	  $("#addWarning").click(function(){
		  $("#handleWarningDailog").createFrameDialog({
				model :"add",
			    width: 800,
				height: 600,
				title:"新增预警",
				data:[
						{title:'预警信息',url:'${path}/incident/emergencyDisposal/dispatchOperate.action?dialogName=handleWarningDailog&mode=add',buttons:{save:true}}
					]
			  });
		  });
	  
	  $("#updateWarning").click (function(){
		  if(ifVar_incidentIdIsNull()==true){
			  $.messageBox({message:"请选择一个事件",level: "warn"});
			  return;
		  }
			$("#handleWarningDailog").createFrameDialog({
			    	model :"edit",
			    	width: 800,
					height: 640,
					title:"修改预警",
					data:[
							{title:'预警信息',url:'${path}/incident/emergencyDisposal/dispatchOperate.action?dialogName=handleWarningDailog&mode=edit&incidents.id='+$("#incidentId").val(),buttons:{save:true}}
						]
											 
				});
		  });
	  
    $("#handleWarning").click(function(){
    	if(ifVar_incidentIdIsNull()==true){
    	  $.messageBox({message:"请选择一个事件",level: "warn"});
          return;
        }
      $("#handleWarningDailog").createDialog({
    		width: 550,
   			height:250,
   			title:'预警处置',
   			modal : true,
   			url:'${path}/incident/emergencyDisposal/right/handleDialog.jsp?incidentId='+$("#incidentId").val(),
   			buttons: {
   			  "确定" : function(event){
		          $("#handleWarningForm").submit();
			    },
   			   "关闭" : function(){
   			        $(this).dialog("close");
   			   }
   			}
        });
     });
    $("#cancelWarning").click(function(){
    	if(ifVar_incidentIdIsNull()==true){
    		$.messageBox({message:"请选择一个事件",level: "warn"});
        	return;
        }
    	  $("#handleWarningDailog").createDialog({
      		    width: 500,
     			height:300,
     			title:'解除预警',
     			modal : true,
     			url:'${path}/incident/emergencyDisposal/right/cancelWarningDialog.jsp?incidentId='+$("#incidentId").val(),
     			buttons: {
     			  "确定" : function(event){
  		            $("#cancelWarningForm").submit();
  			      },
     			   "关闭" : function(){
     			        $(this).dialog("close");
     			   }
     			}
          });
      
      
    });
  });

  function controlButtonState(){
		if($("#incidentId").val()==null ||$("#incidentId").val() =="" || $("#incidentId").val()=='undefined'){
			$("#addWarning").buttonEnable();
			$("#updateWarning").buttonDisable();
			$("#cancelWarning").buttonDisable();
			$("#handleWarning").buttonDisable();
		}else{
			$("#addWarning").buttonEnable();
			$("#updateWarning").buttonEnable();
			$("#cancelWarning").buttonEnable();
			$("#handleWarning").buttonEnable();
			}
	  }
  function ifVar_incidentIdIsNull(){
	  if($("#incidentId").val()==null ||$("#incidentId").val() =="" || $("#incidentId").val()=='undefined'){
		  return true;}
	  } 
</script>
<div class="ui-corner-all" id="nav">
	<a id="addWarning" href="javascript:;"><span>新增</span></a>
	<a id="updateWarning" href="javascript:void(0)"><span>修改预警</span></a>
  	<a id="cancelWarning" href="javascript:void(0)"><span>解除预警</span></a>
	<a id="handleWarning" href="javascript:void(0)"><span>处置</span></a>
</div>
<jsp:include page="/incident/emergencyDisposal/right/earlyWarningBaseContent.jsp">
     <jsp:param value="early" name="rightContent"/>
</jsp:include>
