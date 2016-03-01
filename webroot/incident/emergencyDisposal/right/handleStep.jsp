<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<script type="text/javascript">
var leadteam_steptype;
var handteam_steptype;
var planstep_steptype;

  $(function(){
	  leadteam_steptype='<s:property value="@com.tianque.incident.vo.IncidentConstants@LEADTEAM_STEPTYPE"/>';
	  handteam_steptype='<s:property value="@com.tianque.incident.vo.IncidentConstants@HANDTEAM_STEPTYPE"/>';
	  planstep_steptype ='<s:property value="@com.tianque.incident.vo.IncidentConstants@PLANSTEP_STEPTYPE"/>';
	
	  ajaxGetStepList(leadteam_steptype,handteam_steptype,planstep_steptype);
	  
	   /*$("#stepList h1[id^='title_']").click(function(){
		   var child= $(this).parent().children();
		   for(var i=0;i<child.length;i++){
			   var strId=child[i].id;
			   if(strId!=null){
				   if(strId.substring(0,strId.indexOf("_")+1)=='step_'){
                        if($(child[i]).css("display")=='block'){
                        	$(child[i]).css("display","none");
                          }else{
                        	  $(child[i]).css("display","block");
                          }
					}
				}
			 }
		 });*/
		 $("#stepList  h1").live('click',function(){ 
       	  	$("#startContent").removeAttr("style");
       	  	$("#callbackContent").removeAttr("style");
		});

		 $("#stepList").delegate("a.text","click",function(){
			  $(this).addClass("current").siblings().removeClass("current");
			  $(this).parent().prev("h1").addClass("current");
		});
	   updownlist();
	   
		$("#complete_leadTeam").click(function(){
			var incidentId=$("#incidentId").val();
			if(!incidentId){
				$.messageBox({message:"请先进行预案制定",level: "error"});
				return;
			}
			$.confirm({
				message:"确定要对该预警进行完结处理吗？完结后可在案例库中查看",
				okFunc: function() {
					$.ajax({
						url:"${path}/incident/emergencyDisposal/cancelWarning.action?incidents.id="+incidentId+"&incidents.status="+$("#caseLibrary").val(),
						success:function(data){
							aa=data;
							 if($("#incident_"+incidentId).next().html()!=null){
				           	   $("#incident_"+incidentId).next().click();
							   $("#incident_"+incidentId).remove();
					         }else{
								 emptyEarlyWarningCategoryId();
								  $("#step_leadTeam").empty();
								  $("#step_memberTeam").empty();
								  $("#step_planStep").empty();
								 $("#lookOver").buttonDisable();
								 $("#incident_"+incidentId).remove();
							 }
							$.messageBox({message:"预案已完结处理!"});
							if(!$("#incdList_handle").find("*")[0]){
								$("#incdList_handle").html("暂无预警处置中记录");
							}
						}
					});
				}
			});
			});
		$("#add_leadTeam").click(function(){
		 var title='添加领导小组'
		    var addSeqindex=1;
			var incidentsId=$("#incidentsId").val(); 
			if(!incidentsId){
				$.messageBox({message:"请先进行预案制定并处置",level: "error"});
				return;
			}
		    var urlParam ='mode=add&incidentSteps.seqIndex='+addSeqindex;
	    	  urlParam += '&incidentSteps.stepType='+leadteam_steptype;
	    	  urlParam +='&incidentSteps.incidents.id='+incidentsId;
	    	  urlParam +='&incidentSteps.stepName=领导小组';
	    	  showActualContentDailog(urlParam,title);
		});
       $("#add_memberTeam").click(function(){
          var title='添加专项小组';
    	  var addSeqindex;
    	  if(isNaN(new Number($("#step_memberTeam a:last").attr("name")))){
    		  addSeqindex = 1;
          }else{
        	  addSeqindex = new Number($("#step_memberTeam a:last").attr("name"))+1;
            }
    	  var incidentsId=$("#incidentsId").val();
    	  var incidentsId=$("#incidentsId").val(); 
			if(!incidentsId){
				$.messageBox({message:"请先进行预案制定并处置",level: "error"});
				return;
			}
    	  var urlParam ='mode=add&incidentSteps.seqIndex='+addSeqindex;
    	  urlParam += '&incidentSteps.stepType='+handteam_steptype;
    	  urlParam +='&incidentSteps.incidents.id='+incidentsId;
    	  showActualContentDailog(urlParam,title);
       });

       $("#add_planStep").click(function(){
    	  var title= '添加处置步骤';
    	  var addSeqindex; 
    	  if(isNaN(new Number($("#step_planStep a:last").attr("name")))){
    		  addSeqindex = 1;
          }else{
        	  addSeqindex = new Number($("#step_planStep a:last").attr("name"))+1;
            }
     	  var incidentsId=$("#incidentsId").val();
     	 var incidentsId=$("#incidentsId").val(); 
		if(!incidentsId){
			$.messageBox({message:"请先进行预案制定并处置",level: "error"});
			return;
		}
     	  var urlParam ='mode=add&incidentSteps.seqIndex='+addSeqindex;
     	  urlParam += '&incidentSteps.stepType='+planstep_steptype;
     	  urlParam +='&incidentSteps.incidents.id='+incidentsId;
     	  urlParam +='&incidentSteps.stepName=步骤'+addSeqindex;
     	  showActualContentDailog(urlParam,title);
       });

       
	   $("#stepList  h1").live('click',function(){ 
            	  $("#startContent").removeAttr("style");
            	  $("#callbackContent").removeAttr("style");
		 });

	   
	});

   function  showActualContentDailog(urlParam,title){
	  $("#actualContentDailog").createDialog({
 			width:600,
 			height:400,
 			title:title,
 			modal : true,
 			url:'${path}/incident/emergencyDisposal/findIncidentStep.action?'+urlParam,
 			buttons: {
 			  "确定" : function(event){
		         $("#actualContentForm").submit();
			    },
 			   "关闭" : function(){
 			        $(this).dialog("close");
 			   }
 			}
 		});
   }
   
 
   function getCurrentStepData(id){
	  $("#currentStepId").val(id);
	  if(id==null)return;
		  $.ajax({
		    	data:{"incidentSteps.id":id,"mode":"view"},
				url:"${path}/incident/emergencyDisposal/findIncidentStep.action",
				success:function(data){
					if(data.executed==true){
		            	  $("#callbackContent").removeAttr("style");
		            	  $("#startContent").css("display","none");
					}else{
						 $("#callbackContent").css("display","none");
						  $("#startContent").removeAttr("style");
					}
					if(data.actualContent!=null){
						   $("#warningContentTitle").html("处置内容");
						   var str ="<h3 class='processTitle'><label>实际处置内容:</label></h3>";
						   str+="<pre class='processContent'>"+data.actualContent+"</pre>";
						   var incidentStepFeedBackList=data.incidentStepFeedBackList;
						   if(incidentStepFeedBackList!=null){
                               for(var i=0;i<incidentStepFeedBackList.length;i++){
                                   str+="<h3 class='processTitle processCallbackTitle'>"+"<span class='processTitle_msg'>"+incidentStepFeedBackList[i].feedBackDate+"</span>";
                                   str+="<span class='processTitle_msg'>"+incidentStepFeedBackList[i].feedBackUser+"</span>"+"<span class='processTitle_msg'>"+"反馈如下内容 :"+"</span>"+"</h3>";
                                
                                   str+="<pre class='processContent processCallbackContent'>"+incidentStepFeedBackList[i].content+"</pre>";
                                }
							}
						   $("#warningContentOrActualContent").html(str);
					}else{
						   $("#warningContentTitle").html("预案内容");
						   $("#warningContentOrActualContent").html("<pre class='processContent processCallbackContent'>"+data.planContent+"</pre>");
				   }
					
				}
		   });
	}
	function addTypeData(id,teamName,seqindex){
		var astr= "<a id='"+id+"' class='text' name='"+seqindex+"' href='javascript:getCurrentStepData("+id+");'>"+teamName+"</a>";
		return astr;
	}
	
	function ajaxGetStepList(leadteam_steptype,handteam_steptype,planstep_steptype){
		  $.ajax({
		    	data:{"incidentSteps.incidents.id":'${incidents.id}'},
				url:"${path}/incident/emergencyDisposal/findIncidentStepList.action",
				success:function(data){
					  $("#step_leadTeam").empty();
					  $("#step_memberTeam").empty();
					  $("#step_planStep").empty();
				      for(var i=0;i<data.length;i++){
					    	if(data[i].stepType==leadteam_steptype){
		                        $("#step_leadTeam").append(addTypeData(data[i].id,data[i].stepName,data[i].seqIndex));
		                    }else if(data[i].stepType==handteam_steptype){
		                  	    $("#step_memberTeam").append(addTypeData(data[i].id,data[i].stepName,data[i].seqIndex));
		                    }else if(data[i].stepType==planstep_steptype){
			                    $("#step_planStep").append(addTypeData(data[i].id,data[i].stepName,data[i].seqIndex));
		                    }
					  }
					  if(isNaN(new Number($("#step_leadTeam a:last").attr("name")))){
						 $("#add_leadTeam").css("display","block");
						 }
				  }
		   });
	}
	function updownlist(){
		var $AllTitle=$(".processList>.processList_content>.title");
		var $AllContent=$(".processList>.processList_content>.list");

		$(".processList>.processList_content").each(function(index){
			var $thisTitle=$(this).find(".title");
			var $thisContent=$(this).find(".list");
			$(this).find(".title").unbind("click").bind("click",function(){
				if(!$thisContent.find("*")[0]){return false;};
				$AllTitle.removeClass("current");
				$thisTitle.addClass("current");
				$AllContent.hide();
				$thisContent.show();
				$thisContent.find("a").eq(0).click();
			})
		})
	}

	var processListHeight=function(){
		var timer;
		function Height(){
			$("#processList").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-14);
		}
		Height()
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setInterval(function(){Height()},200);
		})
	}()
	
</script>

<input type="hidden" id="incidentsId" name="incidents.id"
	value="${incidents.id}" />
<input type="hidden" id="caseLibrary" name="caseLibray"
	value="<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/>" />
<input type="hidden" id="currentStepId" value="" />
<div class="deal_ProcessL" id="processList" style="border: 1px solid #d6d6d6;">
	<div id="stepList" class="processList">
		<div class="processList_complete">
			<div id="title_memberTeam" class="title">
				<a id="complete_leadTeam" href="javascript:;" class="proAdd">完结</a>
			</div>
		</div>
		<div class="processList_content">
			<h1 id="title_leadTeam" class="title"><span class="parent-icon"></span>
				成立领导小组
				<a id="add_leadTeam" href="javascript:;" style="display: none"
					class="proAdd"><span>+</span>添加</a>
			</h1>
			<div id="step_leadTeam" style="display: block;" class="list">
			</div>
		</div>
		<div class="processList_content">
			<h1 id="title_memberTeam" class="title"><span class="parent-icon"></span>
				成立专项小组
				<a id="add_memberTeam" href="javascript:;" class="proAdd"><span>+</span>添加</a>
			</h1>
			<div id="step_memberTeam" style="display: none" class="list">
			</div>
		</div>
		<div class="processList_content">
			<h1 id="title_planStep" class="title"><span class="parent-icon"></span>
				应急处置
				<a id="add_planStep" href="javascript:;" class="proAdd"><span>+</span>添加</a>
			</h1>
			<div id="step_planStep" style="display: none" class="list">
			</div>
		</div>
	</div>
</div>
